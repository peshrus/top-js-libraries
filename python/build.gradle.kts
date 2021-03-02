// To make the module work in Idea it's needed to do the following:
// - Mark main directory as Sources Root;
// - Mark test directory as Test Sources Root;
// - Add Python facet to python module.

// To run the tests in IDE the working directory must be set to test.

task<Exec>("pyTest") {
    environment = mapOf("PYTHONPATH" to projectDir.resolve("main").absolutePath)
    workingDir = java.nio.file.Paths.get("test").toFile()
    commandLine("python", "-m", "unittest")
}