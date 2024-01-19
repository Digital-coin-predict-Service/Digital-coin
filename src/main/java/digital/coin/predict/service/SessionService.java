package digital.coin.predict.service;

import digital.coin.predict.domain.Session;
import digital.coin.predict.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public boolean validSession(String sessionId) {
        return sessionRepository.existsBySessionId(sessionId);
    }

    public void deleteSession(String sessionId) {
        if (!validSession(sessionId))
            return;

        sessionRepository.deleteById(sessionId);
    }

    public void saveSession(String sessionId) {
        if (validSession(sessionId))
            return;

        Session newSession = new Session(sessionId);
        sessionRepository.save(newSession);
    }
}
