#!/usr/bin/env bash
# 用docker创建一个mysql container
# 2015-06-07 01:47

docker rm mysql
docker run --name mysql -e MYSQL_ROOT_PASSWORD=123 -p 127.0.0.1:3306:3306 -d mysql
docker exec mysql /bin/sh -c 'mysql -uroot -p123 -e "CREATE DATABASE IF NOT EXISTS user"'

#宿主机连接
#mysql -uroot -p123 -h127.0.0.1 -P3306