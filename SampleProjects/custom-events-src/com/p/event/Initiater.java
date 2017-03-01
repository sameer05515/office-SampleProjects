package com.p.event;

import java.util.ArrayList;
import java.util.List;

// Someone who says "Hello"
public class Initiater {
	private List<HelloListener> listeners = new ArrayList<HelloListener>();

	public Initiater addListener(HelloListener toAdd) {
		listeners.add(toAdd);
		return this;
	}
	
	public Initiater removeAll(){
		listeners.removeAll(listeners);
		return this;
	}
	
	public Initiater removeListener(HelloListener toRemove){
		listeners.remove(toRemove);
		return this;
	}

	public void sayHello() {
		System.out.println("Hello!!");

		// Notify everybody that may be interested.
		for (HelloListener hl : listeners)
			hl.someoneSaidHello();
	}
}