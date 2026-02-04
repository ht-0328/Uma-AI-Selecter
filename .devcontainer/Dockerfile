FROM mcr.microsoft.com/devcontainers/java:21-bookworm

# Install Docker CLI
RUN apt-get update && apt-get install -y docker.io && apt-get clean -y && rm -rf /var/lib/apt/lists/*

# Install Node.js (LTS) & npm
RUN curl -fsSL https://deb.nodesource.com/setup_22.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g pnpm && \
    apt-get clean -y && rm -rf /var/lib/apt/lists/*
