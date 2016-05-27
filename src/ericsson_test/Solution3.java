package ericsson_test;


public class Solution3 {
	public static void main(String[] args) {
		// Correct strings.
		for (String testStr : new String[] { "()((()()))", "(()(())())", "()", "(())" }) {
			System.out.println(solution(testStr) ? "1" : "0");
		}
		
		// Incorrect strings.
		for (String testStr : new String[] { "()((())()))", "()))", "(()" }) {
			System.out.println(solution(testStr) ? "1" : "0");
		}
	}

	public static boolean solution(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		
		int stackLength = 0;
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			switch (ch) {
			case '(':
				stackLength++;
				break;
			
			case ')':
				if (stackLength <= 0) {
					return false;
				}
				stackLength--;
				break;

			default:
				return false;
			}
		}
		
		if (stackLength > 0) {
			return false;
		}
		
		return true;
	}
}
