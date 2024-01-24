package digital.coin.predict.controller;

import digital.coin.predict.domain.Favorite;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.dto.FavoriteResponseDto;
import digital.coin.predict.dto.StockRequestDto;
import digital.coin.predict.service.FavoriteService;
import digital.coin.predict.service.SessionService;
import digital.coin.predict.service.StockService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final StockService stockService;
    private final SessionService sessionService;
    private final FavoriteService favoriteService;

//    @GetMapping("/")
//    public String home(@ModelAttribute("stockRequestDto") StockRequestDto stockRequestDto) {
//        return "home";
//    }

    @GetMapping("/")
    public String stocksViewer(Model model, HttpServletRequest request, @CookieValue(value = "userName", required = false) String userName) {
        List<Stock> stocks = stockService.findAll();
        List<Long> favoriteStocks = new ArrayList<>();

        HttpSession httpSession = request.getSession(false);

        if (httpSession == null || userName == null) {
            model.addAttribute("login", false);
        } else {
            model.addAttribute("login", true);
            List<Favorite> favorites = favoriteService.findAllByUserName(userName);

            for (Favorite favoriteStock : favorites) {
                stocks.remove(favoriteStock.getStock());
                stocks.add(0, favoriteStock.getStock());
                favoriteStocks.add(favoriteStock.getStock().getId());
            }
        }

        model.addAttribute("favorites", favoriteStocks);
        model.addAttribute("stocks", stocks);

        return "stockView";
    }

}
