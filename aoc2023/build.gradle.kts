plugins {
    id("java")
}

group = "dev.graffa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("commons-io:commons-io:2.7")
}

tasks.test {
    useJUnitPlatform()
}