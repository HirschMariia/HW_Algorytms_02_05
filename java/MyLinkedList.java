public class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void pushToIndex(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node newNode = new Node(value);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                tail = newNode;
            }
        }
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    public void removeLast() {
        if (size == 0) {
            throw new IllegalStateException("List is empty");
        }
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            removeFirst();
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
            size--;
        }
    }

    // Задача 2: Метод для определения наличия петли
    public boolean hasLoop() {
        if (head == null) {
            return false;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // Задача 3: Метод для нахождения К-ого элемента с конца
    public int getValueFromTail(int positionFromTail) {
        if (positionFromTail < 0 || positionFromTail >= size) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        Node front = head;
        Node back = head;
        for (int i = 0; i < positionFromTail; i++) {
            front = front.next;
        }
        while (front.next != null) {
            front = front.next;
            back = back.next;
        }
        return back.value;
    }
}
