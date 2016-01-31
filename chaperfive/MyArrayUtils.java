package chaperfive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

import javax.xml.crypto.dsig.CanonicalizationMethod;

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
    
}

