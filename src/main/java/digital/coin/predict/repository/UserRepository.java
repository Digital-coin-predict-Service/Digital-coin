package digital.coin.predict.repository;

import digital.coin.predict.domain.User;
import digital.coin.predict.dto.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    void deleteUserByEmail(String email);
}
