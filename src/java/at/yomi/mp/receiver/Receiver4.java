package at.yomi.mp.receiver;

public interface Receiver4<A,B,C,D> extends Receiver3<A,B,C> {
	void handle(D d);
}
