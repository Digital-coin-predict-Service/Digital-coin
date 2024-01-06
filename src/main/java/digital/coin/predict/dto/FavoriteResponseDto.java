package digital.coin.predict.dto;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class FavoriteResponseDto {
    private Long favorite_id;
    private String email;
    private Long stock_id;
    private String stock_name;
}
