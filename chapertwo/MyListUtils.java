package chapertwo;

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

    /**
     * 题目：删除单链表的倒数第K个结点
     */
    /*
     * 倒数第k个结点是正数第n-k+1个结点（序号为n-k），实际要找的是它（要删除的结点）的前一个结点 
     * 一定要注意序号 
     * 方法1：
     * 遍历单链表，每经过一个结点，k减去1，那么遍历到单链表末尾时，
     * k可能有三种情况： 
     * k=0,说明单链表的头结点就是要删除的结点
     * k>0,k比整个单链表的长度还要大，参数不合理，返回head就好了
     * k<0,参数合理，此时的k等于k-n,那么再次从头开始遍历，每经过一个结点时，k加上1， 
     * 当k=0对应的结点就是要删除的结点
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
     * 方法2 使用快慢指针
     */
    public static Node delKthNode2(Node head, int k) {
        if (head == null || k < 1) {// 空链表或者k不合理
            return head;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++) {// 提前走k步
            if (fast == null) {
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
        Node curr = dummy;// 处理的结点
        while (curr != null) {
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
    
    
}
