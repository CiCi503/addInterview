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
        for (int i = 0; i < k; i++) {// ��ǰ��k��
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
    /**
     * ɾ��˫����ĵ�����K�����
     */
    /*
     * �������ͬС�죬��ʱҪ����ǰ������ָ��
     * ͷ�����β�ڵ�����Ҫ�Ľ��
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
            head.prev = null;// ���Ҫ����head�����Բ�����ֱ��head.next.prev
        }
        if (k < 0) {
            curr = head;
            while (++k != 0) {
                curr = curr.next;
            }
            DoubleNode delNodeNext = curr.next.next;// Ҫɾ���Ľ�����һ�����
            curr.next = delNodeNext;
            if (delNodeNext != null) {// ɾ���Ĳ������һ�����������Ҫ�ı�ǰ��
                curr = delNodeNext.prev;
            }
        }
        return head;
    }
    /**
     * ɾ����������м���
     */
    /*
     * ������ɾ��ĳ���������Ҫ�ľ���Ҫ�ҵ�Ҫɾ��������һ�����
     */
    public static Node delMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        if (head.next.next == null) {// ������㣬ɾ����һ��
            return head.next;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {// �����������ż�������
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    /**
     * ɾ�������� a/b ���Ľ�㣨��ȡ����
     */
    /*
     * ����Ҫ�ҵ� a/b �������Ȼ���ҵ���ǰ��Ľ��
     */
    public static Node delNodeByRatio(Node head, int a, int b) {
        if (head == null || a < 1 || a > b) {
            return head;
        }
        Node curr = head;
        int listLen = getSize(head);
        int delIndex = (int)Math.ceil((double)a * listLen / b);// ��ȡ���ҵ�Ҫɾ���Ľ��
        // �������Ȳ�Ϊ0�������ȡ��������Ϊ0
        if (delIndex == 1) {// Ҫɾ������ͷ�ӵ�
            return head.next;
        }
        // �ҵ�Ҫɾ������ǰһ�����
        for (int i = 1; i < delIndex - 1 ; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return head;
    }
    /**
     * ��ȡ������ĳ���
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
    
    
    
    
}
