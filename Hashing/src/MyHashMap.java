import java.util.LinkedList;
import java.util.List;

class MyHashMap {
    class Pair {
        int key, value;

        public Pair() {
        }

        public Pair(int a, int b) {
            key = a;
             value = b;
        }
    }
    List<Pair> arr[];
    int buckets = 10000;

    public MyHashMap() {
        arr = new LinkedList[buckets];
    }

    private int hashFunction(int key) {
        return key % buckets;
    }

    public void put(int key, int value) {
        int idx = hashFunction(key);
        if (arr[idx] != null) {
            List<Pair> list = arr[idx];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key == key) {
                    remove(key);
                    break;
                }
            }
            list.add(new Pair(key, value));
            return;
        }

        List<Pair> list = new LinkedList<>();
        list.add(new Pair(key, value));
        arr[idx] = list;

    }

    public int get(int key) {
        int idx = hashFunction(key);
        if (arr[idx] != null) {
            List<Pair> list = arr[idx];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key == key) {
                    return list.get(i).value;
                }
            }
        }
        return -1;
    }

    public void remove(int key) {
        int idx = hashFunction(key);
        if (arr[idx] != null) {
            List<Pair> list = arr[idx];
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).key == key) {
                    list.remove(i);
                    break;
                }
            }
        }
    }
}