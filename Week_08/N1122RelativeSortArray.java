package cn.lihua.week08;

/**
 * 1122. 数组的相对排序
 *
 * 给你两个数组，arr1 和 arr2，

 arr2 中的元素各不相同
 arr2 中的每个元素都出现在 arr1 中
 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

  

 示例：

 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 输出：[2,2,2,1,4,3,3,9,6,7,19]
  

 提示：

 arr1.length, arr2.length <= 1000
 0 <= arr1[i], arr2[i] <= 1000
 arr2 中的元素 arr2[i] 各不相同
 arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 */
public class N1122RelativeSortArray {

    /**
     * 计数排序
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] arr = new int[1001];  // 由于题目说arr1的范围在0-1000，所以生成一个1001大小的数组用来存放每个数出现的次数。
        int[] res = new int[arr1.length];   // 储存答案的数组。
        int index = 0;
        for (int item : arr1) { // 遍历arr1，把整个arr1的数的出现次数储存在arr上，arr的下标对应arr1的值，arr的值对应arr1中值出现的次数。
            arr[item]++;        // 如果遇到了这个数，就把和它值一样的下标位置上+1，表示这个数在这个下标i上出现了1次。
        }
        for (int item : arr2) { // 遍历arr2，现在开始要输出答案了。
            while (arr[item] > 0) { // 如果arr2的值在arr所对应的下标位置出现次数大于0，那么就说明arr中的这个位置存在值。
                res[index++] = item;// 如果存在值，那就把它加到 res 中，因为要按arr2的顺序排序。
                arr[item]--;        // 加进去了次数 减1 ，不然就死循环了
            }
        }
        for (int i = 0; i < 1001; i++) { // 如果arr1的值不在arr2中，那么不能就这么结束了，因为题目说了如果不在，剩下的值按照升序排序。
            while (arr[i] > 0) {         // 同样也是找到大于0的下标，然后一直加到ans中，直到次数为0。
                res[index++] = i;
                arr[i]--;
            }
        }
        return res;     // 返回最终答案
    }
}
