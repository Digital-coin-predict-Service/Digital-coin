package digital.coin.predict.dto;

import digital.coin.predict.domain.Role;
import lombok.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class UserResponseDto {
    private String name;

    private LocalDateTime created_at;

    private LocalDateTime update_at;
}
