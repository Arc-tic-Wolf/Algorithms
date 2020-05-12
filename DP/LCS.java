package Algorithms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Longest_Common_Subsequence {
	
	static String commonLCS;

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		File file = new File("lcsInput.txt");
		Scanner zs = new Scanner(file);
		
		int length = zs.nextInt();
		commonLCS = zs.next();
		
		while(zs.hasNext()) {
			lcs(commonLCS, zs.next());
		}
		System.out.println("Length of the LCS : " + commonLCS.length() + " and LCS : " + commonLCS);
		
	}

	private static void lcs(String s1, String s2) {
		
		int[][] lcs = new int[s1.length()+1][s2.length()+1];
		
		for(int i = 0; i<=s1.length(); i++) {
			for(int j = 0; j<=s2.length(); j++) {
				if(i == 0 || j == 0) {
					lcs[i][j] = 0;
				}
				else if(s1.charAt(i-1) == s2.charAt(j-1)) {
					lcs[i][j] = 1 + lcs[i-1][j-1];
				}
				else {
					lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
		
		int i = s1.length();
		int j = s2.length();
		Stack<Character> stack = new Stack<Character>();
		while(i > 0 && j > 0) {
			if(s1.charAt(i-1) == s2.charAt(j-1)) {
				stack.push(s1.charAt(i-1));
				i--;
				j--;
			}
			else if(lcs[i-1][j] >= lcs[i][j-1]) {
				i--;
			}
			else {
				j--;
			}
		}
		commonLCS = "";
		while(!(stack.isEmpty())) {
			commonLCS += stack.pop();
		}
	}

	private static int max(int i, int j) {
		if(i >= j) return i;
		else return j;
	}

}
