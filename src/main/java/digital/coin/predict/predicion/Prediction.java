package digital.coin.predict.predicion;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Prediction {
    @Scheduled(cron = "0 0 0 * * ?", zone = "Asia/Seoul")
    public void executePrediction() {
        WebClient webClient = WebClient.builder().build();
        String url = "http://127.0.0.1:5000/prediction/BTC";

        webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
    }
}
