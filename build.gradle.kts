plugins {
    java
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.9"
}

group = "de.toxicpointer"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(15))
    }
}

application {
    mainClass.set("de.toxicpointer.clubmanager.gui.ClubUI")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.0")
    testImplementation("org.jeasy", "easy-random-core", "5.0.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
    implementation("org.openjfx", "javafx-graphics", "15")
}

javafx {
    version = "15"
    modules("javafx.controls", "javafx.fxml", "javafx.graphics")
}

tasks.test {
    useJUnitPlatform()
}
