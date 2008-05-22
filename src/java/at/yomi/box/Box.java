package at.yomi.box;

public abstract class Box<A> {
	protected A a;

	public abstract A openOr(final A a);
}