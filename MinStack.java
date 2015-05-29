class MinStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();

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


    public void pop() {
    	if(stack.isEmpty()) return;
        if(stack.peek() == minStack.peek()) {
            minStack.pop();
         }
        stack.pop();
    }

    public int top() {
    	if(stack.isEmpty()) return Integer.MAX_VALUE;
        return stack.peek();
    }

    public int getMin() {
    	if(stack.isEmpty()) return Integer.MAX_VALUE;
        return minStack.peek();
    }
}
