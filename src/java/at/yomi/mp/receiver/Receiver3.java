package at.yomi.mp.receiver;

public interface Receiver3<A,B,C> extends Receiver2<A,B> {
	void handle(C c);
}
