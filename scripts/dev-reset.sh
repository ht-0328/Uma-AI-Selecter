#!/bin/bash
echo "WARNING: This will destroy all data in the database volume. Are you sure? [y/N]"
read -r response
if [[ "$response" =~ ^([yY][eE][sS]|[yY])+$ ]]
then
    docker compose -f docker/docker-compose.yml --env-file .env down -v
    echo "Environment reset complete."
else
    echo "Reset cancelled."
fi
