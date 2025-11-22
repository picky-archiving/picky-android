package hackathon.picky.convention.util

import hackathon.picky.convention.androidExtension
import org.gradle.api.Project

fun Project.setNamespace(name: String) {
    androidExtension.apply {
        namespace = "com.example.$name"
    }
}