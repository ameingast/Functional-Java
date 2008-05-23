package at.yomi.pair;

import java.util.Comparator;

public class PairSecondElementComparator<A,B extends Comparable<B>> implements
		Comparator<Pair<A,B>> {
	@Override
	public int compare(final Pair<A,B> o1, final Pair<A,B> o2) {
		return o1.second.compareTo(o2.second);
	}
}