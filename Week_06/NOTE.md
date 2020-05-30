# 第六周学习笔记
本周动态规划好难啊，总感觉时间不够，赶第一遍都忙死了，来不及刷遍数。

## 53. 最大子序和
```
    /**
     * 改进:复用数组,因为循环下标从小到大,前面的已经记录最大值
     * DP: 最后一个的最大子串: 或者 自己,或者包含前面的最大子串
     * 1.分治(子问题) max_sum[i] = Max (max_sum[i-1], 0) + nums[i]
     * 2.状态数组定义 dp[i]
     * 3.DP方程: dp[i] = Max(0, dp[i-1]) + nums[i]
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 当前子数组的最大值: 或者不包含前面的最大子数组,或者包含前面的最大子数组
            // 若前面都<0,还不如不选
            // dp[i] = nums[i] + Math.max(0, dp[i - 1]);
            nums[i] = nums[i] + Math.max(0, nums[i - 1]);
            max = Math.max(max, nums[i]);
        }
        return max;
    }
```

## 62. 不同路径 
路径问题转换为二维数组。
```
   /**
     * 无障碍情况下,起点和终点可以互换,从起点走动终点,就是从终点走到起点的方法
     * 也可以理解为,机器人走到当前位置有几条路径
     * a[i][j] = a[i][j+1] + a[i+1][j] //只能向右和向下,右边格子+下边格子
     * 转换为:
     * a[i][j] = a[i][j-1] + a[i-1][j] //只能向左和向上,左边格子+上边格子
     */
    public int uniquePaths(int m, int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                a[j] += a[j - 1];
            }
        }
        return a[n - 1];
    }
```

## 63. 不同路径 II
```
    /**
     * a[0][0]不能重复计算
     * 在计算第0列和第0行的时候,要看前面有无障碍物,若有,则路径0条
     */
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        // start就是障碍物,就没有路径了
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1; // 第一个单独算
        // 设置第0列的值,若为空地设为1,否则设为0
        for (int i = 1; i < rows; i++) {
            // 当前是空地,并且上边格子走法是1,否则上边格子就是0(说明之前是障碍物)
            obstacleGrid[i][0] = obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1 ? 1 : 0;
        }
        // 设置第0行的值,若为空地设为1,否则设为0
        for (int j = 1; j < cols; j++) {
            obstacleGrid[0][j] = obstacleGrid[0][j] == 0 && obstacleGrid[0][j - 1] == 1 ? 1 : 0;
        }
        // 计算当前格子值 = 上边格子 + 左边格子
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (obstacleGrid[i][j] == 0) { // 空地
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        // 最后一个格子的值,是最终结果
        return obstacleGrid[rows - 1][cols - 1];
    }
```

## 77. 组合
```
    /**
     * 递归,类似左右括号
     * @param n 表示整数范围[1,n]
     * @param k 取k个数的组合
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n * k == 0 || n < k) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new LinkedList<>();

        recur(1, n, k, new LinkedList<>(), result);
        return result;
    }

    private void recur(int first, int n, int k, LinkedList<Integer> sub, List<List<Integer>> result) {
        // terminater
        if (sub.size() == k) {
            result.add(new ArrayList<>(sub));
            return;
        }

        for (int i = first; i <= n; i++) {
            sub.add(i);
            recur(i + 1, n, k, sub, result);
            // 删除一个,为了first能跟所有的都匹配一遍
            sub.removeLast();
        }
    }
```

## 120. 三角形最小路径和
```
    /**
     * 动态规划2
     * 第[i,j]的最小值,可以分为 下一行 左边的最小路径  与 右边的最小路径 中取最小值
     * 就成为了分治
     * 此处 在 原list 上操作
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 最后一行的值不用动了,从倒数第二行开始递推
        for (int i = triangle.size() - 2; i >= 0; i--) {
            // 每行的列要计算
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 当前[i,j] 的下一行的左右值的最小值
                int min = Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1));
                // 对第行j列设置值 = 自己 + 下一行的最小值
                triangle.get(i).set(j, triangle.get(i).get(j) + min);
            }
        }
        return triangle.get(0).get(0);
    }
```

## 198. 打家劫舍
可以简化，但时间不够，先提交吧。
```
    /**
     * a[i]: 从 0 到 i 能偷到的最大值.最后变成 求a[n-1] ,即 从 0 到 n-1 个房子,能偷到的最大值
     * a[i][0,1]: 0 - 第i个房子不偷, 1 - 第一个房子偷 的最大值
     * 1. 第i个房子不偷, 则i-1 可以偷,也可以不偷,取第i-1个房子偷与不偷的最大值
     *      a[i][0] = Max( a[i-1][0], a[i-1][1] )
     * 2. 第i个房子偷, 则 i-1 就不能偷了(a[i-1][0]), 然后要加上当前的金额nums[i]
     *      a[i][1] = a[i-1][0] + nums[i]
     * 最后在n-1房子的偷与不偷中找最大值  Max(a[n-1][0], a[n-1][1] )
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0; // 边界

        int n = nums.length;
        int[][] a = new int[n][2];  // 第二维 用于存放 偷与不偷的最优解,下标0表示不偷,下标1表示偷

        a[0][0] = 0;        // 第0个房子不偷,金额为0
        a[0][1] = nums[0];  // 第0个房子偷,金额为数组的第0个元素值 nums[0]

        for (int i = 1; i < n; i++) {
            a[i][0] = Math.max(a[i - 1][0], a[i - 1][1]);   // 第i个房子不偷
            a[i][1] = a[i - 1][0] + nums[i];                // 第i个房子偷
        }

        return Math.max(a[n - 1][0], a[n - 1][1]); // 在最后一个房子 偷 与 不偷 中找最大值
    }
```

## 221. 最大正方形
```
    /**
     * 动态规划
     * min(上, 左, 左上) + 1
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length < 1) return 0;

        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;    // 最长边
        int[][] dp = new int[height + 1][width + 1]; // 增加第0行和第0列为0

        // 在matrix范围内循环
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (matrix[row][col] == '1') {
                    // 左,上,左上 的最小边长 + 1  -- min(,)只能有2个参数,故要2次
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]),
                            dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
                // == '0' 时不计算,则dp[row][col]==0 初始化时预设了
            }
        }
        return maxSide * maxSide;
    }
```

## 312. 戳气球
```
    /**
     * 动态规划(自顶向下）
     */
    public int maxCoins(int[] nums) {
        int n = nums.length + 2;            // 长度+2,用来放左右边界
        int[] new_nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            new_nums[i + 1] = nums[i];      // 数组赋值
        }
        new_nums[0] = new_nums[n - 1] = 1;  // 左右边界设为1
        int[][] memo = new int[n][n];   // 存储左右边界内的最大值

        return dp(memo, new_nums, 0, n - 1);
    }

    // 左右边界内的最大值
    private int dp(int[][] memo, int[] nums, int left, int right) {
        // 如果左右边界相邻,表示中间没有气球,则此范围结束
        if (left + 1 == right) return 0;

        // 做左右边界中的最大值已有了,则直接取
        if (memo[left][right] > 0) return memo[left][right];

        int max = 0;
        for (int i = left + 1; i < right; i++) {
            max = Math.max(max, nums[left] * nums[i] * nums[right]
            + dp(memo, nums, left, i) + dp(memo, nums, i, right));
        }
        memo[left][right] = max;
        return max;
    }
```

## 322. 零钱兑换
```
    /**
     * 贪心 + dp 剪枝
     * 此题精妙之处在于:降序排列 + 剪枝
     */
    int answer = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        Arrays.sort(coins); // 升序
        int n = coins.length;
        // 降序排列
        for (int i = 0; i < n / 2; i++) {
            int temp = coins[i];
            coins[i] = coins[n - 1 - i];
            coins[n - 1 - i] = temp;
        }
        coinChange(coins, amount, 0, 0);
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void coinChange(int[] coins, int amount, int c_index, int count) {
        if (amount == 0) {
            answer = Math.min(answer, count);
            return;
        }

        if (c_index == coins.length) return;    // 硬币索引超出了

        // k + count < answer 这个剪枝为什么?
        // 因为币值从高到底排列,而k是金额不超情况下的数量,若 此时数量就已经超过answer了,
        // 那么(k-1)个当前币值 + 低于该币值的硬币数量就更加超过answer了
        for (int k = amount / coins[c_index] ; k >=0 && k + count < answer; k--) {
            coinChange(coins, amount - k * coins[c_index], c_index + 1, count + k);
        }
    }
```

##  1143. 最长公共子序列
```
    /**
     * 用二维数组,类似路径问题
     * 这里将null值归入第0行和第0列,故数组行列长度都需要+1
     * 循环时从1开始,到长度+1 // 这个容易错
     * 比较时,是字符串中的,下标从0开始,故i-1,j-1,如text1.charAt(j - 1)
     * 返回结果时,注意长度已经是字符串长度+1了,故要dp[rows][cols]
     * 总结:本题的错误主要在下标没弄清楚
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int cols = text1.length();
        int rows = text2.length();
        int[][] dp = new int[rows + 1][cols + 1];   // 行和列都要+1,将null 算进去
        // 由于空串的时候肯定最长公共子串为0，所以直接从dp[1][1]开始遍历
        for (int i = 1; i < rows + 1; i++) {
            for (int j = 1; j < cols + 1; j++) {
                // 若s1[j] == s2[i] 时, 是去掉此字符时的长度+1
                if (text1.charAt(j - 1) == text2.charAt(i - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //s1[i] != s2[j]则表示至少有一个不在（要么s1[i]不在，要么s2[j]不在，或者都不在）。
                    // 所以当前结果就相当于之前结果的中最大的那一个
                    // 二维数组上面格子 和 左边格子 的最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[rows][cols];
    }
```
