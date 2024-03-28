/**
 * First attempt.
 *
 * This solution is an attempt to get an O(n log n) time complexity. In
 * contrast, the simplest solution would be brute-forcing every pair in the
 * array, which will take O(n^2) time.
 * 
 * The idea is to first sort the array using a typical O(n log n) algorithm,
 * then on each iteration, we set the first index, retrieve the number from
 * that index and determine the number it should be paired with, and finally
 * perform binary search to check whether it exists in the array. Each
 * iteration takes O(log n) time.
 * 
 * SUBMISSION LINK: https://leetcode.com/problems/two-sum/submissions/1216129517/
 */
class Solution {
  /**
   * Encapsulates a key-value pair, where the key and value are supposed to
   * represent the index of a number and that number, respectively.
   */
  private class KeyValue {
    public int key;
    public int value;

    public KeyValue(int k, int v) {
      this.key = k;
      this.value = v;
    }
  }

  /**
   * Sorts part of the array between index "begin" and "end", inclusive.
   */
  private static void mergeSort(KeyValue[] arr, int begin, int end) {
    if (begin == end) return;

    int mid = begin + (end - begin) / 2;
    mergeSort(arr, begin, mid);
    mergeSort(arr, mid+1, end);

    // Merge two sorted half arrays
    KeyValue[] temp = new KeyValue[end - begin + 1];
    int leftPointer = begin;
    int rightPointer = mid+1;
    for (int i = 0; i < end - begin + 1; i++) {
      if (leftPointer > mid) {
        // Left half already exhausted
        temp[i] = arr[rightPointer];
        rightPointer++;
      } else if (rightPointer > end) {
        // Right half already exhausted
        temp[i] = arr[leftPointer];
        leftPointer++;
      } else if (arr[leftPointer].value > arr[rightPointer].value)  {
        temp[i] = arr[rightPointer];
        rightPointer++;
      } else {
        temp[i] = arr[leftPointer];
        leftPointer++;
      }
    }

    for (int i = 0; i < end - begin + 1; i++) {
      arr[i + begin] = temp[i];
    }
  }

  public int[] twoSum(int[] nums, int target) {
    int len = nums.length;
    KeyValue[] arr = new KeyValue[len];
    for (int i = 0; i < len; i++) {
      arr[i] = new KeyValue(i, nums[i]);
    }

    mergeSort(arr, 0, len - 1);

    for (int i = 0; i < len; i++) {
      int search = target - arr[i].value;
      // Perform binary search on arr[i+1:] (note that searching to the left
      // is not needed because we know from previous iterations that it does
      // not have a satisfying pair.)
      int begin = i + 1;
      int end = len - 1;
      while (begin <= end) {
        int mid = begin + (end - begin) / 2;
        if (search > arr[mid].value) {
          begin = mid + 1;
        } else if (search < arr[mid].value) {
          end = mid - 1;
        } else {
          return new int[] {arr[i].key, arr[mid].key};
        }
      }
    }

    // This should never occur (by problem assumption).
    return new int[] {-1, -1};
  }
}