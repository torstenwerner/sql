# SQL tutorial

Preparation:

- docker run -d --name postgres -p 5432:5432 postgres:alpine
- docker run -i --rm --link postgres:postgres postgres:alpine psql -h postgres -U postgres sen < world/world.sql

Stop and start without losing data:

- docker stop postgres
- docker start postgres
