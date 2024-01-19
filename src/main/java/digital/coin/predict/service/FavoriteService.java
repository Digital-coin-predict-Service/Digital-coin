package digital.coin.predict.service;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import digital.coin.predict.repository.FavoriteRepository;
import digital.coin.predict.repository.StockRepository;
import digital.coin.predict.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final FavoriteRepository favoriteRepository;

    public Boolean addFavorite(String userName, String stockName){
        Optional<User> userResult = userRepository.findByUserName(userName);
        Optional<Stock> stockResult = stockRepository.findByName(stockName);

        if(stockResult.isEmpty() || userResult.isEmpty())
            return false;

        Stock stock = stockResult.get();
        User user = userResult.get();

        Favorite favorite = Favorite.builder().stock(stock).user(user).build();
        favoriteRepository.save(favorite);

        return true;
    }

    //입력받은 모든 id에 대한 검색
    public List<Favorite> findAllByStockId(Long stockId) {
        Optional<Stock> result = stockRepository.findById(stockId);

        if (result.isEmpty())
            return null;

        return favoriteRepository.findAllByStock(result.get());
    }

    public List<Favorite> findAllByUserName(String name) {
        Optional<User> result = userRepository.findByUserName(name);

        if (result.isEmpty())
            return null;

        return favoriteRepository.findAllByUser(result.get());
    }

    public boolean deleteFavorite(String userName, String stockName) {
        Optional<User> userResult = userRepository.findByUserName(userName);
        Optional<Stock> stockResult = stockRepository.findByName(stockName);

        if (userResult.isEmpty() || stockResult.isEmpty())
            return false;

        return favoriteRepository.deleteByUserAndStock(userResult.get(), stockResult.get());
    }

//    public Boolean deleteFavorite(User user, Long id) {
//        Optional<Stock> result = stockRepository.findById(id);
//
//        if (result.isEmpty())
//            return false;
//
//        favoriteRepository.deleteFavoriteByUserAndStock(user, result.get());
//        return true;
//    }
//
//    public Boolean deleteFavoriteByUser(User user) {
//        favoriteRepository.deleteFavoritesByUser(user);
//        return true;
//    }

}