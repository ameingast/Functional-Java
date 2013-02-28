package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Exists;
import at.yomi.functor.f.ExistsFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;

public class ParallelExists<A> extends Exists<A> {
    protected final Integer workerCount;

    protected final MapFunctor<A, Boolean> mapFunctor = new MapFunctor<A, Boolean>() {
        @Override
        public Boolean apply(A a) {
            return functor.apply(a);
        }
    };

    protected final FoldFunctor<Boolean, Boolean> foldFunctor = new FoldFunctor<Boolean, Boolean>() {
        @Override
        public Boolean apply(Boolean a, Boolean b) {
            return a || b;
        }
    };

    public ParallelExists(final ExistsFunctor<A> functor) {
        this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
    }

    public ParallelExists(final ExistsFunctor<A> functor,
        final Integer workerCount) {
        super(functor);
        this.workerCount = workerCount;
    }

    @Override
    public Boolean apply(final List<A> as) {
        return new ParallelMapFold<A, Boolean, Boolean>(mapFunctor,
            foldFunctor, workerCount).apply(as, true);
    }
}
