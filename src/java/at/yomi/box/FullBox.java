package at.yomi.box;

public class FullBox<A> extends Box<A> {
	public FullBox(A a) {
		this.a = a;
	}

	public A openOr(A a) {
		return this.a;
	}
}
