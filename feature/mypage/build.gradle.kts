plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.core.data)
    implementation(projects.core.network)
}