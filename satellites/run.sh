#!/bin/bash

if [ ! -f .gitignore ];then
    mv gitignore .gitignore
fi

mvn clean install &&

mvn clean package -pl satellites-server &&

JVM_PARAM="-Xmx1g -Xms1g -Xmn512m -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:logs/gc.log -Dspring.profiles.active=dev -Dorg.jboss.logging.provider=slf4j"

java $JVM_PARAM -jar satellites-server/target/satellites-server.jar