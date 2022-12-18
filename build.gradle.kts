plugins {
    val kotlinVersion = "1.7.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.13.2"
}

group = "top.jie65535.mirai.mailnotify"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/jakarta.mail/jakarta.mail-api
    implementation("jakarta.mail:jakarta.mail-api:2.0.0")
    implementation("com.sun.mail:jakarta.mail:2.0.1")
}