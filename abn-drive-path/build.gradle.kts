// Define versions
val commonsLang3Version = "3.14.0"
val kotlinTestVersion = "1.5.31"

plugins {
    kotlin("jvm") version "1.9.23"
    idea
}

group = "pro.abnjava.abn-drive-path"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:$commonsLang3Version")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}
