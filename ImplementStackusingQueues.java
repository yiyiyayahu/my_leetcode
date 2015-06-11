/*
Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Notes:
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue -- which means only push to back, pop from front, size, and is empty operations are valid.
Update (2015-06-11):
The class name of the Java function had been updated to MyStack instead of Stack.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and all test cases.
*/

/*
开始一直想着两个queue，
但其实只要知道queue.size()，那我用一个queue就行，就不断的append到后面就行，但注意要n-1次，并且不断更新topElem
*/
class MyStack {
    Queue<Integer> queue = new LinkedList<Integer>();
    int topElem = 0;
    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        topElem = x;
    }

    // Removes the element on top of the stack.
    public void pop() {
        for(int i = 0; i < queue.size() - 1; i++) {
            topElem = queue.remove();
            queue.add(topElem);
        }
        queue.remove();
    }

    // Get the top element.
    public int top() {
        return topElem;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}
