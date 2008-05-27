package at.yomi.functor.parallel;

import at.yomi.functor.Exists;
import at.yomi.functor.ExistsTest;
import at.yomi.functor.Utils;

public class ParallelExistsTest extends ExistsTest {
	protected Exists<Integer> getEvenExists() {
		return new ParallelExists<Integer>(Utils.existsEvenFuncor, WORKER_COUNT, COMMIT_INTERVAL);
	}
}
