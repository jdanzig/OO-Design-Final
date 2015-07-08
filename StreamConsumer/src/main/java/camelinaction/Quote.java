package camelinaction;

import org.apache.camel.Processor;
import org.apache.camel.Exchange;


public class Quote implements Processor{

	private final static int CONSUMER_INTERVAL = 2000;
	
	public void process(Exchange e) throws Exception {
		Thread.sleep(CONSUMER_INTERVAL);
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis()); // current time
		String[] s = e.getIn().getBody(String.class).split("\t");
		s[0] = s[0].replaceAll("\\[", "");
		s[4] = s[4].replaceAll("\\]", "");
		e.getOut().setBody(s[0] + "\t" + s[1] + "\t" + s[2] + "\t" + s[3] + "\t" + s[4] + "\t" + ts);
		System.out.println(ts + " MESSAGE FROM FILE: " + e.getIn().getHeader("CamelFileName") + " is heading to CHANNEL_" + s[0]);
	}
}