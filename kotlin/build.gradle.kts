plugins {
    kotlin("jvm") version "1.4.30"
}

group = "com.peshchuk"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}