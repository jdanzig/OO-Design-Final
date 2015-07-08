/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package camelinaction;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BrokerProcessor implements Processor{

	OptimistStrategy os = new OptimistStrategy();
	HateMSFTStrategy hms = new HateMSFTStrategy();
	private final static int DELAY = 3000;
	int i = 0;
	boolean makeTheTrade = false;
	
	public void process(Exchange e) throws Exception {
		Thread.sleep(DELAY);
		String[] s = e.getIn().getBody(String.class).split("\t");
		String stockName = s[0];
		double bidPrice = Double.parseDouble(s[1]);
		int bidQuantity = Integer.parseInt(s[2]);
		double askPrice = Double.parseDouble(s[3]);
		int askQuantity = Integer.parseInt(s[4]);
		String timeStamp = s[5];
		System.out.println(timeStamp + " Stock:" + stockName + " Bid Price:" + bidPrice + " Bid Quantity:" + bidQuantity + " Ask Price:" + askPrice + " Ask Quantity:" + askQuantity);
		Stock stock = StockList.getInstance().getStock(stockName);
		stock.addQuote(bidPrice, bidQuantity,askPrice,askQuantity,timeStamp);
		
		String body = timeStamp;
		body += "\nBid Quantity: " + bidQuantity + " | Current Bid: $" + bidPrice;
		body += "\nMin Bid: $" + stock.getMin("bp") + " | Max Bid: $" + stock.getMax("bp") + " | Average Bid: $" + stock.getAvg("bp") + " | Std Dev: $" + stock.getStdDev("bp");
		body += "\nMin Ask: $" + stock.getMin("ap") + " | Max Ask: $" + stock.getMax("ap") + " | Average Bid: $" + stock.getAvg("ap") + " | Std Dev: $" + stock.getStdDev("ap");
		
		// Testing out strategy every few tics.
		if(i%10 == 2) {
			makeTheTrade = os.goodStrategy(stock);
		} else if (i%10 == 5) {
			makeTheTrade = hms.goodStrategy(stock);
		}
		++i;
		
		body += "\nGood trade? " + makeTheTrade;
		System.out.println(body);
		e.getIn().setBody(body);

	}
}