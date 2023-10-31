package digital.coin.predict.controller;

import digital.coin.predict.domain.User;
import digital.coin.predict.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> addFavorite(@AuthenticationPrincipal OAuth2User oAuth2User, @PathVariable Long id){
        User user = favoriteService.getUser(oAuth2User);
        if(favoriteService.addFavorite(user,id)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
