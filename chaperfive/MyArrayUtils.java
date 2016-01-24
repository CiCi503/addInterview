package chaperfive;

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
        if (arr == null) {
           throw new RuntimeException("The array input is null!");
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
    
}
