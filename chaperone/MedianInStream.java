package chaperone;

import java.util.PriorityQueue;

/**
 * 题目：得到数据流中的中位数
 */
/*
 * 求中位数，也就是找到这样一个数，它的左侧所有元素都比它要小，
 * 右侧所有元素都比它要大。实际上并不需要把两边的元素都排序，只需要找到左边最大的和右边最小的，
 * 因此想到用最大堆和最小堆，java里有对应的数据结构PriorityQueue，默认为自然排序。
 * 
 * 当数据流长度为奇数时，最小堆的长度要小于最大堆，所以此时的中位数就是最大堆的堆顶元素;
 * 偶数时，为两个堆堆顶元素的平均数。
 */
public class MedianInStream {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
    
    public void Insert(Integer num) {
        if (((minHeap.size() + maxHeap.size()) & 1) == 0) {// 长度为偶数
            if (num < minHeap.peek()) {// num比右边最小的值还要小，那么num应该加到左边
                maxHeap.offer(num);
            } else {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
        } else {
            if (num > maxHeap.peek()) {// num比左边最大值还要大，那么num应加到右边
                minHeap.offer(num);
            } else {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            }
        }
    }

    public Double GetMedian() {
        if (minHeap.size() != maxHeap.size()) {
            return Double.valueOf(maxHeap.peek());
        } else {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
