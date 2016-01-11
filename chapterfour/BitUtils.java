package chapterfour;

public class BitUtils {
    
    /**
     * 题目：二进制数中1的个数
     */
    /*
     * 解法1:num不断右移，直至为0
     * 注意：使用无符号右移，否则当输入的num<0时，会陷入死循环
     */
    public static int getCountOfOne(int num) {
        int count = 0;
        while (num != 0 ) {
            if ((num & 1) == 1) {
                count++;
            }
            num >>>= 1;
        }
        return count;
    }
    
    /*
     * 解法2：标志位从1开始，不断左移，直至为0 
     */
    public static int getCountOfOne2(int num) {
        int count = 0;
        int flag = 1;// flag与count等长
        while (flag != 0) {
            if ((num & flag) != 0) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }
    
    /*
     * 解法3：技巧
     * 使用num &= (num - 1)来去掉二进制数最右边的那个1
     */
    public static int getCountOfOne3(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num &= (num - 1);
        }
        return count;
    }

    
    
    
}
