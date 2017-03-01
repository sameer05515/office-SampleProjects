package com.p.event;

// Someone interested in "Hello" events
public class Responder implements HelloListener {
	@Override
	public void someoneSaidHello() {
		System.out.println("Hello there...");
	}
}