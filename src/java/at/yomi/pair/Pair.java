package at.yomi.pair;

public class Pair<A,B> {
	final public A first;
	final public B second;

	public Pair(final A first, final B second) {
		this.first = first;
		this.second = second;
	}
}