package com.tickercash.tapereader.event;

import static net.openhft.chronicle.bytes.StopCharTesters.SPACE_STOP;

import net.openhft.chronicle.bytes.Bytes;

public class Tick {
	
	private Bytes<?> bytes;
	
	public Tick() {
		
	}
	
	public Tick(String string, long time, double doubleValue) {
		setBytes();
		setSymbol(string);
		setTimestamp(time);
		setLast(doubleValue);
	}

	public Tick set(String symbol, long timestamp, double last, double bid, double ask, int volume) {
		setBytes();
		setSymbol(symbol);
		setTimestamp(timestamp);
		setLast(last);
		setBid(bid);
		setAsk(ask);
		setVolume(volume);
		return this;
	}
	
	private void setBytes(){
		bytes = Bytes.elasticByteBuffer();
	}
	
	public Tick set(Tick t) {
		return t;
	}
	
	public String getSymbol(){
		return bytes.parseUtf8(SPACE_STOP);
	}
	
	public void setSymbol(String symbol) {
		bytes.append(symbol).append(' ');
	}
	
	public long getTimestamp(){
		return bytes.parseLong();
	}

	public void setTimestamp(long timestamp) {
		bytes.append(timestamp).append(' ');
	}
	
	public double getLast(){
		return bytes.parseDouble();
	}

	public void setLast(double last) {
		bytes.append(last).append(' ');
	}
	
	public double getBid(){
		return bytes.parseDouble();
	}

	public void setBid(double bid) {
		bytes.append(bid).append(' ');
	}
	
	public double getAsk(){
		return bytes.parseDouble();
	}

	public void setAsk(double ask) {
		bytes.append(ask).append(' ');
	}
	
	public int getVolume(){
		return (int) bytes.parseDouble();
	}

	public void setVolume(int volume) {
		bytes.append(volume).append(' ');
	}
	
	@Override
	public String toString() {
		return bytes.toString();
	}

	public void set(long parseLong, double parseDouble) {
		setBytes();
		setTimestamp(parseLong);
		setLast(parseDouble);
	}
}