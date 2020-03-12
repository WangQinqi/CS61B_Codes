public class LinkedListDeque<T> {

  private class TNode {
      T item;
      TNode pre;
      TNode next;

      public TNode(T x, TNode pp, TNode pn) {
          item = x;
          pre = pp;
          next = pn;
      }
  }

  private int size;
  private TNode sentinel_head;
  private TNode sentinel_tail;

  public LinkedListDeque() {
      sentinel_head = new TNode(null, null, null);
      sentinel_tail = new TNode(null, sentinel_head, null);
      sentinel_head.next = sentinel_tail;
      size = 0;
  }

  public LinkedListDeque(T x) {
      TNode first = new TNode(x, null, null);
      sentinel_head = new TNode(null, null, first);
      sentinel_tail = new TNode(null, first, null);
      first.pre = sentinel_head;
      first.next = sentinel_tail;
      size = 1;
  }

  /**
   * My solution of deep copy.
  public LinkedListDeque(LinkedListDeque other) {
      sentinel_head = new TNode(null, null, null);
      TNode p_old = other.sentinel_head.next;
      TNode p = sentinel_head;
      while(p_old.next != null) {
          p.next = new TNode(p_old.item, p, null);
          p = p.next;
          p_old = p_old.next;
      }
      sentinel_tail = new TNode(null, p, null);
      p.next = sentinel_tail;
      size = other.size();
  }
   */

  /** A better solution.*/
  public LinkedListDeque(LinkedListDeque other) {
      sentinel_head = new TNode(null, null, null);
      sentinel_tail = new TNode(null, sentinel_head,null);
      sentinel_head.next = sentinel_tail;
      size = 0;
      for(int i=0; i<other.size(); i++) {
          addLast((T) other.get(i));
      }
  }

  public int size() {
      return size;
  }

  /**
    * My solution for isEmpty.
  public boolean isEmpty() {
      if (sentinel_head.next != sentinel_tail)
          return false;
      return true;
  }
   */

  /** A better solution for isEmpty. */
  public boolean isEmpty() {
      return size == 0;
  }

  public void printDeque() {
      TNode p = sentinel_head.next;
      while(p.next != null) {
          System.out.print(p.item + " ");
          p = p.next;
      }
      System.out.println();
  }

  public void addFirst(T x) {
      sentinel_head.next = new TNode(x, sentinel_head, sentinel_head.next);
      sentinel_head.next.next.pre = sentinel_head.next;
      size++;
  }

  public void addLast(T x) {
      sentinel_tail.pre = new TNode(x, sentinel_tail.pre, sentinel_tail);
      sentinel_tail.pre.pre.next = sentinel_tail.pre;
      size++;
  }

  public T removeFirst() {
      sentinel_head.next = sentinel_head.next.next;
      sentinel_head.next.pre = sentinel_head;
      size--;
      return sentinel_head.next.item;
  }

  public T removeLast() {
      sentinel_tail.pre = sentinel_tail.pre.pre;
      sentinel_tail.pre.next = sentinel_tail;
      size--;
      return sentinel_tail.pre.item;
  }

  public T get(int index) {
      if (index >= size || index < 0)
          return null;
      TNode p = sentinel_head.next;
      while (index-- != 0)
          p = p.next;
      return p.item;
  }

  private TNode getRecursive(TNode node, int index) {
      if (index == 0)
          return node;
      return getRecursive(node.next, --index);
  }

  public T getRecursive(int index) {
      if (index >= size || index < 0)
          return null;
      return getRecursive(sentinel_head.next, index).item;
  }

}
