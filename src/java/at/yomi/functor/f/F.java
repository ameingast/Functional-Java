package at.yomi.functor.f;

public interface F<A,B> {
	B apply(A a);
}
