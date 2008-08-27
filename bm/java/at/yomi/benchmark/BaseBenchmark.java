package at.yomi.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.yomi.functor.parallel.aggregator.AbstractWorker;

public abstract class BaseBenchmark {
    protected static final Integer SIZE = 100000;

    protected static final Integer WORKER_COUNT = AbstractWorker.DEFAULT_WORKER_COUNT;

    protected static List<Integer> data = getData(SIZE);

    private static List<Integer> getData(final Integer size) {
        final List<Integer> c = new ArrayList<Integer>(size);
        final Random r = new Random();

        for (Integer i = 0; i < size; i++)
            c.add(r.nextInt());
        return c;
    }
}
