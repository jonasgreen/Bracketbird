package com.bracketbird.client.logic;

public abstract class Wrapper<T> {
	private T instance;
	
	public void set(T instance) {
		this.instance = instance;
	}
	public T get() {
		return instance;
	}
}
