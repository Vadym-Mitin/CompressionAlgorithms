package HuffmanCompression;


import java.util.StringTokenizer;

public class SerializeHuffmanTreeNode {

    public static String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private static void serialize(Node x, StringBuilder sb) {
        if (x == null) {
            sb.append("# ");
        } else {
            sb.append(x.getSymbol() + " ");
            sb.append(x.getCode() + " ");
            serialize(x.getLeftNode(), sb);
            serialize(x.getRightNode(), sb);
        }
    }

    public static Node deserialize(String s) {
        if (s == null || s.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(s, " ");
        return deserialize(st);
    }

    private static Node deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens())
            return null;
        Character symbol = null;
        String code = "";
        for (int i = 0; i < 2; i++) {
            String val = st.nextToken();
            if (val.equals("#")) {
                return null;
            }
            if (i == 0) {
                if (val.equals("null")) {
                    symbol = null;
                } else symbol = val.charAt(i);
            }
            if (i == 1) {
                if (val.length() > 1) {
                    code = "";
                } else code = val;
            }
        }
        Node root = new Node(symbol, code);
        root.setLeftNode(deserialize(st));
        root.setRightNode(deserialize(st));
        return root;
    }
}
