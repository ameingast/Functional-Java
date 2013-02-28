package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Fold;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.functor.parallel.aggregator.FoldWorker;
import at.yomi.functor.parallel.aggregator.ListAggregator;

public class ParallelFold<A> extends Fold<A, A> {
    private final Integer workerCount;

    public ParallelFold(final FoldFunctor<A, A> functor) {
        this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
    }

    public ParallelFold(final FoldFunctor<A, A> functor,
        final Integer workerCount) {
        super(functor);
        this.workerCount = workerCount;
    }

    @Override
    public A apply(final List<A> as, final A e) {
        try {
            return FoldWorker.start(workerCount, new ListAggregator<A>(as
                .size()), as, functor, e);
        } catch (final InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
