package TrieOrPrefixTree;

public class AddAndSearchWordsDataStructure {


    class WordDictionary {

        class WordNode {
            boolean isLeaf;
            WordNode[] children;
            public WordNode() {
                isLeaf = false;
                children = new WordNode[26];
            }
        }

        WordNode root;

        public WordDictionary() {
            root = new WordNode();
        }

        public void addWord(String word) {
            WordNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (cur.children[index] == null) {
                    cur.children[index] = new WordNode();
                }
                cur = cur.children[index];
            }
            cur.isLeaf = true;
        }

        public boolean search(String word) {
            return helper(word, 0, root);
        }

        private boolean helper(String word, int index, WordNode cur) {
            if (index == word.length()) {
                return cur.isLeaf;
            }

            char c = word.charAt(index);
            if (c != '.') {
                int i = c - 'a';
                if (cur.children[i] == null) {
                    return false;
                }

                return helper(word, index + 1, cur.children[i]);
            }

            for (int i = 0; i < 26; i++) {
                if (cur.children[i] != null && helper(word, index + 1, cur.children[i])) {
                    return true;
                }
            }
            return false;
        }
    }
}
