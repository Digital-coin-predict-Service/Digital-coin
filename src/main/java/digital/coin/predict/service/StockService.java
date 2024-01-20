package digital.coin.predict.service;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Optional<Stock> findByName(String name) {
        return stockRepository.findByName(name);
    }

    public Optional<Stock> findById(Long id) {
        return stockRepository.findById(id);
    }

    public Long join(Stock stock) {
        stockRepository.save(stock);
        return stock.getId();
    }

    public void updateStock(Long id, String name, String code) {
        Optional<Stock> result = stockRepository.findById(id);

        if (result.isEmpty())
            return;

        Stock stock = result.get();

        if (name == null) {
            stock.setCode(code);
        } else if (code == null) {
            stock.setName(name);
        } else {
            stock.setName(name);
            stock.setCode(code);
        }

        stockRepository.save(stock);
    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }

}
