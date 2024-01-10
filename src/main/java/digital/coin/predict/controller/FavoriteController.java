package digital.coin.predict.controller;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.dto.FavoriteResponseDto;
import digital.coin.predict.service.FavoriteService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

//    @PostMapping("/add")
//    public ResponseEntity<Void> addFavorite(@AuthenticationPrincipal OAuth2User oAuth2User,
//                                            @RequestBody FavoriteRequestDto favoriteRequestDto) {
//        User user = favoriteService.getUser(oAuth2User);
//        if (!favoriteService.addFavorite(user, favoriteRequestDto.getId())) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/list/stock")
    public ResponseEntity<List<FavoriteResponseDto>> findAllByStockId(@RequestParam(value = "s") Long id) {
        List<Favorite> favorites = favoriteService.findAllByStockId(id);

        if (favorites == null)
            return ResponseEntity.notFound().build();

        List<FavoriteResponseDto> favoriteResponseDtos = new ArrayList<>(favorites.size());

        for (Favorite favorite : favorites) {
            favoriteResponseDtos.add(new FavoriteResponseDto(favorite.getId(), favorite.getUser().getUserName(), favorite.getStock().getId(), favorite.getStock().getName()));
        }

        return ResponseEntity.ok(favoriteResponseDtos);
    }

//    @GetMapping("/list/user")
//    public ResponseEntity<List<FavoriteResponseDto>> findAllByUserId(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        User user = favoriteService.getUser(oAuth2User);
//
//        List<Favorite> favorites = favoriteService.findAllByUserId(user.getEmail());
//
//        if (favorites == null)
//            return ResponseEntity.notFound().build();
//
//        List<FavoriteResponseDto> favoriteResponseDtos = new ArrayList<>(favorites.size());
//
//        for (Favorite favorite : favorites) {
//            favoriteResponseDtos.add(new FavoriteResponseDto(favorite.getId(), favorite.getUser().getEmail(),
//                    favorite.getStock().getId(), favorite.getStock().getName()));
//        }
//
//        return ResponseEntity.ok(favoriteResponseDtos);
//    }

//    @DeleteMapping
//    public ResponseEntity<Void> deleteFavorite(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestParam(value = "s") Long favoriteId) {
//        User user = favoriteService.getUser(oAuth2User);
//
//        favoriteService.deleteFavorite(user, favoriteId);
//
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/all")
//    public ResponseEntity<Void> deleteFavoriteByUser(@AuthenticationPrincipal OAuth2User oAuth2User) {
//        User user = favoriteService.getUser(oAuth2User);
//
//        favoriteService.deleteFavoriteByUser(user);
//
//        return ResponseEntity.ok().build();
//    }

}
