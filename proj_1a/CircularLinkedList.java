public class CircularLinkedList<T> {

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
    private TNode sentinel;

    public CircularLinkedList() {
        sentinel = new TNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public CircularLinkedList(T x) {
        sentinel = new TNode(null, null, null);
        sentinel.next = new TNode(x, sentinel, sentinel);
        sentinel.pre = sentinel.next;
        size = 1;
    }

    /**
     * My solution of deep copy.
    public CircularLinkedList(CircularLinkedList other) {
        sentinel = new TNode(null, null, null);
        size = other.size();
        int i = size;
        TNode p = sentinel;
        TNode p_old = other.sentinel.next;
        while (i-- != 0) {
          p.next = new TNode(p_old.item, p, null);
          p = p.next;
          p_old = p_old.next;
        }
        p.next = sentinel;
        sentinel.pre = p;
    }
     */

    /** A better solution using the methods we build. */
    public CircularLinkedList(CircularLinkedList other) {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
        for (int i=0; i<other.size(); i++) {
            addLast((T) other.get(i));  // (T) is "cast" to make sure that the item is in type T, the code cannot correctly run without it.
        }
    }

    public int size() {
        return size;
    }

    /*public boolean isEmpty() {
        if (sentinel.next == sentinel)
            return true;
        return false;
    }*/

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        TNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public void addFirst(T x) {
        sentinel.next = new TNode(x, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size++;
    }

    public void addLast(T x) {
        sentinel.pre = new TNode(x, sentinel.pre, sentinel);
        sentinel.pre.pre.next = sentinel.pre;
        size++;
    }

    public T removeFirst() {
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        size--;
        return sentinel.next.item;
    }

    public T removeLast() {
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        size--;
        return sentinel.pre.item;
    }

    public T get(int index) {
        if (index >= size || index < 0)
            return null;
        TNode p = sentinel.next;
        while (index-- != 0)
            p = p.next;
        return p.item;
    }

    private T getRecursive(TNode p, int index) {
        if (index == 0)
            return p.item;
        return getRecursive(p.next, --index);
    }

    public T getRecursive(int index) {
        if (index >= size || index < 0)
            return null;
        return getRecursive(sentinel.next, index);
    }

}
