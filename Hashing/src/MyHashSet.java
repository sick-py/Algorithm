import java.util.LinkedList;
import java.util.List;

public class MyHashSet {
    int buckets = 15000;
    List<Integer> arr[];

    private int hashFunction(int key) {
        return key % buckets;
    }

    public MyHashSet() {
        arr = new LinkedList[15000];
    }

    public void add(int key) {
        int idx = hashFunction(key);
        if (arr[idx] == null) {
            arr[idx] = new LinkedList<>();
        }

        List<Integer> list = arr[idx];

        if (!list.contains(key)) {
            list.add(key);
        }

    }

    public void remove(int key) {
        int idx = hashFunction(key);
        if (arr[idx] != null) {
            List<Integer> list = arr[idx];
            for (int i = 0; i < list.size(); i++) {
                if (key == list.get(i)) {
                    list.remove(i);
                }
            }
        }
    }

    public boolean contains(int key) {
        int idx = hashFunction(key);
        if (arr[idx] != null) {
            List<Integer> list = arr[idx];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == key) {
                    return true;
                }
            }
        }

        return false;
    }
}
