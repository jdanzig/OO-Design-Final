package camelinaction;

public class Announcer implements Observer {
	
	public void recommendTrade(double bp, double ap, boolean b) {
		String s = "";
		if(b)
			s += "definitely";
		else
			s += "not";
		System.out.println("Bid Price of $" + bp + " and Ask Price of $" + ap + "? The observer says you should " + s + "buy this stock.");
	}
	
	public void encouragingMessage(String s) {
		System.out.println(s);
	}
}
