#!/usr/bin/env bash
dir=`dirname $0`

#buildDocker image
${dir}/../gradlew buildDocker -x test -Pprofile=prod

#start docker-compose
cd ${dir}
docker-compose up -d