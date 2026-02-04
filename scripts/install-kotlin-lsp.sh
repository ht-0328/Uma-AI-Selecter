#!/bin/bash
set -e

# .envが存在する場合、読み込む（環境変数にバージョンがない場合のため）
if [ -f .env ]; then
  export $(grep -v '^#' .env | xargs)
fi

# KOTLIN_LSP_VERSIONが設定されているか確認
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
# 'code'コマンドが存在するか確認
if command -v code >/dev/null 2>&1; then
    code --install-extension "$VSIX_FILE" --force
else
    echo "Warning: 'code' command not found. Skipping extension installation. You may need to install it manually from $VSIX_FILE"
fi

echo "Kotlin LSP setup complete."
# codeコマンドが見つからない場合、ユーザーがインストールできるようにファイルを保持するか、削除するか？
# 要件には「DevContainer作成時に自動インストール」とある。
# postCreateCommandでcodeが利用できない場合、これは失敗する可能性がある。
# しかし、標準的なDevContainerを想定すれば問題ないはず。
rm -f "$VSIX_FILE"
