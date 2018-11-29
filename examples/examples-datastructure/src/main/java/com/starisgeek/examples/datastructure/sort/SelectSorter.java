package com.starisgeek.examples.datastructure.sort;

public class SelectSorter {
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; ++i) {
            int minIdx = i;
            int j = i + 1;
            for (; j < n; ++j) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            Sorts.swap(nums, i, minIdx);
        }
    }

    public static void main(String[] args) {
        int[] nums = Sorts.generateNumbers(20);
        SelectSorter sorter = new SelectSorter();
        sorter.sort(nums);
        Sorts.printArray(nums);
    }
}
