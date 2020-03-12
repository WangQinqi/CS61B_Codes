public class SelfTestLinkedList {

    public static void main(String[] args) {
        System.out. println("Create a new list L.");
        LinkedListDeque<Integer> L = new LinkedListDeque<>(2);
        System.out. println("Create an empty list E.");
        LinkedListDeque<Integer> E = new LinkedListDeque<>();

        System.out. println("Add 1 to the front of L.");
        L.addFirst(1);
        System.out. println("Add 3 to the back of L.");
        L.addLast(3);
        System.out. println("Add 4 to the back of L.");
        L.addLast(4);

        System.out. println("Add 10 to the back of E.");
        E.addLast(10);

        System.out.println();

        System.out. println("Create a deep copy of list L as list C.");
        LinkedListDeque<Integer> C = new LinkedListDeque<>(L);

        System.out.print("List L is as follows: ");
        L.printDeque();
        System.out.print("List E is as follows: ");
        E.printDeque();
        System.out.print("List C is as follows: ");
        C.printDeque();

        System.out.println();

        System.out.println("Using iterative get: The second node of the list L is " + L.get(1) + ".");
        System.out.println("Using recursive get: The last node of the list L is " + L.getRecursive(3)+ ".");
        System.out.println("The size of list L is " + L.size() + ".");
        System.out.println("The first node of list E is " + E.get(0) + ".");
        System.out.println("The size of list E is " + E.size() + ".");

        System.out.println();

        System.out.println("Remove the first node from list L.");
        System.out.println(L.removeFirst());
        System.out.print("The new list L is as follows: ");
        L.printDeque();
        System.out.println("Remove the last node from list L.");
        System.out.println(L.removeLast());
        System.out.print("The new list L is as follows: ");
        L.printDeque();
        System.out.println("Remove the last node from list E.");
        System.out.println(E.removeLast());
        System.out.print("The new list E is as follows: ");
        E.printDeque();

        System.out.println();

        System.out.println("If L is empty: " + L.isEmpty());
        System.out.println("If E is empty: " + E.isEmpty());
    }

}
