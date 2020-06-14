# 第八周学习笔记

## 初级排序1: 选择排序
每次找最小值,然后放到待排序数组的起始位置
```
	/**
     * 选择排序: 时间复杂度 O(n^2)
     */
    public int[] selectSort(int[] nums) {
        if (nums == null) return null;
        int minIdx;
        for (int i = 0; i < nums.length - 1; i++) {
            minIdx = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIdx]) {
                    minIdx = j;
                }
            }
            // 交换
            int temp = nums[i];
            nums[i] = nums[minIdx];
            nums[minIdx] = temp;
        }
        return nums;
    }
```

## 初级排序2: 插入排序
```
	/**
     * 插入排序
     */
    public int[] insertSort1(int[] nums) {
        if (nums == null) return null;
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] <= current) {
                    nums[j + 1] = current;
                    break;
                } else {
                    nums[j + 1] = nums[j]; // 前面的数后移
                    if (j == 0) nums[j] = current;
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序
     */
    public int[] insertSort(int[] nums) {
        if (nums == null) return null;
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int preIdx = i - 1; // 当前待插入值,前面的元素
            // 寻找插入点的前一个位置
            while (preIdx >= 0 && nums[preIdx] > current) {
                nums[preIdx + 1] = nums[preIdx];
                preIdx--;
            }
            // 若preIdx == -1,说明该插入在0的位置
            nums[preIdx+1] = current;
        }
        return nums;
    }

```

## 初级排序3: 冒泡排序
```
	/**
     * 冒泡排序,每一趟将最大的数放到最后.
     */
    public int[] bubbleSort(int[] nums) {
        if (nums == null) return null;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }
```