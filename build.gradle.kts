plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
	kotlin("kapt") version "1.5.31"
	application
}

repositories {
    mavenCentral() 
}

dependencies {
	implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.jdbi:jdbi3-kotlin:3.23.0")
	implementation("com.google.dagger:dagger:2.39.1")
	implementation("org.xerial:sqlite-jdbc:3.36.0.3")
	implementation("org.jdbi:jdbi3-core:3.23.0")
	implementation("org.jdbi:jdbi3-sqlobject:3.23.0")
	implementation("com.zaxxer:HikariCP:5.0.0")
	implementation("com.linecorp.armeria:armeria:1.12.0")

	testImplementation("org.jetbrains.kotlin:kotlin-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

	kapt("com.google.dagger:dagger-compiler:2.39.1")
	kaptTest("com.google.dagger:dagger-compiler:2.39.1")
}

application {
	applicationName = "apalabrados"
	mainClass.set("challenge.apalabrados.BootstrapKt")
	applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

kotlin {
	kotlinDaemonJvmArgs = listOf("-Dfile.encoding=UTF-8",
		"--add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
		"--add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED")
}
