package com.starisgeek.examples.datastructure.sort;

import java.util.Random;

public class Sorts {
    private static final Random r = new Random();

    public static int[] generateNumbers(int size) {
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = r.nextInt(200);
        }
        return nums;
    }

    public static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = generateNumbers(20);
        printArray(nums);
    }
}
