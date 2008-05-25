package at.yomi.mp;

import at.yomi.benchmark.BaseBenchmark;

public class MPBenchmark extends BaseBenchmark {
	// @BMIgnore
	// public static void bmSumPrimes() {
	// new AbstractBenchmark("MP (isPrime) (sum)") {
	// @Override
	// public void benchmark() throws InterruptedException {
	// final PrimeAggregator pa = new PrimeAggregator(WORKER_COUNT, data);
	//
	// pa.startCalculating();
	// pa.waitForShutDown();
	// }
	// };
	//
	// new AbstractBenchmark("MapFold (isPrime) (sum)") {
	// @Override
	// public void benchmark() throws Exception {
	// new ParallelMapFolder<Integer,Integer,Integer>(WORKER_COUNT) {
	// @Override
	// public Integer fold(final Integer b, final Integer e) {
	// return new Integer(b + e);
	// }
	//
	// @Override
	// public Integer map(final Integer a) {
	// return Utils.isPrime(a) ? a : new Integer(0);
	// }
	// }.apply(data, 0);
	// }
	// };
	//
	// new AbstractBenchmark("Iterating (isPrime) (sum)") {
	// @Override
	// public void benchmark() throws Exception {
	// Integer sum = 0;
	//
	// for (final Integer i : data)
	// sum += Utils.isPrime(i) ? i : 0;
	// }
	// };
	// }
	//
	// @BMIgnore
	// public static void bmTPoly() {
	// new AbstractBenchmark("MP (TPoly) (sum)") {
	//
	// @Override
	// public void benchmark() throws Exception {
	// // TODO Auto-generated method stub
	// }
	//
	// };
	//
	// new AbstractBenchmark("[5] MapFold (TPoly) (sum)") {
	// @Override
	// public void benchmark() {
	// new ParallelMapFolder<Integer,Float,Float>(5) {
	// @Override
	// public Float fold(final Float b, final Float e) {
	// return e + b;
	// }
	//
	// @Override
	// public Float map(final Integer a) {
	// return Utils.tpoly(a);
	// }
	// }.apply(data, new Float(0));
	// }
	// };
	//
	// new AbstractBenchmark("Iterating (Tpoly) (sum)") {
	// @Override
	// public void benchmark() {
	// Float k = new Float(0);
	//
	// for (final Integer i : data)
	// k += Utils.tpoly(i);
	// }
	// };
	// }
}
