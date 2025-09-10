package com.kedu.beans;

public interface Tv {
	public void powerOn();
	public void powerOff();
	public void volumeUp();
	public void volumeDown();
	
	public String getBrand();
	public void setBrand(String brand);
	
	public int getPrice();
	public void setPrice(int price);
}
