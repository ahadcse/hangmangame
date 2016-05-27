package ericsson_test;


public class Solution4 {
	public static void main(String[] args) {
		// Correct strings.
		for (String testStr : new String[] { "()((()()))", "(()(())())", "()", "(())" }) {
			System.out.println(solution(testStr));
		}
		
		// Incorrect strings.
		for (String testStr : new String[] { "()((())()))", "()))", "(()" }) {
			System.out.println(solution(testStr));
		}
	}

	public static int solution(String str) {
		if (str == null || "".equals(str)) {
			return 1;
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
					return 0;
				}
				stackLength--;
				break;

			default:
				return 0;
			}
		}
		
		if (stackLength > 0) {
			return 0;
		}
		
		return 1;
	}
}
