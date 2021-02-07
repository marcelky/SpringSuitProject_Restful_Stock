package com.example.restservice;

import java.util.ArrayList;

public class Stock {

	private ArrayList<String> quotesList = new ArrayList<String>();	
	private String name;
	
	
	public Stock(String stkName) { 
		name = stkName;
	}
	
	public void addQuote(String quote) {
		quotesList.add(quote);
	}
	
	
	
	public void setName(String stck) {
		name = stck;
	}
	
	
	public String getName() {
		return name;
	}

	
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("{name:");
    	sb.append(name);
    	sb.append("\"quotes\":");
    	
    	sb.append("[");
    	for (int i=0; i< this.quotesList.size(); i++) {
    		sb.append(quotesList.get(i));
    		
    		if (i != quotesList.size()-1) {
    			sb.append(",");
    		}	
    	}
    	sb.append("]");
    	
    	return sb.toString();
    }
    

	public ArrayList<String> getQuotes() {	
		return quotesList;
	}


}
