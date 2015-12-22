package chaperone;

import java.util.Stack;
/**
 * 利用递归函数和栈操作实现对一个栈的逆序。
 * 比如，一个栈依次压入1，2，3，4，则栈中从栈顶到栈底依次是4，3，2，1。
 * 经过操作后的栈中从栈顶到栈底是1，2，3，4
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
