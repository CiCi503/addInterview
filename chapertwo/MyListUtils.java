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
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }
    /**
     * 两个有序单链表，找出它们结点value相同的结点
     */
    public static void printCommPart(Node head1, Node head2) {
        if (head1==null || head2 == null) {
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
     * 删除单链表的倒数第K个结点
     */
    /* 
     * 倒数第k个结点是正数第n-k+1个结点（序号为n-k），实际要找的是它（要删除的结点）的前一个结点
     * 一定要注意序号
     * 方法1：
     * 遍历单链表，每经过一个结点，k减去1，那么遍历到单链表末尾时，k可能有三种情况：
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
     * 方法2
     * 使用快慢指针
     */
    public static Node delKthNode2(Node head, int k) {
        if (head == null || k < 1) {// 空链表或者k不合理
            return head;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++) {
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
    
    
}
