package com.tickercash.event.translator;

import org.knowm.xchange.coinmarketcap.dto.marketdata.CoinMarketCapTicker;
import org.knowm.xchange.dto.marketdata.Ticker;

import com.tickercash.marketdata.Tick;
import com.tickercash.util.UniqueCurrentTimeMS;

import net.openhft.chronicle.bytes.Bytes;
import net.openhft.chronicle.bytes.StopCharTesters;

import java.nio.ByteBuffer;

public class MarketDataTranslator {
    
    private static Bytes<ByteBuffer> bytes;
    
    public static final void translateTo(Tick event, long sequence, CoinMarketCapTicker ticker) {
        event.set(ticker.getID().toUpperCase()+"/BTC", ":CMC", UniqueCurrentTimeMS.uniqueCurrentTimeMS(), ticker.getPriceBTC().doubleValue());
    }
    
    public static final void translateTo(Tick event, long sequence, String feed, Ticker ticker) {
        event.set(ticker.getCurrencyPair().toString(), feed, ticker.getTimestamp().getTime(), ticker.getLast().doubleValue());
    }
    
    public static final void translateTo(Tick event, long sequence, String[] strArr, long ts, double last){
        event.set(strArr[0], strArr[1], ts, last);
    }
    
    public static final void translateTo(Tick event, long sequence, String tick){
        bytes = Bytes.elasticByteBuffer().append8bit(tick);
        event.set(bytes.parseUtf8(StopCharTesters.SPACE_STOP), bytes.parseUtf8(StopCharTesters.SPACE_STOP), bytes.parseLong(), bytes.parseDouble());
    }

}
