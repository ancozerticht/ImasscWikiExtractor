plugins {
    kotlin("jvm") version "1.3.70"
    war
}

group = "com.hatenablog.ancozerticht"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jsoup", "jsoup", "1.13.1")
    compileOnly("javax", "javaee-api", "8.0.1")
}

tasks {
    this.war {
        archiveBaseName.set("imassc-wiki-extractor")
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

val glassFishHome = "C:/glassfish5/glassfish"
val asadmin = glassFishHome + (if(isWindows()) "/bin/asadmin.bat" else "/bin/asadmin")
val domain = "domain1"
fun isWindows(): Boolean {
    return System.getProperty("os.name")?.toLowerCase()?.contains("windows")
        ?:false
}

tasks {
    val startServer by registering(Exec::class) {
        commandLine(asadmin, "start-domain", "--debug=true", domain)
    }

    val stopServer by registering(Exec::class) {
        commandLine(asadmin, "stop-domain", domain)
    }

    val deploy by registering(Exec::class) {
        dependsOn(war)
        commandLine(asadmin, "deploy", "--force=true",
            war.get().destinationDirectory.get().toString() + "\\" + war.get().archiveFileName.get().toString())
    }
}
