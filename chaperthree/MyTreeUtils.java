package chaperthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/*
 * 树的结构如下：
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
     * 前序遍历二叉树（递归法）
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
     * 前序遍历二叉树（迭代法）
     */
    public static void preOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 用栈结构存放二叉树中的结点，取代递归栈
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        treeNodeStack.push(root);
        while (!treeNodeStack.isEmpty()) {
            root = treeNodeStack.pop();
            System.out.print(root.value + "  ");
            if (root.right != null) {
                treeNodeStack.push(root.right);// 后遍历右结点，先压入右结点
            }
            if (root.left != null) {
                treeNodeStack.push(root.left);
            }
        }
    }

    /**
     * 中序遍历二叉树（递归法）
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
     * 中序遍历二叉树（迭代法）
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
     * 后序遍历二叉树（递归法）
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
     * 后序遍历二叉树（迭代法）
     */
    public static void postOrderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        // 需要两个栈
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
     * 层遍历二叉树（遍历法）
     */
    public static void levelTravers(TreeNode root) {
        if (root == null) {
            return;
        }
        // 每一个元素中存放对应层中的结点
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
     * 层遍历二叉树（迭代法）
     */
    /*
     * refList中依次存放每一层的结点
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
     * 输入某二叉树的先序和中序遍历结果，要求重建二叉树。 注：遍历结果中不含相同的结点值。
     * 
     * @throws Exception
     */
    /*
     * 先序遍历数组的第一个元素就是遍历树的根结点， 中序遍历数组的根节点两侧分别为左右子树的遍历
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
     *            前序遍历数组
     * @param startPre
     *            前序遍历数组的开始索引
     * @param endPre
     *            前序遍历数组的末尾索引
     * @param inorder
     *            中序遍历数组
     * @param startInorder
     *            中序遍历数组的开始索引
     * @param endInorder
     *            中序遍历数组的末尾索引
     * @param inorderMap
     *            存放中序遍历数组的元素及其索引的map
     * @return
     * @throws Exception
     */
    private static TreeNode rebuildPreInHelper(int[] preorder, int startPre,
            int endPre, int[] inorder, int startInorder, int endInorder,
            Map<Integer, Integer> inorderMap) throws Exception {
        // 前序遍历的首索引是startPre + 1，尾索引为 startPre + 左子树结点数，
        // 某结点没有左孩子了，那么下一次递归会出现首索引大于尾索引的情况，这时应该停止
        if (startPre > endPre) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[startPre]);
        int rootIndex = inorderMap.get(preorder[startPre]);
        if (rootIndex < startInorder || rootIndex > endInorder) {// 前后序遍历数组不匹配
            throw new Exception(
                    "inorderTraverse doesn't match preorderTraverse!");
        }
        // 处理左子树，左子树结点数为 rootIndex - startInorder
        root.left = rebuildPreInHelper(preorder, startPre + 1,
                startPre + rootIndex - startInorder, inorder, startInorder,
                rootIndex - 1, inorderMap);
        // 处理右子树，前序遍历数组中右子树遍历开始的索引为左子树结束的索引加1
        root.right = rebuildPreInHelper(preorder,
                startPre + rootIndex - startInorder + 1, endPre, inorder,
                rootIndex + 1, endInorder, inorderMap);
        return root;
    }

    /**
     * 已知某二叉树的中序和后序遍历数组，重建二叉树。
     */
    /*
     * 跟先序和中序重建类似，只是现在的根节点在后序遍历数组的最后一个， 关键点也是找到后序遍历数组的始末索引
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
        if (startPost > endPost) {// 道理同上
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
     * 由前序和后序遍历数组重建二叉树
     * 
     * @return
     */
    /*
     * 并不是所有的二叉树都可以根据前序和后序进行重建，有些二叉树的前序和后序结果是相同的， 
     * 比如： 2 / 1 该树的前后序遍历结果都是 [1, 2]
     * 若一二叉树除了左右节点之外，其他所有的结点都有左右孩子，那么可以根据前后序进行重建.
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
        if (startPre == endPre) {// 碰到没有左子树的情况，那么这一定是个叶节点，返回这个结点
            return root;
        }
        // 先序遍历数组中根节点的下一个结点是左子树的根结点，在后序遍历中是最后一个结点
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
     * 题目：输入两棵二叉树A和B，判断B是否为A的子结构
     */
    public static boolean hasSubTree(TreeNode t1, TreeNode t2) {
        boolean result = false;
        if (t1 != null && t2 != null) {
            if (t1.value == t2.value) {
                result = check(t1, t2);
            }
            if (!result) {// 找左子树
                result = hasSubTree(t1.left, t2);
            }
            if (!result) {// 左子树找不到就找右子树
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
        return check(t.left, t2.left) && check(t.right, t2.right);// 分别检查左右子树
    }

    /**
     * 题目：请完成一个方法，输入一个二叉树，该方法输出它的镜像。
     */
    /*
     * 实际上就是前序遍历这棵树，没有遍历到子节点就交换两个孩子结点的位置
     */
    public static void mirrorRecursively(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode temp = head.left;
        head.left = head.right;
        head.right = temp;
        if (head.left != null) {// 左孩子不是叶子结点
            mirrorRecursively(head.left);
        }
        if (head.right != null) {// 右孩子不是叶子结点
            mirrorRecursively(head.right);
        }
    }

}
