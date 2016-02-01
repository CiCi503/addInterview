package chaperthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
     * 并不是所有的二叉树都可以根据前序和后序进行重建，有些二叉树的前序和后序结果是相同的， 比如： 2 / 1 该树的前后序遍历结果都是 [1, 2]
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
    public static void mirrorRecursively(TreeNode treeRoot) {
        if (treeRoot == null) {
            return;
        }
        TreeNode temp = treeRoot.left;
        treeRoot.left = treeRoot.right;
        treeRoot.right = temp;
        if (treeRoot.left != null) {// 左孩子不是叶子结点
            mirrorRecursively(treeRoot.left);
        }
        if (treeRoot.right != null) {// 右孩子不是叶子结点
            mirrorRecursively(treeRoot.right);
        }
    }

    /**
     * 题目：从上往下打印二叉树的每个结点，同一层的结点按照从左到右的顺序打印
     */
    public static void printFromTopToBottom(TreeNode treeRoot) {
        if (treeRoot == null) {
            return;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();// 要求可以FIFO的容器
        nodes.offer(treeRoot);
        while (!nodes.isEmpty()) {
            TreeNode curr = nodes.poll();
            System.out.print(curr.value + " ");
            if (curr.left != null) {
                nodes.offer(curr.left);
            }
            if (curr.right != null) {
                nodes.offer(curr.right);
            }
        }
    }

    /**
     * 题目：栈的压入、弹出数列
     * 输入两个整数序列。第一个表示压栈顺序，请判断第二个序列是否为该栈的弹出顺序。
     * 假设压栈数字均不相等。
     * 
     * 举个栗子：
     * int[] arrPush = {1, 2, 3, 4, 5};
     * int[] arrPop = {2, 4, 3, 5, 1};
     * arrPop就是一个弹栈序列。
     */
    public static boolean isPopOrder(int[] arrPop, int[] arrPush) {
        
        boolean isPossible = false;
        Stack<Integer> assistStack = new Stack<>();
        int popIndex = 0;
        assistStack.push(arrPush[0]);// 首先压入第一个元素
        int pushIndex = 1;
        if (arrPop != null && arrPush != null) {
            while (popIndex < arrPop.length) {
                while (pushIndex < arrPush.length && assistStack.peek() != arrPop[popIndex]) {
                    assistStack.push(arrPush[pushIndex++]);
                }
                while (popIndex < arrPop.length && assistStack.peek() == arrPop[popIndex]) {
                    assistStack.pop();
                    popIndex++;
                }
                if (pushIndex == arrPush.length && !assistStack.isEmpty()) {// 没有压栈元素了但是栈不为空
                    break;
                }
            }
            if (assistStack.isEmpty()) {
                isPossible = true;
            }
        }
        
        return isPossible;
     }

    /**
     * 题目：输入一个整数数组，判断该数组是否为某二叉搜索树的后序遍历结果
     * 假设输入的数组中没有相同的数字。
     * 
     * @param sequence  待判断的序列
     * @param start  数列开始的索引
     * @param end  数列结束的索引
     * @return  输入的序列是否为搜索二叉树的一个后序遍历数列
     */
    /*
     * 二叉搜索树(二叉查找树，二叉排序树)：它或者是一棵空树，或者是具有下列性质的二叉树： 
     * 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值;
     * 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值; 
     * 它的左、右子树也分别为二叉排序树。
     * 
     * 
     * 相近的题目有：判断给出的数列是否是BST的前序遍历数列（root结点在最前面）
     */
    public static boolean verifySequenceBST(int[] sequence, int start, int end) {
        if (sequence == null) {
            return true;
        }
        if (start < 0 || end >= sequence.length) {
            throw new RuntimeException("Valid param range: start>=0 & end < sequence.length, Please check it!");
        }
     // 从后序遍历的结果来看，两个元素总是可以组成BST
        // 加上一个判断，可以省去几次递归，当然也可以不要，
        // 但是这个判断要在判断参数合法化之后，否则出现-4，-3这样的参数就可能出现误判。
        if (end - start <= 2) {
            return true;
        }
        int root = sequence[end];// 找到树的根节点
        int i = start;
        for (; i < end; i++) {// 左子树结点的遍历结束后的i指向了右子树的第一个结点
            if (sequence[i] > root) {
                break;
            }
        }
        int j = i;
        for (; j < end; j++) {// 右子树结点的遍历，除了最后的根节点
            if (sequence[j] < root) {
                return false;
            }
        }
     // left和right的初始值设置为true，是考虑到只有一个元素时，应该返回true，因为它是最简单的二叉搜索树
        boolean left = true;
        if (start < i) {// 递归左子树结点
            left = verifySequenceBST(sequence, start, i - 1);
        }
        boolean right = true;
        if (i < end) {// 递归右子树结点
            right = verifySequenceBST(sequence, i, end - 1);
        }
        return left & right;
        
    }
    
    
    /**
     * 题目：输入一棵二叉树和一个整数，打印出bt中结点值的和为输入整数的所有路径。
     * 从树的根节点开始往下一直到叶节点所经过的结点形成一条路径。
     */
    /*
     * 分析： 路径是根结点到叶结点，先访问根结点，因此要用先序遍历，
     * 同时需要使用一个数据结构来保存结点，用栈比较合适的。
     * 还需要一个统计变量来叠加路径上的值
     */
    public static void findPath(TreeNode root, int expectedSum) {
        if (root == null) {
            return;
        }
        int pathSum = 0;
        Stack<TreeNode> path = new Stack<>();
        findPathHelper(root, path, pathSum, expectedSum);
    }

    private static void findPathHelper(TreeNode treeNode, Stack<TreeNode> path, int pathSum, int expectedSum) {
        pathSum += treeNode.value;
        path.push(treeNode);
        boolean isLeaf = treeNode.left == null && treeNode.right == null;
        if (isLeaf && pathSum == expectedSum) {
            System.out.println("Find a path:");
            for (TreeNode node : path) {// 从栈底向栈顶打印元素，但不删除元素
                System.out.print(node.value + " ");
            }
            System.out.println();
        }
        
       if (treeNode.left != null) {
           findPathHelper(treeNode.left, path, pathSum, expectedSum);
       }
       if (treeNode.right != null) {
           findPathHelper(treeNode.right, path, pathSum, expectedSum);
       }
       path.pop();// 达到了根结点，在返回递归之前，要把现在的结点从栈中弹出，因为它已经不在路径上了
    }
    
    /**
     *  题目：输入一颗BST，将其转换成一个排序的双向链表。
     *  要求不能创建任何新的结点，只能调整树中结点指针的指向
     */
    /*
     * 方法一：使用栈作为中间容器，存放中序遍历的结点
     * 步骤跟非递归遍历二叉树是一致的
     */
    public static TreeNode BST2DuLinkList(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;// 标识前一个结点，因为要移位
        
        boolean isHead = true;// 为了标识头部
        
        while (curr != null || !nodes.isEmpty()) {
            if (curr != null) {
                nodes.push(curr);
                curr = curr.left;
            } else {
                curr = nodes.pop();
                if (isHead) {// DuLinkList的头部
                    root = curr;
                    prev = root;
                    isHead = false;
                } else {
                    prev.right = curr;
                    curr.left = prev;
                    prev = curr;
                }
                curr = curr.right;
            }
       }
       return root;
    }
    
    /*
     * 方法二：使用递归
     * 解题思路：
     * 1.将左子树构造成双链表，并返回链表头节点。
     * 2.定位至左子树双链表最后一个节点。
     * 3.如果左子树链表不为空的话，将当前root追加到左子树链表。
     * 4.将右子树构造成双链表，并返回链表头节点。
     * 5.如果右子树链表不为空的话，将该链表追加到root节点之后。
     * 6.根据左子树链表是否为空确定返回的节点。（是否只有右子树）
     */
    public static TreeNode BST2DuLinkListRecursely(TreeNode treeRoot) {
        if (treeRoot == null) {
            return null;
        }
        if (treeRoot.left == null && treeRoot.right == null) {// 左子树的最左端的结点
            return treeRoot;
        }
        // 1.
        TreeNode leftSubHead = BST2DuLinkList(treeRoot.left);// 左子树形成的双链表
        TreeNode curr = leftSubHead;
        // 2.
        while (leftSubHead != null && curr.right != null) {// 找到左子树双链表的最后一个结点
            curr = curr.right;
        }
        // 3.
     // 只有右子树的BST，上面的遍历结果leftSubHead为null
        if (leftSubHead != null) {
            curr.right = treeRoot;
            treeRoot.left = curr;
        }
        // 4. 
        TreeNode rightSubHead = BST2DuLinkList(treeRoot.right);
        // 5.
     // 只有左子树的BST，上面的遍历结果rightSubHead为null
        if (rightSubHead != null) {
            rightSubHead.left = treeRoot;
            treeRoot.right = rightSubHead;
        }
        // 6.
        return leftSubHead != null ? leftSubHead : treeRoot;
    }
    
    /**
     * 题目：给一棵二叉树，找出从根节点到叶子节点的所有路径。
     */
    /*
     * 思路：如果是输出全部路径的题目，那一定会想到深度搜索的方式。
     * 在深度优先搜索的每一层的时候用Arraylist保存从根节点到当前节点的所有路径上面的点。
     * 然后判断如果是叶子节点，那么当前是一个可行解，保存在最后要返回的答案里面。
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        pathHelper(root, String.valueOf(root.value), result);
        return result;
    }

    private static void pathHelper(TreeNode root, String paths, List<String> result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.add(paths);
            return;
        }
        if (root.left != null) {
           pathHelper(root.left, paths + "→" + root.left.value, result);
        }
        if (root.right != null) {
            pathHelper(root.right, paths + "→" + root.right.value, result);
        }
    }
    
    /**
     * 题目：输入一棵二叉树的根结点，求该树的深度。
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
     * 最长路径的长度为树的结点。
     */
    public static int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left > right ? (left + 1) : (right + 1);
    }
    
    /**
     * 题目：输入一棵二叉树的根节点，判断该树是否为平衡二叉树。
     */
    /*
     * 方法1：利用前面的方法treeDepth()
     * 该方法存在重复访问相同结点的问题，因此效率较低
     */
    public static boolean isAVLTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }
        return isAVLTree(root.left) && isAVLTree(root.right);
    }
    
    /*
     * 方法2；改进算法：着重解决结点重复访问问题。
     * 考虑后序遍历，左→右→中，先遍历完了孩子结点再遍历根节点。
     * 当中途发现有不满足AVL树的性质时，整个过程会立即退出。
     * 可以采用返回特殊值的方式来检查子树是否为AVL树，本算法采取-1。
     */
    
    public static boolean isAVLTree2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return checkTreeHeight(root) == -1 ? false : true;
    }

    private static int checkTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lHeight = checkTreeHeight(root.left);
        if (lHeight == -1) {
            return -1;
        }
        int rHeight = checkTreeHeight(root.right);
        if (rHeight == -1) {
            return -1;
        }
        int diff = Math.abs(lHeight - rHeight);
        return diff > 1 ? -1 : Math.max(lHeight, rHeight) + 1;
    }
    
}
