package at.yomi.util.box;

public class EmptyBox<A> extends Box<A> {
	public A openOr(A a) {
		return a;
	}
}