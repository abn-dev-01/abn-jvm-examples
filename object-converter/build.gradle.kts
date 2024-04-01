val lang3Version: String by project
val junitVersion: String by project

plugins {
    id("java-library")
    id("idea")
    id("io.freefair.lombok") version "8.6"
}

group = "pro.abnjava.jvm"
version = "0.1.0-snapshot"

repositories {
    mavenCentral()
}

dependencies {
    // Add Log4j2 dependencies
    implementation( "org.apache.logging.log4j:log4j-api:2.14.1")
    implementation ("org.apache.logging.log4j:log4j-core:2.14.1")

    implementation("org.apache.commons:commons-lang3:$lang3Version")

    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
