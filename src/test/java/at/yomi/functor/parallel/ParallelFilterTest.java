package at.yomi.functor.parallel;

import at.yomi.functor.Filter;
import at.yomi.functor.FilterTest;
import at.yomi.functor.Utils;

public class ParallelFilterTest extends FilterTest {
    protected Filter<Integer> getEvenFilter() {
        return new ParallelFilter<Integer>(Utils.isEvenFunctor);
    }
}
