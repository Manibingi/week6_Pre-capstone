plugins {
    java
    id("io.qameta.allure") version "2.12.0"
}

group = "com.shopkart"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

repositories {
    mavenCentral()
}

dependencies {

    // JUnit
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite-api")

    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Cucumber
    testImplementation("io.cucumber:cucumber-java:7.14.0")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.14.0")
    testImplementation("io.cucumber:cucumber-picocontainer:7.14.0")

    // Selenide
    testImplementation("com.codeborne:selenide:7.9.3")

    // REST Assured
    testImplementation("io.rest-assured:rest-assured:5.5.6")

    // DotEnv
    testImplementation("io.github.cdimascio:dotenv-java:3.2.0")

    // Allure
    testImplementation("io.qameta.allure:allure-junit5:2.35.1")
    testImplementation("io.qameta.allure:allure-cucumber7-jvm:2.35.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.35.1")
    testImplementation("io.qameta.allure:allure-selenide:2.35.1")

    // MySQL
    testImplementation("com.mysql:mysql-connector-j:9.4.0")

    // Testcontainers
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")
    testImplementation("org.testcontainers:mysql:1.21.3")

    // Flyway
    testImplementation("org.flywaydb:flyway-core:11.12.0")
}

tasks.test {
    useJUnitPlatform()
}