# SQL tutorial

Preparation:

- install docker → https://www.docker.com/ → Get Docker → For Desktops
- docker run -d --name postgres -p 5432:5432 postgres:alpine
- echo 'create database sen;' | docker run -i --rm --link postgres:postgres postgres:alpine psql -h postgres -U postgres postgres
- docker run -i --rm --link postgres:postgres postgres:alpine psql -h postgres -U postgres sen < world/world.sql

Stop and start without losing data:

- docker stop postgres
- docker start postgres
