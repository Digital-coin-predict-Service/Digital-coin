package digital.coin.predict.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import digital.coin.predict.domain.CoinPredict;
import digital.coin.predict.domain.PredictValues;
import digital.coin.predict.domain.Stock;
import digital.coin.predict.dto.StockRequestDto;
import digital.coin.predict.dto.StockResponseDto;
import digital.coin.predict.service.CoinPredictService;
import digital.coin.predict.service.PredictValuesService;
import digital.coin.predict.service.SessionService;
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
    private final SessionService sessionService;
    private final CoinPredictService coinPredictService;
    private final PredictValuesService predictValuesService;

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
        Stock stock = new Stock(stockRequestDto.getName(), stockRequestDto.getCode());

        stockService.join(stock);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStock(@PathVariable Long id, @RequestBody StockRequestDto stockRequestDto) {
        stockService.updateStock(id, stockRequestDto.getName(), stockRequestDto.getCode());

        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> deleteStock(@RequestParam(value = "v") Long id) {
//        stockService.deleteStock(id);
//
//        return ResponseEntity.ok().build();
//    }

    @GetMapping("/new/{stockName}/{stockCode}")
    public void addView(@PathVariable String stockName, @PathVariable String stockCode) {
        Stock stock = new Stock(stockName, stockCode);

        stockService.join(stock);
    }

    @PostMapping("/new")
    public String addStock(@ModelAttribute("stockRequestDto") StockRequestDto stockRequestDto) {
        System.out.println(stockRequestDto.getName());
        Stock stock = new Stock(stockRequestDto.getName(), stockRequestDto.getCode());

        stockService.join(stock);

        return "redirect:/";
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<?>> getStockPrice(@PathVariable String name, HttpServletRequest request) {
        List<Double> stockPrices = new ArrayList<>();

        HttpSession httpSession = request.getSession(false);

        Optional<Stock> stockResult = stockService.findByName(name);
        Stock stock;

        CoinPredict coinPredict;
        List<PredictValues> predictValues;

        if (stockResult.isEmpty())
            return ResponseEntity.internalServerError().build();
        else
            stock = stockResult.get();

        coinPredict = coinPredictService.findByCoinId(stock.getId());
        predictValues = predictValuesService.findAllByCoinPredictId(coinPredict.getCoinId());

        if (httpSession == null) {
            for (int i = 0; i < predictValues.size() - 5; i++) {
                stockPrices.add(predictValues.get(i).getValue());
            }

            return ResponseEntity.ok(stockPrices);
        }

        for (PredictValues values :
                predictValues) {
            stockPrices.add(values.getValue());
            System.out.println(values.getValue());
        }

        return ResponseEntity.ok(stockPrices);

    }
}
