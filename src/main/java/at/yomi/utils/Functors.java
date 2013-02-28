package at.yomi.utils;

import java.util.ArrayList;
import java.util.List;

import at.yomi.functor.Filter;
import at.yomi.functor.Fold;
import at.yomi.functor.Map;
import at.yomi.functor.f.FilterFunctor;
import at.yomi.functor.f.FoldFunctor;
import at.yomi.functor.f.Functor;
import at.yomi.functor.parallel.ParallelFilter;
import at.yomi.functor.parallel.ParallelFold;
import at.yomi.functor.parallel.ParallelMap;

public final class Functors {
    private Functors() {}

    public final static FilterFunctor<Integer> isEvenFunctor = new FilterFunctor<Integer>() {
        @Override
        public Boolean apply(final Integer a) {
            return new Boolean(a % 2 == 0);
        }
    };

    public final static Filter<Integer> evenFilter = new Filter<Integer>(
        isEvenFunctor);

    public final static Filter<Integer> pEvenFilter = new ParallelFilter<Integer>(
        isEvenFunctor);

    public final static FoldFunctor<Integer, Integer> sumFoldFunctor = new FoldFunctor<Integer, Integer>() {
        @Override
        public Integer apply(final Integer a, final Integer b) {
            return new Integer(a + b);
        }
    };

    public final static Fold<Integer, Integer> sumFold = new Fold<Integer, Integer>(
        sumFoldFunctor);

    public final static Fold<Integer, Integer> pSumFold = new ParallelFold<Integer>(
        sumFoldFunctor);

    public final static FilterFunctor<Integer> isPrimeFunctor = new FilterFunctor<Integer>() {
        @Override
        public Boolean apply(final Integer a) {
            return isPrime(a);
        }

        private Boolean isPrime(final Integer a) {
            for (int i = 2; i < Math.sqrt(a); i++)
                if (a % i == 0)
                    return new Boolean(false);
            return new Boolean(true);
        }
    };

    public final static Map<Integer, Boolean> isPrimeMap = new Map<Integer, Boolean>(
        isPrimeFunctor);

    public final static Map<Integer, Boolean> pIsPrimeMap = new ParallelMap<Integer, Boolean>(
        isPrimeFunctor);

    public final static Filter<Integer> primesFilter = new Filter<Integer>(
        isPrimeFunctor);

    public final static Filter<Integer> pPrimesFilter = new ParallelFilter<Integer>(
        isPrimeFunctor);

    public final static Functor<List<Integer>, List<Integer>> qSort = new Functor<List<Integer>, List<Integer>>() {
        @Override
        public List<Integer> apply(final List<Integer> as) {
            if (as.size() <= 1)
                return as;

            final Integer pivot = as.get(0);
            final List<Integer> result = new ArrayList<Integer>(as.size() / 2);
            final List<Integer> rest = as.subList(1, as.size());

            final Filter<Integer> biggerFilter = new Filter<Integer>(
                new FilterFunctor<Integer>() {
                    @Override
                    public Boolean apply(final Integer i) {
                        return i > pivot;
                    }
                });

            final Filter<Integer> smallerEqFilter = new Filter<Integer>(
                new FilterFunctor<Integer>() {
                    @Override
                    public Boolean apply(final Integer i) {
                        return i <= pivot;
                    }
                });

            result.addAll(qSort.apply(smallerEqFilter.apply(rest)));
            result.add(pivot);
            result.addAll(qSort.apply(biggerFilter.apply(rest)));
            return result;
        }
    };
}
