package digital.coin.predict.dto;

import digital.coin.predict.domain.Role;
import lombok.Getter;

@Getter
public class UserRequestDto {
    private String email;
    private Role role;
}
