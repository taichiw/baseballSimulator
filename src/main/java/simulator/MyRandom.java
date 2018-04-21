package simulator;

import java.security.SecureRandom;
import java.util.Random;

public class MyRandom {

	//①
	private static Random singleton = new SecureRandom();
	//②
	private MyRandom() {
		System.out.println("Cretae Instance");
	}
	//➂
	public static Random getInstance() {
		return singleton;
	}
}
