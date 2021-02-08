plugins {
    java
}

group = "de.toxicpointer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.6.0")
    testImplementation("org.jeasy", "easy-random-core", "5.0.0")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
}

tasks.test {
    useJUnitPlatform()
}
