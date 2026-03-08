
package com.example.rag.service;



import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

 private final ChatClient chatClient;

 public ChatService(ChatClient.Builder builder){
  this.chatClient = builder.build();
 }

 public String ask(String question, List<String> docs){

  String context = String.join("\n", docs);

  return chatClient.prompt()
   .system("Answer using the provided context")
   .user("Context:\n" + context + "\nQuestion:\n" + question)
   .call()
   .content();
 }

}
