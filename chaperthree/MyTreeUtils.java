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
     * 输入某二叉树的先序和中序遍历结果，要求重建二叉树。 
     * 注：遍历结果中不含相同的结点值。
     */
    /*
     * 先序遍历数组的第一个元素就是遍历树的根结点， 中序遍历数组的根节点两侧分别为左右子树的遍历
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
     *            前序遍历数组
     * @param stIndexPre
     *            前序遍历数组的开始索引
     * @param endIndexPre
     *            前序遍历数组的末尾索引
     * @param inorder
     *            中序遍历数组
     * @param stIndexIn
     *            中序遍历数组的开始索引
     * @param eIndexIn
     *            中序遍历数组的末尾索引
     * @param inMap
     *            存放中序遍历数组的元素及其索引的map
     * @return
     */
    private static TreeNode rebuildPreInHelper(int[] pre, int stIndexPre,
            int endIndexPre, int[] inorder, int stIndexIn, int eIndexIn,
            Map<Integer, Integer> inMap) {
        // 前序遍历的首索引是stIndexPre + 1，尾索引为 stIndexPre + 左子树结点数，
        // 某结点没有左孩子了，那么下一次递归会出现首索引大于尾索引的情况，这时应该停止
        if (stIndexPre > endIndexPre) {
            return null;
        }
        TreeNode root = new TreeNode(pre[stIndexPre]);
        int pos = inMap.get(pre[stIndexPre]);
        // 处理左子树，左子树结点数为 pos - sIndexIn
        root.left = rebuildPreInHelper(pre, stIndexPre + 1, stIndexPre + pos - stIndexIn,
                inorder, stIndexIn, pos - 1, inMap);
        // 处理右子树，前序遍历数组中右子树遍历开始的索引为左子树结束的索引加1
        root.right = rebuildPreInHelper(pre, stIndexPre + pos - stIndexIn + 1,
                endIndexPre, inorder, pos + 1, eIndexIn, inMap);
        return root;
    }

    /**
     * 已知某二叉树的中序和后序遍历数组，重建二叉树。
     */
    /*
     * 跟先序和中序重建类似，只是现在的根节点在后序遍历数组的最后一个， 关键点也是找到后序遍历数组的始末索引
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
        if (stIndexPost > endIndexPost) {// 道理同上
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
     * 由前序和后序遍历数组重建二叉树
     * @return
     */
    /*
     * 并不是所有的二叉树都可以根据前序和后序进行重建，有些二叉树的前序和后序结果是相同的，
     * 比如：    2
     *         /
     *        1
     * 该树的前后序遍历结果都是 [1, 2]
     * 若一二叉树除了左右节点之外，其他所有的结点都有左右孩子，那么可以根据前后序进行重建 
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
        if (stIndexPre == endIndexPre) {// 碰到没有左子树的情况，那么这一定是个叶节点，返回这个结点
            return root;
        }
     // 先序遍历数组中根节点的下一个结点是左子树的根结点，在后序遍历中是最后一个结点
        int pos = postMap.get(pre[++stIndexPre]);
        root.left = rebuildPrePostHelper(pre, stIndexPre, stIndexPre + pos - stIndexPost, 
                post, stIndexPost, pos, postMap);
        root.right = rebuildPrePostHelper(pre, stIndexPre + pos - stIndexPost + 1, 
                endIndexPre, post, pos + 1, endIndexPost - 1, postMap);
        return root;
    }

}
