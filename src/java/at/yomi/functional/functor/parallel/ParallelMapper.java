package at.yomi.functional.functor.parallel;

import java.util.List;

import at.yomi.functional.functor.Functor;
import at.yomi.functional.functor.Mapper;
import at.yomi.functional.functor.parallel.aggregator.ListAggregator;

public abstract class ParallelMapper<A,B> extends Mapper<A,B> {

	private final Integer workerCount;

	public ParallelMapper(Integer workerCount) {
		this.workerCount = workerCount;
	}

	public List<B> apply(final List<A> as, final Object... os) {
		ListAggregator<B> aggregator = new ListAggregator<B>(as.size());

		Worker.createWorkers(workerCount, aggregator, as, true, new Functor<A,B,B>() {
			public B apply(A a, B... bs) {
				return map(a);
			}
		});

		try {
			return aggregator.getResult();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}