package chaperone;

import java.util.Stack;

public class MyStackUtils {
    
    /**
     * 利用递归函数和栈操作实现对一个栈的逆序。
     * 比如，一个栈依次压入1，2，3，4，则栈中从栈顶到栈底依次是4，3，2，1。
     * 经过操作后的栈中从栈顶到栈底是1，2，3，4
     *
     */
    /* 获取并删除栈底元素 */
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
   /* 反转 */
    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int elem = getAndRemoveLastElem(stack);
        reverse(stack);
        stack.push(elem);
    }
    
    /**
     * 利用另一个栈对一个栈中的元素进行排序，元素从栈顶到栈底由大到小
     * 要求：可以申请一个辅助栈和新的变量，除此之外不能申请额外的数据结构
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
                // 当helper的栈顶元素小于curr时，要将helper栈中小于curr的部分重新压回stack中
                stack.push(helper.pop());
            }
            helper.push(curr);
        }
        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
    }
    
}
