package at.yomi.functor.parallel;

import at.yomi.functor.ForAll;
import at.yomi.functor.ForAllTest;
import at.yomi.functor.Utils;

public class ParallelForAllTest extends ForAllTest {
	protected ForAll<Integer> getAllEvens() {
		return new ParallelForAll<Integer>(Utils.allEvensFunctor);
	}
}
