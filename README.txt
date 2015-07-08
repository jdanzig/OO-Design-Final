README:

EIP patterns: Message Channel, Point to Point Channel, Message Translator, 
Content Enricher

Singleton:
Stocklist, Stock(pseudo-singleton)

Iterator:
Stock

Template:
Statistics

Strategy:
Strategy, HateMSFTStrategy, Optimist Strategy

Observer:
Observer, Announcer

StreamProducer loads the message files into ActiveMQ.
StreamConsumer splits the messages into three difference queues by company.
Broker performs a variety of functions and splits the messages into "TRADE" or "NO_TRADE"

Directions:
Run StreamProducer, then StreamConsumer, then Broker. StreamProducer loads a new message every 4 
seconds.
