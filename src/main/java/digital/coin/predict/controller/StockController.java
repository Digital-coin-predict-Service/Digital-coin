package digital.coin.predict.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.dto.StockRequestDto;
import digital.coin.predict.dto.StockResponseDto;
import digital.coin.predict.service.StockService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/stock")
public class StockController {
    private final StockService stockService;

    @GetMapping("/all")
    public ResponseEntity<List<StockResponseDto>> findAll() {
        List<Stock> stocks = stockService.findAll();

        if (stocks == null)
            return ResponseEntity.notFound().build();

        StockResponseDto stockResponseDto;

        List<StockResponseDto> stockResponseDtoList = new ArrayList<>(stocks.size());

        for (Stock stock : stocks) {
            stockResponseDto = new StockResponseDto(stock.getId(), stock.getName(),
                    stock.getCreate_at(), stock.getUpdate_at());

            stockResponseDtoList.add(stockResponseDto);
        }

        return ResponseEntity.ok(stockResponseDtoList);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<StockResponseDto> findOne(@PathVariable Long id) {
//        Optional<Stock> result = stockService.findById(id);
//
//        if (result.isEmpty())
//            return ResponseEntity.notFound().build();
//
//        Stock stock = result.get();
//
//        StockResponseDto stockResponseDto = new StockResponseDto(stock.getId(), stock.getName(),
//                stock.getCreate_at(), stock.getUpdate_at());
//
//        return ResponseEntity.ok(stockResponseDto);
//    }

    @PostMapping("/search")
    public ResponseEntity<StockResponseDto> findByName(@RequestBody StockRequestDto stockRequestDto) {
        Optional<Stock> result = stockService.findByName(stockRequestDto.getName());

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        Stock stock = result.get();

        StockResponseDto stockResponseDto = new StockResponseDto();

        stockResponseDto.setName(stock.getName());
        stockResponseDto.setId(stock.getId());

        return ResponseEntity.ok(stockResponseDto);
    }

    @PostMapping("/join")
    public ResponseEntity<Void> join(@RequestBody StockRequestDto stockRequestDto) {
        Stock stock = new Stock(stockRequestDto.getName());

        stockService.join(stock);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestBody StockRequestDto stockRequestDto) {
        stockService.updateStock(id, stockRequestDto.getName(), stockRequestDto.getPath());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStock(@RequestParam(value = "v") Long id) {
        stockService.deleteStock(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/new")
    public String addView(@ModelAttribute("stockRequestDto") StockRequestDto stockRequestDto) {
        System.out.println(123);
        return "stockJoin";
    }

    @PostMapping("/new")
    public String addStock(@ModelAttribute("stockRequestDto") StockRequestDto stockRequestDto) {
        System.out.println(stockRequestDto.getName());
        Stock stock = new Stock(stockRequestDto.getName());

        stockService.join(stock);

        return "redirect:/";
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<?>> getStockPrice(@PathVariable String name, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Double> stockPrices = new ArrayList<>();

        HttpSession httpSession = request.getSession(false);
        System.out.println(httpSession);

        if (httpSession == null) {
            try {
                JsonNode jsonNode = objectMapper.readTree(new File("E:\\Digital coin\\src\\main\\resources\\static\\" + name + ".json"));

                for (int i = 0; i < jsonNode.size() - 2; i++) {
                    stockPrices.add(jsonNode.get(i).asDouble());
                }
                System.out.println(stockPrices);

                return ResponseEntity.ok(stockPrices);
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.notFound().build();
            }
        }

        try {
            JsonNode jsonNode = objectMapper.readTree(new File("E:\\Digital coin\\src\\main\\resources\\static\\" + name + ".json"));

            for (JsonNode price : jsonNode) {
                stockPrices.add(price.asDouble());
            }
            System.out.println(stockPrices);

            return ResponseEntity.ok(stockPrices);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
