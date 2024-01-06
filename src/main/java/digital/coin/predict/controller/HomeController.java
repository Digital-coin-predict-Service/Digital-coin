package digital.coin.predict.controller;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final StockService stockService;
    @GetMapping("/")
    public String homeViewer(Model model){
        List<Stock> stocks = stockService.findAll();

        model.addAttribute("stocks", stocks);

        return "home";
    }

//    @GetMapping("/new")
//    public String addView() {
//        System.out.println(123);
//        return "save";
//    }

}
