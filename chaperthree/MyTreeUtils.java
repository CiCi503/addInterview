package chaperthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/*
 * ���Ľṹ���£�
 *             6
 *           /   \
 *          5     2
 *         / \   /
 *        7   3 4
 *       /
 *      1
 */

public class MyTreeUtils {

    /**
     * ǰ��������������ݹ鷨��
     */
    public static void preOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + "  ");
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    /**
     * ǰ���������������������
     */
    public static void preOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        // ��ջ�ṹ��Ŷ������еĽ�㣬ȡ���ݹ�ջ
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        treeNodeStack.push(root);
        while (!treeNodeStack.isEmpty()) {
            root = treeNodeStack.pop();
            System.out.print(root.value + "  ");
            if (root.right != null) {
                treeNodeStack.push(root.right);// ������ҽ�㣬��ѹ���ҽ��
            }
            if (root.left != null) {
                treeNodeStack.push(root.left);
            }
        }
    }

    /**
     * ����������������ݹ鷨��
     */
    public static void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        System.out.print(root.value + "  ");
        inOrderTraverse(root.right);
    }

    /**
     * �����������������������
     */
    public static void inOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        while (!treeNodeStack.isEmpty() || root != null) {
            if (root != null) {
                treeNodeStack.push(root);
                root = root.left;
            } else {
                root = treeNodeStack.pop();
                System.out.print(root.value + "  ");
                root = root.right;
            }
        }
    }

    /**
     * ����������������ݹ鷨��
     */
    public static void postOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.print(root.value + "  ");
    }

    /**
     * �����������������������
     */
    public static void postOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        // ��Ҫ����ջ
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        Stack<TreeNode> postStack = new Stack<TreeNode>();
        treeNodeStack.push(root);
        while (!treeNodeStack.isEmpty()) {
            root = treeNodeStack.pop();
            postStack.push(root);
            if (root.left != null) {
                treeNodeStack.push(root.left);
            }
            if (root.right != null) {
                treeNodeStack.push(root.right);
            }
        }
        while (!postStack.isEmpty()) {
            System.out.print(postStack.pop().value + "  ");
        }
    }

    /**
     * ���������������������
     */
    public static void levelTravers(TreeNode root) {
        if (root == null) {
            return;
        }
        // ÿһ��Ԫ���д�Ŷ�Ӧ���еĽ��
        ArrayList<ArrayList<TreeNode>> res = new ArrayList<ArrayList<TreeNode>>();
        dfs(root, 0, res);
        for (ArrayList<TreeNode> levelNodes : res) {
            for (TreeNode node : levelNodes) {
                System.out.print(node.value + "  ");
            }
        }
    }

    private static void dfs(TreeNode root, int level,
            ArrayList<ArrayList<TreeNode>> res) {
        if (level >= res.size()) {
            res.add(new ArrayList<TreeNode>());
        }
        res.get(level).add(root);
        if (root.left != null) {
            dfs(root.left, level + 1, res);
        }
        if (root.right != null) {
            dfs(root.right, level + 1, res);
        }
    }

    /**
     * ���������������������
     */
    /*
     * refList�����δ��ÿһ��Ľ��
     */
    public static void levelTravers2(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> buffList = new LinkedList<TreeNode>();
        LinkedList<TreeNode> resList = new LinkedList<TreeNode>();
        buffList.add(root);
        while (!buffList.isEmpty()) {
            root = buffList.poll();
            resList.add(root);
            if (root.left != null) {
                buffList.add(root.left);
            }
            if (root.right != null) {
                buffList.add(root.right);
            }
        }
        while (!resList.isEmpty()) {
            System.out.print(resList.poll().value + "  ");
        }
    }

    /**
     * ����ĳ�������������������������Ҫ���ؽ��������� ע����������в�����ͬ�Ľ��ֵ��
     * 
     * @throws Exception
     */
    /*
     * �����������ĵ�һ��Ԫ�ؾ��Ǳ������ĸ���㣬 �����������ĸ��ڵ�����ֱ�Ϊ���������ı���
     */
    public static TreeNode rebuildTreepreIn(int[] preorder, int[] inorder)
            throws Exception {
        if (preorder == null || inorder == null) {
            return null;
        }
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return rebuildPreInHelper(preorder, 0, preorder.length - 1, inorder, 0,
                inorder.length - 1, inorderMap);
    }

    /**
     * 
     * @param preorder
     *            ǰ���������
     * @param startPre
     *            ǰ���������Ŀ�ʼ����
     * @param endPre
     *            ǰ����������ĩβ����
     * @param inorder
     *            �����������
     * @param startInorder
     *            �����������Ŀ�ʼ����
     * @param endInorder
     *            ������������ĩβ����
     * @param inorderMap
     *            ���������������Ԫ�ؼ���������map
     * @return
     * @throws Exception
     */
    private static TreeNode rebuildPreInHelper(int[] preorder, int startPre,
            int endPre, int[] inorder, int startInorder, int endInorder,
            Map<Integer, Integer> inorderMap) throws Exception {
        // ǰ���������������startPre + 1��β����Ϊ startPre + �������������
        // ĳ���û�������ˣ���ô��һ�εݹ���������������β�������������ʱӦ��ֹͣ
        if (startPre > endPre) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[startPre]);
        int rootIndex = inorderMap.get(preorder[startPre]);
        if (rootIndex < startInorder || rootIndex > endInorder) {// ǰ����������鲻ƥ��
            throw new Exception(
                    "inorderTraverse doesn't match preorderTraverse!");
        }
        // �����������������������Ϊ rootIndex - startInorder
        root.left = rebuildPreInHelper(preorder, startPre + 1,
                startPre + rootIndex - startInorder, inorder, startInorder,
                rootIndex - 1, inorderMap);
        // ������������ǰ�����������������������ʼ������Ϊ������������������1
        root.right = rebuildPreInHelper(preorder,
                startPre + rootIndex - startInorder + 1, endPre, inorder,
                rootIndex + 1, endInorder, inorderMap);
        return root;
    }

    /**
     * ��֪ĳ������������ͺ���������飬�ؽ���������
     */
    /*
     * ������������ؽ����ƣ�ֻ�����ڵĸ��ڵ��ں��������������һ���� �ؼ���Ҳ���ҵ�������������ʼĩ����
     */
    public static TreeNode rebuildTreeInPost(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        Map<Integer, Integer> inorderMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return rebuildInPostHelper(inorder, 0, inorder.length - 1, postorder, 0,
                postorder.length - 1, inorderMap);
    }

    private static TreeNode rebuildInPostHelper(int[] inorder, int startInorder,
            int endInorder, int[] postorder, int startPost, int endPost,
            Map<Integer, Integer> inMap) {
        if (startPost > endPost) {// ����ͬ��
            return null;
        }
        // TODO validate
        TreeNode root = new TreeNode(postorder[endPost]);
        int pos = inMap.get(postorder[endPost]);
        root.left = rebuildInPostHelper(inorder, startInorder, pos - 1,
                postorder, startPost, startPost + pos - startInorder - 1,
                inMap);
        root.right = rebuildInPostHelper(inorder, pos + 1, endInorder,
                postorder, startPost + pos - startInorder, endPost - 1, inMap);
        return root;
    }

    /**
     * ��ǰ��ͺ�����������ؽ�������
     * 
     * @return
     */
    /*
     * ���������еĶ����������Ը���ǰ��ͺ�������ؽ�����Щ��������ǰ��ͺ���������ͬ�ģ� 
     * ���磺 2 / 1 ������ǰ�������������� [1, 2]
     * ��һ�������������ҽڵ�֮�⣬�������еĽ�㶼�����Һ��ӣ���ô���Ը���ǰ��������ؽ�.
     */
    public static TreeNode rebuildTreePrePost(int[] preorder, int[] postorder) {
        if (preorder == null || postorder == null) {
            return null;
        }
        Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < postorder.length; i++) {
            postMap.put(postorder[i], i);
        }
        return rebuildPrePostHelper(preorder, 0, preorder.length - 1, postorder,
                0, postorder.length - 1, postMap);
    }

    private static TreeNode rebuildPrePostHelper(int[] preorder, int startPre,
            int endPre, int[] postorder, int startPost, int endPost,
            Map<Integer, Integer> postMap) {
        TreeNode root = new TreeNode(preorder[startPre]);
        if (startPre == endPre) {// ����û�����������������ô��һ���Ǹ�Ҷ�ڵ㣬����������
            return root;
        }
        // ������������и��ڵ����һ��������������ĸ���㣬�ں�������������һ�����
        // TODO validate
        int pos = postMap.get(preorder[++startPre]);
        root.left = rebuildPrePostHelper(preorder, startPre,
                startPre + pos - startPost, postorder, startPost, pos, postMap);
        root.right = rebuildPrePostHelper(preorder,
                startPre + pos - startPost + 1, endPre, postorder, pos + 1,
                endPost - 1, postMap);
        return root;
    }

    /**
     * ��Ŀ���������ö�����A��B���ж�B�Ƿ�ΪA���ӽṹ
     */
    public static boolean hasSubTree(TreeNode t1, TreeNode t2) {
        boolean result = false;
        if (t1 != null && t2 != null) {
            if (t1.value == t2.value) {
                result = check(t1, t2);
            }
            if (!result) {// ��������
                result = hasSubTree(t1.left, t2);
            }
            if (!result) {// �������Ҳ�������������
                result = hasSubTree(t1.right, t2);
            }
        }
        return result;
    }

    private static boolean check(TreeNode t, TreeNode t2) {
        if (t2 == null) {
            return true;
        }
        if (t == null) {
            return false;
        }
        if (t.value != t2.value) {
            return false;
        }
        return check(t.left, t2.left) && check(t.right, t2.right);// �ֱ�����������
    }

    /**
     * ��Ŀ�������һ������������һ�����������÷���������ľ���
     */
    /*
     * ʵ���Ͼ���ǰ������������û�б������ӽڵ�ͽ����������ӽ���λ��
     */
    public static void mirrorRecursively(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode temp = head.left;
        head.left = head.right;
        head.right = temp;
        if (head.left != null) {// ���Ӳ���Ҷ�ӽ��
            mirrorRecursively(head.left);
        }
        if (head.right != null) {// �Һ��Ӳ���Ҷ�ӽ��
            mirrorRecursively(head.right);
        }
    }

}
