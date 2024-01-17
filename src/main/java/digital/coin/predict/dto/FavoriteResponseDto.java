package digital.coin.predict.dto;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import lombok.*;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class FavoriteResponseDto {
    private Long favoriteId;

    private Long userId;
    private String userName;

    private Long stockId;
    private String stockName;
}
