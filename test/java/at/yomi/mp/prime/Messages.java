// package at.yomi.mp.prime;
//
// import at.yomi.mp.message.Cast;
// import at.yomi.mp.message.Info;
// import at.yomi.mp.message.IMessage;
// import at.yomi.mp.receiver.Receiver2;
//
// public class Messages {
//
// private static Messages instance = null;
//
// public static Messages get() {
// return instance == null ? instance = new Messages() : instance;
// }
//
// public class FoundPrimeCast extends Cast<Integer> {
// public FoundPrimeCast(final Integer content) {
// super(content);
// }
// }
//
// public class StartCalculatingMessage extends Info {}
//
// public class NoPrimeCast extends Cast<Integer> {
// public NoPrimeCast(final Integer content) {
// super(content);
// }
// }
//
// public class IsPrimeMessage extends IMessage<Integer,Integer> {
// public IsPrimeMessage(final Receiver2<NoPrimeCast,FoundPrimeCast> sender,
// final Integer content) {
// super(sender, content);
// }
// }
// }
