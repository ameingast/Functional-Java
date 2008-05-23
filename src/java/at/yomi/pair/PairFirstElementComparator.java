package at.yomi.pair;

import java.util.Comparator;

public class PairFirstElementComparator<A extends Comparable<A>,B> implements Comparator<Pair<A,B>> {
	@Override
	public int compare(final Pair<A,B> o1, final Pair<A,B> o2) {
		return o1.first.compareTo(o2.first);
	}

}
