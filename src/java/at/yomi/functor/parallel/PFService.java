package at.yomi.functor.parallel;

import at.yomi.utils.ThreadPoolExecutorSingleton;

public abstract class PFService {
	public static void shutDown() {
		ThreadPoolExecutorSingleton.shutDown();
	}

	public static void startUp() {
		ThreadPoolExecutorSingleton.startUp();
	}
}
