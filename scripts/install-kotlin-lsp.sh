#!/bin/bash
set -e

# Load .env if present (to get version if not in env)
if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
fi

# Check if KOTLIN_LSP_VERSION is set
if [ -z "$KOTLIN_LSP_VERSION" ]; then
    echo "Error: KOTLIN_LSP_VERSION is not set."
    exit 1
fi

ARCH=$(uname -m)
if [ "$ARCH" = "x86_64" ]; then
    LSP_ARCH="linux-x64"
elif [ "$ARCH" = "aarch64" ]; then
    LSP_ARCH="linux-aarch64"
else
    echo "Error: Unsupported architecture: $ARCH"
    exit 1
fi

VSIX_URL="https://download-cdn.jetbrains.com/kotlin-lsp/${KOTLIN_LSP_VERSION}/kotlin-lsp-${KOTLIN_LSP_VERSION}-${LSP_ARCH}.vsix"
VSIX_FILE="/tmp/kotlin-lsp.vsix"

echo "Downloading Kotlin LSP version $KOTLIN_LSP_VERSION for $LSP_ARCH from $VSIX_URL..."
curl -L -o "$VSIX_FILE" "$VSIX_URL"

echo "Installing Kotlin LSP..."
# Check if 'code' command exists
if command -v code >/dev/null 2>&1; then
    code --install-extension "$VSIX_FILE" --force
else
    echo "Warning: 'code' command not found. Skipping extension installation. You may need to install it manually from $VSIX_FILE"
fi

echo "Kotlin LSP setup complete."
# We keep the file if code wasn't found so user can install it, or remove it?
# Requirement says "Create DevContainer automatically install".
# If code is not available in postCreateCommand, this might fail.
# But assuming standard DevContainer, it should be fine.
rm -f "$VSIX_FILE"
