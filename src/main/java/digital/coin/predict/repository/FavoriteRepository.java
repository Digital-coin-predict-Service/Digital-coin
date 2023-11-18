package digital.coin.predict.repository;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
//    List<Favorite> findAllByEmail(String email);
}
