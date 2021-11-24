From openjdk:8
ADD build/libs/url.shortner-0.0.1-SNAPSHOT.jar url-shortener.jar
EXPOSE 8080
CMD ["java","-jar","url-shortener.jar"]