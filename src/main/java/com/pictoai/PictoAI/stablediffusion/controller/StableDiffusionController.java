package com.pictoai.PictoAI.stablediffusion.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/v1")
public class StableDiffusionController {

    @PostMapping("/generate")
    public ResponseEntity<Resource> generateImage(@RequestBody PromptRequest promptRequest) {
      
        String stableDiffusionApiUrl = "https://35eb-181-66-139-198.ngrok-free.app/sdapi/v1/txt2img";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PromptRequest> requestEntity = new HttpEntity<>(promptRequest, headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(stableDiffusionApiUrl, HttpMethod.POST, requestEntity, byte[].class);

        if (response.getBody() == null) {
            return ResponseEntity.status(500).body(null);
        }

        ByteArrayResource resource = new ByteArrayResource(response.getBody());

        return ResponseEntity.ok().body(resource);
    }
}

class PromptRequest {
    private String prompt;

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
