package chaperone;

import java.util.Stack;

/**
 * ��Ŀ�����һ�������ջ����ʵ��ջ�Ļ������ܵĻ����ϣ���ʵ�ַ���ջ����СԪ�صĲ���
 * Ҫ��
 *  �� pop/push/getMin������ʱ�临�Ӷȶ���O(1)
 *  �� ��Ƶ�ջ���Ϳ���ʹ�����е�ջ�ṹ
 */
public class MyStack {
    private Stack<Integer> dataStack;// �������
    private Stack<Integer> minStack;// ջ��Ԫ����Ŀǰλ�õ�ջ�е���Сֵ
    public MyStack() {
        dataStack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    public void push(int newNum) {
        if (minStack.isEmpty() || newNum <= getMin() ) {
            minStack.push(newNum);
        } 
        dataStack.push(newNum);
    }
    public int pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("��ջΪ�գ�");
        }
        int value = dataStack.pop();
        if (value == getMin() ) {
            minStack.pop();
        }
        return value;
    }
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("��ջΪ�գ�");
        }
        return minStack.peek();
    }
}
