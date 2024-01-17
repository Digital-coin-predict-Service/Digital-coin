package digital.coin.predict.dto;

import lombok.Getter;

@Getter
public class FavoriteRequestDto {
    private Long userId;
    private Long stockId;
    private String userName;
    private String stockName;
}
