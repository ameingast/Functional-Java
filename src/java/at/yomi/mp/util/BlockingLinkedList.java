package at.yomi.mp.util;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class BlockingLinkedList<A> extends LinkedList<A> {
	private static final long serialVersionUID = -9126201800947040911L;

	private final Semaphore critical = new Semaphore(1);

	private final Semaphore counter = new Semaphore(0);

	@Override
	public boolean add(final A a) {
		boolean result = false;

		try {
			critical.acquire();
			result = super.add(a);
			counter.release();
			critical.release();
			return result;
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public A poll() {
		A a = null;

		try {
			counter.acquire();
			critical.acquire();
			a = super.poll();
			critical.release();
			return a;
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
