plugins {
    // See https://kotlinlang.org/docs/releases.html#release-details
    kotlin("jvm") version "1.5.31"
}

group = "com.peshchuk"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

val ktorVersion = "1.6.3"
val coroutinesVersion = "1.5.2"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion") {
        version {
            strictly(coroutinesVersion)
        }
    }
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")

    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")

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
