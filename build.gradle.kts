plugins {
    kotlin("jvm") version "1.3.21"
    java
}

repositories {
    jcenter()
}

dependencies {
    implementation("junit:junit:3.8.2")
    compile( kotlin("stdlib") )
    compile("com.google.guava:guava:18.0")
    compile( "org.apache.commons:commons-lang3:3.3.2")
    compile ("com.opencsv:opencsv:5.5.2")
    testCompile("junit:junit:4.+")
}

tasks.test {
    testLogging {
        events("PASSED", "FAILED", "SKIPPED", "STANDARD_ERROR", "STANDARD_OUT")
    }
}