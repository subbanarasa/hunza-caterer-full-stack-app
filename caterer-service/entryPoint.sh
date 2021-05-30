#!/bin/sh

java -cp /work/app.jar org.springframework.boot.loader.PropertiesLauncher \
    --spring.config.location=/config/application.properties |\
        tee /logs/app.log
