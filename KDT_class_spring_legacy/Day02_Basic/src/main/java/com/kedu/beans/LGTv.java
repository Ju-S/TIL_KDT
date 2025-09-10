package com.kedu.beans;

public class LGTv implements Tv{
	private String brand;
	private int price;
	
	public LGTv() {
		System.out.println("LG Tv 생성");
	}
	
	public LGTv(String brand, int price) {
		this.brand = brand;
		this.price = price;
	}

	@Override
	public void powerOn() {
		
	}

	@Override
	public void powerOff() {
		
	}
	
	@Override
	public void volumeUp() {

    }

	@Override
	public void volumeDown() {

	}
	
	@Override
	public String getBrand() {
		return brand;
	}

	@Override
	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void setPrice(int price) {
		this.price = price;
	}
}
