package digital.coin.predict.service;

import digital.coin.predict.domain.CoinPredict;
import digital.coin.predict.repository.CoinPredictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoinPredictService {
    @Autowired
    private CoinPredictRepository coinPredictRepository;

    public CoinPredict findByCoinId(Long coinId) {
        Optional<CoinPredict> result = coinPredictRepository.findFirstByCoinIdOrderByPredictAtDesc(coinId);

        if(result.isEmpty())
            return null;

        return result.get();
    }
}
