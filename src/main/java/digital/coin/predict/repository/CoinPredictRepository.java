package digital.coin.predict.repository;

import digital.coin.predict.domain.CoinPredict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinPredictRepository extends JpaRepository<CoinPredict, Integer> {
    Optional<CoinPredict> findFirstByCoinIdOrderByPredict_at(Integer coinId);
}
