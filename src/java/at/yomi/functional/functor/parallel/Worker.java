package at.yomi.functional.functor.parallel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.yomi.functional.functor.Functor;
import at.yomi.functional.functor.parallel.aggregator.Aggregator;

class Worker<A,B> extends Thread {

	public static final Integer DEFAULT_COMMIT_INTERVAL = -1;

	public static final Integer DEFAULT_WORKER_COUNT = 4;

	private final Aggregator<B,?> aggregator;

	private final Map<Integer,A> items = new HashMap<Integer,A>();

	private final Functor<A,B,?> functor;

	private final Integer commitInterval;

	public static <A,B> List<Worker<A,B>> createWorkers(final Integer workerCount,
			final Integer commitInterval, final Aggregator<B,?> aggregator, final List<A> as,
			final boolean start, final Functor<A,B,?> functor) {
		final List<Worker<A,B>> workers = new ArrayList<Worker<A,B>>(workerCount);
		int j = 0;

		for (Integer i = 0; i < workerCount; i++)
			workers.add(new Worker<A,B>(aggregator, commitInterval, functor));

		for (final A a : as)
			workers.get(j++ % workerCount).add(a);

		if (start)
			startWorkers(workers);

		return workers;
	}

	private static <A,B> void startWorkers(final List<Worker<A,B>> workers) {
		for (final Worker<A,B> worker : workers)
			worker.start();
	}

	public Worker(final Aggregator<B,?> aggregator, final Functor<A,B,?> functor) {
		this.functor = functor;
		this.aggregator = aggregator;
		this.commitInterval = DEFAULT_COMMIT_INTERVAL;
	}

	public Worker(final Aggregator<B,?> aggregator, final Integer commitInterval,
			final Functor<A,B,?> functor) {
		this.functor = functor;
		this.aggregator = aggregator;
		this.commitInterval = commitInterval;
	}

	public synchronized void add(final A a) {
		items.put(aggregator.getTicket(), a);
	}

	@Override
	public void run() {
		final Map<Integer,B> results = new HashMap<Integer,B>(items.size());
		int i = 0;

		for (final Integer ticket : items.keySet()) {
			results.put(ticket, functor.apply(items.get(ticket)));
			if (i++ > commitInterval) {
				aggregator.add(results);
				results.clear();
				i = 0;
			}
		}
		aggregator.add(results);
	}
}