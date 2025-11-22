plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    implementation(projects.feature.home)
}