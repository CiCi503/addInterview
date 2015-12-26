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
     * ����ĳ�������������������������Ҫ���ؽ��������� 
     * ע����������в�����ͬ�Ľ��ֵ��
     */
    /*
     * �����������ĵ�һ��Ԫ�ؾ��Ǳ������ĸ���㣬 �����������ĸ��ڵ�����ֱ�Ϊ���������ı���
     */
    public static TreeNode rebuildTreepreIn(int[] pre, int[] inorder) {
        if (pre == null || inorder == null) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return rebuildPreInHelper(pre, 0, pre.length - 1, inorder, 0,
                inorder.length - 1, inMap);
    }

    /**
     * 
     * @param pre
     *            ǰ���������
     * @param stIndexPre
     *            ǰ���������Ŀ�ʼ����
     * @param endIndexPre
     *            ǰ����������ĩβ����
     * @param inorder
     *            �����������
     * @param stIndexIn
     *            �����������Ŀ�ʼ����
     * @param eIndexIn
     *            ������������ĩβ����
     * @param inMap
     *            ���������������Ԫ�ؼ���������map
     * @return
     */
    private static TreeNode rebuildPreInHelper(int[] pre, int stIndexPre,
            int endIndexPre, int[] inorder, int stIndexIn, int eIndexIn,
            Map<Integer, Integer> inMap) {
        // ǰ���������������stIndexPre + 1��β����Ϊ stIndexPre + �������������
        // ĳ���û�������ˣ���ô��һ�εݹ���������������β�������������ʱӦ��ֹͣ
        if (stIndexPre > endIndexPre) {
            return null;
        }
        TreeNode root = new TreeNode(pre[stIndexPre]);
        int pos = inMap.get(pre[stIndexPre]);
        // �����������������������Ϊ pos - sIndexIn
        root.left = rebuildPreInHelper(pre, stIndexPre + 1, stIndexPre + pos - stIndexIn,
                inorder, stIndexIn, pos - 1, inMap);
        // ������������ǰ�����������������������ʼ������Ϊ������������������1
        root.right = rebuildPreInHelper(pre, stIndexPre + pos - stIndexIn + 1,
                endIndexPre, inorder, pos + 1, eIndexIn, inMap);
        return root;
    }

    /**
     * ��֪ĳ������������ͺ���������飬�ؽ���������
     */
    /*
     * ������������ؽ����ƣ�ֻ�����ڵĸ��ڵ��ں��������������һ���� �ؼ���Ҳ���ҵ�������������ʼĩ����
     */
    public static TreeNode rebuildTreeInPost(int[] inorder, int[] post) {
        if (inorder == null || post == null) {
            return null;
        }
        Map<Integer, Integer> inMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return rebuildInPostHelper(inorder, 0, inorder.length - 1, post, 0,
                post.length - 1, inMap);
    }
    
    private static TreeNode rebuildInPostHelper(int[] inorder, int stIndexIn,
            int endIndexIn, int[] post, int stIndexPost, int endIndexPost,
            Map<Integer, Integer> inMap) {
        if (stIndexPost > endIndexPost) {// ����ͬ��
            return null;
        }
        TreeNode root = new TreeNode(post[endIndexPost]);
        int pos = inMap.get(post[endIndexPost]);
        root.left = rebuildInPostHelper(inorder, stIndexIn, pos - 1, post,
                stIndexPost, stIndexPost + pos - stIndexIn - 1, inMap);
        root.right = rebuildInPostHelper(inorder, pos + 1, endIndexIn, post,
                stIndexPost + pos - stIndexIn, endIndexPost - 1, inMap);
        return root;
    }

    /**
     * ��ǰ��ͺ�����������ؽ�������
     * @return
     */
    /*
     * ���������еĶ����������Ը���ǰ��ͺ�������ؽ�����Щ��������ǰ��ͺ���������ͬ�ģ�
     * ���磺    2
     *         /
     *        1
     * ������ǰ�������������� [1, 2]
     * ��һ�������������ҽڵ�֮�⣬�������еĽ�㶼�����Һ��ӣ���ô���Ը���ǰ��������ؽ� 
     */
    public static TreeNode rebuildTreePrePost(int[] pre, int[] post) {
        if (pre == null || post == null) {
            return null;
        }
        Map<Integer, Integer> postMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return rebuildPrePostHelper(pre, 0, pre.length - 1, post, 0,
                post.length - 1, postMap);
    }
    
    private static TreeNode rebuildPrePostHelper(int[] pre, int stIndexPre,
            int endIndexPre, int[] post, int stIndexPost, int endIndexPost,
            Map<Integer, Integer> postMap) {
        TreeNode root = new TreeNode(pre[stIndexPre]);
        if (stIndexPre == endIndexPre) {// ����û�����������������ô��һ���Ǹ�Ҷ�ڵ㣬����������
            return root;
        }
     // ������������и��ڵ����һ��������������ĸ���㣬�ں�������������һ�����
        int pos = postMap.get(pre[++stIndexPre]);
        root.left = rebuildPrePostHelper(pre, stIndexPre, stIndexPre + pos - stIndexPost, 
                post, stIndexPost, pos, postMap);
        root.right = rebuildPrePostHelper(pre, stIndexPre + pos - stIndexPost + 1, 
                endIndexPre, post, pos + 1, endIndexPost - 1, postMap);
        return root;
    }

}
