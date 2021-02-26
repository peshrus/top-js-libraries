defaultTasks("clean", "build")

task<Exec>("clean") {
    workingDir = java.nio.file.Paths.get("topjslibs").toFile()
    commandLine("go", "clean")
}

task<Exec>("build") {
    dependsOn("test")
    workingDir = java.nio.file.Paths.get("topjslibs").toFile()
    commandLine("go", "build")
}

task<Exec>("test") {
    workingDir = java.nio.file.Paths.get("topjslibs").toFile()
    commandLine("go", "test")
}