package top.danny.tools.string;

import org.apache.commons.lang.StringUtils;

/**
 * @author huyuyang@lxfintech.com
 * @Title: TextSimilarCompute
 * @Copyright: Copyright (c) 2016
 * @Description: 字符串相似度计算
 * @Company: lxjr.com
 * @Created on 2017-09-06 14:52:03
 * http://www.zuidaima.com/share/3528021308378112.htm
 */
public class TextSimilarCompute {
    private TextSimilarCompute(){}

    /**
     * 获取两字符串的相似度
     * @param str
     * @param target
     * @return
     */
    public static double similarDegree(String str, String target) {
        boolean strBlank = StringUtils.isBlank(str);
        boolean targetBlank = StringUtils.isBlank(target);
        if(strBlank && targetBlank){
            return 1D;
        }
        if(strBlank || targetBlank){
            return 1D;
        }

        final int maxLen = Math.max(str.length(), target.length());
        int allowMaxLen = 7000;
        double num = 0D;
        double total = 0D;
        for(int i=0;i<maxLen;i+=allowMaxLen){
            int strIndex = Math.min(i+allowMaxLen,str.length());
            int targetIndex = Math.min(i+allowMaxLen,target.length());
            double tmpR;
            if(i >= strIndex || i >= targetIndex){
                tmpR = 0D;
            }else{
                String tmpStr = str.substring(i,strIndex);
                String tmpTarget = target.substring(i,targetIndex);
                tmpR = 1 - (double) compare(tmpStr, tmpTarget) / Math.max(tmpStr.length(), tmpTarget.length());
            }
            total += tmpR;
            num++;
        }
        return total / num;
    }

    private static int compare(String str, String target) {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++) { // 初始化第一列
            d[i][0] = i;
        }

        for (j = 0; j <= m; j++) { // 初始化第一行
            d[0][j] = j;
        }

        for (i = 1; i <= n; i++) { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++) {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2) {
                    temp = 0;
                } else {
                    temp = 1;
                }

                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1]
                        + temp);
            }
        }
        return d[n][m];
    }

    private static int min(int one, int two, int three) {
        return (one = one < two ? one : two) < three ? one : three;
    }

    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis();
        String str = "aaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaac";
        String target = "aaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaacaaaaaaaaaaaaaaaaaaaaaaaaaaaac";
        System.out.println("similarityRatio=" + TextSimilarCompute.similarDegree(str,target));
        System.out.println("用时：" + (System.currentTimeMillis()-l));
    }
}
