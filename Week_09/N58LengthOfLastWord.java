package cn.lihua.week09;

import org.junit.Test;

/**
 * 58. 最后一个单词的长度
 *
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。

 如果不存在最后一个单词，请返回 0 。

 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。


 示例:

 输入: "Hello World"
 输出: 5

 */
public class N58LengthOfLastWord {

    public int lengthOfLastWord1(String s) {
        String[] strs = s.trim().split(" +");
        // 前面和中间多余的空格会当作字符串,但最后无论多少个空格都忽略
//        System.out.println("字符串数组:");
//        for (String str : strs) {
//            System.out.println(str);
//        }
        int len = strs.length;
        //System.out.println("长度:" + len);
        if (len == 0) return 0;
        return strs[len - 1].length();
    }

    public int lengthOfLastWord2(String s) {
        try {
            String[] split = s.split(" ");
            return split[split.length - 1].length();
        }catch (Exception e){
            return 0;
        }
    }

    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while(end >= 0 && s.charAt(end) == ' ') end--;
        if(end < 0) return 0;
        int start = end;
        while(start >= 0 && s.charAt(start) != ' ') start--;
        return end - start;
    }

    @Test
    public void test() {
        String s = "   Hello   World  ";
        System.out.println(lengthOfLastWord1(s));
    }
}
