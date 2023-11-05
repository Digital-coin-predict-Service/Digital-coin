package digital.coin.predict.service;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.User;
import digital.coin.predict.repository.FavoriteRepository;
import digital.coin.predict.repository.StockRepository;
import digital.coin.predict.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String email) {
        return userRepository.findByEmail(email);
    }

    public void deleteUser(String email) {
        Optional<User> rUser = userRepository.findByEmail(email);

        if (rUser.isPresent()) {
            userRepository.deleteUserByEmail(email);
        }
    }
}
