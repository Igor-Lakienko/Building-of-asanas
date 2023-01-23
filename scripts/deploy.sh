#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/Building-asanas-1.0-SNAPSHOT.jar\
    root@79.133.183.18:/root

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa root@79.133.183.18 <<EOF
    pgrep java | xargs kill -9
    nohup java -jar Building-asanas-1.0-SNAPSHOT.jar > log.txt 2>&1 &
EOF

echo 'Bye'