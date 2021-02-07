package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StockService {
	
	private List<Stock> stockList = new ArrayList<>();
	
	
	public List<Stock> getAllStock(){
		return stockList;
	}
	
	public Stock getStockValues(String stockSearch){
		return null;
	}
	

	public void addStock(Stock stck) {
		stockList.add(stck);
	}

	public Stock findElement(String stockName) {
		
		for (int i=0; i< this.stockList.size(); i++) {
			if(stockList.get(i).getName().contains(stockName)) {
				return stockList.get(i);
			}
		}	
		return null;
	}

	public Stock updateStock(String stockName, String quote) {
		for (int i=0; i< this.stockList.size(); i++) {
			if(stockList.get(i).getName().contains(stockName)) {
				
				stockList.get(i).addQuote(quote);
				return stockList.get(i);
			}
		}	
		
		return null;
	}

	public void deleteStockElement(String stockName) {
		for (int i=0; i< this.stockList.size(); i++) {
			if(stockList.get(i).getName().contains(stockName)) {
				stockList.remove(i);
				break;
			}
		}
	}

	public boolean isStockDefined(String stockName) {

		for (int i=0; i< this.stockList.size(); i++) {
			if(stockList.get(i).getName().contains(stockName)) {
				return true;
			}
		}
		return false;
	}
	

}
