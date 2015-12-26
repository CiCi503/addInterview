package chaperone;

import java.util.LinkedList;
import java.util.Queue;

public class TwoQueueStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public TwoQueueStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    public void push(int value) {
        // ����������������һ��Ϊ�յģ�ʼ�ս�value���뵽�ǿյ��Ǹ�������
        // ��ʼ��ʱ��Ϊ��ʱ����value����queue2��
        if (!queue1.isEmpty()) {
            queue1.add(value);
        } else {
            queue2.add(value);
        }
    }
    
    public Integer pop() throws Exception {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new Exception("The stack is empty!");
        }
        Integer num = null;
        if (queue2.isEmpty()) {
            // �ƶ���ĳ������ʣ��һ��Ԫ�غ���ô���Ԫ�ؾ�Ҫ�ҵ�
            while (queue1.size() != 1) {
                queue2.add(queue1.poll());
            }
            num = queue1.poll();
        } else {
            while (queue2.size() != 1) {
                queue1.add(queue2.poll());
            }
            num = queue2.poll();
        }
        return num;
    }
    
    public Integer peek() throws Exception {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new Exception("The stack is empty!");
        }
        Integer num = null;
        if (queue2.isEmpty()) {
            while (queue1.size() != 1) {
                queue2.add(queue1.poll());
            }
            num = queue1.peek();// �鵽���Ժ����ƶ���ȥ
            queue2.add(queue1.poll());
        } else {
            while (queue2.size() != 1) {
                queue1.add(queue2.poll());
            }
            num = queue2.peek();
            queue1.add(queue2.poll());
        }
        return num;
    }
    
    

}
