package at.yomi.functor.parallel;

import at.yomi.functor.Zip;
import at.yomi.functor.ZipTest;

public class ParallelZipTest extends ZipTest {
    @Override
    public Zip<Integer, Integer> getZip() {
        return new ParallelZip<Integer, Integer>(WORKER_COUNT);
    }
}
