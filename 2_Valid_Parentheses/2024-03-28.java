/**
 * First attempt.
 * 
 * This solution is an attempt to get an O(n) time complexity using the
 * built-in utility class Stack.
 * 
 * Essentially, on encountering an opening bracket, push it to the stack.
 * On encountering a closing bracket, check if the top element on the stack
 * is of the same type as the closing bracket. This must be the case if the
 * string is valid (proof left to the reader). Then, pop that element, and
 * continue. In the end, the stack must be empty again.
 * 
 * I actually learned about this in CS2040S, so this is kind of a no-brainer.
 * 
 * SUBMISSION LINK: https://leetcode.com/problems/valid-parentheses/submissions/1216172775/
 */
import java.util.Stack;

class Solution {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else if (c == ')') {
        if (stack.empty() || stack.pop() != '(') return false;
      } else if (c == '}') {
        if (stack.empty() || stack.pop() != '{') return false;
      } else if (c == ']') {
        if (stack.empty() || stack.pop() != '[') return false;
      }
    }
    return stack.empty();
  }
}