package com.singleton;

/**
 * Doubly Locked Singleton DP
 * 
 * @author nerursur
 *
 */
public enum PersonSingleton {

	INSTANCE;

	PersonSingleton() {
	}

	int Calculate(int int1, int int2) {
		return int1 + int2;
	}
	
	public static void main(String[] args) {
		System.out.println(PersonSingleton.INSTANCE.Calculate(2, 3));
		try {
			System.out.println(PersonSingleton.INSTANCE.clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}