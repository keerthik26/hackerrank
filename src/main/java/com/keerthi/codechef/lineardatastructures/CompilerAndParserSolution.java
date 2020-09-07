package com.keerthi.codechef.lineardatastructures;

import java.util.Scanner;
import java.util.Stack;
/**Lira is now very keen on compiler development. :)
 She knows that one of the most important components of a compiler, is its parser.
 A parser is, in simple terms, a software component that processes text, and checks it's semantic correctness, or, if you prefer, if the text is properly built.
 As an example, in declaring and initializing an integer, in C/C++, you can't do something like: int = x ;4
 as the semantics of such statement is incorrect, as we all know that the datatype must precede an identifier and only afterwards should come the equal sign and the initialization value, so, the corrected statement should be:
 int x = 4;

 Today, Lira is concerned with an abstract instruction which is composed of the characters "<" and ">" , which she will use on the design of her language, L++ :D.
 She is using it as an abstraction for generating XML code Tags in an easier fashion and she understood that, for an expression to be valid, a "<" symbol must always have a corresponding ">" character somewhere (not necessary immediately) after it. Moreover, each ">" symbol should correspond to exactly one "<" symbol
 So, for instance, the instructions:
 <<>>
 <>
 <>< are all valid. While:
 >>
 ><>< are not.

 Given some expressions which represent some instructions to be analyzed by Lira's compiler, you should tell the length of the longest prefix of each of these expressions that is valid, or 0 if there's no such a prefix.

 Input
 Input will consist of an integer T denoting the number of test cases to follow.
 Then, T strings follow, each on a single line, representing a possible expression in L++.

 Output
 For each expression you should output the length of the longest prefix that is valid or 0 if there's no such a prefix.
 Constraints
 1 ≤ T ≤ 500
 1 ≤ The length of a single expression ≤ 106
 The total size all the input expressions is no more than 5*106

 Example
 Input:
 3
 <<>>
 ><
 <>>>
 Output:
 4
 0
 2

 */
public class CompilerAndParserSolution {
    public static void main(String[] args) {
        runTests();
        Scanner in = new Scanner(System.in);
        int numOfTests = Integer.parseInt(in.nextLine());
        for (int i = 0; i < numOfTests; i++) {
            System.out.println(getLongestValidPrefix(in.nextLine()));
        }
    }

    //#Approach - 1
    /*private static int getLongestValidPrefix(String input) {
        char[] tags = input.toCharArray();
        boolean[] tagValidations = new boolean[input.length()];
        Stack<Integer> indicesStack = new Stack<>();
        for (int i = 0; i < tags.length; i++) {
            if(tags[i] == '<'){
                indicesStack.push(i);
            }else {
                if(!indicesStack.empty()){
                    tagValidations[indicesStack.pop()] = true;
                    tagValidations[i] = true;
                }else {
                    break;
                }
            }
        }
        int maxLength = 0;

        for (boolean value : tagValidations) {
            if(value){
                maxLength ++;
            }else break;
        }
        return maxLength;
    }*/
    //#Approach - 2
    /*private static int getLongestValidPrefix(String input) {
        char[] tags = input.toCharArray();
        Stack<Character> indicesStack = new Stack<>();
        int currentLength = 0;
        int prefixLength = 0;
        for (char tag : tags) {
            if (tag == '<') {
                indicesStack.push(tag);
            } else {
                if (!indicesStack.empty()) {
                    indicesStack.pop();
                    currentLength += 2;
                    if (indicesStack.empty()) {
                        prefixLength += currentLength;
                        currentLength = 0;
                    }
                } else {
                    break;
                }
            }
        }

        return prefixLength;
    }*/

    private static int getLongestValidPrefix(String input) {
        char[] tags = input.toCharArray();
        int currentLength = 0;
        int prefixLength = 0;
        int v = 0;
        for (char tag : tags) {
            if (tag == '<') {
                v++;
            } else {
                if (v > 0) {
                    v--;
                    currentLength += 2;
                    if (v == 0) {
                        prefixLength += currentLength;
                        currentLength = 0;
                    }
                } else {
                    break;
                }
            }
        }

        return prefixLength;
    }

    public static  int getLongestValidExpression(String input){
        char[] tags = input.toCharArray();
        boolean[] tagValidations = new boolean[input.length()];
        Stack<Integer> indicesStack = new Stack<>();
        for (int i = 0; i < tags.length; i++) {
            if(tags[i] == '<'){
                indicesStack.push(i);
            }else {
                if(!indicesStack.empty()){
                    tagValidations[indicesStack.pop()] = true;
                    tagValidations[i] = true;
                }
            }
        }
        int maxLength = 0;
        int currentLength = 0;
        for (boolean value : tagValidations) {
            if(value){
                currentLength ++;
                maxLength =  Math.max(maxLength,currentLength);
            }else {
                currentLength = 0;
            }
        }
        return maxLength;
    }
    public static void runTests(){
        assert getLongestValidPrefix("<<>>")== 4 : "Should be 4";
        assert getLongestValidPrefix("><")== 0 : "Should be 0";
        assert getLongestValidPrefix("<>>>")== 2 : "Should be 2";
        assert getLongestValidPrefix("><><><><<>>")== 0 : "Should be 0";
        assert getLongestValidPrefix("<<<><>><<>")== 0 : "Should be 0";
        assert getLongestValidPrefix("<<<><>>><>")== 10 : "Should be 10";
        assert getLongestValidPrefix("<<><><><<>>")== 0 : "Should be 0";
        assert getLongestValidPrefix("<><><><<>><")== 10 : "Should be 10";

        assert getLongestValidExpression("<<>>")== 4 : "Should be 4";
        assert getLongestValidExpression("><")== 0 : "Should be 0";
        assert getLongestValidExpression("<>>>")== 2 : "Should be 2";
        assert getLongestValidExpression("><><><><<>>")== 10 : "Should be 10";
        assert getLongestValidExpression("<<<><>><<>")== 6 : "Should be 6";
        assert getLongestValidExpression("<<><><><<>>")== 10 : "Should be 10";
    }
}
