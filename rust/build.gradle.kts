defaultTasks("clean", "build")

task<Exec>("clean") {
    commandLine("cargo", "clean")
}

task<Exec>("build") {
    dependsOn("test")
    commandLine("cargo", "build")
}

task<Exec>("test") {
    commandLine("cargo", "test")
}