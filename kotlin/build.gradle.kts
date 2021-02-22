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
    implementation("io.ktor:ktor-client-core:1.5.1")
    implementation("io.ktor:ktor-client-cio:1.5.1")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "15"
        }
    }

    test {
        useJUnitPlatform()
    }
}