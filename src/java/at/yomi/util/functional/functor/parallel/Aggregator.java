package at.yomi.util.functional.functor.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

public class Aggregator<A> {
	private static Integer tickets = 0;

	private final Semaphore counter;

	protected SortedMap<Integer,A> results = new TreeMap<Integer,A>();

	public Aggregator(int itemCount) {
		counter = new Semaphore(-1 * itemCount + 1);
	}

	public synchronized Integer getTicket() {
		return tickets++;
	}

	public synchronized void add(Map<Integer,A> items) {
		results.putAll(items);
		counter.release(items.size());
	}

	public void waitForWorkers() throws InterruptedException {
		counter.acquire();
	}

	public synchronized List<A> getResults() {
		return new ArrayList<A>(results.values());
	}
}