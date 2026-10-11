# Niagram

[![Crowdin](https://badges.crowdin.net/NagramX/localized.svg)](https://crowdin.com/project/NagramX)
[![GitHub Release](https://img.shields.io/github/v/release/HSSkyBoy/NiagramX?include_prereleases&style=flat-square)](https://github.com/HSSkyBoy/NiagramX/releases)
[![Telegram Channel](https://img.shields.io/badge/Telegram-Channel-blue?logo=telegram&style=flat-square)](https://t.me/NiagramX)
[![MinSDK](https://img.shields.io/badge/Android-7.0%2B%20(API%2024%2B)-green?logo=android&style=flat-square)](https://developer.android.com/about/versions/nougat)

An enhanced, high-performance Telegram client variant based on [Nagram](https://github.com/NextAlone/Nagram), [Nekogram](https://github.com/Nekogram/Nekogram), and [NagramX](https://github.com/risin42/NagramX), refined with modern UI enhancements, expanded customization, and aggressive performance optimizations.

---

## ✨ Features & Highlights

- **Neo-Settings & Deep Customization**: Refined settings suite with comprehensive UI, chat behavior, and visual controls.
- **Custom Font Engine**: Dynamic system font matching, custom TTF/OTF font importing, and seamless font application across the entire interface.
- **Enhanced Media Controls**: Global default video playback quality settings, custom streaming parameters, and optimized media rendering.
- **Privacy & Spy Mode**: Granular message database management, read-status controls, and enhanced privacy options.
- **Adaptive Layout**: Polished tablet and foldable screen adaptability with dual-pane and landscape-aware scaling.
- **Modern Android Architecture**: 16KB memory page size support (Android 15+ ready), APK Signature Scheme v2 + v3, and native ABI optimization.

---

## 📱 Compatibility

- **Minimum Version**: Android 7.0 (API Level 24)
- **Target Version**: Android 17 (API Level 37)
- **Supported Architectures**: `arm64-v8a`, `armeabi-v7a`, `x86_64`
- **Signing Scheme**: APK Signature Scheme v2 + v3

---

## 📥 Download

Official releases and beta builds can be obtained from:

- **Beta Builds**: [Telegram Channel (@NiagramX)](https://t.me/NiagramX)
- **Stable Releases**: [GitHub Releases](https://github.com/HSSkyBoy/NiagramX/releases)

### Verify APK Signature

This fork's builds are signed with a self-managed keystore:

- **Package Name**: `io.neko.niagramx`
- **Key Alias**: `niagramx`
- **SHA-256 Fingerprint**:  
  `5C:21:63:99:64:71:C9:60:7E:5B:3D:C1:0D:41:96:6F:F8:01:78:14:B8:E7:93:A7:51:CC:A6:FF:1E:95:58:F8`

---

## 🛠️ Building from Source

### Prerequisites

- Android Studio Ladybug / Meerkat or newer
- JDK 21
- Android SDK (API 36, Build-Tools 36.0.0+)
- NDK (27.2.12479018)
- CMake 3.31+

### Clone & Compile

```bash
# Clone repository with all submodules
git clone --recursive --shallow-submodules https://github.com/HSSkyBoy/NiagramX.git Niagram
cd Niagram

# If submodules were not initialized during clone
git submodule update --init --recursive --depth=1
```

Create `local.properties` in the project root:

```properties
TELEGRAM_APP_ID=<your_telegram_app_id>
TELEGRAM_APP_HASH=<your_telegram_app_hash>
```

### Signing

Local signing credentials are read from `local.properties` (or environment variables):

```properties
KEYSTORE_PATH=E:/path/to/niagramx.jks
KEYSTORE_PASS=<store password>
ALIAS_NAME=niagramx
ALIAS_PASS=<key password>
```

If any of them is missing or blank, release/staging builds fall back to **unsigned** APKs (the `debug` build is unaffected).

### CI Signing (GitHub Actions)

`release.yml` / `staging.yml` read the same credentials from repo secrets. Configure these in *Settings → Secrets and variables → Actions* to get signed APKs in CI:

| Secret | Value |
|---|---|
| `KEYSTORE_BASE64` | `base64 -w0 keystore/niagramx.jks` 的输出 |
| `KEYSTORE_PASS` | keystore store password |
| `ALIAS_NAME` | `niagramx` |
| `ALIAS_PASS` | key password |

Alternatively, put the whole `local.properties` (with `KEYSTORE_PATH` etc.) into the `LOCAL_PROPERTIES` secret as base64: `base64 -w0 local.properties`. Without these secrets CI still runs but produces unsigned APKs; the Telegram upload step is skipped automatically when the helper-bot secrets are absent.

---

## 🔒 Privacy & Diagnostics

Official builds may collect anonymous diagnostic and usage statistics (via Firebase Crashlytics & Analytics) to help identify stability issues and optimize application performance. You can disable diagnostic reporting at any time in app settings.

---

## 💖 Acknowledgments & Credits

Special thanks to upstream projects, contributors, and the Telegram open-source community:

- [AyuGram](https://github.com/AyuGram/AyuGram4A)
- [Cherrygram](https://github.com/arsLan4k1390/Cherrygram)
- [Dr4iv3rNope](https://github.com/Dr4iv3rNope/NotSoAndroidAyuGram)
- [exteraGram](https://github.com/exteraSquad/exteraGram)
- [Nagram](https://github.com/NextAlone/Nagram)
- [NagramX](https://github.com/risin42/NagramX)
- [NagramXTurbo](https://github.com/temporaryna/NagramXTurbo)
- [Nekogram](https://github.com/Nekogram/Nekogram)
- [OctoGram](https://github.com/OctoGramApp/OctoGram)
