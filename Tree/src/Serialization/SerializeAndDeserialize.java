package Serialization;

import Main.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class SerializeAndDeserialize {
    /**
     * pre order
     * */
    private static final int MARKER = Integer.MAX_VALUE;

    void serializeRec(TreeNode root, List<Integer> stream) {
        if (root == null) {
            stream.add(MARKER);
            return;
        }

        stream.add(root.data);
        serializeRec(root.left, stream);
        serializeRec(root.right, stream);
    }

    List<Integer> serialize(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        serializeRec(root, res);
        return res;
    }

    TreeNode deserialize(List<Integer> stream) {
        int val = stream.remove(0);
        if (val == MARKER) {
            return null;
        }

        TreeNode root = new TreeNode(val);
        root.left = deserialize(stream);
        root.right = deserialize(stream);
        return root;
    }
}
