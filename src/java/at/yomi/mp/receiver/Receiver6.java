package at.yomi.mp.receiver;

public interface Receiver6<A,B,C,D,E,F> extends Receiver5<A,B,C,D,E> {
	void handle(F f);
}
