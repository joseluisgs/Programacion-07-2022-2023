plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "es.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Moshi
    //implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //implementation("com.squareup.moshi:moshi-adapters:1.14.0")

    // Simple XML
    implementation("org.simpleframework:simple-xml:2.7.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}