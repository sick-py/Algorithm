package Bacis;

import Main.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class FindIdenticalTree {
    /**
     * The input is the root of a binary tree, which returns a list containing several binary tree nodes, and the subtree to these nodes is duplicated in the original binary tree.
     * How to do this question? Still the old trick, first think about what it should do for a certain node.
     * If you want to know whether the subtree rooted by itself is duplicated and whether it should be added to the result list, what information do you need to know?
     * 1. What does this binary tree (subtree) rooted in me look like(structure)?
     * 2. What do subtrees rooted in other nodes look like?
     *
     * This is called knowing yourself and knowing your enemy. I have to know what I look like, and I have to know what others look like, and then I can know if anyone repeats with me, right? OK, let's take a look one by one.
     *
     * First of all, how can I know what this binary tree based on myself looks like?
     * In fact, if you think of this, you can judge that this question needs to write code in the post-order position of the binary tree.
     *
     * Why? It's very simple. I want to know what the subtree with my own root looks like. Do I have to know what my left and right subtrees look like first? In addition, I will form the whole subtree? Does the appearance of the left and right subtree have to be passed back by the return value of the recursive function in the post-order position?
     *
     * Now, it is clear that post-order traversal is to be used, so how should a binary tree look like? We have actually written about serializing and deserializing binary trees in the previous article. The preorder/inorder/postorder/layer order traversal results of binary trees can describe the structure of binary trees. Then, I will use the postorder traversal result as the serialization result. You can serialize the binary tree by splicing strings. Look at the code:
     *
     * Now let's solve the second problem. I know what I look like, how can I know what other people look like? Only in this way can I know if there are other subtrees that repeat me, right? This is very simple. We use an external data structure to let each node store the serialization result of its own subtree. In this way, for each node, can we know whether there are any subtrees of other nodes that are duplicated with ourselves? ? The initial idea can use HashSet to record the serialization results of all subtrees, the code is as follows:
     *
     * However, there is a problem. If there are multiple duplicate subtrees, there must be duplication in the result set res, and the title requires that there is no duplication.
     * To solve this problem, you can upgrade HashSet to HashMap and record the occurrences of each subtree:
     * */
    HashMap<String, Integer> subTrees = new HashMap<>();
    LinkedList<TreeNode> res = new LinkedList<>();
    List<TreeNode> findDuplicate(TreeNode root) {
        serialize(root);
        return res;
    }

    private String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = serialize(root.left);
        String right = serialize(root.right);
        String self = left + "," + right + "," + root.data;

        int freq = subTrees.getOrDefault(self, 0);
        //even several duplicate, we only add once
        if (freq == 1) {
            res.add(root);
        }
        subTrees.put(self, freq + 1);
        return self;
    }

}
