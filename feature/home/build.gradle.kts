plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    implementation(libs.coil.compose)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)
    implementation(libs.androidx.compose.foundation)
}