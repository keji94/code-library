package com.keji.codelibrary.算法题.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * 输入一个字符串，转成整数
 */
public class String2Int {


  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

      while (scanner.hasNextLine()) {

        String str = scanner.next();

        //判空
        if (StringUtils.isBlank(str)) {
          //不输出
          return;
        }

        boolean isNeg = false;
        //如果是负数
        if (str.charAt(0) == '-') {
          isNeg = true;
          str = str.substring(1);
        }

        //判断每一位是否都是数字
        char[] chars = str.toCharArray();

        //转换成duble
        double res = 0;
        for (int i = 0; i < chars.length; i++) {
          if (!Character.isDigit(chars[i])) {
            //如果字符串中有字符不是数字，不输出
            return;
          }else {
            //字符转int
            int num = chars[i]-48;
            res = res + Math.pow(10, chars.length - i - 1) * num;
          }
        }

        //转换int
        if (isNeg) {//负数
          if ((0 - res) < Integer.MIN_VALUE) {
            //超过int最小值
            return;
          }else {
            System.out.println((int)(0-res));
          }
        }else {
          if ( res > Integer.MAX_VALUE) {
            //超过最大值
            return;
          }else {
            System.out.println((int)res);
          }
        }


      }

  }
}
