package digital.coin.predict.repository;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite,Long> {
//    @Query("SELECT f FROM Favorite f WHERE f.stock_id = :stock")
//    List<Favorite> findAllByStock_id(@Param("stock") Stock stock);

//    @Query("SELECT f FROM Favorite f WHERE f.user_id = :user")
//    List<Favorite> findAllByUserId(@Param("user") User user);
}
