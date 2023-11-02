package digital.coin.predict.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Id
    @Column(name = "user_email")
    private String email;

    private String provider;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @Enumerated
    private Role role;

    public User updateUser(String email, Role role) {
        this.email = email;
        this.role = role;
        return this;
    }
}
