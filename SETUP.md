# Legal.AI — Android App Setup Guide

## Quick Start (5 steps)

### Step 1 — Open in Android Studio
1. Open Android Studio (Hedgehog 2023.1.1+)
2. File → Open → select the `LegalAI/` folder
3. Wait for Gradle sync (first time takes ~3 mins, downloads dependencies)

### Step 2 — Add google-services.json (Firebase)
1. Go to https://console.firebase.google.com
2. Create project → Add Android app → Package: `com.legalai.app`
3. Download `google-services.json`
4. Place it at: `LegalAI/app/google-services.json`
5. Enable in Firebase Console:
   - Authentication → Email/Password
   - Firestore Database (test mode)
   - Realtime Database
   - Cloud Messaging

### Step 3 — Add Gemini API Key
1. Go to https://aistudio.google.com/app/apikey → Create API key
2. Open: `app/src/main/java/com/legalai/app/network/GeminiClient.java`
3. Replace: `YOUR_GEMINI_API_KEY_HERE` with your key

> The app works WITHOUT the key using MockAiService (offline fallback with 8 legal topic responses)

### Step 4 — Build & Run
- **Run on device/emulator**: Press ▶ in Android Studio
- **Build debug APK**: Build → Build Bundle(s)/APK(s) → Build APK(s)
- **CLI**: `./gradlew assembleDebug`
- **APK location**: `app/build/outputs/apk/debug/app-debug.apk`

### Step 5 — Release APK
1. Build → Generate Signed Bundle / APK → APK
2. Create keystore, fill details
3. Select `release` variant → Finish
4. APK at: `app/build/outputs/apk/release/app-release.apk`

---

## Project Structure

```
com.legalai.app/
├── LegalAiApplication.java       App class (dark mode init)
├── activities/                   16 screens
│   ├── SplashActivity            Auto-routes to onboarding/login/main
│   ├── OnboardingActivity        4-page intro with ViewPager2
│   ├── LoginActivity             Firebase email/password auth
│   ├── SignupActivity            Creates account + Firestore profile
│   ├── ForgotPasswordActivity    Firebase password reset email
│   ├── MainActivity              BottomNav + FAB chat button
│   ├── ChatActivity              Gemini AI chat (MVVM + LiveData)
│   ├── LawyerProfileActivity     Profile, bookmark, book button
│   ├── CaseTrackingActivity      5-step animated timeline
│   ├── EditProfileActivity       Update name/phone/location
│   ├── LegalGlossaryActivity     20 terms, search, expand
│   ├── NotificationsActivity     Firebase + mock notifications
│   ├── SavedLawyersActivity      Realtime DB bookmarked lawyers
│   ├── SearchResultsActivity     Filter lawyers by specialty
│   ├── AgentMarketplaceActivity  8 AI agents → launches chat
│   └── DocumentUploadActivity    File picker (OCR coming soon)
├── adapters/                     8 RecyclerView adapters
│   ├── ChatAdapter               3 view types: user/bot/action
│   ├── LawyerAdapter             With search filter()
│   ├── AgentAdapter              Use Agent → opens ChatActivity
│   ├── CaseAdapter               Status pills, timestamp format
│   ├── GlossaryAdapter           Expand/collapse with animation
│   ├── NotificationAdapter       Unread dot indicator
│   ├── PracticeAreaAdapter       Grid cards
│   └── OnboardingAdapter         ViewPager2 pages
├── fragments/                    4 bottom nav tabs
│   ├── LawyersFragment           Search bar + lawyer list
│   ├── AgentsFragment            AI agent cards
│   ├── CasesFragment             Cases from Firebase + mock
│   └── ProfileFragment           Dark mode, nav to settings
├── models/                       9 data classes
├── network/
│   ├── GeminiApiService          Retrofit interface
│   ├── GeminiClient              Retrofit singleton + API key
│   ├── GeminiLegalService        System prompt + conversation history
│   ├── GeminiRequest/Response    API payload models
│   └── MockAiService             8-category offline fallback
├── nlp/
│   └── LegalClassifier           Keyword NLP + urgency detection
├── utils/
│   ├── FirebaseHelper            Auth + Firestore + Realtime DB
│   ├── MockDataGenerator         10 lawyers, 8 agents, 20 glossary terms
│   ├── AppUtils                  SharedPrefs, dark mode, time format
│   └── LegalAiFcmService         FCM push notifications
└── viewmodels/
    └── ChatViewModel             MVVM: sends messages, LiveData updates
```

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| `google-services.json not found` | Place in `app/` folder (not root) |
| Gradle sync fails | File → Invalidate Caches → Restart |
| `Default FirebaseApp is not initialized` | google-services.json is missing/wrong package name |
| Gemini returns error | Check API key in GeminiClient.java; app falls back to MockAiService automatically |
| Dark mode not applying instantly | Activity needs recreation; call `recreate()` after toggle if needed |
| Build error `R cannot be resolved` | Build → Clean Project → Rebuild |
| FCM token not appearing | Ensure google-services.json matches Firebase project |
| `ViewPager2 not found` | Run Gradle sync again — viewpager2:1.0.0 is in build.gradle |

---

## Firebase Firestore Structure

```
users/{uid}/
  ├── name, email, phone, location, createdAt
  ├── cases/{caseId}/
  │     └── id, title, type, description, status, agentUsed, assignedLawyer, timestamp
  └── notifications/{notifId}/
        └── id, emoji, title, message, timestamp, read

saved_lawyers/{uid}/{lawyerId}/
  └── id, name, specialty, avatarInitials  ← Realtime Database
```

---

## API Keys Checklist

- [ ] `google-services.json` placed in `app/`
- [ ] Firebase Auth: Email/Password enabled
- [ ] Firebase Firestore: created in test mode
- [ ] Firebase Realtime DB: created
- [ ] Gemini API key set in `GeminiClient.java`

