package chaperfive;

import javax.xml.namespace.QName;

public class MyArrayUtils {
    /**
     * 题目：输入一个递增排序数组的一个旋转，输出旋转数组的最小值。
     * 举个栗子：
     * 输入数组为{3, 4, 5, 1, 2},它是经{1, 2, 3, 4, 5}旋转而来的，
     * 输出为1。
     */
    /*
     * 要考虑到含有重复元素
     */
    public static int getRotateArrayMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空，请检查!");
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = left;// mid取值left，考虑到数组可能没有经过旋转，此时最小值就是第一个元素了
        while (arr[left] >= arr[right]) {// 等号是考虑到初始数组始末元素相等的情况
         // low指向了前段递增数列的最后一个，high指向了后段递增数列的第一个
         // 最小的数就应该是high指向的元素
            if (right - left == 1) {
                mid = right;
                break;
            }
            mid = (left + right) >>> 1;
         // 特殊情况，无法分辨最小值在哪一段，只能使用顺序查找了
            if (arr[mid] == arr[left] && arr[mid] == arr[right]) {
                return minInorder(arr, left, right);
            }
         // 特殊情况arr[mid] == arr[left]，那么left~mid这一段元素是重复数字，最小值应该在后半段找
            if (arr[mid] < arr[left]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return arr[mid];
    }

    // 顺序查找
    private static int minInorder(int[] arr, int left, int right) {
        int result = arr[left];
        for (int i = left + 1; i <= right; i++) {
            if (arr[i] < result) {
                result = arr[i];
            }
        }
        return result;
    }
    
    /**
     * 题目：输入一个整数数组，实现一个方法来调整数组中的元素，
     *       使得奇数位于前半部分，偶数位于后半部分。
     */
    /*
     * 从头部和尾部同时相向遍历数组，这个思想也应用在快排的partition方法
     */
    public static void reorderOddEven(int[] arr, int len) {
        if (arr == null || len == 0) {
            return;
        }
        int prev = 0;
        int post = len - 1;
        while (prev < post) {
         // prev < post限制，当数组中元素全部是奇数时，prev的上限是post
            while (prev < post && (arr[prev] & 0x1) != 0) {
                prev++;
            }
         // prev < post限制，当数组中元素全部是偶数时，post的下限是prev
            while (prev < post && (arr[post] & 0x1) == 0) {
               post--; 
            }
            if (prev < post) {
                swap(arr, prev, post);
                prev++;
                post--;
            }
        }
    }
    private static void swap(int[] arr, int prev, int post) {
        int temp = arr[prev];
        arr[prev] = arr[post];
        arr[post] = temp;
    }
    
    
    /**
     * 题目：对于一个无序数组A，请设计一个算法，求出需要排序的最短子数组的长度。
     * 给定一个整数数组A及它的大小n，请返回最短子数组的长度。
     * 举个栗子：
     * [1,5,3,4,2,6,7],7
     * 输出为4
     */
    public static int findShortestLen(int[] arr, int len) {
        if (arr == null || arr.length == 0) {
           throw new RuntimeException("The array input is null or the length of array is 0!");
        }
        if (len != arr.length) {
            throw new RuntimeException("The len input is not match the length of array!");
        }
        int right = 0 ;
        int left = len - 1;
        int max = arr[0];// 设置默认最大值
        int min = arr[len - 1];// 设置默认最小值
        
        for (int i = 1; i < len; i++) {// 正向遍历找到右边界
            if (arr[i] >= max) {// 当前元素要大于max，就要更新max
                max = arr[i];
            } else {// 否则，更新右边界
                right = i;
            }
        }
        for (int i = len - 2; i >= 0; i--) {
            if (arr[i] <= min) {
                min = arr[i];
            } else {
                left = i;
            }
        }
        
        if (right < left) {// 当数组已完全排序，right和left还是初始值，此时right < left
            return 0;
        }
        return right - left + 1;
    }
    /**
     * 题目：数组中出现超过一半的数字
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 举个栗子：
     * 输入一个长度为9的数组｛1，2，3，2，2，2，5，4，2｝。
     * 由于数字2在数组中出现5次，超过数组长度的一半，因此输出2.
     * 注：若输出为0，则输入参数不合理或者没有符合要求的结果
     */
    
    /*
     * 方法1：基于patition快排思想
     * 时间复杂度为O(n)
     * 思路：找到数组中第n/2大的数，即中位数。
     * 但是中位数对应的数字的出现次数不一定超过了数组长度的一半，
     * 所以要有一个检验的过程。
     * 
     * 该解法需要改变输入的数组！
     */
    public static int moreThanHalfNum(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int stIndex = 0;
        int endIndex = array.length - 1;
        int mid = endIndex >>> 1;
        int pivotIndex = partition(array, stIndex, endIndex);
        while (pivotIndex != mid) {
            if (pivotIndex > mid) {
                endIndex = pivotIndex - 1;
            } else {
                stIndex = pivotIndex + 1;
            }
            pivotIndex = partition(array, stIndex, endIndex);
        }
        int result = array[pivotIndex];
        if (!isMoreThanHalf(array, result)) {
            return 0;
        }
        return result;
    }
    // 检查找到的result在数组中出现的次数是否超过了数组长度的一半
    private static boolean isMoreThanHalf(int[] array, int result) {
        boolean isQualifed = false;
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == result) {
                times++;
            }
        }
        if (times << 1 >= array.length) {
            isQualifed = true;
        }
        
        return isQualifed;
    }

    public static int partition(int[] array, int stIndex, int endIndex) {
        int pivot = array[stIndex];
        while (stIndex < endIndex) {
            while (stIndex < endIndex && pivot < array[endIndex]) {
                endIndex--;
            }
            array[stIndex] = array[endIndex];
            while (stIndex < endIndex && pivot >= array[stIndex]) {
                stIndex++;
            }
            array[endIndex] = array[stIndex];
        }
        array[stIndex] = pivot;
        return stIndex;
    }
    
    
    /*
     * 方法2：根据数组特点找出O(n)的算法
     * 在遍历数组时保存两个值：一个是数组中的一个数字，一个是次数。
     * 遍历下一个元素时，若跟我们之前保存的数字相同，则次数加1，
     * 若不同就减1；若次数等于0了，那么就重新设置保存的数字，将次数重置。
     * 那么要找的数字肯定是最后一次把次数设置为时的数字。
     * 
     * 该解法不会改变输入的数组！
     */
    public static int moreThanHalfNum2(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int result = array[0];
        int times = 1;
        for (int i = 1, len = array.length; i < len; i++) {
            if (times == 0) {
                result = array[i];
                times = 1;
            } else if (array[i] == result) {
                times++;
            } else {
                times--;
            }
        }
        if (!isMoreThanHalf(array, result)) {
            return 0;
        }
        return result;
    }
    
    
}

