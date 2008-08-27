package at.yomi.functor.f;

public interface Functor3<A, B, C, D> {
    D apply(A a, B b, C c);
}
