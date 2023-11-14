package digital.coin.predict.repository;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    //Does it work well?
    List<Favorite> findAllByEmail(String email);
    Optional<Favorite> findByUser_idAndId(String email, Long favoriteId);
}
