# Uma-AI-Selecter

## 開発環境セットアップ

### 前提条件
*   VS Code
*   Docker
*   Dev Containers 拡張機能

### クイックスタート

1.  VS Code でこのリポジトリを開きます。
2.  example から `.env` ファイルを作成します:
    ```bash
    cp .env.example .env
    ```
3.  プロンプトが表示されたら「Reopen in Container」をクリックするか、コマンド `Dev Containers: Reopen in Container` を実行します。
4.  Dev Container 内に入ったら、コアサービス（DB、Flyway、WireMock）を開始します:
    ```bash
    ./scripts/dev-up.sh
    ```
5.  VS Code タスク（Terminal -> Run Task...）またはスクリプトを使用してアプリケーションを開始します:
    *   **Run All:** `run-all` タスク または `./scripts/run-all.sh`
    *   **Run API:** `run-api` タスク または `./scripts/run-api.sh`
    *   **Run Web:** `run-web` タスク または `./scripts/run-web.sh`

6.  サービスを確認します:
    *   **Web (Nuxt):** [http://localhost:3000](http://localhost:3000)
    *   **API (Spring Boot):** [http://localhost:8080/health](http://localhost:8080/health) -> `OK` が返るはずです
    *   **WireMock:** [http://localhost:8082/mock/ping](http://localhost:8082/mock/ping) -> `{ "ok": true }` が返るはずです
    *   **API -> WireMock:** [http://localhost:8080/wiremock/ping](http://localhost:8080/wiremock/ping) -> `{ "ok": true }` が返るはずです

### Web (Nuxt) 開発

Web アプリケーションを手動でセットアップ・起動する場合の手順:

1.  Dev Container に入ります。
2.  依存関係をインストールします:
    ```bash
    cd web && npm install
    ```
3.  開発サーバーを起動します:
    ```bash
    npm run dev
    ```
4.  ブラウザで確認します:
    [http://localhost:3000](http://localhost:3000)

### DB マイグレーション (Flyway)

マイグレーションは起動時（`dev-up.sh` 経由）に自動的に適用されます。

**新しいマイグレーションの追加:**
1.  `flyway/sql/` に新しい SQL ファイルを追加し、`V2__Description.sql` などの名前を付けます。
2.  flyway サービスを再起動します:
    ```bash
    docker compose -f docker/docker-compose.yml restart flyway
    ```

**データベースのリセット:**
すべてのデータを削除して最初からやり直す場合:
```bash
./scripts/dev-reset.sh
```

### Kotlin LSP
Kotlin LSP（JetBrains/kotlin-lsp）は、Dev Container 内の VS Code 拡張機能として自動的にインストールされます。
インストールに失敗した場合は、ログを確認するか、`bash scripts/install-kotlin-lsp.sh` を手動で実行してください。
