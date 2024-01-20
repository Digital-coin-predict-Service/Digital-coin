package digital.coin.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class StockRequestDto {
    private Long id;
    private String name;
    private String code;
}
