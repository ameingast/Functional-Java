package at.yomi.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class ThreadPoolExecutorSingleton {
	private static final int CORE_POOL_SIZE = 5;

	private static final int MAX_POOL_SIZE = 20;

	private static final long KEEP_ALIVE = 10;

	private static final TimeUnit T_UNIT = TimeUnit.SECONDS;

	private static ThreadPoolExecutor instance = null;

	public static void startUp() {
		getInstance();
	}

	public static void shutDown() {
		getInstance().shutdown();
		instance = null;
	}

	public static ThreadPoolExecutor getInstance() {
		return null == instance ? instance = createExecutor() : instance;
	}

	private static ThreadPoolExecutor createExecutor() {
		return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE, T_UNIT,
				new LinkedBlockingQueue<Runnable>());
	}
}
