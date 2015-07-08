package camelinaction;

public class HateMSFTStrategy implements Strategy {
	public boolean goodStrategy(Stock s) {
		Announcer a = new Announcer();
		
		if(s.getName() == "MSFT") {
			a.encouragingMessage("Microsoft? That's a bad trade.");
			a.recommendTrade((double)s.bidPrice.lastElement(),(double)s.askPrice.lastElement(),false);
			return false;
		} else {
			a.encouragingMessage("Not Microsoft. Good trade.");
			a.recommendTrade((double)s.bidPrice.lastElement(),(double)s.askPrice.lastElement(),true);
			return true;
		}
	}
}
