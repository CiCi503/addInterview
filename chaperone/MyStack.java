package chaperone;

import java.util.Stack;

/**
 * 题目：设计一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 要求：
 *  ① pop/push/getMin操作的时间复杂度都是O(1)
 *  ② 设计的栈类型可以使用现有的栈结构
 */
public class MyStack {
    private Stack<Integer> dataStack;// 存放数据
    private Stack<Integer> minStack;// 栈顶元素是目前位置的栈中的最小值
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
            throw new RuntimeException("此栈为空！");
        }
        int value = dataStack.pop();
        if (value == getMin() ) {
            minStack.pop();
        }
        return value;
    }
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("此栈为空！");
        }
        return minStack.peek();
    }
}
