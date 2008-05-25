package at.yomi.mp.receiver;

public interface Receiver5<A,B,C,D,E> extends Receiver4<A,B,C,D> {
	void receive(E e);
}
