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
    @Query("SELECT f FROM Favorite f WHERE f.stock = :stock")
    List<Favorite> findAllByStock(@Param("stock") Stock stock);

    @Query("SELECT f FROM Favorite f WHERE f.user = :user")
    List<Favorite> findAllByUser(@Param("user") User user);

    void deleteByUserAndStock(User user, Stock stock);

    boolean existsByUserAndStock(User user, Stock stock);


//    @Query("DELETE FROM Favorite f WHERE f.user = :user AND f.stock = :stock")
//    Boolean deleteFavoriteByUserAndStock(@Param("user") User user,@Param("stock") Stock stock);
//
//    Boolean deleteFavoritesByUser(User user);
}
