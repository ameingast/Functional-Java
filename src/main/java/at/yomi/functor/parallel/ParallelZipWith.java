package at.yomi.functor.parallel;

import java.util.List;

import at.yomi.functor.Zip;
import at.yomi.functor.ZipWith;
import at.yomi.functor.f.MapFunctor;
import at.yomi.functor.f.ZipWithFunctor;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.pair.Pair;

public class ParallelZipWith<A, B, C> extends ZipWith<A, B, C> {
    protected final Integer workerCount;

    protected final MapFunctor<Pair<A, B>, C> zipFunctor = new MapFunctor<Pair<A, B>, C>() {
        @Override
        public C apply(Pair<A, B> a) {
            return functor.apply(a.first, a.second);
        }
    };

    public ParallelZipWith(final ZipWithFunctor<A, B, C> functor) {
        this(functor, AbstractWorker.DEFAULT_WORKER_COUNT);
    }

    public ParallelZipWith(final ZipWithFunctor<A, B, C> functor,
        final Integer workerCount) {
        super(functor);
        this.workerCount = workerCount;
    }

    @Override
    public List<C> apply(final List<A> as, final List<B> bs) {
        return new ParallelMap<Pair<A, B>, C>(zipFunctor, workerCount)
            .apply(new Zip<A, B>().apply(as, bs));
    }
}
