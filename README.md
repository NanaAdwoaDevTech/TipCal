# TipCal
## Build without installing anything (GitHub Actions)
1. Create a new GitHub repo and upload this folder's contents (including the hidden .github folder).
2. Open the repo's Actions tab, wait for "Build release bundle" to finish (a few minutes).
3. Download the TipCal-release-aab artifact; it contains app-release.aab.

## Build locally
    gradle wrapper --gradle-version 8.9
    ./gradlew bundleRelease
