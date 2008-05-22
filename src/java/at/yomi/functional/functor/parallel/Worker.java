package at.yomi.functional.functor.parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.yomi.functional.functor.Functor;
import at.yomi.functional.functor.parallel.aggregator.Aggregator;

class Worker<A,B> extends Thread {
	private final Aggregator<B,?> aggregator;

	private final Map<Integer,A> items = new HashMap<Integer,A>();

	private final Functor<A,B,?> functor;

	public static <A,B> List<Worker<A,B>> createWorkers(Integer workerCount,
			Aggregator<B,?> aggregator, List<A> as, boolean start, Functor<A,B,?> functor) {
		List<Worker<A,B>> workers = new ArrayList<Worker<A,B>>(workerCount);
		int j = 0;

		for (Integer i = 0; i < workerCount; i++)
			workers.add(new Worker<A,B>(aggregator, functor));

		for (A a : as)
			workers.get(j++ % workerCount).add(a);

		if (start)
			startWorkers(workers);

		return workers;
	}

	private static <A,B> void startWorkers(List<Worker<A,B>> workers) {
		for (Worker<A,B> worker : workers)
			worker.start();
	}

	public Worker(Aggregator<B,?> aggregator, Functor<A,B,?> functor) {
		this.functor = functor;
		this.aggregator = aggregator;
	}

	public synchronized void add(A a) {
		items.put(aggregator.getTicket(), a);
	}

	public void run() {
		Map<Integer,B> results = new HashMap<Integer,B>(items.size());

		for (Integer ticket : items.keySet())
			results.put(ticket, functor.apply(items.get(ticket)));
		aggregator.add(results);
	}
}