# Mixta

A Franken-App to develop capabilities in several aspects of Android mobile development

## Getting Started

[The Android Developer website's courses, codelabs and documentation](https://developer.android.com/) 
is the source for much of the development work on this project. [Kodeco](https://www.kodeco.com/), [Contentful](https://www.contentful.com/developers/docs/android/tutorials/using-contentful-graphql-with-android/) and [Apollo GraphQL](https://www.apollographql.com/docs/kotlin)

Steps to run this app:
- Download Android Studio
- Run command `git clone https://github.com/whysinati/mixta.git` in the desired location for the repository.
- Open the project in Android Studio, allow to sync, then build and run on the emulator.
- In order to get the API data, add a new `keystore.properties` file in the root project directory and add the following line (you can use the token from the Contentful Kotlin tutorial):
  `token="<your_contentful_token_goes_here>"`

## Android Architecture, Libraries and References

- Jetpack Compose, Material Design, Animation, UI Design Systems and Figma Relay Plugins
- MVVM, View Models, State Management and Flows
- GraphQL, Apollo and Contentful's API
- Version Catalog Dependency Management
- Gradle with Kotlin DSL build configuration
- Coil image loading
- [KtLint](https://github.com/JLLeitschuh/ktlint-gradle) 

## Troubleshooting notes

- The Figma Relay plugins require temporary downgrades to Android Studio Hedgehog|2023.1.1 and AGP 8.2.2, as of this update, in order to import or updated ui packages. Thankfully, once the latest ui packages are brought in, the latest Android Studio and AGP will be able to auto-generate the corresponding Composables.
  - [Multiple versions of Android Studio can be installed and run, as required.](https://stackoverflow.com/questions/27836347/how-to-install-multiple-android-studio-with-different-versions-on-same-pc)
- The Apollo GraphQL library requires a schema file in order to sync properly at initial setup. This will enable the build process to generate Query, Mutation and Subscription classes from their corresponding graphql files.
- In some instances, the state Animations require `coerce` functions to ensure layout calculations stay within padding and line boundaries (i.e. to avoid app crashing).

## What's next?

- Consider alternatives to store tokens and keys securely as a secrets and enable use with a pipeline
- Add a Repository to manage the data sources
- Add a CI/CD pipeline
- Add Unit and UI testing
- Add ML Kit / Facial recognition
- Leverage the security-crypto library to access PKI tokens and store in SecureElement with EncryptedSharedPreferences
- Setup Android Gradle build security via dependency verification, repository filtering and other gradle verifications
- Add build signing
- Setup Automated Google Play deployment, perhaps leveraging Fastlane
- Refactor Navigation
