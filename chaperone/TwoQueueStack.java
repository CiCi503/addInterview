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
        // 两个队列里总是有一个为空的，始终将value插入到非空的那个队列中
        // 初始化时都为空时，则将value插入queue2中
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
            // 移动到某个队列剩了一个元素后，那么这个元素就要找的
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
            num = queue1.peek();// 查到了以后再移动回去
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
