plugins {
    kotlin("jvm") version "2.1.0"
    id("com.diffplug.spotless") version "7.0.0.BETA4"
}

group = "arts.mark"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        ktfmt("0.51").kotlinlangStyle().configure {
            it.setMaxWidth(120)
        }
    }
}