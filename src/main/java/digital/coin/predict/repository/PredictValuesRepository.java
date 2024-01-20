package digital.coin.predict.repository;

import digital.coin.predict.domain.PredictValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictValuesRepository extends JpaRepository<PredictValues, Long> {
    List<PredictValues> findAllByCoinPredictIdOrderByStepAsc(Long coinPredictId);
}
