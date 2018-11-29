package com.starisgeek.examples.datastructure.sort;

/**
 * 冒泡排序
 */
public class BubbleSorter {
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                }
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
