package digital.coin.predict.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseEntity{
    @Id
    private String sessionId;

    private Long userId;
}
