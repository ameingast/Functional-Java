package at.yomi.functor.parallel;

import at.yomi.functor.Map;
import at.yomi.functor.MapTest;
import at.yomi.functor.Utils;

public class ParallelMapTest extends MapTest {
    @Override
    protected Map<Integer, Integer> getAddOneMapper() {
        return new ParallelMap<Integer, Integer>(Utils.addOneFunctor);
    }

    @Override
    protected Map<Integer, Integer> getMulTenMapper() {
        return new ParallelMap<Integer, Integer>(Utils.mulTenFunctor);
    }
}
