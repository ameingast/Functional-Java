package at.yomi.util.functional.functor.parallel;

import java.util.SortedMap;
import java.util.TreeMap;

import at.yomi.util.functional.functor.Functor;

public abstract class FunctorWorker<A,B,C> extends Thread implements Functor<A,B,C> {

	private SortedMap<Integer,A> items = new TreeMap<Integer,A>();

	private Aggregator<B> aggregator = null;

	public FunctorWorker(Aggregator<B> aggregator) {
		this.aggregator = aggregator;
	}

	public void run() {
		TreeMap<Integer,B> results = new TreeMap<Integer,B>();

		for (Integer ticket : items.keySet())
			results.put(ticket, apply(items.get(ticket)));
		aggregator.add(results);
		items = new TreeMap<Integer,A>();
	}

	public synchronized void add(Integer ticket, A a) {
		items.put(ticket, a);
	}
}
