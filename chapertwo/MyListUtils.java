package chapertwo;

import javax.swing.LayoutStyle.ComponentPlacement;

public class MyListUtils {

    /**
     * �������������
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

    /**
     * ��Ŀ���������������ҳ����ǽ��value��ͬ�Ľ��
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

    /*******************ɾ�����������ض����Ĺؼ����ҵ���ǰ����Ǹ����***********************************/
    /**
     * ��Ŀ��ɾ��������ĵ�����K�����
     */
    /*
     * ������k�������������n-k+1����㣬ʵ��Ҫ�ҵ�������Ҫɾ���Ľ�㣩��ǰһ����� 
     * һ��Ҫע����� 
     * 
     * ����1��(�������鵥����)
     * ����������ÿ����һ����㣬k��ȥ1����ô������������ĩβʱ��
     * k��������������� 
     * k=0,˵���������ͷ������Ҫɾ���Ľ��
     * k>0,k������������ĳ��Ȼ�Ҫ�󣬲�������������head�ͺ���
     * k<0,����������ʱ��k����k-n,��ô�ٴδ�ͷ��ʼ������ÿ����һ�����ʱ��k����1�� 
     * ��k=0��Ӧ�Ľ�����Ҫɾ���Ľ��
     * 
     * �������鵥����Ļ�������һ�ַ�������Ȼ�Ѿ��������˵�������ôһ��֪���˳���n��
     * �ٱ���һ�飬�ҵ���n-k����㡣
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
     * ����2 ʹ�ÿ���ָ�루����һ�鵥����
     */
    public static Node delKthNode2(Node head, int k) {
        if (head == null || k < 1) {// ���������k������
            return head;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i < k; i++) {// ��ǰ��k��
            if (fast == null) {// Ҫ�����ĵ�����ĳ��Ȳ���
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
     * ��Ŀ����O(1)ʱ�临�Ӷ���ɾ��ָ�����
     */
    /*
     * ����Ҫɾ���Ľ����β��㣿ͷ����㣿����㣿
     * Ҫɾ���Ľ����β���ʱ�������ͷ��ʼѰ�ң�ʱ�临�Ӷ�O(n)
     * ƽ��ʱ�临�Ӷ�Ϊ[O(1)*(n-1)+O(n)]/n,�����O(1)
     * ע�⣺���ⷨ��û�п���Ҫɾ���Ľ�㲻�ڸ����ĵ������У�����ҪO(n)��ʱ�临�Ӷ���ȷ�ϣ�
     */
    public static Node delSpecificNode(Node head, Node toBeDeleted) {
        if (head == null || toBeDeleted == null) {
            return head;
        }
     // Ҫɾ���Ľ��ǡ����head��㣬
        // 2��������ٵ�������Σ�Ҫɾ���Ľ��ǡ�þ��Ǹý��
        //          �ڶ������Σ�Ҫɾ����ǡ����ͷ����㡣
        if (head == toBeDeleted) {
            return head.next;
        } 
        Node curr = head;
        // ���¶��Ƕ�������
        if (toBeDeleted.next == null) {// β�����
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
     * ��Ŀ��ɾ��˫����ĵ�����K�����
     */
    /*
     * �������ͬС�죬��ʱҪ����ǰ������ָ�� ͷ�����β�ڵ�����Ҫ�Ľ��
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
     * ��Ŀ��ɾ����������м���
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
     * ��Ŀ��ɾ�������� a/b ���Ľ�㣨��ȡ����
     */
    /*
     * ����Ҫ�ҵ� a/b �������Ȼ���ҵ���ǰ��Ľ��
     */
    public static Node delNodeByRatio(Node head, int a, int b) {
        if (head == null || a < 1 || a > b) {
            return head;
        }

        int listLen = getSize(head);
        int delIndex = (int) Math.ceil((double) a * listLen / b);// ��ȡ���ҵ�Ҫɾ���Ľ��
        // �������Ȳ�Ϊ0�������ȡ��������Ϊ0
        Node dummy = new Node();// ��ʡȥ��ɾ����һ���������������
        dummy.next = head;
        Node curr = dummy;
        // �ҵ�Ҫɾ������ǰһ�����
        for (int i = 1; i < delIndex; i++) {
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return dummy.next;
    }

    /**
     * ��Ŀ����ת������
     * Ҫ����������Ϊn����ʱ�临�Ӷ�ΪO(n)���ռ临�Ӷ�ΪO(1)
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
     * ��Ŀ����ת˫������
     */
    /* �뷴ת��������Ĳ���������ͬ */
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
     * ��Ŀ��
     * ��ת���ֵ�����,������������from��to���ֱ��ʾҪ��ת�����ֵĿ�ʼ�ͽ�����
     * ��������Ϊn����ʱ�临�Ӷ�ΪO(n),����ռ临�Ӷ�Ҫ��ΪO(1)
     * �ٸ����ӣ�
     * ������1->2->3->4->null
     * from=2,to=4
     * ��� 1->4->3->2->null
     * from=1,to=4
     * ��� 4->3->2->1->null
     */
    /*
     * ��������dummy������ܿ���Ҫ��ת��Ԫ�صĲ�����
     * ɾ���Ͳ���ʱ���õý϶ࡣ
     * ���⣬Ҳ��������from�Ƿ�Ϊ1�����Ƿ���׽�㿪ʼ��ת
     * ˼·��
     * �ҵ�Ҫ��ת���ֵ�ǰһ���ͺ�һ����㣬����һ���ֽ��з�ת��
     * Ȼ���ٽ��뵽ԭ�������С�
     */
    public static Node reversePartList(Node head, int from, int to) {
        int len = -1;// ע���ʼֵ
        Node preFrom = null;// ��from������ǰһ�����
        Node postTo = null;// ��to�����ĺ�һ�����
        Node dummy = new Node();// ����dummy���
        dummy.next = head;
        Node curr = dummy;
        while (curr != null) {// ���ȱ���һ�鵥�����ҵ�preFrom����postTo���
            len++;
            preFrom = len == (from - 1) ? curr : preFrom;
            postTo = len == (to + 1) ? curr : postTo;
            curr = curr.next;
        }// ѭ������len��������ĳ���
        
        // �Ϸ��Լ��
        if (head == null || from < 1 || to > len) {
            return head;
        }
        Node prev = preFrom.next;
        curr = prev.next;
        
      //(�ؼ�)��ת���ֵĵ�һ����㷴ת������һ���ֵ����һ����㣬Ȼ����һ��������postTo
        prev.next = postTo;
        Node next = null;
        while (curr != postTo) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        //(�ؼ�)��ת�����prevָ���˷�ת���ֵĵ�һ����㣬����ǰһ�������preFrom
        preFrom.next = prev;
        return dummy.next;
    }
    
    /**
     * ��Ŀ������������������ĵ������ϲ���������������ʹ�������еĽ����Ȼ����������
     */
    /*
     * �ⷨ1���ݹ鷨
     * ����ʹ�õݹ���������⣬
     * �������������
     * ������һ��������Ϊnull��������������
     * �ڵ���������ֵ��ȵĽ��
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
     * �ⷨ2��ѭ��������
     */
    public static Node mergeSortedListIter(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2; 
        }
        Node mergedHead = head1.value <= head2.value ? head1 : head2;
        Node curr1 = mergedHead == head1 ? head1 : head2;// curr1ָ������ϲ����ͷ����������
        Node curr2 = mergedHead == head1 ? head2 : head1;
        Node prev = null;
        Node next = null;
        while (curr1 != null && curr2 != null) {
            if (curr1.value <= curr2.value) {// �����жϿ��Զ���<=�����ǲ����Զ���<
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
    
    /**
     * ��Ŀ����������ĸ��ơ�
     * �ڸ��������У�ÿ����������һ��next������һ������sibling��ָ��
     */
    /*
     * ��������
     * 1.��ԭ���Ľ������һ�����Եĸ���
     * 2.�����������sibling���
     * 3.�������������������������ͷ��
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
            if (curr.sibling != null) {//curr.sibling=null�ᷢ����ָ���쳣
                cloneNode.sibling = curr.sibling.next;
            }
            curr = cloneNode.next;
            
        }
    }
    
    private static ComplexListNode splitList(ComplexListNode head) {
        ComplexListNode curr = head;
        ComplexListNode cloneHead = null;
        ComplexListNode cloneNode = null;
        if (curr != null) {// ��һ�εĴ���
            cloneHead = curr.next;
            cloneNode = cloneHead;
            curr.next = cloneNode.next;//��������䲻�ܺϲ� 
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
    

}
