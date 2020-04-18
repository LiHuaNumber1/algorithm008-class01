# 第一周学习笔记
报了这个班，非常开心，感觉能学到东西，干货满满，群里的同学们也非常的友善、乐于助人，希望自己接来下的时间能坚持到底，收获满满吧。
>> 本周学到反转和双指针，非常好用，希望以后能够熟练。也第一次知道斐波那契数列的应用场景。

# 189.旋转数组
## 使用反转
### 算法

这个方法基于这个事实：当我们旋转数组 k 次， k\%nk%n 个尾部元素会被移动到头部，剩下的元素会被向后移动。

在这个方法中，我们首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-kn−k 个元素，就能得到想要的结果。

假设 n=7n=7 且 k=3k=3 。

原始数组                  : 1 2 3 4 5 6 7
反转所有数字后             : 7 6 5 4 3 2 1
反转前 k 个数字后          : 5 6 7 4 3 2 1
反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果
```
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        int temp = 0;
        while (start < end) {
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
```

# 11. 盛最多水的容器
## 双指针法
### 算法流程
设置双指针 ii,jj 分别位于容器壁两端，根据规则移动指针（后续说明），并且更新面积最大值 res，直到 i == j 时返回 res。

```
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            // 要先使用i或j的值, ++ 该放后面
            int area = (height[i] < height[j]) ? (j - i) * height[i++] : (j - i) * height[j--];
            max = Math.max(max, area);
        }
        return max;
    }
```

# 70. 爬楼梯
## 斐波那契数列
### 思路
列举一些基本情况，找出重复子问题（if else for while recursion）
### 分析
    第1级: 1 种
    第2级: 2 种
    第3级: 只能从第1级 或 第2级 台阶走过来 f(1) + f(2) = 3
    第4级: 只能从第2级 或 第3级 台阶走过来 f(2) + f(3)
    第n级: 只能从第n-2级 或 第n-1级 台阶走过来 f(n-2) + f(n-1)
```
   public int climbStairs(int n) {
        int f1 = 1;
        int f2 = 2;
        int f3 = 3;
        if (n <= 2) {
            return n;
        }
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2;
            f1 = f2;
            f2 = f3;
        }
        return f3;
    }
```
