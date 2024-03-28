/**
 * First attempt.
 * 
 * This solution was the most obvious and the only solution I can think of.
 * It has an O(n_1 + n_2) time complexity where n_1 and n_2 are the lengths
 * of each linked list.
 * 
 * SUBMISSION LINK: https://leetcode.com/problems/merge-two-sorted-lists/submissions/1216197990/
 */
class Solution {
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode start = null;
    ListNode end = null;
    ListNode ptrOne = list1;
    ListNode ptrTwo = list2;

    while (ptrOne != null || ptrTwo != null) {
      if (ptrOne == null) {
        if (end == null) {
          end = ptrTwo;
          start = end;
        } else {
          end.next = ptrTwo;
          end = end.next;
        }
        ptrTwo = ptrTwo.next;
      } else if (ptrTwo == null) {
        if (end == null) {
          end = ptrOne;
          start = end;
        } else {
          end.next = ptrOne;
          end = end.next;
        }
        ptrOne = ptrOne.next;
      } else if (ptrOne.val > ptrTwo.val) {
        if (end == null) {
          end = ptrTwo;
          start = end;
        } else {
          end.next = ptrTwo;
          end = end.next;
        }
        ptrTwo = ptrTwo.next;
      } else {
        if (end == null) {
          end = ptrOne;
          start = end;
        } else {
          end.next = ptrOne;
          end = end.next;
        }
        ptrOne = ptrOne.next;
      }
    }

    return start;
  }
}