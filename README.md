# Uma-AI-Selecter

## Development Environment Setup

### Prerequisites
*   VS Code
*   Docker
*   Dev Containers extension

### Quick Start

1.  Open this repository in VS Code.
2.  Create `.env` file from example:
    ```bash
    cp .env.example .env
    ```
3.  Click "Reopen in Container" when prompted, or run the command `Dev Containers: Reopen in Container`.
4.  Once inside the Dev Container, start the core services (DB, Flyway, WireMock):
    ```bash
    ./scripts/dev-up.sh
    ```
5.  Start the applications using VS Code Tasks (Terminal -> Run Task...) or scripts:
    *   **Run All:** `run-all` task or `./scripts/run-all.sh`
    *   **Run API:** `run-api` task or `./scripts/run-api.sh`
    *   **Run Web:** `run-web` task or `./scripts/run-web.sh`

6.  Verify the services:
    *   **Web (Nuxt):** [http://localhost:3000](http://localhost:3000)
    *   **API (Spring Boot):** [http://localhost:8080/health](http://localhost:8080/health) -> Should return `OK`
    *   **WireMock:** [http://localhost:8082/mock/ping](http://localhost:8082/mock/ping) -> Should return `{ "ok": true }`

### DB Migration (Flyway)

Migrations are automatically applied on startup (via `dev-up.sh`).

**Adding a new migration:**
1.  Add a new SQL file to `flyway/sql/` naming it `V2__Description.sql`, etc.
2.  Restart the flyway service:
    ```bash
    docker compose -f docker/docker-compose.yml restart flyway
    ```

**Resetting the database:**
To delete all data and start fresh:
```bash
./scripts/dev-reset.sh
```

### Kotlin LSP
The Kotlin LSP (from JetBrains/kotlin-lsp) is automatically installed as a VS Code extension inside the Dev Container.
If it fails to install, check the logs or run `bash scripts/install-kotlin-lsp.sh` manually.
