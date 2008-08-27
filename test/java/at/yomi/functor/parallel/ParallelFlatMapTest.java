package at.yomi.functor.parallel;

import at.yomi.functor.FlatMap;
import at.yomi.functor.FlatMapTest;
import at.yomi.functor.Utils;

public class ParallelFlatMapTest extends FlatMapTest {
    protected FlatMap<Integer, Integer> getMulTenFlatMap() {
        return new ParallelFlatMap<Integer, Integer>(Utils.mulTenFunctor);
    }
}
