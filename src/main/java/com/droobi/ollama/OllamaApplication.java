package com.droobi.ollama;

import io.swagger.v3.oas.annotations.servers.Servers;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class OllamaApplication {

  public static void main(String[] args) {
    SpringApplication.run(OllamaApplication.class, args);
  }

  @Bean
  ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
    return chatClientBuilder.build();
  }

  @RestController
  public class OllamaController {

    @Autowired private OllamaService ollamaService;

    @PostMapping("/ollama")
    public String getOllama(@RequestBody Chat chat) {
      return ollamaService.getOllama(chat);
    }
  }

  record Chat(String content) {}

  @Service
  public class OllamaService {

    private final ChatClient chatclient;

    public OllamaService(ChatClient chatclient) {
      this.chatclient = chatclient;
    }

    public String getOllama(Chat chat) {
      return chatclient.prompt(chat.content).call().content();
    }
  }
}
