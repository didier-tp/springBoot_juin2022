FROM openjdk:11
# this new image will be create from parent image = openjdk:11(stable)

# Create app directory inside docker image
WORKDIR /usr/app


COPY  target/appliSpring.jar  ./

#optimisation possible : https://spring.io/guides/topicals/spring-boot-docker

EXPOSE 8080

#CMD ["java","-Dspring.profiles.active=remoteDB","-jar","appliSpring.jar" ]
CMD ["java","-jar","appliSpring.jar" ]