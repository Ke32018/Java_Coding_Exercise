import java.util.Scanner;

public class SanCha {
    /**
     * 定义构造三叉搜索树规则如下：
     * 每个节点都存有一个数，当插入一个新的数时，从根节点向下寻找，直到找到一个合适的空节点插入。查找的规则是：
     *
     *  如果数小于节点的数减去500，则将数插入节点的左子树
     *  如果数大于节点的数加上500，则将数插入节点的右子树
     *  否则，将数插入节点的中子树
     *
     * 给你一系列数，请按以上规则，按顺序将数插入树中，构建出一棵三叉搜索树，最后输出树的高度。
     */
    static class Node {
        int data;
        Node left, mid, right;
        public Node(int data) {this.data = data;}
    }


    static class TernarySearchTree {
        public Node insert(Node node, int data) {
            if (node == null) {
                return new Node(data);
            }
            if (data < node.data - 500) {
                node.left = insert(node.left, data);
            } else if (data > node.data + 500) {
                node.right = insert(node.right, data);
            } else {
                node.mid = insert(node.mid, data);
            }
            return node;
        }

        public int getHeight(Node node) {
            if (node == null) {return 0;}
            int leftHeight = getHeight(node.left);
            int rightHeight = getHeight(node.right);
            int midHeight = getHeight(node.mid);
            return Math.max(Math.max(leftHeight, midHeight), rightHeight) + 1;
        }

    }
    public static void main(String[] args) {
        // 创建一个三叉搜索树的类
        TernarySearchTree root = new TernarySearchTree();

        // 读取输入的整数
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] numbers = sc.nextLine().split(" ");
        Node rootNode = null;
        // 按照三叉搜索树的规则将他们插入到树中
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(numbers[i]);
            rootNode = root.insert(rootNode, number);
        }
        // 最后计算并输出树的高度
        int res = root.getHeight(rootNode);
        System.out.println(res);
    }
}
