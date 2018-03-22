package com.iqmsoft;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@Service
class StockTransactionService {
    /**
	 * 
	 */
	private final MainApp mainApp;

	/**
	 * @param mainApp
	 */
	StockTransactionService(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	Flux<StockTransaction> getStockTransactions() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        interval.subscribe((i) -> this.mainApp.stockList.forEach(stock -> stock.setPrice(this.mainApp.changePrice(stock.getPrice()))));
        
        Flux<StockTransaction> stockTransactionFlux = Flux.fromStream(Stream.generate(() -> new StockTransaction(this.mainApp.getRandomUser(), this.mainApp.getRandomStock(), new Date())));
        return Flux.zip(interval, stockTransactionFlux).map(Tuple2::getT2);
    }
}