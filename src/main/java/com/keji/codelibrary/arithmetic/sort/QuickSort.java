package com.keji.codelibrary.arithmetic.sort;

import java.util.Arrays;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/12/3
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {47,29,71,99,78,19,24,47};

        quickSort(arr, 9, arr.length - 1);

        int i = 0;

        int pivot = arr[i];
        System.out.println("基准=" + pivot);

        for (int j = 0; j < arr.length; j++) {
            //第一次循环，比基准值小的放左边，比基准值大的放右边

            int endIndex = arr.length - 1 - j;

            if (j >= endIndex) {
                System.out.println(Arrays.toString(arr));
                return;
            }

            //从右往左找，比基准值小的交换位置
            if (arr[i] > arr[endIndex] && i < endIndex) {
                arr[i] = arr[endIndex];
                arr[endIndex] = pivot;
                i = endIndex;
            }

            //从左往右找，比基准值大的交换位置
            if (arr[i] < arr[j] && i >j) {
                arr[i] = arr[j];
                arr[j] = pivot;
                i = j;
            }
            System.out.println(Arrays.toString(arr));

            if (j == endIndex) {
                return;
            }
        }

    }

    private static void quickSort(int[] arr, int begin, int end) {

        if (begin < end) {
            int key = arr[begin];
            int i = begin;
            int j = end;

            while (i < j) {
                while (i < j && arr[j] > key) {
                    j--;
                }

            }

        }
    }

}
