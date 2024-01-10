package digital.coin.predict.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    public User updateUser(String email) {
        this.userName = userName;
        return this;
    }

    public User(String userName) {
        this.userName = userName;
    }
}
