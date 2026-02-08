plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.2"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.openapi.generator") version "7.11.0"
}

group = "com.uma_ai_selecter"
version = "0.0.1-SNAPSHOT"
description = "Uma AI Selecter API"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// MyBatis
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")

	// OpenAPI Generator dependencies
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.20")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("javax.annotation:javax.annotation-api:1.3.2")

	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

openApiGenerate {
	generatorName.set("kotlin-spring")
	inputSpec.set("$rootDir/src/main/resources/openapi.yml")
	outputDir.set("$buildDir/generated")
	apiPackage.set("com.uma_ai_selecter.api")
	modelPackage.set("com.uma_ai_selecter.model")
	configOptions.set(mapOf(
		"interfaceOnly" to "true",
		"useTags" to "true",
		"gradleBuildFile" to "false",
		"documentationProvider" to "none",
		"useSpringBoot3" to "true"
	))
	typeMappings.set(mapOf(
		"DateTime" to "java.time.LocalDateTime"
	))
	importMappings.set(mapOf(
		"java.time.LocalDateTime" to "java.time.LocalDateTime"
	))
}

sourceSets {
	main {
		java {
			srcDir("$buildDir/generated/src/main/kotlin")
		}
	}
}
