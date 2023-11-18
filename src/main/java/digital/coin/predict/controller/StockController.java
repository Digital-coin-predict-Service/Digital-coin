package digital.coin.predict.controller;

import digital.coin.predict.domain.Stock;
import digital.coin.predict.dto.StockRequestDto;
import digital.coin.predict.dto.StockResponseDto;
import digital.coin.predict.service.StockService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
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
            stockResponseDto = new StockResponseDto(stock.getId(), stock.getName(), stock.getPath(),
                    stock.getCreate_at(), stock.getUpdate_at());

            stockResponseDtoList.add(stockResponseDto);
        }

        return ResponseEntity.ok(stockResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockResponseDto> findOne(@PathVariable Long id) {
        Optional<Stock> result = stockService.findById(id);

        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        Stock stock = result.get();

        StockResponseDto stockResponseDto = new StockResponseDto(stock.getId(), stock.getName(),
                stock.getPath(), stock.getCreate_at(), stock.getUpdate_at());

        return ResponseEntity.ok(stockResponseDto);
    }

    @PostMapping("/search")
    public ResponseEntity<StockResponseDto> findByName(@RequestBody StockRequestDto stockRequestDto) {
        Optional<Stock> result = stockService.findByName(stockRequestDto.getName());

        if(result.isEmpty())
            return ResponseEntity.notFound().build();

        Stock stock = result.get();

        StockResponseDto stockResponseDto = new StockResponseDto(stock.getId(), stock.getName(), stock.getPath(),
                stock.getCreate_at(), stock.getUpdate_at());

        return ResponseEntity.ok(stockResponseDto);
    }

    @PostMapping("/join")
    public Stock join(@RequestBody StockRequestDto stockRequestDto) {
        Stock stock = new Stock(stockRequestDto.getName());
        stockService.join(stock);
        return stock;
    }
}
