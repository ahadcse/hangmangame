package ericsson_test;

import java.util.Stack;

public class Parenthesis_chcker {
	public static void main(String[] args) {
		// Correct strings.
		for (String testStr : new String[] { "()((()()))", "()", "(())" }) {
			System.out.println(isNestedProperly(testStr) ? "1" : "0");
		}
		
		// Incorrect strings.
		for (String testStr : new String[] { "()((())()))", "()))", "(()" }) {
			System.out.println(isNestedProperly(testStr) ? "1" : "0");
		}
	}

	private static boolean isNestedProperly(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '(':
				stack.push(ch);
				break;
			
			case ')':
				if (stack.size() <= 0) {
					return false;
				}
				stack.pop();
				break;

			default:
				return false;
			}
		}
		
		if (stack.size() > 0) {
			return false;
		}
		
		return true;
	}
}
