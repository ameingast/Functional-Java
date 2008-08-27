package at.yomi.functor;

import at.yomi.benchmark.AbstractBenchmark;
import at.yomi.benchmark.BaseBenchmark;
import at.yomi.functor.parallel.ParallelMapFold;
import at.yomi.functor.parallel.StrictParallelMapFold;

public class MapFoldBenchmark extends BaseBenchmark {

    public static void bmCountEven() {
        new AbstractBenchmark("MapFold (even) (count)") {
            @Override
            public void benchmark() {
                new MapFold<Integer, Boolean, Integer>(Utils.isEvenFunctor,
                    Utils.sumTruesFunctor).apply(data, 0);
            }
        };

        new AbstractBenchmark("[P] MapFold (even) (count)") {
            @Override
            public void benchmark() {
                new ParallelMapFold<Integer, Boolean, Integer>(
                    Utils.isEvenFunctor, Utils.sumTruesFunctor).apply(data, 0);
            }
        };

        new AbstractBenchmark("Iterating count even") {
            @Override
            public void benchmark() {
                Integer evens = 0;

                for (final Integer i : data)
                    if (0 == i % 2)
                        evens++;
            }
        };
    }

    public static void bmCountPrimes() {
        new AbstractBenchmark("MapFold (TPoly) (count)") {
            @Override
            public void benchmark() {
                new MapFold<Integer, Float, Float>(Utils.tpolyFunctor,
                    Utils.sumFloatFunctor).apply(data, new Float(0));
            }
        };

        new AbstractBenchmark("[P] MapFold (TPoly) (sum)") {
            @Override
            public void benchmark() {
                new ParallelMapFold<Integer, Float, Float>(Utils.tpolyFunctor,
                    Utils.sumFloatFunctor).apply(data, new Float(0));
            }
        };

        new AbstractBenchmark("[P] StrictMapFold (TPoly) (sum)") {
            @Override
            public void benchmark() {
                new StrictParallelMapFold<Integer, Float, Float>(
                    Utils.tpolyFunctor, Utils.sumFloatFunctor).apply(data,
                    new Float(0));
            }
        };

        new AbstractBenchmark("Iterating (Tpoly) (sum)") {
            @Override
            public void benchmark() {
                Float k = new Float(0);

                for (final Integer i : data)
                    k += Utils.tpoly(i);
            }
        };
    }
}
