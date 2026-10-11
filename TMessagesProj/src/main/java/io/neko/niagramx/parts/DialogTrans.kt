package io.neko.niagramx.parts

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import io.neko.niagramx.helpers.MessageHelper
import io.neko.niagramx.translate.Translator
import io.neko.niagramx.translate.code2Locale
import io.neko.niagramx.utils.AlertUtil
import io.neko.niagramx.utils.uDismiss
import io.neko.niagramx.config.NyaConfig
import java.util.concurrent.atomic.AtomicBoolean

@JvmOverloads
fun startTrans(
    ctx: Context,
    text: String,
    toLang: String = NyaConfig.translateToLang.String(),
    provider: Int = 0
) {

    val dialog = AlertUtil.showProgress(ctx)
    val canceled = AtomicBoolean(false)
    val finalToLang = toLang.code2Locale
    val finalProvider = provider.takeIf { it != 0 } ?: NyaConfig.translationProvider.Int()
    val appendOriginal = MessageHelper.shouldKeepOriginalForManualTranslation(NyaConfig.translatorMode.Int())
    val job = Job()

    dialog.show()
    dialog.setOnCancelListener {
        canceled.set(true)
        job.cancel()
    }

    val scope = CoroutineScope(Dispatchers.IO + job)
    scope.launch {
        runCatching {
            val result = Translator.translate(finalToLang, text, finalProvider)
            if (!canceled.get()) {
                withContext(Dispatchers.Main) {
                    dialog.uDismiss()
                    val finalText = MessageHelper.buildTranslatedDisplayText(text, result, appendOriginal)
                    AlertUtil.showCopyAlert(ctx, finalText)
                }
            }
        }.onFailure { e ->
            scope.cancel()
            withContext(Dispatchers.Main) {
                dialog.uDismiss()
                if (!canceled.get()) {
                    AlertUtil.showTransFailedDialog(
                        ctx, e is UnsupportedOperationException, e.message ?: e.javaClass.simpleName
                    ) {
                        startTrans(ctx, text, toLang, finalProvider)
                    }
                }
            }
        }
    }
}
