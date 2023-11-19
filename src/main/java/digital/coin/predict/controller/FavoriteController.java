package digital.coin.predict.controller;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import digital.coin.predict.dto.FavoriteRequestDto;
import digital.coin.predict.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<Void> addFavorite(@AuthenticationPrincipal OAuth2User oAuth2User,
                                            @RequestBody FavoriteRequestDto favoriteRequestDto) {
        User user = favoriteService.getUser(oAuth2User);
        if (!favoriteService.addFavorite(user, favoriteRequestDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<List<Favorite>> findAllByStockId(@PathVariable Long id) {
//        List<Favorite> favorites = favoriteService.findAllByStockId(id);
//
//        return ResponseEntity.ok(favorites);
//    }

//    @GetMapping("/list")
//    public ResponseEntity<List<Favorite>> findAllByUserId(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        User user = favoriteService.getUser(oAuth2User);
//
//        List<Favorite> favorites = favoriteService.findAllByUserId(user);
//
//        return ResponseEntity.ok(favorites);
//    }


}
