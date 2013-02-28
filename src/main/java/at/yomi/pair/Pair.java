package at.yomi.pair;

public class Pair<A, B> {
    final public A first;
    final public B second;

    public Pair(final A first, final B second) {
        this.first = first;
        this.second = second;
    }

    @SuppressWarnings("unchecked")
    public boolean equals(final Object other) {
        if (!(other instanceof Pair))
            return false;

        final Pair otherPair = (Pair) other;

        if (first == null)
            return otherPair.first == null && second.equals(otherPair.second);
        else if (second == null)
            return otherPair.second == null && first.equals(otherPair.first);
        else
            return first.equals(otherPair.first)
                && second.equals(otherPair.second);

    }
}