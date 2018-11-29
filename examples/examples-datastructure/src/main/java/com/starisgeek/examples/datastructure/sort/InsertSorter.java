package com.starisgeek.examples.datastructure.sort;

public class InsertSorter {
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; ++i) {
            //待插入数据
            int value = nums[i];
            int j = i - 1;
            //找到插入位置
            for (; j >= 0; --j) {
                if (value < nums[j]) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        int[] nums = Sorts.generateNumbers(20);
        BubbleSorter sorter = new BubbleSorter();
        sorter.sort(nums);
        Sorts.printArray(nums);
    }
}
