package com.kedu.controllers;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kedu.beans.Tv;

public class Main {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("ctx.xml");

		Tv tv = ctx.getBean(Tv.class);
		
		tv.volumeUp();

		//		Tv tv = TvFactory.getInstance(args[0]);
		//		
		//		tv.powerOn();
		//		tv.volumeUp();

    }
}
