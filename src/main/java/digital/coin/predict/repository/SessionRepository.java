package digital.coin.predict.repository;

import digital.coin.predict.domain.Session;
import digital.coin.predict.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    boolean existsBySessionIdAndUser(String sessionId, User user);
}
