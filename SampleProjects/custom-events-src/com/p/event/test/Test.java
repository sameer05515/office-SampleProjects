package com.p.event.test;

import com.p.event.HelloListener;
import com.p.event.Initiater;
import com.p.event.Responder;

public class Test {
	public static void main(String[] args) {
		Initiater initiater = new Initiater();
		Responder responder = new Responder();

		initiater.addListener(responder);

		initiater.sayHello(); // Prints "Hello!!!" and "Hello there..."
		
		initiater.addListener(new HelloListener() {
			
			@Override
			public void someoneSaidHello() {
				System.out.println("Anonymous listener is calling");
				
			}
		}).sayHello();;
	}
}