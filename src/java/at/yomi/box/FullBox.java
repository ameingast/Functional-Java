package at.yomi.box;

public class FullBox<A> extends Box<A> {
    public FullBox(final A a) {
        this.a = a;
    }

    @Override
    public A openOr(final A a) {
        return this.a;
    }
}