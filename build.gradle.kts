plugins {
    java
    id("com.diffplug.spotless") version "6.7.2"
}

group = "com.huarngpa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

spotless {
    java {
        googleJavaFormat()
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
