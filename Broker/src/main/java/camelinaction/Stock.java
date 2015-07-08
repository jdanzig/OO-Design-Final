package camelinaction;

import java.util.Vector;
import java.util.Collections;
import java.util.Iterator;

public class Stock implements Statistics {
	
	private String name = "";
	Vector<Double> bidPrice = new Vector<Double>();
	Vector<Integer> bidQuantity = new Vector<Integer>();
	Vector<Double> askPrice = new Vector<Double>();
	Vector<Integer> askQuantity = new Vector<Integer>();
	Vector<String> timeStamp = new Vector<String>();
	
    public Stock(String stockName){
        this.name = stockName;
    }
    
    public void addQuote(double bp, int bq, double ap, int aq, String ts) {
    	bidPrice.addElement(bp);
    	bidQuantity.addElement(bq);
    	askPrice.addElement(ap);
    	askQuantity.addElement(aq);
    	timeStamp.addElement(ts);
    }
    
    public int quotesSoFar() {
    	return timeStamp.size();
    }

    public String getName() {
    	return name;
    }
    
    @Override
	public double getMin(String s) {
    	return s == "bp" ? (double)Collections.min(bidPrice) : (double)Collections.min(askPrice);
	}
    
    @Override
    public double getMax(String s) {
    	return s == "bp" ? (double)Collections.max(bidPrice) : (double)Collections.max(askPrice);
    }
    
    @Override
    public double getAvg(String s) {
    	Iterator i;
    	if(s == "bp") {
    		i = bidPrice.iterator();
    	} else {
    		i = askPrice.iterator();
    	}
    	double total = 0;
    	while(i.hasNext()) {
    		total += (double)i.next();
    	}
    	return total / bidPrice.size();
    }
    
    @Override
    public double getVariance(String s) {
    	Iterator i;
    	if(s == "bp") {
    		i = bidPrice.iterator();
    	} else {
    		i = askPrice.iterator();
    	}
        double avg = getAvg(s);
        double total = 0;
        double temp = 0;
        while(i.hasNext()) {
        	temp = (double)i.next();
        	total += (avg - temp)*(avg - temp);
        }
        return total / bidPrice.size();
    }
    
    @Override
    public double getStdDev(String s) {
    	return s == "bp" ? Math.sqrt(getVariance("bp")) : Math.sqrt(getVariance("ap"));
    }
}
