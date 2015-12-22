package chapertwo;

public class MyListUtils {
    
    /**
     * �������������
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
     * �������������ҳ����ǽ��value��ͬ�Ľ��
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
     * ɾ��������ĵ�����K�����
     */
    /* 
     * ������k�������������n-k+1����㣨���Ϊn-k����ʵ��Ҫ�ҵ�������Ҫɾ���Ľ�㣩��ǰһ�����
     * һ��Ҫע�����
     * ����1��
     * ����������ÿ����һ����㣬k��ȥ1����ô������������ĩβʱ��k���������������
     * k=0,˵���������ͷ������Ҫɾ���Ľ��
     * k>0,k������������ĳ��Ȼ�Ҫ�󣬲�������������head�ͺ���
     * k<0,����������ʱ��k����k-n,��ô�ٴδ�ͷ��ʼ������ÿ����һ�����ʱ��k����1��
     * ��k=0��Ӧ�Ľ�����Ҫɾ���Ľ��
     */
    public static Node delKthNode(Node head, int k) {
        if (head == null || k < 1) {// ���������k������
            return head;
        }
        Node curr = head; 
        while (curr != null) {
            curr = curr.next;
            k--;
        }// ѭ����ɺ�currΪnull
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
     * ����2
     * ʹ�ÿ���ָ��
     */
    public static Node delKthNode2(Node head, int k) {
        if (head == null || k < 1) {// ���������k������
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
        while (fast.next != null) {// ѭ��������slowָ�����Ҫɾ������ǰһ�����
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    
    
}
