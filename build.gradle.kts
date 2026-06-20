plugins {
    application
}

application {
    mainClass.set("app.Main")
}

group = "app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("com.fasterxml.jackson.core:jackson-core:2.15.+")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.+")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.+")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.+")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
}


