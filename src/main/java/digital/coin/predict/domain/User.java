package digital.coin.predict.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{
    @Id
    @Column(name = "email")
    private String email;

    private String provider;

    @Enumerated
    private Role role;

    public User updateUser(String email,  Role role){
        this.email = email;
        this.role = role;
        return this;
    }
}
