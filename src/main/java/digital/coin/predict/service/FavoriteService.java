package digital.coin.predict.service;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import digital.coin.predict.repository.FavoriteRepository;
import digital.coin.predict.repository.StockRepository;
import digital.coin.predict.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class FavoriteService {
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final FavoriteRepository favoriteRepository;

    public User getUser(OAuth2User oAuth2User){
        return userRepository.findByEmail(oAuth2User.getAttribute("email")).orElseThrow();
    }

    public Boolean addFavorite(User user, Long id){
        Optional<Stock> optionalStock = stockRepository.findById(id);

        if(optionalStock.isEmpty()) {
            return false;
        }

        Stock stock = optionalStock.get();
        Favorite favorite = Favorite.builder().stock_id(stock).user_id(user).build();
        favoriteRepository.save(favorite);

        return true;
    }


    //입력받은 모든 id에 대한 검색
//    public List<Favorite> findAllByStockId(Long stockId) {
//        Optional<Stock> result = stockRepository.findById(stockId);
//
//        if (result.isEmpty())
//            return null;
//
//        return favoriteRepository.findAllByStock_id(result.get());
//    }

//    public List<Favorite> findAllByUserId(User user) {
//        return favoriteRepository.findAllByUserId(user);
//    }

//    public Boolean deleteFavorite(User user, Long id) {
//        Optional<Stock> result = stockRepository.findById(id);
//
//        if (result.isEmpty())
//            return false;
//
//        Stock stock = result.get();
//
//    }

}
