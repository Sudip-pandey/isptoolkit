# ISP Toolkit — Network & Wi-Fi Diagnostics
**Built by Pandey** | Package: `com.pandey.isptoolkit`

ISP Toolkit is a native Android application engineered for field network engineers and ISP maintenance technicians troubleshooting authorized local networks.

---

## Technical Stack & Architecture

- **Language:** Kotlin 1.9
- **UI Framework:** Jetpack Compose with Material 3 (Default Dark Theme)
- **Architecture:** Clean Architecture + MVVM
- **Dependency Injection:** Dagger Hilt
- **Local Persistence:** Room Database & DataStore
- **Concurrency:** Kotlin Coroutines & Flow / StateFlow
- **Networking Engine:** Android ConnectivityManager, WifiManager, NsdManager, Native Sockets & OkHttp

---

## Authorized Network & Safety Compliance Policy

This application is strictly designed for field diagnostic operations on networks that the technician owns or is explicitly authorized to diagnose.

It intentionally excludes:
- Authentication bypass / password cracking
- Unauthorized router configuration injection
- Stealth scanning or packet interception
- Credential harvesting or exploit payloads

Data Accuracy Assurance: Missing or restricted Android platform telemetry is displayed as "Unavailable" or "Permission required". Data is never fabricated.

---

## Android Permissions Model

- `ACCESS_FINE_LOCATION` & `ACCESS_COARSE_LOCATION`: Required by Android system framework for local Wi-Fi scan access.
- `NEARBY_WIFI_DEVICES`: Android 13+ local network discovery permission.
- `ACCESS_LOCAL_NETWORK`: Forward-compatible declaration for modern Android local network isolation controls.

Offline tools (Subnet Calculator, IPv6 Analyzer, Manual ONT Logs) remain fully operational when network/location permissions are denied.

---

## Build & Deployment Instructions

### Command Line / CI Build (Without Android Studio)
```bash
./gradlew assembleDebug