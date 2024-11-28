plugins {
    id("java")
    id("application")
}

group = "jade"
version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

application { mainClass.set("jade.Square") }

dependencies {
    implementation("com.google.guava:guava:33.3.1-jre")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test { useJUnitPlatform() }