package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.FlatMap;
import at.yomi.functor.MapFold;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;

public class ParallelFlatMap<A, B> extends FlatMap<A, B> {
    protected final Integer workerCount;

    public ParallelFlatMap(final MapFunctor<A, B> functor) {
        this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
    }

    public ParallelFlatMap(final MapFunctor<A, B> functor,
        final Integer workerCount) {
        super(functor);
        this.workerCount = workerCount;
    }

    @Override
    protected MapFold<List<A>, List<B>, List<B>> getMapFold() {
        return new ParallelMapFold<List<A>, List<B>, List<B>>(mapFunctor,
            foldFunctor, workerCount);
    }
}
