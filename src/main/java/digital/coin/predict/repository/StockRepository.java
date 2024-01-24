package digital.coin.predict.repository;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByName(String stockName);

    Optional<Stock> findByCode(String stockCode);

}
