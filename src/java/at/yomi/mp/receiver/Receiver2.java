package at.yomi.mp.receiver;

public interface Receiver2<A,B> extends Receiver1<A> {
	void handle2(B b);
}
