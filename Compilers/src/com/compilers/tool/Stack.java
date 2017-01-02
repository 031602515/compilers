package com.compilers.tool;

import java.util.LinkedList;

public class Stack<T> {
	
	LinkedList<T> stack = new LinkedList<T>();
	
	public int size() {
		return stack.size();
	}
	
	public boolean isEmpty() {
		return stack.size() == 0;
	}
	
	public T pop() {
		if(isEmpty())
			return null;
		return stack.removeFirst();
	}
	
	public void push(T value) {
	 	stack.addFirst(value);
	}
	
	public Stack<T> inverse() {
		Stack<T> inverseStack = new Stack<T>();
		for(int i = 0; i < stack.size(); i ++) {
			inverseStack.push(stack.get(i));
		}
		return inverseStack;
	}

	public void clear() {
		stack.clear();
	}
	
	public T getFirst() {
		return stack.getFirst();
	}
	
	public T getLast() {
		return stack.getLast();
	}
	
	public void removeLast() {
		stack.removeLast();
	}
	
	public T get(int i) {
		return stack.get(i);
	}
	
}
