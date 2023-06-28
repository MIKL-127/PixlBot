plugins {
    id("java")
}

group = "de.pixl"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        name = "craftsblockReleases"
        url = uri("https://repo.craftsblock.de/releases")
    }
    mavenCentral()
}

dependencies {
    implementation ("net.dv8tion:JDA:5.0.0-alpha.21")
    implementation ("de.craftsblock.craftscore:CraftsCore:3.7.9-SNAPSHOT")
}