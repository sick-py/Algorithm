public class Main {
    /**
     * An array is a collection of elements of the same data type stored in contiguous memory locations. Each element in an array can be accessed using an index, which is an integer value that represents the position of the element in the array.
     * Arrays have several advantages, such as constant-time random access to elements, and compact storage of elements. However, they also have some limitations, such as fixed size and the need to shift elements when inserting or deleting elements in the middle of the array.
     *
     * a static array is an array with a fixed size that is allocated at compile time. The size of the array must be known at compile time, and cannot be changed at runtime.
     *
     * Static arrays are usually allocated on the stack, which means that they are automatically deallocated when they go out of scope.
     *
     * Static arrays have the advantage of fast access to elements, as the elements are stored in contiguous memory locations. However, they have the limitation of a fixed size, which can be a problem if you need to store more elements than the size of the array. In addition, static arrays can be inefficient if you need to insert or delete elements in the middle of the array, as this requires shifting the remaining elements.
     *
     *
     * A dynamic array is an array whose size can be changed at runtime. In most programming languages, dynamic arrays are implemented using pointers and memory allocation functions.
     *
     * To create a dynamic array, you first allocate a block of memory on the heap using a memory allocation function, such as malloc in C or new in C++. The size of the block is determined by the number of elements you want to store in the array, multiplied by the size of each element.To free the memory allocated for a dynamic array, you must use the corresponding deallocation function, such as free in C or delete in C++. Here's an example of how to free the memory allocated for the dynamic array created above:
     *
     * Dynamic arrays have the advantage of flexibility, as they can be resized as needed. However, they have the disadvantage of slower access to elements than static arrays, as the elements are not necessarily stored in contiguous memory locations. In addition, dynamic arrays require explicit memory management, which can lead to memory leaks or segmentation faults if not handled correctly.
     * */
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}