package chaperfive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

public class MyArrayUtils {
    /**
     * 题目：输入一个递增排序数组的一个旋转，输出旋转数组的最小值。
     * 举个栗子：
     * 输入数组为{3, 4, 5, 1, 2},它是经{1, 2, 3, 4, 5}旋转而来的，
     * 输出为1。
     */
    /*
     * 要考虑到含有重复元素的情况
     */
    public static int getRotateArrayMin(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new RuntimeException("数组为空，请检查!");
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = left;// mid取值left，考虑到数组可能没有经过旋转，此时最小值就是第一个元素了
        
        /* 注意：这里不能以left和right大小比较来控制循环！！ */
        
        while (arr[left] >= arr[right]) {// 等号是考虑到初始数组始末元素相等的情况
         // left指向了前半段递增数列的最后一个，right指向了后半段递增数列的第一个
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
            result = (arr[i] < result ? arr[i] : result);
        }
        return result;
    }
    
    /**
     * 题目：有一个数组是由有序递增数组经过旋转得到的，现在要在这种数组中
     * 查询某个元素是否存在，不存在返回-1，存在则返回索引值。
     */
    /*
     * 注意考虑重复元素，可以先不考虑这种情况，得到解答后再进行修改(*_*)
     */
    public static int getindexInRotateArr(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < arr[left]) {// 后半部分大的旋转数组
                if (target > arr[mid] && target <= arr[right]) {// 此时，后半部分是有序的
                    left = mid;
                } else {
                    right = mid;
                }
            } else if (arr[mid] > arr[left]) {// 前半部分大的旋转数组
                if (target>= arr[left] && target < arr[mid]) {// 此时，前半部分是有序的
                    right = mid;
                } else {
                    left = mid;
                }
            } else {// 特殊情况：arr[mid] == arr[left]
               left++; 
            }
        }
        return -1;
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

    /*
     * 方法3：使用额外的数据结构hashMap来存储每个数字出现的次数
     */
    
    /**
     * 题目：最小的k个数
     * 输入n个整数，找出其中最小的k个数。
     * 举个栗子：输入为4、5、1、6、2、7、3、8
     * 则最小的4个数字是1、2、3、4
     */
    /*
     * 方法1：对数组进行排序，取前k个数，最好的时间复杂度为O(nlogn)。
     * 像冒泡这样的排序，只取k次就可以，不需要全排序
     */
    /*
     * 方法2：基于partition方法（快排思想）
     * 时间复杂度为O(n)，使用该方法后，原数组会改变
     * 
     * 特殊测试(返回[])：
     * input==null
     * input.lenth=0;
     * k<=0;
     * k>input.length
     */
    public static ArrayList<Integer> getLeastNNum(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        if (input == null || input.length == 0 || k <= 0 || k > input.length) {
            return result;
        }
        int stIndex = 0;
        int endIndex = input.length - 1;
        int pivotIndex = partition(input, stIndex, endIndex);
        while (pivotIndex != k - 1) {
            if (pivotIndex > k - 1) {
                endIndex = pivotIndex - 1;
                
            } else {
                stIndex = pivotIndex + 1;
            }
            pivotIndex = partition(input, stIndex, endIndex);
        }
        
        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }
    /*
     * 方法3：O(nlgk)算法，特别适合大数据处理
     * 创建一个大小为k的容器来存储最小的k个数，当新的数到来时，与容器中最小数进行比较，
     * 若比容器中最大数还要大，那么不考虑，否则就要把容器中最大的数取出，替换成新数。
     * 
     * 使用红黑树来实现该容器，在java里有hashMap，其底层实现是hashMap,查找、输入和删除
     * 都只需要O(logk)。
     */
    public static void getLeastNNum2(int[] input, int k) {
        TreeMap<Integer, Integer> result = new TreeMap<>();
        if (input == null || input.length == 0 || k <= 0 || k > input.length) {
            return;
        }
        int i = 0;
        int lastKey = 0;
        for (; i < k; i++) {// 先插入k个数，TreeMap会对键进行排序
            result.put(input[i], i);
        }
        
        for (int j = i; j < input.length; j++) {
            if ((lastKey = result.lastKey()) > input[j]) {
                result.remove(lastKey);// 删除
                result.put(input[j], j);// 替换
            }
        }
        System.out.println(result.keySet());// 打印所有的键
    }

    /**
     * 题目：连续字数组的最大和
     * 输入一个整形数组，数组里有整数也有负数。数组中一个或者连续多个整数组成一个字数组。
     * 求所有字数组的和的最大值。要求时间复杂度为O(n)
     */

    /*
     * 思路：当curSum累加到0或者负数时，curSum从下一个数开始重新计算,
     * 每次greatestSum总是更新到目前最大的值
     * 
     * 若以f(i)表示以第i个数字结尾的字数组的最大和，那么我们需要求出max(f(i))。
     * 当i=0或f(i-1)<=0时，f(i)=Data[i],
     * 当f(i-1)>0时，f(i)=f(i-1)+Data[i].
     * 虽然我们用递归的方式分析动态规划的问题，但最终都会基于循环去编码。
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length == 0) {
            return  0;
        }
        int curSum = array[0];
        int greatestSum = array[0];
        for (int i = 1, len = array.length; i < len; i++) {
            curSum = curSum > 0 ? curSum + array[i] : array[i];// f(i)
            greatestSum = Math.max(curSum, greatestSum); // max(f(i))
        }
        return greatestSum;
    }
    
    /**
     * 题目：输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。
     * 举个栗子：
     * 输入12，从1到12这些整数中包含1的数字有1，10，11和12，一共出现5次。
     */
    
    /*
     * 方法1：最原始的方法
     * 从头到尾进行遍历，每个数字需要lgn次才能把所有的数位遍历完，
     * 一个n个数字，所以时间复杂度为O(nlgn)。
     * 当n很大时，该解法效率不高。
     */
    public static int numberOf1Between1AndN(int n) {
        if (n < 1) {
            return 0;
        }
        int count = 0;
        int curr = 0;
        for (int i = 1; i <= n; i++) {
            curr = i;
            while (curr != 0) {
                if (curr % 10 == 1) {
                    count++;
                }
                curr /= 10;
            }
        }
        return count;
    }
    /*
     * 方法2：从数字的规律着手
     */
    public static int numberOf1Between1AndN2(int n) {
        if (n < 1) {
            return 0;
        }
        int count = 0;
        int lowNum = 0;
        int curNum = 0;
        int highNum = 0;
        int factory = 1;
        while (n / factory != 0) {
            lowNum = n - (n / factory) * factory;
            curNum = (n / factory) % 10;
            highNum = n / (factory * 10);
            
          //如果为0,出现1的次数由高位决定  
            if (curNum == 0) {
                count += highNum * factory; 
         //如果为1,出现1的次数由高位和低位决定  
            } else if (curNum == 1) {
                count += (highNum * factory + lowNum + 1);
            } else {
        //如果大于1,出现1的次数由高位决定  
                count += (highNum + 1) * factory;
            }
            factory *= 10;
        }
        return count;
    }
    
    /**
     * 题目：输入一个正整数数组，把数组里所有的数字拼接起来排成一个数，
     * 打印能拼接出的所有数字中最小的一个。
     * 举个栗子：
     * 输入数组{3, 32, 321},则打印出这3个数字能排成的最小数字321323
     */
    /*
     * 首先这是一个大数问题。
     * 最直接的方法是列出所有可能的组合，然后比较出最小的，但是效率低。
     * 
     * 思路：将输入的整型数组转换成字符串型数组，然后字符串数组进行排序，
     * 最后依次输出字符串数组即可。关键点在于字符串中的数组要根据特殊的比较规则进行排序，
     * 而不能按照传统的字符串排序。
     * 定义如下：ab<ba，则a<b。
     * 如：对321和32，排序为32132<32321，所以321<32
     * 想到使用Comparator<T>接口,需要一个容器来存放数组。
     * 
     * 方法1仍然使用数组，将整型数组转变成字符串数组，借助Arrays类的静态方法sort,
     * 使用自定义的比较器完成从小到大的排序。
     * 
     * 方法2使用的是List，没有将整型数组转变成字符串数组，而是在完成比较器的时候定义特殊的比较规则。
     * 接住Colletions类的静态方法sort
     * 
     * 方法1
     */
    public static String printMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
//        Comparator<Integer> minComparator = new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return (o1 + "" + o2).compareTo(o2 + "" + o1);
//            }
//        };
        // JDK8的新特性函数式编程，Comparator是一个函数式接口，可以使用Lambda表达式
        Comparator<String> minComparator = (o1, o2) -> (o1 + o2).compareTo(o2 + o1);
        
        String[] numsArr = intArrToStrArr(numbers);
        Arrays.sort(numsArr, minComparator);
        for (String str : numsArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    // 将整型数组转换成字符串数组
    private static String[] intArrToStrArr(int[] numbers) {
        String[] numsArr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numsArr[i] = numbers[i] + "";
        }
        return numsArr;
    }
    
    /*
     * 方法2
     */
    public static String printMinNumber2(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        // JDK7新特性，钻石语法
        List<Integer> numlist = new ArrayList<>(numbers.length);
        for (int num : numbers) {
            numlist.add(num);
        }
        
        StringBuilder sb = new StringBuilder();
        Comparator<Integer> minComparator = (o1, o2) -> (o1 + "" + o2).compareTo(o2 + "" + o1);
        
        Collections.sort(numlist, minComparator);
        for (int num : numlist) {
            sb.append(num);
        }
        return sb.toString();
    }
    
    /**
     * 题目：我们把只包含因子2、3和5的数称为丑数。求按从小到大的顺序的第1500个丑数。
     * 举个栗子：
     * 6和8都是丑数，但14不是，因为它还有因子7。
     * 习惯上，我们把1当做第一个丑数。
     */
    
    /*
     * 方法1：逐个判断每个整数是不是丑数，
     * 虽然直观，但是效率很低
     */
    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }
        int result = 0;
        int uglyCount = 0;
        while (uglyCount < index) {
            result++;
            if (isUgly(result)) {
               uglyCount++; 
            }
        }
        return result;
    }

    private static boolean isUgly(int result) {
        while (result % 2 == 0) {
            result /= 2;
        }
        while (result % 3 == 0) {
            result /= 3;
        }
        while (result % 5 == 0) {
            result /= 5;
        }
        return result == 1 ? true : false;
    }
    
    /*
     * 方法2：用一个数组来保存已经生成的丑数，
     * 每一个丑数都是前面的丑数乘以2、3或者5得到的。
     * 因为只考虑了丑数，而不是逐个判断是不是丑数，因此效率更高。
     * 求第1500个丑数，那么创建一个1500容量的数组，需要占用1500*4B=6KB的内存。
     * 以空间消耗换得了时间效率。
     */
    public static int getUglyNumber2(int index) {
        if (index <= 0) {
            return 0;
        }
        int[] uglyNums = new int[index];
        uglyNums[0] = 1;
        
        // 记录乘2，乘3和乘5的数的索引
        int multiply2 = 0, multiply3 = 0, multiply5 = 0;
        int min = 0;
        for (int i = 1; i < index; i++) {
            
            min = Math.min(Math.min(uglyNums[multiply2] * 2, uglyNums[multiply3] * 3), uglyNums[multiply5] * 5);
            uglyNums[i] = min;
            multiply2 = uglyNums[multiply2] * 2 == min ? multiply2 + 1: multiply2;
            multiply3 = uglyNums[multiply3] * 3 == min ? multiply3 + 1: multiply3;
            multiply5 = uglyNums[multiply5] * 5 == min ? multiply5 + 1: multiply5;
        }
        return uglyNums[index - 1];
    }
    
    /**
     * 题目：在字符串中找出第一个只出现一次的字符。
     * 举个栗子：输入为"abaccdeff"，
     * 则输出"b"。
     */
    /*
     * 方法1：最直接想到的方法。
     * 从头开始遍历字符串，逐个检查当前字符在该字符串中是否只出现了一次。
     * 字符串长度为n，每个字符可能与后面的O(n)个字符相比较，因此时间复杂度为O(n^2)。
     */
    
    /*
     * 方法2：假设字符串中的字符为ASCII字符，256种可能，所以可以使用一个256长度的数组进行表示。
     * 构造一个简单的Map，键为字符串中的字符，值为它在字符串中出现的次数。
     * 这样可以在O(1)的时间内找到某个字符在字符串中出现的次数。
     * 字符串长度为n，则时间复杂度为O(n)。
     * 数组大小固定，因此空间复杂度为O(1)。
     */
    public static char firstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] counts = new int[256];
        char result = 0;
        
        // 两遍遍历字符串
        for (int i = 0, len = chars.length; i < len; i++) {
            counts[chars[i]]++;
        }
        for (int i = 0, len = chars.length; i < len; i++) {
            if (counts[chars[i]] == 1) {
                result = chars[i];
                break;
            }
        }
        return result;
    }
    
    /**
     * 题目：输入两个字符串，从第一字符串中删除第二个字符串中所有的字符。
     */
    /*
     * 使用与上面相同的思路，查找的时间复杂度降到了O(1)，
     * 整个算法的时间复杂度为O(n)。
     * 
     * 使用该思路也可以解决换位词问题！或者字符串中删除相同的字符！
     */
    public static String delCharInStr(String str1, String str2) {
        if (str2 == null || str2.length() == 0 || str1 == null || str1.length() == 0) {
            return str1;
        }
        int[] counts = new int[256];
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        StringBuilder result = new StringBuilder();
        
        for (int i = 0, len = chars2.length; i < len; i++) {
            counts[chars2[i]]++;
        }
        for (int i = 0, len = chars1.length; i < len; i++) {
            if (counts[chars1[i]] == 0) {
                result.append(chars1[i]);
            }
        }
        
        return result.toString();
    }
    
    /**
     * 题目：求出给定数组中的逆序数对的总数。
     * 举个栗子：
     * 输入 array[] = {7, 5, 6, 4},
     * 输出为5，因为一共5对逆序数对，
     * （7, 5）（7, 6）（7, 4）（5, 4）（6, 4）
     */
    
    /*
     * 方法1：顺序扫描数组，扫描到一个数字的时候，逐个比较它与其后数字的大小，
     * 以此来得到逆序对的数目。假设数组长度为n,则该方法的时间复杂度为O(n^2)。
     */
    
    /*
     * 方法2：基于归并排序的算法。
     * 多出一个长度为n的空间来存放临时操作数组，
     * 而归并算法的时间复杂度为O(nlogn)
     */
    public static int pairCount = 0;// 定义一个全局变量来控制逆序对数目
    public static int inversePairs(int [] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int len = array.length;
        int[] copy = new int[len];
        inversePairsCore(array, copy, 0, array.length - 1);
        return pairCount;
        
    }

    private static void inversePairsCore(int[] array, int[] copy, int start, int end) {
        if (start < end) {
            int mid = (start + end) >> 1;
            inversePairsCore(array, copy, start, mid);
            inversePairsCore(array, copy, mid + 1, end);
            merge(array, copy, start, mid, mid + 1, end);
        }
        
    }
    
//  合并
    private static void merge(int[] array, int[] copy, int lStart, int lEnd, int rStart, int rEnd) {
        int i = lEnd;
        int j = rEnd;
        int indexCopy = rEnd;// copy数组的索引变量
        while (i >= lStart && j >= rStart) {
            if (array[i] > array[j]) {
                copy[indexCopy--] = array[i--];
                pairCount += (j - rStart + 1);
            } else {
                copy[indexCopy--] = array[j--];
            }
        }
        while (i >= lStart) {
            copy[indexCopy--] = array[i--];
        }
        while (j>= rStart) {
            copy[indexCopy--] = array[j--];
        }
        for (int k = lStart; k <= rEnd; k++) {// 将排序好的数字复制到原来的数组
            array[k] = copy[k];
        }
    }
    
    
    /**
     * 题目：统计一个数字在排序(递增)数组中出现的次数。
     * 举个栗子：
     * 输入的数组为{1, 2, 3, 3, 3, 3, 4, 5}和数字3，
     * 输出为4。
     */
    /*
     * 方法1：
     * 分别从数组头尾进行遍历，记下k出现的开始和结束位置，然后再得到k的数量。
     * 时间复杂度为O(n)
     * 
     * 但是没有利用到排序数组的性质（一般要想到二分法）
     */
    public static int getNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int len = array.length;
        int firstK = -1, lastK = len;// 初始值设置很关键
        for (int i = 0, j = len - 1; i < len && j >= 0; i++, j--) {
            if (array[i] == k) {
                lastK = i;
            }
            if (array[j] == k) {
                firstK = j;
            }
        }
        if (firstK < 0) {// 数组中没有k这个元素
            return 0;
        }
        return lastK - firstK + 1;
    }
    
    /*
     * 方法2：
     * 遍历数组，因为数字都是0-9，所以额外用一个长度为10的数组来记录各数字出现的次数。
     * map的思路！
     */
    
    /*
     * 方法3：基于二分法（递归或者循环）
     * 分别找到第一个和最后一个位置。
     * 二分法的时间复杂度为O(logn)
     */
    public static int getNumberOfK2(int [] array , int k) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int end = array.length - 1;
        int firstK = getFirstKLoc(array, k , 0, end);// 当k不在数组中时，返回-1，下同
        int lastK = getLastKLoc(array, k, 0, end);
        if (firstK == -1 && lastK == -1) {
            return 0;
        }
        return lastK - firstK + 1;
   }

    private static int getLastKLoc(int[] array, int k, int start, int end) {
        int mid = -1;
        while (start <= end) {
            mid = (start + end) >> 1;
            if (array[mid] == k) {
             // 找到最后的位置：该位置元素等于k，并且其后一个元素不等于k，或者该位置位于最后
                if (mid == end || array[mid+1] != k) {
                    return mid;
                }
                start = mid + 1;
            } else if (array[mid] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int getFirstKLoc(int[] array, int k, int start, int end) {
        int mid = -1;
        while (start <= end) {
            mid = (start + end) >> 1;
            if (array[mid] == k) {
             // 找到第一个位置：该位置元素等于k，且其前一个元素不等于k，或者该位置位于最前
                if (mid == 0 || array[mid - 1] != k) {
                    return mid;
                } 
                end = mid - 1;
            } else if (array[mid] > k) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
    
    /*
     * 二分查找的递归实现
     */
    public static int binarySearch(int[] arr, int k, int start, int end) {
        if (arr == null || arr.length == 0 || start < 0 || end >= arr.length) {
            return -1;// 不合理的输入
        }
        if (start > end) {// 给定数组中没有k，start=0或者end+1
            return -1;
        }
        int mid = (start + end) >> 1;
        if (arr[mid] > k) {
            end = mid - 1;
        } else if (arr[mid] < k) {
            start = mid + 1;
        } else {
            return mid;
        }
        return binarySearch(arr, k, start, end);
    }
    
    /**
     * 题目：一个整型数组里除了两个数字以外，其他的数字都出现了两次。
     * 请写程序找出这两个只出现了一次的数字。时间复杂度为O(n),空间复杂度为O(1)。
     */
    
    /*
     * 思路：
     * 利用异或运算的性质：任何一个数字异或它自己都等于0。
     * 若一个数组中其余的数字出现2次，只有一个数字出现1次，那么对数组中数字进行异或运算，
     * 结果为出现一次的那个数字，其余的两两抵消了。
     * 因此，本题进行异或运算的结果为两个出现1次的数字的异或结果。
     * 根据该结果的二进位表示中第一次出现1的位置，将数组中的数字分成两组，
     * 每一组中含有一个出现次数为1的数字。
     */
    public static void findNumAppearOnce(int[] data) {
        if (data == null || data.length == 0) {
            return;
        }
        int resultxor = 0;
        int num1 = 0, num2 = 0;
        int len = data.length;
        
        for (int i = 0; i < len; i++) {
            resultxor ^= data[i];
        }
        if (resultxor == 0) {// 数组中的数字全部两两成对出现，异或结果为0！
            System.out.println("未找到符合条件的数字！数组中所有数字均出现2次！");
            return;
        }
        int indexBit1 = findFirstBit1(resultxor);// 找到二进制表示中第一个为1的位置
        for (int i = 0; i < len; i++) {
            if (isBit1(data[i], indexBit1)) {
                num1 ^= data[i];
            } else {
                num2 ^= data[i];
            }
        }
        // 可能出现超过2个数字的出现次数为1，因此必须进行一次检查！
        if (checkTimes(data, num1) && checkTimes(data, num2)) {
            System.out.printf("%d, %d", num1, num2);
        } else {
            System.out.println("不符合条件的数组！数组中超过2个数字的出现次数为1！");
        }
        
    }

    private static boolean checkTimes(int[] data, int target) {
        int count = 0;
        for (int num : data) {
            if (num == target) {
                count++;
            }
        }
        return count == 1 ? true : false;
    }

    private static int findFirstBit1(int resultxor) {
        int indexBit = 0;
        while ((resultxor & (0x1 << indexBit)) == 0 && indexBit < Integer.SIZE - 1) {
            indexBit++;
        }
        return indexBit;
    }
    
    private static boolean isBit1(int num, int indexBit1) {
        return (num & (0x1 << indexBit1)) != 0;
    }
    
    /**
     * 题目：输入一个递增排序的数组和一个数字s，在数组中查找2个数，
     * 使得它们的和正好是s。若出现多对的和等于s，输出任意一对即可。
     */
    
    /*
     * 充分利用排序数组的大小关系，从两端扫描数组，时间复杂度为O(n)。
     */
    public static void findNumsWithSum(int[] data, int sum) {
        if (data == null || data.length == 0) {
            return;
        }
        int len = data.length;
        int ahead = 0, behind = len - 1;
        int curSum = 0;
        while (ahead < behind) {
            curSum = data[ahead] + data[behind];
            if (curSum == sum) {
               break;
            } else if (curSum < sum) {
                ahead++;
            } else {
                behind--;
            }
        }
        if (ahead == behind) {
            System.out.println("未找到符合条件的数字对！");
        } else {
            System.out.printf("[%d, %d]", data[ahead], data[behind]);
        }
    }
    
    /**
     * 题目：输入一个正数s，打印出所有和为s的连续正整数（至少含2个数）。
     * 举个栗子：
     * 输入15，
     * 由于1+2+3+4+5=4+5=7+8=17,所有输出有1-5，4-5和7-8一共3个连续序列。
     */
    public static void findContinuousSeq(int sum) {
        if (sum < 3) {
            return;
        }
        int small = 1, big = 2;
        int mid = (sum + 1) >> 1;// small不能超过mid，否则curSum会永远大于sum
        int curSum = small + big;
        while (small < big) {
            if (curSum == sum) {
                printSeq(small, big);
            } 
            while (curSum > sum && small < mid) {
                curSum -= small;
                small++;
                if (curSum == sum) {
                    printSeq(small, big);
                }
            }
            
            big++;
            if (big > sum) {// big超过sum，停止！
                break;
            }
            curSum += big;
        }
        
    }

    private static void printSeq(int small, int big) {
        while (small <= big) {
            System.out.print(small + " ");
            small++;
        }
        System.out.println();
    }
    
    /**
     *  题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     *  数组中某些数字是重复的，但不知道有几个是重复了，也不知道每个数字重复了几次。
     *  请找出数组中的任意一个重复的数字。
     *  举个栗子：若输入长度为7的数组{2, 3, 1, 0, 2, 5, 3},那么对应的输出是重复的数字2或1。
     */
    /*
     * 方法1：将数组元素进行排序，然后从头到尾扫描数组即可。
     * 排序一个长度为n的数组的复杂度为O(nlogn)。
     */
    /* 
     * 方法2：利用哈希表。
     */
    /*
     * 方法3：数组中的数字从0到n-1，数组中共有n个元素，所以，若数组中没有重复元素，
     * 排序后的数组中位置i的元素应该为i。
     * 代码中虽然有一个双重循环，但是每个数字最多只要交换两次就能找到属于自己的位置，
     * 因此总的时间复杂度为O(n),由于不需要额外分配内存，所以空间复杂度为O(1)。
     */
     public static int findDumpluicateNum(int[] numbers) {
         if (numbers == null || numbers.length == 0) {// 检查数组有效性
             return -1;
         }
         int len = numbers.length;
         int temp;
         for (int num : numbers) {
             if (num < 0 || num >= len) {// 检查数组中的数字是否超出了0~(n-1)的范围
                 return -1;
             }
         }
         for (int i = 0; i < len; i++) {
             while (numbers[i] != i) {
                 if (numbers[i] == numbers[numbers[i]]) {// 返回重复的数字（符合条件的数字）
                     return numbers[i];
                 }
                 // 交换numbers[i]和numbers[numbers[i]]
                 temp = numbers[i];
                 numbers[i] = numbers[temp];
                 numbers[temp] = temp;
             }
         }
         return -1;
     }
     
     /**
      * 题目：给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
      * 其中B中的元素B[i]=A[0]*A[1]*..*A[i-1]*A[i+1]*...*A[n-1]
      * 要求不能使用除法！
      */
     /*
      * 方法1：n-1个数字连乘，得到数组B。时间复杂度为O(n^2)
      */
     /*
      * 方法2：将B[i]拆分成C[i]*D[i],
      * C[i]=A[0]*A[1]*..*A[i-1],D[i]=A[i+1]*...*A[n-1],
      * 那么C[i]=C[i-1]*A[i-1],D[i]=D[i+1]*A[i+1].
      * 这样可以把时间复杂度将为O(n)!
      */
     public static int[] multiplyArr(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        int len = array.length;
        int[] result = new int[len];
        result[0] = 1;// 关键值！
        for (int i = 1; i < len; i++) {// 从第二个位置开始
            result[i] = result[i - 1] * array[i - 1];
        }
        int temp = 1;// 关键值！
        for (int i = len - 2; i >= 0; i--) {// 从倒数第二个位置开始
            temp *= array[i + 1];
            result[i] *= temp;
        }
        return result;
    }
    
     
     /**
      * 题目：设计一个方法，用来判断在一个矩阵中是否存在一条包含字符串所有字符的路径。
      * 路径可以从矩阵中任意一格开始，每一步有上下左右四个方向可以选择。若一条路径经过了
      * 矩阵中的某一格，那么该路径不能再次进入该格子。
      * 
      * @param matrix
      * @param rows
      * @param cols
      * @param str
      * @return
      */
     /*
      * 回溯法典型栗子
      * visited数组来标记是否已经走过！
      */
     public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
         if (matrix == null || matrix.length == 0 || rows < 1 || cols < 1 || str == null || str.length == 0) {
            return false;
        }
         boolean[] visited = new boolean[rows * cols];
         int pathLen = 0;
         // 可以从矩阵的任意位置开始，i，j分别表示开始位置的横纵坐标
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathHelper(matrix, rows, i, cols, j, str, visited, pathLen)) {
                    return true;
                }
            }
        }
        return false;
     }

    private static boolean hasPathHelper(char[] matrix, int rows, int row, int cols,
            int col, char[] str, boolean[] visited, int pathLen) {
        if (pathLen == str.length) {
            return true;
        }
        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row*cols+col] == str[pathLen] &&!visited[row*cols+col]) {
            pathLen++;
            visited[row * cols + col] = true;
            hasPath = hasPathHelper(matrix, rows, row - 1, cols, col, str, visited, pathLen) || 
                    hasPathHelper(matrix, rows, row + 1, cols, col, str, visited, pathLen) ||
                    hasPathHelper(matrix, rows, row, cols, col - 1, str, visited, pathLen) ||
                    hasPathHelper(matrix, rows, row, cols, col + 1, str, visited, pathLen);
            
            if (!hasPath) {
                pathLen--;
                visited[row * cols + col] = false;
            }
        }
       return hasPath; 
    }
    
    /**
     * 题目：地上有一个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动，
     * 它每次可以向四个方向中的一个进行移动，但不能进入行列坐标的数位之和大于k的格子。
     * 请问该机器人能够到达多少个格子？
     * 举个栗子：
     * 当k=18时，机器人可以进入方格(35,37)，因为3+5+3+7<=18，
     * 而不能进入方格(35,38),因为3+5+3+8=19>18。
     * 
     * @param threshold i 限制阈值
     * @param rows 方格行数
     * @param cols 方格列数
     * @return 机器人可以到达的格子数
     */
    /*
     * 同样是回溯法。
     * 机器人要进入(i,j)时，首先要判断是否可以进入该格子，
     * 进入该格子后，再判断它周围的四个格子能否进入。
     */
    public int movingCount(int threshold, int rows, int cols) {
        boolean[] visited = new boolean[rows * cols];
        return movingCountHelper(threshold, 0, rows, 0, cols, visited);
    }

    private int movingCountHelper(int threshold, int row, int rows, int col,
            int cols, boolean[] visited) {
        int count = 0;
        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row*cols+col] && checkSum(threshold, row, col)) {
            visited[row*cols+col] = true;
            count = 1 + movingCountHelper(threshold, row + 1, rows, col, cols, visited) +
                    movingCountHelper(threshold, row - 1, rows, col, cols, visited) + 
                    movingCountHelper(threshold, row, rows, col + 1, cols, visited) + 
                    movingCountHelper(threshold, row, rows, col - 1, cols, visited);
        }
        return count;
    }

    private boolean checkSum(int threshold, int row, int col) {
        int sum = getDigitSum(row) + getDigitSum(col);
        return sum > threshold ? false : true;
    }

    private int getDigitSum(int digit) {
        int sum = 0;
        while (digit != 0) {
            sum += digit % 10;
            digit /= 10;
        }
        return sum;
    }
   
    /**
     * 题目：在一个含有重复元素的有序数组中删除重复元素，使得每个元素只出现一次。
     * 返回新数组的长度，不允许开辟新的空间。
     * 举个栗子：
     * 输入为{1, 1, 2, 3, 3}，那么压缩后的数组应该只包含1,2,3这三个元素，
     * 因此输出为3
     */
    public static int removeDeplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0;
        for (int i = 1, len = nums.length; i < len; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
            }
        }
        return index + 1;
    }
    
    /**
     * 题目：在上题基础上，一个元素至多出现两次。
     */
    public static int removeDeplicatepro(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int index = 0;
        int count = 0;
        for (int i = 1, len = nums.length; i < len; i++) {
            if (nums[index] != nums[i]) {
                nums[++index] = nums[i];
                count = 0;
            } else if (nums[index] == nums[i] && count < 1) {
                nums[++index] = nums[i];
                count++;
            }
       }
       return index + 1;
    }

}

