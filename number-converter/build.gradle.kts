import org.codehaus.groovy.tools.shell.util.Logger.io

plugins {
    id("java-library")
    id("idea")
    id("io.freefair.lombok") version "8.6"
}

group = "pro.abnjava.jvm"
version = "0.1-snapshot"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
