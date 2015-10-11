#!/usr/bin/env bash
# 用docker创建一个redis container
# 2015-06-07 01:47

docker rm engine-redis
docker run -d -p 127.0.0.1:6379:6379 --name engine-redis redis
