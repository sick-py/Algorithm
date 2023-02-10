package Construction;

import Main.TreeNode;

import java.util.HashMap;

public class PostOrderInOrder {
    /**
     * very familiar from the preorder and inorder
     * */
    HashMap<Integer, Integer> valToIndex = new HashMap<>();
    TreeNode build(int[] postOrder, int[] inOrder) {
        for (int i = 0; i < inOrder.length; i++) {
            valToIndex.put(inOrder[i], i);
        }
        return buildRec(postOrder, 0, postOrder.length,
                inOrder, 0, inOrder.length);
    }

    private TreeNode buildRec(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd) {
        if (postStart > postEnd) {
            return null;
        }

        int rootVal = postOrder[postEnd];
        int index = valToIndex.get(rootVal);
        int leftSize = index - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = buildRec(postOrder, postStart, postStart + leftSize - 1,
                inOrder, inStart, index - 1);
        root.right = buildRec(postOrder, postStart + leftSize, postEnd - 1,
                inOrder, index + 1, inEnd);
        return root;
    }
}
