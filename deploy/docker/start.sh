#!/bin/bash

docker run -d -p 127.0.0.1:3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123 mysql

docker run -d -p 127.0.0.1:6379:6379 --name redis redis