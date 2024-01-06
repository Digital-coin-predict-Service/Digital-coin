package digital.coin.predict.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Stock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    //json file path
    private String path;

    private int latestPrice;

    private int errorRate;

    public Stock(String name) {
        this.name = name;
        this.path = "E:/Digital coin/src/main/resources/static/" + name + ".json";
    }
}
