import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.4.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("com.google.protobuf") version "0.8.8"
	idea
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
}

group = "com.tradeix"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.google.protobuf:protobuf-java:3.6.1")
	implementation("io.grpc:grpc-stub:1.15.1")
	implementation("io.grpc:grpc-protobuf:1.15.1")
	implementation("io.grpc:grpc-netty:1.15.1")
	implementation("io.github.microutils:kotlin-logging:1.7.7")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

protobuf {
	protoc {
		// The artifact spec for the Protobuf Compiler
		artifact = "com.google.protobuf:protoc:3.6.1"
	}
	plugins {
		// Optional: an artifact spec for a protoc plugin, with "grpc" as
		// the identifier, which can be referred to in the "plugins"
		// container of the "generateProtoTasks" closure.
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.15.1"
		}
	}
	generateProtoTasks {
		ofSourceSet("main").forEach {
			it.plugins {
				// Apply the "grpc" plugin whose spec is defined above, without options.
				id("grpc")
			}
		}
	}
}

sourceSets["main"].java {
	srcDir("build/generated/source/proto")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

idea {
	module {
		sourceDirs.add(file("${projectDir}/build/generated/source/proto/main/java"))
		sourceDirs.add(file("${projectDir}/build/generated/source/proto/main/grpc"))
	}
}
