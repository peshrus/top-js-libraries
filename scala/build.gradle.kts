plugins {
    scala
}

group = "com.peshchuk"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.5")
    implementation("org.scalaj:scalaj-http_2.12:2.4.2")

    testImplementation("junit:junit:4.13")
    testImplementation("org.scalatest:scalatest_2.13:3.2.6")
    testImplementation("org.scalatestplus:junit-4-13_2.13:3.2.6.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_15
}