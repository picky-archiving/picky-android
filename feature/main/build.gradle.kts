plugins {
    alias(libs.plugins.example.feature)
}

dependencies {
    implementation(projects.feature.home)
    implementation(projects.feature.auth)
    implementation(projects.feature.search)
    implementation(projects.feature.mypage)

    implementation(libs.kotlinx.immutable)
}