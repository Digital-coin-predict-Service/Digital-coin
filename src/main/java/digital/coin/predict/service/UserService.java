package digital.coin.predict.service;

import digital.coin.predict.domain.User;
import digital.coin.predict.dto.UserRequestDto;
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


    public User updateUser(String email, UserRequestDto userRequestDto) {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            return null;
        }

        User updateUser = user.get().updateUser(email, userRequestDto.getRole());
        userRepository.save(updateUser);

        return updateUser;
    }

    public boolean deleteUser(String email) {
        Optional<User> rUser = userRepository.findByEmail(email);

        if (rUser.isEmpty()) {
            return true;
        }

        userRepository.deleteUserByEmail(email);
        return true;
    }
}
