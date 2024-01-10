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
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findOne(String name) {
        return userRepository.findByUserName(name);
    }

//    public User getUser(OAuth2User oAuth2User){
//        return userRepository.findByEmail(oAuth2User.getAttribute("email")).orElseThrow();
//    }

//    public User updateUser(String email, UserRequestDto userRequestDto) {
//        Optional<User> user = userRepository.findByEmail(email);
//
//        if (user.isEmpty())
//            return null;
//
//        User getUser = user.get();
//        getUser.updateUser(email, userRequestDto.getRole());
//
//        userRepository.save(getUser);
//
//        return getUser;
//    }

    public boolean deleteUser(String name) {
        userRepository.deleteUserByUserName(name);

        return true;
    }

    public void joinUser(String name) {
        User newUser = new User(name);
        userRepository.save(newUser);
    }

    public Boolean userExists(String name) {
        return userRepository.existsByUserName(name);
    }
}
