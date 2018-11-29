package com.starisgeek.examples.datastructure.sort;

/**
 * 冒泡排序
 */
public class BubbleSorter {
    public void sort(int[] nums) {
        int n = nums.length;
        boolean flag = false;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    Sorts.swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = Sorts.generateNumbers(20);
        BubbleSorter sorter = new BubbleSorter();
        sorter.sort(nums);
        Sorts.printArray(nums);
    }
}
