package com.iqmsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SpringBootApplication
public class MainApp {

    List<Stock> stockList = new ArrayList<>();
    List<String> stockNames = Arrays.asList("GOOG, MSFT, AMZ, WAL".split(",")); 
    
	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
	}

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            createRandomStock();
            stockList.forEach(System.out::println);
        };
    }
    
	void createRandomStock() {
        stockNames.forEach(stockName -> {
            stockList.add(new Stock(stockName, generateRandomStockPrice()));
        });
    }
    
    float generateRandomStockPrice() {
        float min = 30;
        float max = 50;
        return min + roundFloat(new Random().nextFloat() * (max - min));
    }
    
    String getRandomUser() {
        String users[] = "test1, test2, test3, test4, test5, test6".split(",");
        return users[new Random().nextInt(users.length)];
    }
    
    Stock getRandomStock() {
        return stockList.get(new Random().nextInt(stockList.size()));
    }
    
    float changePrice(float price) {
        return roundFloat(Math.random() >= 0.5 ? price * 1.05f : price * 0.95f);
    }
	
    float roundFloat(float number) {
        return Math.round(number * 100.0) / 100.0f;
    }
    
}