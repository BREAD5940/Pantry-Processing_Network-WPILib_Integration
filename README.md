# Pantry: Processing Network - WPILib Integration (v0.0.0)
This pantry ingredient integrates WPILib with the processing network ingredient, making it suitable for controlling robots. It is currently under initial development, come back soon!

# Dependencies
- WPILib
  - Automatically included through the GradleRIO plugin.
- Processing Network (v0.0+)
  - Because Gradle does not currently support nested composite builds, you must either add something like `includeBuild '../Pantry-Processing_Network/'` to the `settings.gradle` for the project you are actually building or `--include-build '../Pantry-Processing_Network/'` to the end of a gradle command.
- Logging (v0.0+)
  - Because Gradle does not currently support nested composite builds, you must either add something like `includeBuild '../Pantry-Logging/'` to the `settings.gradle` for the project you are actually building or `--include-build '../Pantry-Logging/'` to the end of a gradle command.
