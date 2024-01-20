package digital.coin.predict.service;

import digital.coin.predict.domain.PredictValues;
import digital.coin.predict.repository.PredictValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PredictValuesService {
    @Autowired
    private PredictValuesRepository predictValuesRepository;

    public List<PredictValues> findAllByCoinPredictId(Long coinPredictId) {
        List<PredictValues> result = predictValuesRepository.findAllByCoinPredictIdOrderByStepAsc(coinPredictId);

        if (result.isEmpty())
            return null;

        return result;
    }
}
