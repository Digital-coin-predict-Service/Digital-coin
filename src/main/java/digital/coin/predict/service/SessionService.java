package digital.coin.predict.service;

import digital.coin.predict.domain.Session;
import digital.coin.predict.domain.User;
import digital.coin.predict.repository.SessionRepository;
import digital.coin.predict.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public boolean validSession(String sessionId, String userName) {
        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isEmpty())
            return false;

        return sessionRepository.existsBySessionIdAndUserId(sessionId, user.get().getId());
    }

    public void deleteSession(String sessionId, String userName) {
        if (!validSession(sessionId, userName))
            return;

        sessionRepository.deleteById(sessionId);
    }

    public void saveSession(String sessionId, String userName) {
        if (validSession(sessionId, userName))
            return;

        Optional<User> user = userRepository.findByUserName(userName);

        if (user.isEmpty())
            return;

        Session newSession = new Session(sessionId, user.get().getId());

        sessionRepository.save(newSession);
    }
}
