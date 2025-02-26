# spring-ai-mcp-client

The Model Context Protocol (MCP) client application utilizes Spring AI and Anthropic Claude models (Or OpenAI). It integrates with MCP protocol-supported servers to enable AI-powered chat interactions.

## What is Spring MCP Client
The MCP Client is a key component in the Model Context Protocol (MCP) architecture, responsible for establishing and managing connections with MCP servers. 
It implements the client-side of the protocol, handling:

    - Protocol version negotiation to ensure compatibility with servers
    - Capability negotiation to determine available features
    - Message transport and JSON-RPC communication
    - Tool discovery and execution
    - Resource access and management
    - Prompt system interactions
    - Optional features like roots management and sampling support
    - Supports openai and anthropic models

## Requirements
    Java 17+
    Maven or Gradle
    Docker (Optional)
    Anthropic API key Or OpenAI API key

- Anthropic API key from the [Anthropic Console](https://console.anthropic.com).
- OpenAI API key from the [OpenAI Console](https://platform.openai.com/settings/organization/api-keys).

## Installation
 ### 1. Clone Repository
        
        git clone <repo-url>
        cd spring-ai-mcp-client

### 2. MCP Server Configuration

The MCP server information must be added to the mcp-servers.json file as follows.

```json mcpservers.json
{
    "mcpServers":{
        "any-mcp-server":{
          "command":"node",
          "args":[
            "any-mcp-server/build/index.js"
          ]
        }
    }
}
 ``` 

### 3. Spring Configuration

The application.yml file should be configured as follows:

```yaml
spring:
  ai:
    mcp:
      client:
        enabled: true
        name: any-mcp-server # MCP server name
        version: 1.0.0
        type: SYNC
        request-timeout: 20s
        stdio:
          root-change-notification: true
          servers-configuration: classpath:mcp-servers.json # MCP server config such/same as claude desktop configs.
    anthropic:
      api-key: ${ANTHROPIC_API_KEY}
#   openai:
#     api-key:  ${OPENAI_API_KEY}  
server:
  port: 8081
```

### 4. Run Applicaton

    mvn clean install
    mvn spring-boot:run

### Usage
Once the application is running, it can be accessed via the following endpoint:
      
- http://localhost:8091/ai

You can start a chat with the following cURL command:

```curl -X GET "http://localhost:8081/ai?message=Hello" -H "Accept: application/json"```

