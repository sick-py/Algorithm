package Construction;

import Main.TreeNode;

import java.util.HashMap;

public class PreOrderInOrder {
    /**
     * just think about the idea directly, first think about what the root node should do.
     * Similar to the largest tree question, we must find a way to determine the value of the root node, make the root node, and then recursively construct the left and right subtrees .
     *
     * Finding the root node is very simple, the first value of the preorder traversal is the value preorder[0]of the root node.
     * The key is how to divide the preorder and inorder arrays into two halves through the value of the root node to construct the left and right subtrees of the root node?
     *
     * the numbers in the left of the root node in InOrder is the left subtree
     *
     * Because the topic says that the values of binary tree nodes do not have duplicate values, you can use a HashMap to store the mapping from elements to indexes, so that you can directly find the rootValcorresponding index:
     * */

    HashMap<Integer, Integer> valToIndex = new HashMap<>();

    TreeNode build(int[] preOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            valToIndex.put(inOrder[i], i);
        }
        return buildRec(preOrder, 0, preOrder.length,
                inOrder, 0, inOrder.length);
    }

    TreeNode buildRec(int[] preOrder, int preStart, int preEnd,
                   int[] inOrder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        int rootVal = preOrder[preStart];
        int index = valToIndex.get(rootVal);
        int leftSize = index - inStart;

        TreeNode root = new TreeNode(rootVal);

        root.left = buildRec(preOrder, preStart + 1, preStart + leftSize,
                inOrder, inStart, index - 1);

        root.right = buildRec(preOrder, preStart + leftSize + 1, preEnd,
                inOrder, index + 1, inEnd);

        return root;
    }
}
