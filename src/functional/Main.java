package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Integer> as = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));
		List<Collection<Integer>> aas = new ArrayList<Collection<Integer>>(Arrays.asList(as, as, as));

		final Integer c = 2;
		Integer i = new Functor<Integer,Integer,Integer>() {
		        public Integer apply(final Integer a, final Integer...is) {
		                return a + c;
		        }
		}.apply(2);
		System.out.println(i);
		
		final String prefix = "PRE";
		String result = new Functor<String,String,String>() {
			public String apply(String a, String... cs) {
				String r = prefix + a;
				for (String s : cs)
					r += s;
				return r;
			}
		}.apply("-", "FIX", "-", "POST", "-", "FIX");
		System.out.println(result);
		
		// add 1 to every number in list
		Collection<Integer> bs = new Mapper<Integer, Integer>() {
			public Integer map(Integer a) {
				return a + 1;
			}
		}.apply(as);
		printOperation("map", bs, "(+1)", as);

		// add all numbers in list
		Integer k = new Folder<Integer, Integer>() {
			public Integer fold(Integer a, Integer e) {
				return a + e;
			}
		}.apply(as, 0);
		printOperation("foldr", k, "(+)", 0, as);
		String sf = new Folder<String, String>() {
			public String fold(String a, String e) {
				return e + a;
			}
		}.apply(Arrays.asList("PRE", "-", "FIX", "-", "POST", "-", "FIX"), "");
		System.out.println(sf);
		
		// filter even numbers in list
		Collection<Integer> cs = new Filter<Integer>() {
			public boolean filter(Integer a) {
				return 0 == a % 2;
			}
		}.apply(as);
		printOperation("filter", cs, "(even)", as);
		
		// cast all integers to floats
		Collection<Float> ds = new Mapper<Integer,Float>() {
			public Float map(Integer a) {
				return new Float(a);
			}
		}.apply(as);
		printOperation("map", ds, "((float))", as);
		
		// first apply (*2) to all values in sublist
		// and then concatenate them into a single
		// resulting list
		Collection<Integer> es = new FlatMapper<Integer,Integer>() {
			public Integer map(Integer a) {	
				return a * 2;
			}
		}.apply(aas);
		printOperation("flatmap", es, "(*2)", Arrays.asList(as, as, as));
		
		Integer fs = new MapFolder<Integer, Integer, Integer>() {
			public Integer ffold(Integer b, Integer e) {
				return e + b;
			}

			public Integer fmap(Integer a) {
				return a + 1;
			}
		}.apply(as, 0);
		printOperation("mapfold", fs, "(+1)", "(++)", as);
	}
	
	private static void printOperation(Object op, Object result, Object...operands) {
		System.out.println(formatOperation(op, result, operands));
	}
	
	private static String formatOperation(Object op, Object result, Object...operands) {
		String r = op.toString();
		for (Object operand : operands)
			r += " " + operand.toString();
		r += " = " + result.toString();
		return r;
	}
}
