/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.14.1/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // This dependency is used by the application.
    implementation(libs.guava)
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // Define the main class for the application.
    mainClass = "dev.necraul.aoc2015.App"
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

val appDir = layout.projectDirectory
val srcMainJavaDir = appDir.dir("src/main/java")
val aoc2015PackageDir = srcMainJavaDir.dir("dev/necraul/aoc2015")
val dayPackageDir = aoc2015PackageDir.dir("day")
val templateDir = appDir.dir("template")
val inputDir = appDir.dir("input")
val appJavaFile = aoc2015PackageDir.file("App.java").asFile

// Task: 'new'
tasks.register("new") {
    group = "AOC"
    description = "Generates a new DayXX.java file, an empty input file, and updates App.java."

    doLast {
        // 1. Calculate the next day number
        val existingDayFiles = dayPackageDir.asFileTree.matching {
            include("Day*.java")
        }.files.size

        val nextDayNumber = existingDayFiles + 1
        val formattedDayNumber = String.format("%02d", nextDayNumber)

        logger.lifecycle("--- Generating files for Day $formattedDayNumber ---")

        // 2. Create the new input file
        val newInputFile = inputDir.file("Day$formattedDayNumber.txt").asFile
        if (newInputFile.exists()) {
            logger.warn("Input file '${newInputFile.name}' already exists. Skipping creation.")
        } else {
            newInputFile.createNewFile()
            logger.lifecycle("Created: ${newInputFile.relativeTo(appDir.asFile)}")
        }

        // 3. Copy the template and replace 'number'
        val templateFile = templateDir.file("Day.java.template").asFile
        if (!templateFile.exists()) {
            throw GradleException("Template file not found at: ${templateFile.absolutePath}. Please create it.")
        }

        val newJavaFile = dayPackageDir.file("Day$formattedDayNumber.java").asFile
        if (newJavaFile.exists()) {
            logger.warn("Java file '${newJavaFile.name}' already exists. Skipping creation.")
        } else {
            templateFile.copyTo(newJavaFile)
            var content = newJavaFile.readText()
            content = content.replace("number", formattedDayNumber)
            newJavaFile.writeText(content)
            logger.lifecycle("Created: ${newJavaFile.relativeTo(appDir.asFile)}")
            logger.lifecycle("Replaced 'number' with '$formattedDayNumber' in ${newJavaFile.name}")
        }

        // 4. Add new DayXX(); to App.java
        if (appJavaFile.exists()) {
            val appLines = appJavaFile.readLines().toMutableList()
            val previousDayNumber = nextDayNumber - 1
            val formattedPreviousDayNumber = String.format("%02d", previousDayNumber)

            val insertionLine = "        new Day$formattedDayNumber();" // 8 spaces for indentation

            var inserted = false
            for (i in appLines.indices) {
                // Find the line that creates the previous day's instance
                if (appLines[i].trim() == "new Day$formattedPreviousDayNumber();" ||
                    // Handle the case where App.java might be empty or only has Day01() initially
                    (previousDayNumber == 0 && appLines[i].contains("public static void main(String[] args) {"))) {

                    // Insert the new line right after the previous day's instantiation
                    // If previousDayNumber is 0, insert after the main method declaration
                    val insertIndex = if (previousDayNumber == 0) i + 2 else i + 1 // +2 for main method, +1 for others
                    appLines.add(insertIndex, insertionLine)
                    inserted = true
                    logger.lifecycle("Added 'new Day$formattedDayNumber();' to App.java.")
                    break
                }
            }

            if (!inserted) {
                logger.warn("Could not find suitable insertion point for 'new Day$formattedDayNumber();' in App.java. Please add it manually.")
                logger.warn("Expected insertion after: new Day$formattedPreviousDayNumber();")
            } else {
                appJavaFile.writeText(appLines.joinToString("\n"))
            }
        } else {
            logger.error("App.java not found at ${appJavaFile.absolutePath}. Cannot update with new Day instance.")
        }

        logger.lifecycle("--- Generation complete for Day $formattedDayNumber ---")
    }
}