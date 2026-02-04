# Base Image: Eclipse Temurin (Adoptium) JDK 21 (LTS) + Ubuntu Jammy
FROM eclipse-temurin:21-jdk-jammy

# Prevent interactive prompts during package installation
ENV DEBIAN_FRONTEND=noninteractive

# Timezone & Locale settings
ENV TZ=Asia/Tokyo
ENV LANG=C.UTF-8

# Install necessary packages
RUN set -eux && \
    apt-get update && \
    apt-get install -y \
    bash-completion \
    tree \
    curl \
    wget \
    git \
    vim \
    jq \
    zip \
    unzip \
    tar \
    gzip \
    build-essential \
    postgresql-client \
    less \
    docker.io \
    tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

###### Kotlin Compiler Installation ######

# Kotlin Version
ARG KOTLIN_VERSION=2.1.10
ARG BASE_URL_KOTLIN=https://github.com/JetBrains/kotlin/releases/download/

ENV KOTLIN_HOME=/opt/kotlin/kotlin-compiler-${KOTLIN_VERSION}
ENV PATH="${KOTLIN_HOME}/bin:${PATH}"

RUN set -eux && \
    curl -L -o /tmp/kotlin-compiler.zip ${BASE_URL_KOTLIN}/v${KOTLIN_VERSION}/kotlin-compiler-${KOTLIN_VERSION}.zip && \
    unzip /tmp/kotlin-compiler.zip -d /opt/kotlin && \
    rm /tmp/kotlin-compiler.zip

###### Gradle Installation ######

# Gradle Version
ARG GRADLE_VERSION=8.13
ARG BASE_URL_GRADLE=https://services.gradle.org/distributions

ENV GRADLE_HOME=/opt/gradle/gradle-${GRADLE_VERSION}
ENV PATH="${GRADLE_HOME}/bin:${PATH}"

RUN set -eux && \
    curl -L -o /tmp/gradle.zip ${BASE_URL_GRADLE}/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip /tmp/gradle.zip -d /opt/gradle && \
    rm /tmp/gradle.zip

###### act Installation ######

RUN set -eux && \
    curl --proto '=https' --tlsv1.2 -sSf https://raw.githubusercontent.com/nektos/act/master/install.sh | bash

###### Node.js (LTS 22.x) Installation ######
RUN set -eux && \
    curl -fsSL https://deb.nodesource.com/setup_22.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g pnpm

# Working Directory
WORKDIR /workspace

# VSCode DevContainer Environment Variable
ENV DEVCONTAINER=true
