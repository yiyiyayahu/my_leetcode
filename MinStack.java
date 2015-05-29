/*
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
*/


class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        } 
    }

    public void pop() {
        if(stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

}

/*
开始写复杂了，用一个minstack来维护最小值，但是最开始我以为，比如，5，1，3，在3进入的时候，要把minStack中的1pop出来，3进去，再把1压进去
其实这个是没有必要的。minStack只要保证当前最小的就可以了
minStack现在有5，1，当调用pop的时候，3 pop出去了，对minStack没有影响
上面那段代码，我不知道为什么一定要stack.peek().equals(minStack.peek())，用"=="的话leetcode过不了，eclipse可以的，可能觉得Integer是个object
*/
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        } else {
            Stack<Integer> tmp = new Stack<Integer>();
            while(!minStack.isEmpty() && minStack.peek() < x) {
                tmp.push(minStack.pop());
            }
            minStack.push(x);
            while(!tmp.isEmpty()) {
                minStack.push(tmp.pop());
            }
        }
    }
