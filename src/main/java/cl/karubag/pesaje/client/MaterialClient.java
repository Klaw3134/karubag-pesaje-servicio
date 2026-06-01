package cl.karubag.pesaje.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Component
public class MaterialClient {

    private final WebClient webClient;

    public MaterialClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8083")
                .build();
    }

    public Double obtenerPrecioPorKilo(Long materialId) {
        try {
            Map response = webClient.get()
                    .uri("/api/materiales/" + materialId)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
            if (response != null && response.containsKey("precioPorKilo")) {
                return ((Number) response.get("precioPorKilo")).doubleValue();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existeMaterial(Long materialId) {
        try {
            webClient.get()
                    .uri("/api/materiales/" + materialId)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}