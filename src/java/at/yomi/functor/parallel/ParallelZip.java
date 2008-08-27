package at.yomi.functor.parallel;

import at.yomi.functor.Zip;
import at.yomi.functor.ZipWith;
import at.yomi.functor.parallel.aggregator.AbstractWorker;
import at.yomi.pair.Pair;

public class ParallelZip<A, B> extends Zip<A, B> {
    protected final Integer workerCount;

    public ParallelZip() {
        this(AbstractWorker.DEFAULT_WORKER_COUNT);
    }

    public ParallelZip(final Integer workerCount) {
        this.workerCount = workerCount;
    }

    protected ZipWith<A, B, Pair<A, B>> getZipper() {
        return new ParallelZipWith<A, B, Pair<A, B>>(pairFunctor, workerCount);
    }
}
