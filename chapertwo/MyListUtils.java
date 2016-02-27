package chapertwo;

import java.util.ArrayDeque;

import javax.swing.LayoutStyle.ComponentPlacement;

public class MyListUtils {

    /**
     * 正向遍历单链表
     */
    public static void printList(Node head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    /**
     * 获取单链表的长度
     */
    public static int getSize(Node head) {
        if (head == null) {
            return 0;
        }
        Node curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    /**
     * 题目：两个有序单链表，找出它们结点value相同的结点
     */
    public static void printCommPart(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return;
        }
        System.out.println("The common node value:");
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.println(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    /*******************删除单链表中特定结点的关键是找到它前面的那个结点***********************************/
    /**
     * 题目：删除单链表的倒数第K个结点
     */
    /*
     * 倒数第k个结点是正数第n-k+1个结点，实际要找的是它（要删除的结点）的前一个结点 
     * 一定要注意序号 
     * 
     * 方法1：(遍历两遍单链表)
     * 遍历单链表，每经过一个结点，k减去1，那么遍历到单链表末尾时，
     * k可能有三种情况： 
     * k=0,说明单链表的头结点就是要删除的结点
     * k>0,k比整个单链表的长度还要大，参数不合理，返回head就好了
     * k<0,参数合理，此时的k等于k-n,那么再次从头开始遍历，每经过一个结点时，k加上1， 
     * 当k=0对应的结点就是要删除的结点
     * 
     * 遍历两遍单链表的话，还有一种方法，既然已经遍历完了单链表，那么一定知道了长度n，
     * 再遍历一遍，找到第n-k个结点。
     */
    public static Node delKthNode(Node head, int k) {
        if (head == null || k < 1) {// 空链表或者k不合理
            return head;
        }
        Node curr = head;
        while (curr != null) {
            curr = curr.next;
            k--;
        }// 循环完成后，curr为null
        if (k == 0) {
            return head.next;
        }
        if (k < 0) {
            curr = head;
            while (++k != 0) {
                curr = curr.next;

            }
            curr.next = curr.next.next;
        }
        return head;
    }

    /*
     * 方法2 使用快慢指针（遍历一遍单链表）
     */
    public static Node delKthNode2(Node head, int k) {
        if (head == null || k < 1) {// 空链表或者k不合理
            return head;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++) {// 提前走k步
            if (fast == null) {// 要操作的单链表的长度不够
                return head;
            }
            fast = fast.next;
        }
        while (fast.next != null) {// 循环结束后slow指向的是要删除结点的前一个结点
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    
    /**
     * 题目：在O(1)时间复杂度内删除指定结点
     */
    /*
     * 考虑要删除的结点是尾结点？头部结点？单结点？
     * 要删除的结点是尾结点时，必须从头开始寻找，时间复杂度O(n)
     * 平均时间复杂度为[O(1)*(n-1)+O(n)]/n,结果是O(1)
     * 注意：本解法并没有考虑要删除的结点不在给定的单链表中，这需要O(n)的时间复杂度来确认！
     */
    public static Node delSpecificNode(Node head, Node toBeDeleted) {
        if (head == null || toBeDeleted == null) {
            return head;
        }
     // 要删除的结点恰好是head结点，
        // 2种情况：①单结点情形，要删除的结点恰好就是该结点
        //          ②多结点情形，要删除的恰好是头部结点。
        if (head == toBeDeleted) {
            return head.next;
        } 
        Node curr = head;
        // 以下都是多结点的情况
        if (toBeDeleted.next == null) {// 尾部结点
            while (curr.next != toBeDeleted) {
                curr = curr.next;
            }
            curr.next = null;
        }  else {
            toBeDeleted.value = toBeDeleted.next.value;
            toBeDeleted.next = toBeDeleted.next.next;
        }
        return head;
        
    }

    /**
     * 题目：删除双链表的倒数第K个结点
     */
    /*
     * 与上面大同小异，这时要考虑前后两个指向 头结点与尾节点是重要的结点
     */
    public static DoubleNode delKthDoubleNode(DoubleNode head, int k) {
        if (head == null || k < 1) {
            return head;
        }
        DoubleNode curr = head;
        while (curr != null) {
            curr = curr.next;
            k--;
        }
        if (k == 0) {
            head = head.next;
            head.prev = null;// 结果要返回head，所以不可以直接head.next.prev
        }
        if (k < 0) {
            curr = head;
            while (++k != 0) {
                curr = curr.next;
            }
            DoubleNode delNodeNext = curr.next.next;// 要删除的结点的下一个结点
            curr.next = delNodeNext;
            if (delNodeNext != null) {// 删除的不是最后一个结点的情况还要改变前趋
                curr = delNodeNext.prev;
            }
        }
        return head;
    }

    /**
     * 题目：删除单链表的中间结点
     */
    /*
     * 单链表删除某个结点最重要的就是要找到要删除结点的上一个结点
     */
    public static Node delMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        if (head.next.next == null) {// 两个结点，删除第一个
            return head.next;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {// 奇数个结点与偶数个结点
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 题目：删除单链表 a/b 处的结点（上取整）
     */
    /*
     * 首先要找到 a/b 处在哪里，然后找到它前面的结点
     */
    public static Node delNodeByRatio(Node head, int a, int b) {
        if (head == null || a < 1 || a > b) {
            return head;
        }

        int listLen = getSize(head);
        int delIndex = (int) Math.ceil((double) a * listLen / b);// 上取整找到要删除的结点
        // 单链表长度不为0，因此上取整不可能为0
        Node dummy = new Node();// 可省去对删除第一个结点的情况的讨论
        dummy.next = head;
        Node curr = dummy;
        // 找到要删除结点的前一个结点
        for (int i = 1; i < delIndex; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return dummy.next;
    }

    /**
     * 题目：反转单链表
     * 要求：若链表长度为n，则时间复杂度为O(n)，空间复杂度为O(1)
     */
    public static Node reversList(Node head) {
        if (head == null) {
            return head;
        }
        Node curr = head;
        Node next = null;
        Node prev = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    /**
     * 题目：反转双向链表
     */
    /* 与反转单向链表的操作基本相同 */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null) {
            return head;
        }
        DoubleNode curr = head;
        DoubleNode next = null;
        DoubleNode prev = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        return prev;
    }
    /**
     * 题目：
     * 反转部分单链表,给定两个整数from和to，分别表示要反转链表部分的开始和结束。
     * 若链表长度为n，则时间复杂度为O(n),额外空间复杂度要求为O(1)
     * 举个栗子：
     * 单链表1->2->3->4->null
     * from=2,to=4
     * 输出 1->4->3->2->null
     * from=1,to=4
     * 输出 4->3->2->1->null
     */
    /*
     * 可以利用dummy结点来避开需要反转首元素的操作，
     * 删除和插入时，用得较多。
     * 另外，也可以讨论from是否为1，即是否从首结点开始反转
     * 思路：
     * 找到要反转部分的前一个和后一个结点，对这一部分进行反转，
     * 然后再接入到原单链表中。
     */
    public static Node reversePartList(Node head, int from, int to) {
        int len = -1;// 注意初始值
        Node preFrom = null;// 第from个结点的前一个结点
        Node postTo = null;// 第to个结点的后一个结点
        Node dummy = new Node();// 设置dummy结点
        dummy.next = head;
        Node curr = dummy;
        while (curr != null) {// 首先遍历一遍单链表，找到preFrom结点和postTo结点
            len++;
            preFrom = len == (from - 1) ? curr : preFrom;
            postTo = len == (to + 1) ? curr : postTo;
            curr = curr.next;
        }// 循环结束len等于链表的长度
        
        // 合法性检查
        if (head == null || from < 1 || to > len) {
            return head;
        }
        Node prev = preFrom.next;
        curr = prev.next;
        
      //(关键)反转部分的第一个结点反转后变成这一部分的最后一个结点，然后下一个结点就是postTo
        prev.next = postTo;
        Node next = null;
        while (curr != postTo) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //(关键)反转处理的prev指向了反转部分的第一个结点，它的前一个结点是preFrom
        preFrom.next = prev;
        return dummy.next;
    }
    
    /**
     * 题目：输入两个递增排序的单链表，合并这两个单链表，并使新链表中的结点仍然按递增排序。
     */
    /*
     * 解法1：递归法
     * 可以使用递归解决这个问题，
     * 测试特殊情况：
     * ①其中一个单链表为null，或者两个都是
     * ②单链表中有值相等的结点
     * 
     */
    public static Node mergeSortedListRecursion(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node mergedHead = null;
        if (head1.value <= head2.value) {
            mergedHead = head1;
            mergedHead.next = mergeSortedListRecursion(head1.next, head2);
        } else {
            mergedHead = head2;
            mergedHead.next = mergeSortedListRecursion(head1, head2.next);
        }
        return mergedHead;
    }
    
    /*
     * 解法2：循环迭代法
     */
    public static Node mergeSortedListIter(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2; 
        }
        Node mergedHead = head1.value <= head2.value ? head1 : head2;
        Node curr1 = mergedHead == head1 ? head1 : head2;// curr1指向包含合并结点头部的那条链
        Node curr2 = mergedHead == head1 ? head2 : head1;
        Node prev = null;
        Node next = null;
        while (curr1 != null && curr2 != null) {
            if (curr1.value <= curr2.value) {// 两个判断可以都是<=，但是不可以都是<
                prev = curr1;
                curr1 = curr1.next;

            } else {
                next = curr2.next;
                prev.next = curr2;
                curr2.next = curr1;
                prev = curr2;
                curr2 = next;

            }
       }
       prev.next = curr1 != null ? curr1 : curr2;
       return mergedHead;
    }
    
    /*
     * 解法3:
     * 使用一个dummyHead结点来处理特殊情况！
     */
    public static Node mergeSortedList(Node head1, Node head2) {
        Node dummyHead = new Node();
        Node curr = dummyHead;
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }
            curr = curr.next;
        }
        if (head1 != null) {
            curr.next = head1;
        }
        if (head2 != null) {
            curr.next = head2;
        }
        return dummyHead.next;
    }
    
    /**
     * 题目：复杂链表的复制。
     * 在复杂链表中，每个结点除了有一个next，还有一个引用sibling，指向
     */
    /*
     * 分三步：
     * 1.在原来的结点后面加一个各自的副本
     * 2.副本结点连接sibling结点
     * 3.将副本结点挑出来，返回它的头部
     */
    public static ComplexListNode CloneComlexList(ComplexListNode head) {
        if (head == null) {
            return head;
        }
        cloneNodes(head);
        connectSublingNodes(head);
        return splitList(head); 
    }

    private static void cloneNodes(ComplexListNode head) {
        ComplexListNode curr = head;
        while (curr != null) {
            ComplexListNode cloneCurr = new ComplexListNode();
            cloneCurr.value = curr.value;
            cloneCurr.next = curr.next;
            curr.next = cloneCurr;
            curr = cloneCurr.next;
        }
    }
    
    private static void connectSublingNodes(ComplexListNode head) {
        ComplexListNode curr = head;
        while (curr != null) {
            ComplexListNode cloneNode = curr.next; 
            if (curr.sibling != null) {//curr.sibling=null会发生空指针异常
                cloneNode.sibling = curr.sibling.next;
            }
            curr = cloneNode.next;
            
        }
    }
    
    private static ComplexListNode splitList(ComplexListNode head) {
        ComplexListNode curr = head;
        ComplexListNode cloneHead = null;
        ComplexListNode cloneNode = null;
        if (curr != null) {// 第一次的处理
            cloneHead = curr.next;
            cloneNode = cloneHead;
            curr.next = cloneNode.next;//这两条语句不能合并 
            curr = curr.next;// 
        }
        while (curr!= null) {
            cloneNode.next = curr.next;
            cloneNode = cloneNode.next;
            
            curr.next = cloneNode.next;
            curr = curr.next;
        }
        
        return cloneHead;
    }
    
    
    /**
     * 题目：输入两个链表，找出它们的第一个公共结点.
     */
    /*
     * 方法1：暴力法。
     * 在第1个链表上顺序遍历每个结点，每遍历一个结点时，
     * 在第2个链表上顺序遍历每个结点。
     * 假设两个链表长度分别为m和n，那么该算法的时间复杂度为O(mn).
     * 效率很低！！
     */
    
    /*
     * 方法2：以时间换空间。
     * 假如可以从结尾开始同时遍历两个结点，
     * 那么最后一个两链表相等的结点就是它们的第一个公共结点。
     * 但是单链表中的结点没有前趋引用，所以想到好两个栈，先把两个链表的结点分别
     * 放入栈中，然后同时弹栈，以找到第一个公共结点。
     * 时间复杂度和空间复杂度都是O(m+n)。
     */
    
    /*
     * 方法3：不用辅助空间。
     * 两个单链表的长度不一定相同，让长链表先走”长度差”这么多步，
     * 然后同时遍历两链表，它们是同步的，若没有公共结点，
     * 它们可以同时到达结尾。
     * 时间复杂度也是O(m+n)
     */
    public static Node findFirstCommonNode(Node phead1, Node phead2) {
        if (phead1 == null || phead2 == null) {
            return null;
        }
        int len1 = getSize(phead1);// getSize()方法用于求出链表长度，在前面已定义
        int len2 = getSize(phead2);
        int lenDif = len1 - len2;// 长度差
        Node curlongList = phead1;
        Node curshortList = phead2;
        if (lenDif < 0) {
           curlongList = phead2;
           curshortList = phead1;
           lenDif = -lenDif;
        }
        
        for (int i = 0; i < lenDif; i++) {// 长链表先走lenDif步
            curlongList = curlongList.next; 
        }
        // 当找到公共结点或者走到链表尽头时停止
        while (curshortList != null && curlongList != null && curlongList != curshortList) {
            curshortList = curshortList.next;
            curlongList = curlongList.next;
        }
        return curlongList;
    }
    
    /**
     * 题目：一个链表中包含环，如何找出环的入口结点？
     */
    /*
     * 思路：首先利用快慢指针定位到环中的某一结点，
     * 然后计算出环中含有的结点数nodesInLoop。
     * 设置两个指针，fast首先走nodesInLoop步，
     * 然后，slow和fast同时前进，它们相遇的结点就是环的入口结点。
     */
    
    // 定位到链表中环的某一个结点
    public static Node meetingInCircle(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (slow != fast) {
            if (slow == null || fast == null) {// 无环单链表
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 计算环中的结点个数
    public static int circleNodeNum(Node node) {
        if (node == null) {
            return 0;
        }
        int nodesInLoop = 1;// 初始值
        Node curr = node;
        while (curr.next != node) {
           nodesInLoop++;
           curr = curr.next;
        }
        return nodesInLoop;
    }
    
    public static Node findEntryNodeOfLoop(Node head) {
        if (head == null) {
            return null;
        }
        Node meetingNode = meetingInCircle(head);
        int nodesInLoop = circleNodeNum(meetingNode);
        if (nodesInLoop == 0) {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        for (int i = 0; i < nodesInLoop; i++) {
            fast = fast.next;
        }
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 题目：在一个排序的链表中，如何删除重复的结点？
     * 举个栗子：
     * 1→2→3→3→4→4→5 经过操作后变为 1→2→5
     */
    public static Node delDuplicateNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        int val;
        Node curr = head;
        Node prev = null;
        Node next = null;
        boolean isNeedDel = false;// 标记是否碰到了重复结点
        while (curr != null) {
            next = curr.next;
            // curr不是尾结点
            if (next != null && curr.value == next.value) {
                isNeedDel = true;
            }
            if (!isNeedDel) {//没有碰到重复结点
                prev = curr;
                curr = curr.next;
            } else {
                val = curr.value;
                isNeedDel = false;
                while (curr != null && curr.value == val) {// 连续多个重复值结点
                    curr = curr.next;
                }
                if (prev == null) {// 头部结点需要删除
                    head = curr;
                } else {
                    prev.next = curr;
                }
            }
        }
        return head;
    }
    
    
    /**
     * 题目：判断一个链表是否为回文结构
     */
    
    /*
     * 方法1：两次遍历单链表，时间复杂度为O(n)，
     * 需要一个栈（FILO），空间复杂度也为O(n)。
     * 这是最基本的方法。
     */
    public static boolean isPalindrome(Node head) {
        if (head == null) {
            return false;
        }
        boolean result = true;
        Node curr = head;
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (curr != null) {
            stack.push(curr.value);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            if (curr.value != stack.pop()) {
                result = false;
                break;
            }
            curr = curr.next;
        }
        return result;
    }
    /*
     * 方法2：将后一半结点压入栈中，然后再从头遍历
     * 比方法1，空间上节省了一半，但是优化不明显
     */
    public static boolean isPalindrome2(Node head) {
        if (head == null) {
            return false;
        }
        Node fast = head;
        Node slow = head;
        boolean result = true;
        
        while (fast.next != null && fast.next.next != null) {// 奇数/偶数个结点要分开
            fast = fast.next.next;
            slow = slow.next;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        while (slow.next != null) {
            slow = slow.next;
            stack.push(slow.value);
        }
        slow = head;
        while (!stack.isEmpty()) {
            if (slow.value != stack.pop()) {
                result = false;
                break;
            }
            slow = slow.next;
        }
        return result;
    }
    
    /*
     * 方法3:
     * 将链表后半部分反转，然后前后两个部分进行比较。
     * 举个栗子：
     * 1→2→3→2→1翻转为1→2→3←2←1。
     * 时间复杂度不变，空间复杂度为O(1)。
     */
    public static boolean isPalindrome3(Node head) {
        if (head == null) {
            return false;
        }
        
        Node fast = head;
        Node slow = head;
        boolean result = true; 
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }// 此时，slow指向中间结点
        
        // 反转单链表后半部分
       Node curr = slow.next; 
       slow.next = null;
       fast = curr.next;
       while (fast != null) {
           curr.next = slow;
           slow = curr;
           curr = fast;
           fast = fast.next;
       }
       curr.next = slow; // 此时curr在结尾结点处
       fast = curr;// fast保存最后一个结点
       slow = head;// slow指向单链表起点，从两头开始遍历
       while (slow != null && fast != null) {
           if (slow.value != fast.value) {
            result = false;
            break;
           }
           slow = slow.next;
           fast = fast.next;
       }
       // 将反转的链表复原
       fast = curr.next;
       Node curr2 = fast.next;
       curr.next = null;
       while (curr2 != null) {
           fast.next = curr;
           curr = fast;
           fast = curr2;
           curr2 = curr2.next;
       }
       fast.next = curr;
       return result;
    }
    
}
