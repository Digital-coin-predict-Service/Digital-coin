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

import java.util.Optional;

@Service
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

        if(optionalStock.isEmpty())
            return false;

        Stock stock = optionalStock.get();
        Favorite favorite = Favorite.builder().stock_id(stock).user_id(user).build();
        favoriteRepository.save(favorite);

        return true;
    }

}
