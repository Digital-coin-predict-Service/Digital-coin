package digital.coin.predict.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class CoinPredict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer coinId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime predict_at;
}
