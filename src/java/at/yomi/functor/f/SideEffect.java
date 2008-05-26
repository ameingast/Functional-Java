package at.yomi.functor.f;

public interface SideEffect<A> {
	void apply(A s);
}
