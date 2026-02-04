#!/bin/bash
(trap 'kill 0' SIGINT; bash scripts/run-api.sh & bash scripts/run-web.sh & wait)
