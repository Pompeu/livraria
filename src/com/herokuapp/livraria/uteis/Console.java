package com.herokuapp.livraria.uteis;

public class Console {

	private Console() {
	}

	public static void log(String msg) {
		System.out.println(msg);
	}

	public static void log(Object msg) {
		System.out.println(msg.toString());
	}
}
