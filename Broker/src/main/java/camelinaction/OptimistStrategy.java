package camelinaction;

public class OptimistStrategy implements Strategy {
	public boolean goodStrategy(Stock s) {
		Announcer a = new Announcer();

		if(s.getAvg("ap") > (double)s.askPrice.lastElement()) {
			a.encouragingMessage("Buy now while the ask price is below historic rates. Keep up the good work.");
			a.recommendTrade((double)s.bidPrice.lastElement(),(double)s.askPrice.lastElement(),true);
			return true;
		} else {
			a.encouragingMessage("Don't risk it.");
			a.recommendTrade((double)s.bidPrice.lastElement(),(double)s.askPrice.lastElement(),false);	
			return false;
		}
		
	}
}
