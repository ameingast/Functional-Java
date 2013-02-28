package at.yomi.functor.parallel;

import at.yomi.functor.MapFold;
import at.yomi.functor.MapFoldTest;
import at.yomi.functor.Utils;

public class ParallelMapFoldTest extends MapFoldTest {
    @Override
    protected MapFold<Integer, Integer, String> getMulTenConcatAsStringFolder() {
        return new StrictParallelMapFold<Integer, Integer, String>(
            Utils.mulTenFunctor, Utils.toIntegerConcatFunctor);
    }

    @Override
    protected MapFold<Integer, Integer, Integer> getAddOneSumFolder() {
        return new ParallelMapFold<Integer, Integer, Integer>(
            Utils.addOneFunctor, Utils.sumFunctor);
    }
}