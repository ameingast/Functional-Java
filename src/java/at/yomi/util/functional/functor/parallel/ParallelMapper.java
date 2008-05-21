package at.yomi.util.functional.functor.parallel;

import java.util.ArrayList;
import java.util.List;

import at.yomi.util.functional.functor.Mapper;

public abstract class ParallelMapper<A,B> extends Mapper<A,B> {

	private Integer workerCount;

	public ParallelMapper(Integer workerCount) {
		this.workerCount = workerCount;
	}

	public abstract B map(final A a);

	public List<B> apply(final List<A> as, final Object... os) {
		final Aggregator<B> aggregator = new Aggregator<B>(as.size());
		final List<FunctorWorker<A,B,B>> workers = new ArrayList<FunctorWorker<A,B,B>>(workerCount);

		for (int i = 0; i < workerCount; i++)
			workers.add(new FunctorWorker<A,B,B>(aggregator) {
				public B apply(A a, B... bs) {
					return map(a);
				}
			});

		int j = 1;
		for (A a : as)
			workers.get(j++ % workerCount).add(aggregator.getTicket(), a);

		for (FunctorWorker<A,B,B> worker : workers)
			worker.start();

		try {
			aggregator.waitForWorkers();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		return aggregator.getResults();
	}
}