package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Map;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.functor.parallel.aggregator.ListAggregator;
import at.yomi.functor.parallel.aggregator.MapWorker;

public class ParallelMap<A, B> extends Map<A, B> {
    protected final Integer workerCount;

    public ParallelMap(final MapFunctor<A, B> functor) {
        this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
    }

    public ParallelMap(final MapFunctor<A, B> functor, final Integer workerCount) {
        super(functor);
        this.workerCount = workerCount;
    }

    @Override
    public List<B> apply(final List<A> as) {
        try {
            return MapWorker.start(workerCount,
                new ListAggregator<B>(as.size()), as, functor);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}