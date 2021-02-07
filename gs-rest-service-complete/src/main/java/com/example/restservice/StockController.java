package com.example.restservice;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

	@Autowired 
	StockService  stockService = new StockService();
	
	

	
	/**
	 * createStock()
	 * URL: http://<host>:<port>/stock HTTP Method: POST
	 * Request Payload, Response Payload:
	 * {
	 *"name": "PETR4",
	 *"quotes": [
	 *15.5,
	 *15.1,
	 *10.5,
	 *18.6
	 *]
	 *}
	 */
	
	@RequestMapping(method = RequestMethod.POST, value="/stock")
	public String createStock(@RequestBody String json ) {
		
	
		String jsonClean = json.replace("\n","").replace("\r","").replace(" ","");
		
		String[] words = null;
		String[] quotesPart = null;
		String[] namePart = null;
		
		
		//extract name of stock
		namePart = jsonClean.split("\"");
		String name = namePart[3];
		
		Stock newStock = new Stock(name);
		
		if (stockService.isStockDefined(name)) {
			return "duplicate";
		}else {
		
		
		//quotes defined
		if (jsonClean.contains("quotes")){
			words = jsonClean.split("quotes");
			quotesPart = words[1].split("\\[|,|\\]");				
			for (int i=1; i < quotesPart.length -1; i++) {
				newStock.addQuote(quotesPart[i]);
			}
		}	
		stockService.addStock(newStock);	

		return name;
		}
	}
	

	
	
	/**
	 * updateStock()
	 * URL: http://<host>:<port>/stock/<stock_name> HTTP Method: PATCH
	 * Payload:
	 * {
	 * 		"quotes": [
	 * 			9.6
	 *        ]
	 * }
	 *  
	 */
	@RequestMapping(method = RequestMethod.PATCH, value="/stock/{stockName}")
	public Stock updateStock(@PathVariable String stockName, @RequestBody String json) {
		
		String jsonClean = json.replace("\n","").replace("\r","").replace(" ","");
		StringBuilder sb = new StringBuilder(jsonClean);
		
		sb.delete(0, sb.indexOf("[")+1);
		sb.delete(sb.indexOf("]"), sb.length());
		
		return stockService.updateStock(stockName, sb.toString());
	}
	
	
	/**
	 * readAllStock() 
	 * Read all Stock
	 * URL: http://<host>:<port>/stock HTTP Method: GET
	 * 
	 */ 
	@RequestMapping(method = RequestMethod.GET, value="/stock")
	public List<Stock> readAllStock() {	
		return stockService.getAllStock();
	}
	
	 /**
	 * readOneElement()
	 * Read stock by name
	 * URL: http://<host>:<port>/stock?name=<stock_name> HTTP Method: GET
	 */
	@RequestMapping(method = RequestMethod.GET, value="/stock", params = "name")
	public Stock readOneElement(@RequestParam("name") String name) {
		return stockService.findElement(name);
	}
	

		
	/**
	 * deleteStock()
	 * URL: http://<host>:<port>/stock/<stock_name> HTTP Method: DELETE
	 */
	@RequestMapping(method = RequestMethod.DELETE, value="/stock/{stockName}")
	public @ResponseBody void deleteStock(@PathVariable String stockName) {
		
		stockService.deleteStockElement(stockName);
	}
	
	
}
