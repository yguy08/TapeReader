package com.tickercash.tapereader.core;

import java.util.function.Function;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bittrex.BittrexExchange;
import org.knowm.xchange.poloniex.PoloniexExchange;

import com.tickercash.tapereader.event.handler.Log;
import com.tickercash.tapereader.event.handler.Save2File;
import com.tickercash.tapereader.event.handler.TickEventHandler;
import com.tickercash.tapereader.tape.CsvTape;
import com.tickercash.tapereader.tape.Tape;
import com.tickercash.tapereader.tape.XchangeLiveTape;

public class TickerFunction {

	public static final Function<String,Exchange> XCHANGEFUNC = 
            new Function<String,Exchange>() {

            @Override
            public Exchange apply(String t){
                switch(t.toUpperCase()){
                case "POLONIEX":
                    return ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
                case "TREX":
                	return ExchangeFactory.INSTANCE.createExchange(BittrexExchange.class.getName());
                case "GDAX":
                	return null;
                default:
                	return ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
                }
            }
    };
    
	public static final Function<String,Tape> TAPEFACTORY = (String s) -> {
	            switch(s.toUpperCase()){
	            case "XCHANGE":
	                return new XchangeLiveTape();
	            case "CSV":
	                return new CsvTape();
	            case "FAKE":
	            default:
	                return new CsvTape();
	        	}
	        };

	public static final Function<String,TickEventHandler> EVENTFACTORY = (String s) -> {
			switch(s) {
			case "LOG":
				return new Log();
			case "SAVE":
				return new Save2File();
			default:
				return new Log();
			}
	};
	
}
