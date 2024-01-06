package digital.coin.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class StockResponseDto {
    private Long id;
    private String name;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
}
