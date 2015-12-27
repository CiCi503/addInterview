package chaperone;

import java.util.Stack;

public class MyStackUtils {
    
    /**
     * ���õݹ麯����ջ����ʵ�ֶ�һ��ջ������
     * ���磬һ��ջ����ѹ��1��2��3��4����ջ�д�ջ����ջ��������4��3��2��1��
     * �����������ջ�д�ջ����ջ����1��2��3��4
     *
     */
    /* ��ȡ��ɾ��ջ��Ԫ�� */
    private static int getAndRemoveLastElem(Stack<Integer> stack) {
        int elem = stack.pop();
        if (stack.isEmpty()) {
            return elem;
        } else {
            int last = getAndRemoveLastElem(stack);
            stack.push(elem);
            return last;
        }
    }
   /* ��ת */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int elem = getAndRemoveLastElem(stack);
        reverse(stack);
        stack.push(elem);
    }
    
    /**
     * ������һ��ջ��һ��ջ�е�Ԫ�ؽ�������Ԫ�ش�ջ����ջ���ɴ�С
     * Ҫ�󣺿�������һ������ջ���µı���������֮�ⲻ�������������ݽṹ
     */
    public static void sortStackDependStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Stack<Integer> helper = new Stack<Integer>();
        int curr;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            while (!helper.isEmpty() && curr > helper.peek()) {
                // ��helper��ջ��Ԫ��С��currʱ��Ҫ��helperջ��С��curr�Ĳ�������ѹ��stack��
                stack.push(helper.pop());
            }
            helper.push(curr);
        }
        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
    }
    
}
