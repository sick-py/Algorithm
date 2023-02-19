public class MinHeap {
    /**
     * A min-Heap is a complete binary tree in which the value in each internal node is smaller than or equal to the values in the children of that node. Mapping the elements of a heap into an array is trivial: if a node is stored an index k, then its left child is stored at index 2k + 1 and its right child at index 2k + 2.
     *
     *
     * */
    public int[] heap;
    public int size;
    public int capacity;

    public MinHeap(int a) {
        heap = new int[a];
        capacity = a;
        size = 0;
    }

    private void swap(int f, int s) {
        int temp = heap[f];
        heap[f] = heap[s];
        heap[s] = temp;
    }

    public void insert(int value) {
        if (size >= capacity) {
            throw new IndexOutOfBoundsException("Heap is full.");
        }
        heap[size] = value;
        int cur = size;

        while (heap[cur] < heap[(cur - 1) / 2]) {
            swap(cur, (cur - 1) / 2);
            cur = (cur - 1) / 2;
        }
        size++;
    }

    boolean isLeaf(int pos) {
        if (pos > size / 2 && pos <= size) {
            return true;
        }
        return false;
    }

    void heapify(int pos) {
        if (isLeaf(pos)) {
            return;
        }

        if (heap[pos] > heap[pos * 2 + 1] || heap[pos] > heap[pos * 2 + 2]) {
            if (heap[pos * 2 + 1] < heap[pos * 2 + 2]) {
                swap(pos, pos * 2 + 1);
                heapify(pos * 2 + 1);
            }
            else {
                swap(pos, pos * 2 + 2);
                heapify(pos * 2 + 2);
            }
        }
    }

    public int removeMin() {
        int popped = heap[0];
        heap[0] = heap[--size];
        heapify(0);
        return popped;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}