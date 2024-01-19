package digital.coin.predict.controller;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.domain.User;
import digital.coin.predict.dto.FavoriteResponseDto;
import digital.coin.predict.dto.StockRequestDto;
import digital.coin.predict.service.FavoriteService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
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

    @PostMapping("/{stockName}")
    public void addFavorite(@CookieValue(value = "userName") String userName, @PathVariable String stockName) {
        favoriteService.addFavorite(userName, stockName);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FavoriteResponseDto>> findAllByUserName(@CookieValue(value = "userName") String userName) {
        List<Favorite> result = favoriteService.findAllByUserName(userName);

        List<FavoriteResponseDto> favoriteResponseDtos = new ArrayList<>(result.size());

        for (Favorite favorite :
                result) {
            favoriteResponseDtos.add(new FavoriteResponseDto(favorite.getId(), favorite.getUser().getId(),
                    favorite.getUser().getUserName(), favorite.getStock().getId(), favorite.getStock().getName()));
        }

        return ResponseEntity.ok(favoriteResponseDtos);
    }

    @DeleteMapping("/{stockName}")
    public void deleteFavorite(@CookieValue(value = "userName") String userName, @PathVariable String stockName) {
        favoriteService.deleteFavorite(userName, stockName);
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
