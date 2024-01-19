package digital.coin.predict.repository;

import digital.coin.predict.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {
    boolean existsBySessionId(String sessionId);
}
