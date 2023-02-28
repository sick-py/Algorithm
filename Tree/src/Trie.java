public class Trie {
    /**
     *  A trie is a type of search tree where nodes are usually keyed by strings1. It can be used to efficiently search for keys starting with a specific prefi. For example, if you have a trie with keys “cat”, “car”, “dog” and “door”, you can quickly find all the keys that start with “d” by following the branch that corresponds to that letter.
     *
     *  To implement a trie, you need to create a root node with a constructor that can store child pointers for each symbol in the alphabet. Then you can insert strings into the trie by following the child pointers that match each character of the string, and creating new nodes if needed. To search for a string in the trie, you can also follow the child pointers that match each character of the string, and return true if you reach the end of the string and false otherwise.
     * */
    class TrieNode {
        boolean isLeaf;
        TrieNode[] children;

        public TrieNode() {
            isLeaf = false;
            children = new TrieNode[26];
        }
    }

    TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isLeaf = true;
    }

    public boolean search(String word) {
        return helper(word, 0, root);
    }

    private boolean helper(String word, int index, TrieNode cur) {
        if (index == word.length()) {
            return cur.isLeaf;
        }
        char c = word.charAt(index);
        int i = c - 'a';
        if (cur.children[i] == null) {
            return false;
        }
        return helper(word, index + 1, cur.children[i]);
    }

    public boolean startsWith(String prefix) {
        return helper2(prefix, 0, root);
    }

    private boolean helper2(String word, int index, TrieNode cur) {
        if (index == word.length()) {
            return true;
        }
        char c = word.charAt(index);
        int i = c - 'a';
        if (cur.children[i] == null) {
            return false;
        }
        return helper2(word, index + 1, cur.children[i]);
    }
}
