package chaperone;

import java.util.Stack;
/**
 * ���õݹ麯����ջ����ʵ�ֶ�һ��ջ������
 * ���磬һ��ջ����ѹ��1��2��3��4����ջ�д�ջ����ջ��������4��3��2��1��
 * �����������ջ�д�ջ����ջ����1��2��3��4
 *
 */
public class MyStackUtils {
    private int getAndRemoveLastElem(Stack<Integer> stack) {
        int elem = stack.pop();
        if (stack.isEmpty()) {
            return elem;
        } else {
            int last = getAndRemoveLastElem(stack);
            stack.push(elem);
            return last;
        }
    }
    
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int elem = getAndRemoveLastElem(stack);
        reverse(stack);
        stack.push(elem);
    }
    
    
}
