package digital.coin.predict.repository;

import digital.coin.predict.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
    //Does it work well?
    List<Favorite> findAllByEmail(String email);
}
