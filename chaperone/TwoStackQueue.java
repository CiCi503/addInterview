package chaperone;

import java.util.Stack;
/**
 * 由两个栈组成一个队列，支持队列的poll,push,peek等基本操作
 */
/*
 * 栈结构FILO，而队列FIFO,因此一个栈结构不够用，需要另一个栈进行辅助反转：
 * 栈stackPush负责压入元素;
 * 栈stackPop负责弹出元素。
 * push操作:直接将数据压入stackPush栈就可以。
 * poll操作:需要返回stackPop栈顶的元素，因此这个是最先进入栈的。
 * 如果stackPop栈中有元素，那么就直接把栈顶元素返回，若栈为空，那么就需要把stackPush中的所有元素都压入，然后返回栈顶。
 * 注意：①当stackPop不为空时，不可以将stackPush压入，否则会破坏元素的先后次序
 * ②每次从stackPush栈往stackPop栈压入数据时，都是一次性压入所有的。
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
    // 栈不为空就返回栈顶元素，否则输出null
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
