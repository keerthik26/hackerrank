package com.keerthi.codechef.lineardatastructures;

import java.util.Scanner;
import java.util.Stack;
/**You are given an infix expression S of length N. You need to convert the given expression S to its postfix equivalent using stack.

 Input
 The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
 The first line of each test case contains a single integer N denoting the length of S.
 The second line contains a string S, the infix expression.

 Output
 For each test case print a single line containing the postfix equivalent for the given infix expression.
 Constraint
 1≤T≤10
 1≤N≤102
 S contains only uppercase English letters (A...Z), and these characters - (,),+,−,∗,/,^. S is a valid infix expression.

 Example Input
 2
 15
 ((A+B)*D)^(E-F)
 19
 A+(B*C-(D/E^F)*G)*H

 Example Output
 AB+D*EF-^
 ABC*DEF^/G*-H*+

 Explanation
 Example case 1: The postfix equivalent for ((A+B)∗D)^(E−F) is AB+D∗EF−^.
 */
public class InfixToPostfixSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            int expressionLength = Integer.parseInt(in.nextLine());
            String expression  = in.nextLine();
            System.out.println(getPostFixExpression(expression));
        }
    }

    public static  String getPostFixExpression(String inFixExpression){
        String postFixExpression = "";
        char[] literals = inFixExpression.toCharArray();
        Stack<Character> operatorStack = new Stack<>();
        for (char literal : literals) {
            if(Character.isLetter(literal)){
                postFixExpression += literal;
            }else if(literal == '('){
                operatorStack.push(literal);
            }else if(literal == ')'){
                while (operatorStack.peek() != '(') {
                    postFixExpression += operatorStack.pop();
                }
                if(operatorStack.peek() == '(')
                    operatorStack.pop();
            }else {
                while (!operatorStack.empty() && getPrecedence(literal) <= getPrecedence(operatorStack.peek())){
                    postFixExpression += operatorStack.pop();
                }
                operatorStack.push(literal);
            }
        }
        while (!operatorStack.empty()){
            postFixExpression += operatorStack.pop();
        }

        return postFixExpression;

    }

    public static int getPrecedence(char operator){
        switch (operator){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static void runTests(){
        assert getPostFixExpression("((A+B)*D)^(E-F)").equals("AB+D*EF-^") : "Should be AB+D*EF-^";
        assert getPostFixExpression("A+(B*C-(D/E^F)*G)*H").equals("ABC*DEF^/G*-H*+") : " Should be ABC*DEF^/G*-H*+";
    }
}

