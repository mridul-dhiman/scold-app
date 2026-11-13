# SCOLD // Hostile Anti-Engagement Social Network

> *"A social media app that actively guilt-trips users and restricts session time using un-swipeable hostile architecture to cure doomscrolling."*

---

## ⚡ Live Web Demo
Experience the interactive web demo directly in your browser:
**[Launch Scold Web Demo](https://mridul-dhiman.github.io/scold-app/)**

---

## 🛑 Product Thesis
Traditional distraction blockers only act as entry gates ("Ignore for 15 minutes"), and mainstream feeds are algorithmically engineered to maximize infinite attention loops. **Scold** shifts the burden of discipline from the user's willpower to an unapologetic, unyielding UX:
- **Strict Persistent Floating Timer**: Countdowns start on session open (default 3m).
- **Gradual UI Degradation**: At 60 seconds remaining, the UI desaturates into clinical grayscale. At 10 seconds, critical pulsing crimson alerts activate with audio alarms.
- **Root-Level Lockout Hard-Wall**: At 00:00, an un-swipeable, non-dismissible hard-wall drops over the entire application.
- **Humiliating Cognitive Bypass**: Snoozing requires manually typing an exact self-incriminating phrase (*"I lack self control and need 60 more seconds."*). OS clipboard paste is strictly revoked.
- **Media Redaction & Cognitive Math Interlocks**: High-dopamine media is blurred and quarantined behind arithmetic cold-boot challenges and humiliation attestations.
- **Hold-to-Abort Barrier**: 3.0-second continuous press to self-suspend terminal. Releasing early snaps progress back to zero with haptic/sound rejection.

---

## 🏗 Architecture

### 1. Android Native (`/app`)
- **Language / Framework**: Kotlin, Jetpack Compose, Material3 (configured to 0px radii brutalism).
- **Architecture**: MVVM with Clean Architecture repository pattern.
- **Timer Engine**: `SessionTimerService` (Android Foreground Service) tracking absolute UNIX epoch timestamps and cross-referenced with `SystemClock.elapsedRealtime()` to detect clock-tampering.
- **Persistence**: Room Database (`SessionEntity`, `SessionDao`) for offline audit logs + DataStore (`PreferencesManager`) for quotas.
- **Dependency Injection**: Dagger / Hilt.

### 2. Web Demo (`/web`)
- Single-page zero-dependency web simulator.
- Tailwind CSS with custom hostile architecture token palette (`#0A0A0C` obsidian, `#FF3B30` surgical crimson, `#F59E0B` precision amber).
- Web Audio API synthesizer for mechanical clicks, alert klaxons, and error buzzers.
- Real clipboard paste interception and live character mismatch telemetry.

---

## 📂 Project Structure

```
guild_trip_app/
├── app/                              # Android Native Kotlin / Jetpack Compose application
│   ├── src/main/java/com/scold/app/
│   │   ├── timer/                    # Foreground service & state holder
│   │   ├── ui/
│   │   │   ├── theme/                # Hostile Architecture design system (Space Mono, 0px shapes)
│   │   │   ├── components/           # Reusable brutalist components (Keypad, Penalty Input, Hold Button)
│   │   │   └── screens/              # 7 screens (GuiltGate, Feed, Lockout, Math, Stats, Post, Settings)
│   │   ├── data/                     # Room DB, DAOs & DataStore
│   │   └── util/                     # PasteBlocker, ClockTamperDetector, HapticHelper
├── web/                              # Interactive web demo application
│   └── index.html                    # Standalone web simulator
├── docs/                             # Master PRD & Wireframes specifications
└── .github/workflows/                # Automated GitHub Pages deployment
```

---

## 🚀 Running Locally

### Web Simulator:
```bash
cd web
python3 -m http.server 8080
# Open http://localhost:8080
```

### Android Native App:
1. Open the project root folder in **Android Studio** (Ladybug / Hedgehog or newer).
2. Sync Gradle with JDK 17 / JDK 21.
3. Run on an Android emulator or physical device running Android 8.0+ (API 26+).
