package digital.coin.predict.controller;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final StockService stockService;

    @PostMapping("/view")
    public String view(@RequestParam String stock, Model model) {

//        Stock result = stockService.findByName(stock);

//        String path = result.getPath();

//        model.addAttribute("imagePath", path);

        return "view";
    }

}