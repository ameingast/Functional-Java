package at.yomi.mp.receiver;

public interface Receiver1<A> extends Receiver {
	void handle(A a);
}
