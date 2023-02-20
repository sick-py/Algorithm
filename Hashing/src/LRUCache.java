import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRUCache {
    /**
     * At this point, we can answer the question "Why do we have to use a doubly linked list" just now, because we need to delete the operation. Deleting a node not only needs to obtain the pointer of the node itself, but also needs to operate the pointer of its predecessor node, and the doubly linked list can support direct search of the predecessor, ensuring the time complexity of the operation O(1).
     *
     * Note that the double-linked list API we implemented can only be inserted from the tail, that is to say, the data near the tail is the most recently used, and the data near the head is the longest unused .
     *
     * With the implementation of the doubly linked list, we only need to combine it with the hash table in the LRU algorithm, and first build the code framework:
     * */
    public int capacity;
    public HashMap<Integer, Node> keyToValue;
    public DoubleList cache;

    class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    class DoubleList {
        public Node head, tail;
        public int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            size = 0;
            head.next = tail;
            tail.prev = head;
        }

        public void addLast(Node x) {
            x.next = tail;
            x.prev = tail.prev;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        public void remove(Node x) {
            //there must be this node in this situation
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        public Node removeHead() {
            //remove the LRU
            if (head.next == tail) {
                return null;
            }
            Node res = head.next;
            remove(res);
            return res;
        }

        public int size() {
            return size;
        }
    }


    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyToValue = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * Don't panic to implement getthe and putmethods of the LRU algorithm. Since we have to maintain a double-linked list cacheand map, it is easy to miss some operations. For example, keywhen , cachedelete the corresponding in Node, but forget to delete mapin key.
     * An effective way to solve this problem is to provide a layer of abstract API on top of these two data structures .
     *
     * It's a bit fantasy, but it's actually very simple, that is, try to make the main method getand putavoid directly mapoperating cachethe details of and . We can implement the following functions first:
     * */
    public void makeRecently(int key) {
        Node x = keyToValue.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    public void addRecently(int key, int value) {
        Node x = new Node(key, value);
        cache.addLast(x);
        keyToValue.put(key, x);
    }

    public void deleteKey(int key) {
        Node x = keyToValue.get(key);
        cache.remove(x);
        keyToValue.remove(key);
    }

    public void removeLeastUsed() {
        //Here we can answer the previous Q&A question "Why should we store both key and val in the linked list instead of just storing val". Note that in removeLeastRecentlythe function we need to use deletedNodeto get it deletedKey.
        Node x = cache.removeHead();
        keyToValue.remove(x.key);
    }

    public int get(int key) {
        if (!keyToValue.containsKey(key)) {
            return -1;
        }

        makeRecently(key);
        return keyToValue.get(key).val;
    }

    public void put(int key, int value) {
        if (keyToValue.containsKey(key)) { //case1 if it's updating
            deleteKey(key);
            addRecently(key, value);
            return;
        }

        if (keyToValue.size() == capacity) { //case2.1 out of cap
            removeLeastUsed();
        }

        addRecently(key, value); //case2
    }

}
