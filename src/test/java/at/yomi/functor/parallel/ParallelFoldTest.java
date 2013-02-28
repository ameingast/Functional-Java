package at.yomi.functor.parallel;

import at.yomi.functor.Fold;
import at.yomi.functor.FoldTest;
import at.yomi.functor.Utils;

public class ParallelFoldTest extends FoldTest {
    protected Fold<Integer, Integer> getSumFold() {
        return new ParallelFold<Integer>(Utils.sumFunctor);
    }
}
