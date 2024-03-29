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
            userResponseDto = new UserResponseDto(user.getUserName(), user.getCreate_at(),
                    user.getUpdate_at());

            userResponseDtoList.add(userResponseDto);
        }

        return ResponseEntity.ok(userResponseDtoList);
    }

    @GetMapping
    public ResponseEntity<UserResponseDto> findUser(@RequestParam(value = "p") String email) {
        Optional<User> result = userService.findOne(email);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        User user = result.get();

        return ResponseEntity.ok(new UserResponseDto(user.getUserName(), user.getCreate_at(),
                user.getUpdate_at()));
    }

//    @PostMapping("/update")
//    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody UserRequestDto userRequestDto) {
//        User user = userService.getUser(oAuth2User);
//
//        User updateUser = userService.updateUser(user.getEmail(), userRequestDto);
//
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam(value = "p") String email) {
        userService.deleteUser(email);

        return ResponseEntity.ok().build();
    }
}
