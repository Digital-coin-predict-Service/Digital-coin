package digital.coin.predict.controller;

import digital.coin.predict.domain.Role;
import digital.coin.predict.domain.User;
import digital.coin.predict.dto.UserRequestDto;
import digital.coin.predict.dto.UserResponseDto;
import digital.coin.predict.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> findUsers() {
        List<User> users = userService.findUsers();

        if (users == null)
            return ResponseEntity.notFound().build();

        UserResponseDto userResponseDto;

        //model mapper로 교체
        List<UserResponseDto> userResponseDtoList = new ArrayList<>(users.size());

        for (User user : users) {
            userResponseDto = new UserResponseDto(user.getEmail(), user.getCreate_at(),
                    user.getUpdate_at(), user.getRole());

            userResponseDtoList.add(userResponseDto);
        }

        return ResponseEntity.ok(userResponseDtoList);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> findUser(@RequestParam(value = "email") String email) {
        Optional<User> result = userService.findOne(email);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        User user = result.get();

        return ResponseEntity.ok(new UserResponseDto(user.getEmail(), user.getCreate_at(),
                user.getUpdate_at(), user.getRole()));
    }

    @PostMapping("/update/{email}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String email, @RequestBody UserRequestDto userRequestDto) {
        User updateUser = userService.updateUser(email, userRequestDto);

        return ResponseEntity.ok(new UserResponseDto(updateUser.getEmail(), updateUser.getCreate_at(), updateUser.getUpdate_at(), updateUser.getRole()));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteUser(UserRequestDto userRequestDto) {
        userService.deleteUser(userRequestDto.getEmail());

        return ResponseEntity.ok().build();
    }
}
