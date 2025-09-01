# About

This is a Spring Boot, Java application that uses WebClient to integrate with Country Public API.

# Build

To build the app GitHub actions is used, where few steps, like packaging and testing are ran. From this public GitHub repository : https://github.com/Sandile100/country-app

# Docker Hub integration

Successful Build creates a docker image as part of CI/CD for the project and uploads it to public Docker Hub repository here : https://hub.docker.com/repository/docker/sandilembatha/country-app/general

# Runing the project

To run the project, a docker compose file has been added to the root folder of UI projects here (https://github.com/Sandile100/country-app) because the UI projects depends on this backend API. This requires Docker to be install on the host machine. And the command below should be issued on the same directory as the docker-compose.yml file

Command : docker compose -f .\docker-compose.yml up

Alternatively one can checkout the project and run it locally on port 8080.

The application will then be served on http:localhost:8080

