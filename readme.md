# URL Shortener

Spring Boot application which takes a URL and returns a shortened URL.

## Build Project 

To build this project, run

```shell script
git clone https://github.com/neerajr17/url-shortner-assignment
cd url-shortner-assignment
mvn clean install
```

## Starting application

To start the project, run

```shell script
mvn spring-boot:run
```

## Deployment

To deploy the project, run

```shell script
docker-compose up --build
```

**The application will be accessible on http://localhost:8080**

## API Endpoints

You can access following API endpoints at http://localhost:8080

### POST `/shorten/{url}`


#### cURL

```shell script
curl -X POST \
  http://localhost:8080?https://www.google.com/search?q=infracloud
```

Response:

```redirect url
http://localhost:8080/some_hash_code
```


### GET `http://localhost:8080/<some_hash_code>`

This endpoint redirects to the corresponding fullUrl.
