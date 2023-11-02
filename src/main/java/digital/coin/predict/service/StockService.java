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
@Transactional(readOnly = true)
public class StockService {

    @Autowired
    StockRepository stockRepository;

    public Optional<Stock> findByName(String name) {
        return stockRepository.findByName(name);
    }

    @Transactional
    public void updateStock(Long id, String name, String path) {
        Optional<Stock> stock = stockRepository.findById(id);
    }

    public List<Stock> findStocks() {
        return stockRepository.findAll();
    }

//    public Optional<Stock> findOne(Long id) {
//        return stockRepository.findOne();
//    }//

}
