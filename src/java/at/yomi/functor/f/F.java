package at.yomi.functor.f;

/**
 * A transformation or function from <code>A</code> to <code>B</code>. This type can be represented
 * using the Java 7 closure syntax.
 *
 * @version 2.6<br>
 *          <ul>
 *          <li>$LastChangedRevision: 9595 $</li>
 *          <li>$LastChangedDate: 2008-04-16 14:47:05 +1000 (Wed, 16 Apr 2008) $</li>
 *          </ul>
 */
public interface F<A, B> {
  /**
   * Transform <code>A</code> to <code>B</code>.
   *
   * @param a The <code>A</code> to transform.
   * @return The result of the transformation.
   */
  B f(A a);
}
