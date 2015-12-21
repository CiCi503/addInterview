package chaperone;

import java.util.Stack;

public class MyStack2 {
    private Stack<Integer> dataStack;
    private Stack<Integer> minStack;
    
    public MyStack2() {
        dataStack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }
    
    public void push(int newNum) {
        if (minStack.isEmpty()) {
            minStack.push(newNum);
        } else if (newNum > minStack.peek()) {
            minStack.push(minStack.peek());
        } else {
            minStack.push(newNum);
        }     
        dataStack.push(newNum);
    }
    
    public int pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("stack is empty!");
        }
        minStack.pop();
        return dataStack.pop();
    }
    
    public int getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return minStack.peek();
    }
}
