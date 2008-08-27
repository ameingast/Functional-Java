package at.yomi.functor;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.functor.parallel.ParallelFold;
import at.yomi.utils.Functors;

public class FoldBenchmark extends BaseBenchmark {
    public static void bmFold() {
        new AbstractBenchmark("Folding (*)") {
            @Override
            public void benchmark() {
                new Fold<Integer, Integer>(Utils.mulFunctor).apply(data, 1);
            }
        };

        new AbstractBenchmark("[P] Folding (*)") {
            @Override
            public void benchmark() throws Exception {
                new ParallelFold<Integer>(Utils.mulFunctor).apply(data, 1);
            }
        };

        new AbstractBenchmark("Iterating (*)") {
            @Override
            public void benchmark() {
                Integer r = 1;

                for (final Integer i : data)
                    r *= i;
            }
        };

        new AbstractBenchmark("Folding (+)") {
            @Override
            public void benchmark() throws Exception {
                Functors.sumFold.apply(data, 0);
            }
        };

        new AbstractBenchmark("[P] Folding (+)") {
            @Override
            public void benchmark() throws Exception {
                Functors.pSumFold.apply(data, 0);
            }
        };

        new AbstractBenchmark("Iterating (+)") {
            @Override
            public void benchmark() {
                Integer r = 1;

                for (final Integer i : data)
                    r += i;
            }
        };
    }
}
