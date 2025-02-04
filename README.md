
# ollama-deepseek-r1

## Introduction

This application is a demo app for the integration of Spring AI with the Ollama deepseek-r1 model, which runs on a local Docker container.

## Step 1: Run Ollama Docker Container

To run the Ollama Docker container, use the following command:

```sh
docker run -d -v ollama:/root/.ollama -p 11434:11434 --name ollama ollama/ollama
```

## Step 2: Run Specific Model on Ollama

To run the deepseek-r1 model on Ollama, use the following command:

```sh
docker exec -it ollama ollama run deepseek-r1:1.5b
```

## Step 3: Test with API to Verify Model is Running Successfully

To verify that the model is running successfully, you can call the following API in your browser:

```sh
http://localhost:11434/v1/models
```

This API will give you details of the running model in the Ollama container.

## Step 4: Add Spring AI Dependency in Spring Boot Project

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
    <groupId>org.springframework.ai</groupId>
    <artifactId>spring-ai-ollama-spring-boot-starter</artifactId>
</dependency>
```

## Step 5: Add Ollama Properties in `application.yml` File

Add the following properties to your `application.yml` file:

```yaml
server:
    port: 8082

spring:
  application:
    name: ollama

  ai:
    ollama:
      base-url: http://localhost:11434
      chat:
        model: deepseek-r1:1.5b
```
