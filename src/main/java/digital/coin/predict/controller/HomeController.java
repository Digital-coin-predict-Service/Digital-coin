package digital.coin.predict.controller;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.dto.StockRequestDto;
import digital.coin.predict.service.StockService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final StockService stockService;

//    @GetMapping("/")
//    public String home(@ModelAttribute("stockRequestDto") StockRequestDto stockRequestDto) {
//        return "home";
//    }

    @GetMapping("/")
    public String stocksViewer(Model model, HttpServletRequest request, @CookieValue(value = "userName", required = false) String userName) {
        List<Stock> stocks = stockService.findAll();

        model.addAttribute("stocks", stocks);

        HttpSession httpSession = request.getSession(false);

        if (httpSession == null || userName == null) {
            model.addAttribute("login", false);
        } else {
            model.addAttribute("login", true);
        }

        return "stockView";
    }

}
