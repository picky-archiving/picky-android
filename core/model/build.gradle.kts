plugins {
    alias(libs.plugins.example.core)
}

dependencies {
    implementation(projects.core.designsystem)
}