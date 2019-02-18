package com.starisgeek.examples.datastructure.stack;

import java.util.LinkedList;

/**
 * 匹配(),[],{}
 * 
 * @author Administrator
 *
 */
public class BracketMatcher {
	private final String expression;

	public BracketMatcher(String e) {
		if (e == null) {
			throw new NullPointerException("expression is null");
		}
		this.expression = e;
	}

	public boolean match() {
		LinkedList<Character> stack = new LinkedList<>();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (isLeft(c)) {
				stack.addLast(c);
			} else if (isRight(c)) {
				if (!isPair(stack.removeLast(), c)) {
					return false;
				}
			}
		}
		if (stack.isEmpty()) {
			return true;
		}
		return false;
	}

	private boolean isLeft(char c) {
		return c == '(' || c == '[' || c == '{';
	}

	private boolean isRight(char c) {
		return c == ')' || c == ']' || c == '}';
	}

	private boolean isPair(char l, char r) {
		if (l == '(') {
			return r == ')';
		}
		if (l == '[') {
			return r == ']';
		}
		if (l == '{') {
			return r == '}';
		}
		return false;
	}

	public static void main(String[] args) {
		final String e = "{aaa[bbb]](ccc)}";
		BracketMatcher m = new BracketMatcher(e);
		System.out.println("Match bracket:" + m.match());
	}
}
