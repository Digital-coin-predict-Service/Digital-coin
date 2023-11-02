package digital.coin.predict.service;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Optional<Stock> findByName(String name) {
        return stockRepository.findByName(name);
    }

    public Long join(Stock stock) {
        stockRepository.save(stock);
        return stock.getId();
    }

}
