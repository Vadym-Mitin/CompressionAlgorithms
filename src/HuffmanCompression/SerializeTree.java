package HuffmanCompression;

import java.util.StringTokenizer;

public class SerializeTree {

    public static void main(String[] args) {
        TreeNode parent = new TreeNode(1);
        SerializeTree serializeTree = new SerializeTree();
        parent.left = new TreeNode(2);
        parent.right = new TreeNode(3);
        parent.right.left = new TreeNode(4);
        parent.right.right = new TreeNode(5);

        treeTraversal(parent);

        String serialized = serializeTree.serialize(parent);
        System.out.println(serialized);

        TreeNode newNode = serializeTree.deserialize(serialized);
        treeTraversal(newNode);

    }

    private static void treeTraversal(TreeNode node) {

        if (node.left != null || node.right != null) {
//            System.out.println(node.getChars());
            if (node.left != null) {
                System.out.println("left = " + node.left.val);
                treeTraversal(node.left);
            }
            if (node.right != null) {
                System.out.println("right = " + node.right.val);
                treeTraversal(node.right);
            }

        }
    }

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode x, StringBuilder sb) {
        if (x == null) {
            sb.append("# ");
        } else {
            sb.append(x.val + " ");
            serialize(x.left, sb);
            serialize(x.right, sb);
        }
    }

    public TreeNode deserialize(String s) {
        if (s == null || s.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(s, " ");
        return deserialize(st);
    }

    private TreeNode deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens())
            return null;
        String val = st.nextToken();
        if (val.equals("#"))
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(st);
        root.right = deserialize(st);
        return root;
    }
}
