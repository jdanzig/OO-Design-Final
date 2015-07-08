package camelinaction;

import java.util.HashMap;

public class StockList {
	
	HashMap<String, Stock> stockList = new HashMap<String, Stock>();
	
	private static StockList instance = null;
	protected StockList() {	}
	
    public static StockList getInstance(){
        if (instance == null){
        	instance = new StockList();
        }
        return instance;
    }
    
    public Stock getStock(String key){
    	// Pseudo-singleton
    	if(stockList.get(key) == null) {
    		stockList.put(key,new Stock(key));
    	}
    	return stockList.get(key);
    }

}
