package Main;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    /** Overview
     * 1. Is it possible to get the answer by traversing the binary tree ? If possible, use a traverse function with external variables to achieve it. This is called the "traversal" mode of thinking.
     *
     * 2. Is it possible to define a recursive function to derive the answer to the original question through the answer to the sub-question (subtree) ? If possible, write the definition of this recursive function and make full use of the return value of this function. This is called the "decomposition problem" thinking mode.
     *
     * Regardless of which mindset you use, you need to think about:
     *
     * If a binary tree node is extracted separately, what does it need to do? When does it need to be done (pre/middle/post sequence position) ? You don't need to worry about other nodes, the recursive function will help you perform the same operation on all nodes.
     *
     * */


    /** In-depth understanding of the pre, in and post order traverse
     * Regardless of the so-called pre, in and post order, just look at the traverse, it is a function that can traverse all nodes of the binary tree, which is essentially the same as you traverse arrays or linked lists:
     * The traversal of single-linked lists and arrays can be iterative or recursive. The structure of a binary tree is nothing more than a binary linked list . Since there is no way to simply rewrite it into an iterative form, it is generally said that the traversal framework of a binary tree refers to the recursive form.
     *
     * You also noticed that as long as it is a recursive form of traversal, there can be a pre-order position and a post-order position, which are before and after the recursion, respectively.
     * The so-called pre-order position is when you just enter a node (element), and the post-order position is when you are about to leave a node (element).
     * For example, if you are asked to print the values ​​​​of all nodes on a singly linked list in reverse order, what do you do?
     *
     * Of course, there are many ways to implement it, but if you have a thorough understanding of recursion, you can use post-order positions to operate:
     * void traverse(linkedNode head) {
     *     if (head == null) return;
     *     traverse(head.next);
     *     print(head);
     * }
     *
     * But I want to say that the pre, in and post order are three special time points for processing each node in the process of traversing the binary tree , not just three Lists with different orders:
     *
     * The code at the pre-order position is executed when it just enters a binary tree node, do it before entering other nodes
     * The code at the post-order position is executed when it is about to leave a binary tree node, after traversing all its children
     * The code in the inorder position is executed when the left subtree of a binary tree node has been traversed and the right subtree is about to be traversed.
     *
     * All the problems of the binary tree are to allow you to inject ingenious code logic in the front, middle and back order positions to achieve your own goals. You only need to think about what each node should do, and leave the rest to the binary tree traversal framework. Recursion will do the same on all nodes .
     * */


    /** Two Ways of Solving problems
     * The recursive solution to the binary tree problem can be divided into two types of ideas. The first type is to traverse the binary tree to get the answer, and the second type is to calculate the answer by decomposing the problem. These two types of ideas correspond to Backtracking Algorithm Core Frameworkand Dynamic Programming Core Framework.
     *
     * 1. Is it possible to get the answer by traversing the binary tree ? If possible, implement it with a traverse function and external variables.
     *
     * 2. Is it possible to define a recursive function to derive the answer to the original question through the answer to the sub-question (subtree) ? If possible, write the definition of this recursive function, and make full use of the return value of this function.
     *
     * 3. No matter which thinking mode you use, you must understand what each node of the binary tree needs to do, and when it needs to be done (front, middle, and back order) .
     *
     * The Maximum depth of the binary tree:
     * traverse the binary tree once, use an external variable to record the depth of each node, and take the maximum value to get the maximum depth. This is the idea of ​​traversing the binary tree to calculate the answer .
     *
     * This solution should be easy to understand, but why do you need to increase in the pre-order position depth and decrease in the post-order position depth?
     * Because as mentioned earlier, the pre-order position is when entering a node, and the post-order position is when leaving a node, depthrecord the depth of the current recursive node, you understand it as a pointer walking on the binary tree, so of course it should be like this maintain
     * As for resthe update of , you can put it in the front, middle and back order positions, as long as you ensure that it is after entering the node and before leaving the node
     * */
    int res0 = 0;
    int depth0 = 0;
    void maxDepth0(TreeNode root) {
        if (root == null) {
            return;
        }
        depth0++;
        if (root.left == null && root.right == null) {
            res0 = Math.max(res0, depth0);
        }
        maxDepth0(root.left);
        maxDepth0(root.right);
        depth0--;
    }

    /**
     * Of course, you can easily find that the maximum depth of a binary tree can be derived from the maximum depth of the subtree, which is the idea of ​​decomposing the problem to calculate the answer .
     *
     * As long as the definition of the recursive function is clear, this solution is not difficult to understand, but why is the main code logic concentrated in the post-order position?
     *
     * Because the core of this idea is that you can indeed deduce the depth of the original tree through the maximum depth of the subtree, so of course you must first use the definition of the recursive function to calculate the maximum depth of the left and right subtrees, and then deduce the maximum depth of the original tree, mainly Logic is naturally placed in the postorder position.
     * */
    int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth1(root.left);
        int right = maxDepth1(root.right);
        return 1 + Math.max(left, right);
    }


    /** Another example
     * If you understand the two ways of thinking about the maximum depth problem, then let's look back at the most basic binary tree front, middle and back order traversal , such as calculating the result of the preorder traversal.
     * The solution we are familiar with is to use the idea of ​​"traversal". I think there should be nothing to say:
     * */
    List<Integer> res1 = new LinkedList<>();

    void preOrder0(TreeNode root) {
        if (root == null) {
            return;
        }
        res1.add(root.data);
        preOrder0(root.left);
        preOrder0(root.right);
    }
    /**
     * But can you use the idea of "decomposing the problem" to calculate the result of preorder traversal? don't use auxiliary functions like traversethis and any external variables, just use the preorder Traverse function given in the title to solve the problem recursively, will you?
     * We know that the characteristic of pre-order traversal is that the value of the root node is ranked first, followed by the pre-order traversal results of the left subtree, and finally the pre-order traversal results of the right subtree:
     * Then this can decompose the problem, the preorder traversal result of a binary tree = the root node + the preorder traversal result of the left subtree + the preorder traversal result of the right subtree
     * */
    List<Integer> preorderTraverse(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        res.add(root.data);
        res.addAll(preorderTraverse(root.left));
        res.addAll(preorderTraverse(root.right));
        return res;
    }


    /** What is special about postorder
     * Before talking about the post-order position, let’s briefly talk about the in-order and pre-order.
     *
     * The in-order position is mainly used in the BST scene, and you can completely regard the in-order traversal of the BST as traversing an ordered array.
     * The pre-order position itself has no special properties. The reason why you find that many questions seem to be written in the pre-order position is actually because we are used to writing codes that are not sensitive to the front, middle and rear order positions in the pre-order position. That's all.
     *
     * You can find that the code execution in the pre-order position is top-down, while the code execution in the post-order position is bottom-up:
     * This is not surprising, because at the beginning of this article, it is said that the pre-order position is the moment when the node has just entered, and the post-order position is the moment when the node is about to leave back.
     *
     * But there is a big mystery here, which means that the code in the preorder position can only obtain the data passed by the parent node from the function parameters, while the code in the postorder position can not only obtain the parameter data, but also obtain the subtree passed through the function return value data back.
     *
     * To give you a concrete example, now give you a binary tree, I ask you two simple questions:
     * 1. If the root node is regarded as the first layer, how to print out the level number of each node?
     * 2. How to print out the number of nodes in the left and right subtrees of each node?
     *
     * Combining these two simple questions, you can taste the characteristics of the post-order position. Only the post-order position can obtain the information of the subtree through the return value.
     * In other words, once you find that the topic is related to the subtree, there is a high probability that you need to set a reasonable definition and return value for the function, and write the code in the post-order position .
     * */
    void ques0(TreeNode root, int level/* start from 0*/) {
        if (root == null) {
            return;
        }
        System.out.print(level);
        ques0(root.left, level + 1);
        ques0(root.right, level + 1);
    }
    int ques1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = ques1(root.left);
        int right = ques1(root.right);
        System.out.println(left);
        return left + right + 1;
    }



    /**  let's look at how the position of the postorder works in the actual question, and briefly talk about the 543th question " The diameter of the binary tree", allowing you to calculate the longest diameter length of a binary tree
     *
     * */
    int maxDiameter = 0;
    int maxD(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxD(root.left);
        int right = maxD(root.right);
        maxDiameter = Math.max(left + right + 1, maxDiameter);
        return 1 + Math.max(left, right);
    }

    /** Level order
     *
     *
     * */
    void levelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode curr = q.poll();
                //do something like
                System.out.println(curr.data);
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
            }
        }
    }

    class review {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int left = maxDepth(root.left);
            int right = maxDepth(root.right);

            return Math.max(left, right) + 1;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}