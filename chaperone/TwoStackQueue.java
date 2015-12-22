package chaperone;

import java.util.Stack;
/**
 * ������ջ���һ�����У�֧�ֶ��е�poll,push,peek�Ȼ�������
 */
/*
 * ջ�ṹFILO��������FIFO,���һ��ջ�ṹ�����ã���Ҫ��һ��ջ���и�����ת��
 * ջstackPush����ѹ��Ԫ��;
 * ջstackPop���𵯳�Ԫ�ء�
 * push����:ֱ�ӽ�����ѹ��stackPushջ�Ϳ��ԡ�
 * poll����:��Ҫ����stackPopջ����Ԫ�أ������������Ƚ���ջ�ġ�
 * ���stackPopջ����Ԫ�أ���ô��ֱ�Ӱ�ջ��Ԫ�ط��أ���ջΪ�գ���ô����Ҫ��stackPush�е�����Ԫ�ض�ѹ�룬Ȼ�󷵻�ջ����
 * ע�⣺�ٵ�stackPop��Ϊ��ʱ�������Խ�stackPushѹ�룬������ƻ�Ԫ�ص��Ⱥ����
 * ��ÿ�δ�stackPushջ��stackPopջѹ������ʱ������һ����ѹ�����еġ�
 */
public class TwoStackQueue {
    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;
    
    public TwoStackQueue() {
        stackPop = new Stack<Integer>();
        stackPush = new Stack<Integer>();
    }
    
    public boolean add(int pushNum) {
        stackPush.push(pushNum);
        return true;
    }
    // ջ��Ϊ�վͷ���ջ��Ԫ�أ��������null
    public Integer peek() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            return null;
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
    
    public Integer poll() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            return null;
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }
}
