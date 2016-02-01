package chaperthree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
     * ���������еĶ����������Ը���ǰ��ͺ�������ؽ�����Щ��������ǰ��ͺ���������ͬ�ģ� ���磺 2 / 1 ������ǰ�������������� [1, 2]
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
    public static void mirrorRecursively(TreeNode treeRoot) {
        if (treeRoot == null) {
            return;
        }
        TreeNode temp = treeRoot.left;
        treeRoot.left = treeRoot.right;
        treeRoot.right = temp;
        if (treeRoot.left != null) {// ���Ӳ���Ҷ�ӽ��
            mirrorRecursively(treeRoot.left);
        }
        if (treeRoot.right != null) {// �Һ��Ӳ���Ҷ�ӽ��
            mirrorRecursively(treeRoot.right);
        }
    }

    /**
     * ��Ŀ���������´�ӡ��������ÿ����㣬ͬһ��Ľ�㰴�մ����ҵ�˳���ӡ
     */
    public static void printFromTopToBottom(TreeNode treeRoot) {
        if (treeRoot == null) {
            return;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();// Ҫ�����FIFO������
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
     * ��Ŀ��ջ��ѹ�롢��������
     * ���������������С���һ����ʾѹջ˳�����жϵڶ��������Ƿ�Ϊ��ջ�ĵ���˳��
     * ����ѹջ���־�����ȡ�
     * 
     * �ٸ����ӣ�
     * int[] arrPush = {1, 2, 3, 4, 5};
     * int[] arrPop = {2, 4, 3, 5, 1};
     * arrPop����һ����ջ���С�
     */
    public static boolean isPopOrder(int[] arrPop, int[] arrPush) {
        
        boolean isPossible = false;
        Stack<Integer> assistStack = new Stack<>();
        int popIndex = 0;
        assistStack.push(arrPush[0]);// ����ѹ���һ��Ԫ��
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
                if (pushIndex == arrPush.length && !assistStack.isEmpty()) {// û��ѹջԪ���˵���ջ��Ϊ��
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
     * ��Ŀ������һ���������飬�жϸ������Ƿ�Ϊĳ�����������ĺ���������
     * ���������������û����ͬ�����֡�
     * 
     * @param sequence  ���жϵ�����
     * @param start  ���п�ʼ������
     * @param end  ���н���������
     * @return  ����������Ƿ�Ϊ������������һ�������������
     */
    /*
     * ����������(���������������������)����������һ�ÿ����������Ǿ����������ʵĶ������� 
     * ���������������գ��������������н���ֵ��С�����ĸ�����ֵ;
     * ���������������գ��������������н���ֵ���������ĸ�����ֵ; 
     * ������������Ҳ�ֱ�Ϊ������������
     * 
     * 
     * �������Ŀ�У��жϸ����������Ƿ���BST��ǰ��������У�root�������ǰ�棩
     */
    public static boolean verifySequenceBST(int[] sequence, int start, int end) {
        if (sequence == null) {
            return true;
        }
        if (start < 0 || end >= sequence.length) {
            throw new RuntimeException("Valid param range: start>=0 & end < sequence.length, Please check it!");
        }
     // �Ӻ�������Ľ������������Ԫ�����ǿ������BST
        // ����һ���жϣ�����ʡȥ���εݹ飬��ȻҲ���Բ�Ҫ��
        // ��������ж�Ҫ���жϲ����Ϸ���֮�󣬷������-4��-3�����Ĳ����Ϳ��ܳ������С�
        if (end - start <= 2) {
            return true;
        }
        int root = sequence[end];// �ҵ����ĸ��ڵ�
        int i = start;
        for (; i < end; i++) {// ���������ı����������iָ�����������ĵ�һ�����
            if (sequence[i] > root) {
                break;
            }
        }
        int j = i;
        for (; j < end; j++) {// ���������ı������������ĸ��ڵ�
            if (sequence[j] < root) {
                return false;
            }
        }
     // left��right�ĳ�ʼֵ����Ϊtrue���ǿ��ǵ�ֻ��һ��Ԫ��ʱ��Ӧ�÷���true����Ϊ������򵥵Ķ���������
        boolean left = true;
        if (start < i) {// �ݹ����������
            left = verifySequenceBST(sequence, start, i - 1);
        }
        boolean right = true;
        if (i < end) {// �ݹ����������
            right = verifySequenceBST(sequence, i, end - 1);
        }
        return left & right;
        
    }
    
    
    /**
     * ��Ŀ������һ�ö�������һ����������ӡ��bt�н��ֵ�ĺ�Ϊ��������������·����
     * �����ĸ��ڵ㿪ʼ����һֱ��Ҷ�ڵ��������Ľ���γ�һ��·����
     */
    /*
     * ������ ·���Ǹ���㵽Ҷ��㣬�ȷ��ʸ���㣬���Ҫ�����������
     * ͬʱ��Ҫʹ��һ�����ݽṹ�������㣬��ջ�ȽϺ��ʵġ�
     * ����Ҫһ��ͳ�Ʊ���������·���ϵ�ֵ
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
            for (TreeNode node : path) {// ��ջ����ջ����ӡԪ�أ�����ɾ��Ԫ��
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
       path.pop();// �ﵽ�˸���㣬�ڷ��صݹ�֮ǰ��Ҫ�����ڵĽ���ջ�е�������Ϊ���Ѿ�����·������
    }
    
    /**
     *  ��Ŀ������һ��BST������ת����һ�������˫������
     *  Ҫ���ܴ����κ��µĽ�㣬ֻ�ܵ������н��ָ���ָ��
     */
    /*
     * ����һ��ʹ��ջ��Ϊ�м������������������Ľ��
     * ������ǵݹ������������һ�µ�
     */
    public static TreeNode BST2DuLinkList(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> nodes = new Stack<>();
        TreeNode curr = root;
        TreeNode prev = null;// ��ʶǰһ����㣬��ΪҪ��λ
        
        boolean isHead = true;// Ϊ�˱�ʶͷ��
        
        while (curr != null || !nodes.isEmpty()) {
            if (curr != null) {
                nodes.push(curr);
                curr = curr.left;
            } else {
                curr = nodes.pop();
                if (isHead) {// DuLinkList��ͷ��
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
     * ��������ʹ�õݹ�
     * ����˼·��
     * 1.�������������˫��������������ͷ�ڵ㡣
     * 2.��λ��������˫�������һ���ڵ㡣
     * 3.�������������Ϊ�յĻ�������ǰroot׷�ӵ�����������
     * 4.�������������˫��������������ͷ�ڵ㡣
     * 5.�������������Ϊ�յĻ�����������׷�ӵ�root�ڵ�֮��
     * 6.���������������Ƿ�Ϊ��ȷ�����صĽڵ㡣���Ƿ�ֻ����������
     */
    public static TreeNode BST2DuLinkListRecursely(TreeNode treeRoot) {
        if (treeRoot == null) {
            return null;
        }
        if (treeRoot.left == null && treeRoot.right == null) {// ������������˵Ľ��
            return treeRoot;
        }
        // 1.
        TreeNode leftSubHead = BST2DuLinkList(treeRoot.left);// �������γɵ�˫����
        TreeNode curr = leftSubHead;
        // 2.
        while (leftSubHead != null && curr.right != null) {// �ҵ�������˫��������һ�����
            curr = curr.right;
        }
        // 3.
     // ֻ����������BST������ı������leftSubHeadΪnull
        if (leftSubHead != null) {
            curr.right = treeRoot;
            treeRoot.left = curr;
        }
        // 4. 
        TreeNode rightSubHead = BST2DuLinkList(treeRoot.right);
        // 5.
     // ֻ����������BST������ı������rightSubHeadΪnull
        if (rightSubHead != null) {
            rightSubHead.left = treeRoot;
            treeRoot.right = rightSubHead;
        }
        // 6.
        return leftSubHead != null ? leftSubHead : treeRoot;
    }
    
    /**
     * ��Ŀ����һ�ö��������ҳ��Ӹ��ڵ㵽Ҷ�ӽڵ������·����
     */
    /*
     * ˼·����������ȫ��·������Ŀ����һ�����뵽��������ķ�ʽ��
     * ���������������ÿһ���ʱ����Arraylist����Ӹ��ڵ㵽��ǰ�ڵ������·������ĵ㡣
     * Ȼ���ж������Ҷ�ӽڵ㣬��ô��ǰ��һ�����н⣬���������Ҫ���صĴ����档
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
           pathHelper(root.left, paths + "��" + root.left.value, result);
        }
        if (root.right != null) {
            pathHelper(root.right, paths + "��" + root.right.value, result);
        }
    }
    
    /**
     * ��Ŀ������һ�ö������ĸ���㣬���������ȡ�
     * �Ӹ���㵽Ҷ������ξ����Ľ�㣨������Ҷ��㣩�γ�����һ��·����
     * �·���ĳ���Ϊ���Ľ�㡣
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
     * ��Ŀ������һ�ö������ĸ��ڵ㣬�жϸ����Ƿ�Ϊƽ���������
     */
    /*
     * ����1������ǰ��ķ���treeDepth()
     * �÷��������ظ�������ͬ�������⣬���Ч�ʽϵ�
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
     * ����2���Ľ��㷨�����ؽ������ظ��������⡣
     * ���Ǻ������������ҡ��У��ȱ������˺��ӽ���ٱ������ڵ㡣
     * ����;�����в�����AVL��������ʱ���������̻������˳���
     * ���Բ��÷�������ֵ�ķ�ʽ����������Ƿ�ΪAVL�������㷨��ȡ-1��
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
