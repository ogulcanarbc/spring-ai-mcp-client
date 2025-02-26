package mcp.client.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
class McpAssistantChatController {

    private final ChatClient chatClient;

    public McpAssistantChatController(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
            .defaultTools(tools.getToolCallbacks())
            .defaultAdvisors(new PromptChatMemoryAdvisor(new InMemoryChatMemory()))
            .build();
    }

    @GetMapping("/ai")
    public ResponseEntity<Map<String, String>> completion(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return ResponseEntity.ok(Map.of("completion", Objects.requireNonNull(chatClient.prompt()
            .user(message).call().content())));
    }
}