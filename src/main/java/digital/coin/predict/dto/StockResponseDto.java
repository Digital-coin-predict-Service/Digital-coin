package digital.coin.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class StockResponseDto {
    private Long id;
    private String name;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
}
