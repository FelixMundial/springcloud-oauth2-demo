package com.example.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class ProviderApplicationTests {

    @Test
    void testJsonParsingForCollections() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> jsonizedToken = new HashMap<>();
        List<Object> authorities = new ArrayList<>();

        authorities.add("aaa");
        authorities.add("bbb");
        authorities.add("ccc");

        jsonizedToken.put("principal", "p0");
        jsonizedToken.put("authorities", authorities);

        String encodedToken = Base64Utils.encodeToString(objectMapper.writeValueAsString(jsonizedToken).getBytes());

        String token = new String(Base64Utils.decodeFromString(encodedToken));
        JsonNode jsonNode = objectMapper.readTree(token);

        String principal = jsonNode.get("principal").asText();
        JsonNode authorities1 = jsonNode.get("authorities");

        authorities.clear();
        authorities1.forEach(authorities::add);
        System.out.println(authorities);
    }

}
