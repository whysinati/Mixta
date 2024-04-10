# Mixta

A Franken-App to develop capabilities in several aspects of Android mobile development

## Getting Started

[The Android Developer website's courses, codelabs and documentation](https://developer.android.com/) 
is the source for much of the development work on this project.

Steps to run this app:
- Download Android Studio
- Run `git clone https://github.com/whysinati/mixta.git`


## Android Architecture, Libraries and References

- Jetpack Compose, Material Design, Animation, UI Design Systems and Figma Relay Plugins
- MVVM, View Models, State Management and Flows
- GraphQL, Apollo and Contentful's API
- Version Catalog Dependency Management
- Gradle with Kotlin DSL build configuration
- Coil image loading

## Troubleshooting notes

- The Figma Relay plugins require temporary downgrades to Android Studio Hedgehog|2023.1.1 and AGP 8.2.2, as of this update, in order to import or updated ui packages. Thankfully, once the latest ui packages are brought in, the latest Android Studio and AGP will be able to auto-generate the corresponding Composables.
  - [Multiple versions of Android Studio can be installed and run, as required.](https://stackoverflow.com/questions/27836347/how-to-install-multiple-android-studio-with-different-versions-on-same-pc)
- The Apollo GraphQL library requires a schema file in order to sync properly at initial setup. This will enable the build process to generate Query, Mutation and Subscription classes from their corresponding graphql files.
- In some instances, the state Animations require `coerce` functions to ensure layout calculations stay within padding and line boundaries (i.e. to avoid app crashing).

## What's next?

- ****** before i push this, set the contentful token as a secret; github actions or local properties or ...?
- Add a Repository to manage the data sources
- Add a CI/CD pipeline
- Add Unit and UI testing
- Add ML Kit / Facial recognition
- Leverage the security-crypto library to access PKI tokens and store in SecureElement with EncryptedSharedPreferences
- Setup Android Gradle build security via dependency verification, repository filtering and other gradle verifications
- Add build signing