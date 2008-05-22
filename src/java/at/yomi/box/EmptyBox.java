package at.yomi.box;

public class EmptyBox<A> extends Box<A> {
	@Override
	public A openOr(final A a) {
		return a;
	}
}