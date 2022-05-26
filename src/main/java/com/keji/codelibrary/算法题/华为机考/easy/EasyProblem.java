package com.keji.codelibrary.算法题.华为机考.easy;

import java.util.Scanner;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/30
 */
public class EasyProblem {

    public static void main(String[] args) {
        //demo1();
        //
        //demo2();
        //
        //demo3();
        //
        //demo4();
        //
        //demo5();

        //demo6();

        demo7();
    }

    /**
     * Catcher 是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，比如像这些ABBA，ABA，A，123321，
     * 但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。
     * 因为截获的串太长了，而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），
     * Cathcer的工作量实在是太大了，他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
     * （注意：记得加上while处理多个测试用例）
     */
    private static void demo7() {
        String pas1 = "ABBA";
        String pas2 = "ABA";
        String pas3 = "A";
        String pas4 = "123321";


    }

    /**
     * 功能: 求一个byte数字对应的二进制数字中1的最大连续数，例如3的二进制为00000011，最大连续2个1
     * <p>
     * 输入: 一个byte型的数字
     * <p>
     * 输出: 无
     * <p>
     * 返回: 对应的二进制数字中1的最大连续数
     */
    private static void demo6() {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextInt()) {

            String binaryString = Integer.toBinaryString(scanner.nextInt());


            String[] arrs = binaryString.split("0");

            int maxLength = 0;

            for (String arr : arrs) {
                maxLength = Math.max(arr.length(), maxLength);
            }

            System.out.println(maxLength);
        }
    }

    /**
     * 密码按如下规则进行计分，并根据不同的得分为密码进行安全等级划分。
     * 一、密码长度:
     * 5 分: 小于等于4 个字符
     * 10 分: 5 到7 字符
     * 25 分: 大于等于8 个字符
     * 二、字母:
     * 0 分: 没有字母
     * 10 分: 全都是小（大）写字母
     * 20 分: 大小写混合字母
     * 三、数字:
     * 0 分: 没有数字
     * 10 分: 1 个数字
     * 20 分: 大于1 个数字
     * 四、符号:
     * 0 分: 没有符号
     * 10 分: 1 个符号
     * 25 分: 大于1 个符号
     * 五、奖励:
     * 2 分: 字母和数字
     * 3 分: 字母、数字和符号
     * 5 分: 大小写字母、数字和符号
     * 最后的评分标准:
     * >= 90: 非常安全
     * >= 80: 安全（Secure）
     * >= 70: 非常强
     * >= 60: 强（Strong）
     * >= 50: 一般（Average）
     * >= 25: 弱（Weak）
     * >= 0:  非常弱
     * <p>
     * 对应输出为：
     * VERY_SECURE
     * <p>
     * SECURE,
     * <p>
     * VERY_STRONG,
     * <p>
     * STRONG,
     * <p>
     * AVERAGE,
     * <p>
     * WEAK,
     * <p>
     * VERY_WEAK,
     * <p>
     * <p>
     * 请根据输入的密码字符串，进行安全评定。
     * 注：
     * 字母：a-z, A-Z
     * 数字：-9
     * 符号包含如下： (ASCII码表可以在UltraEdit的菜单view->ASCII Table查看)
     * !"#$%&'()*+,-./     (ASCII码：x21~0x2F)
     * :;<=>?@             (ASCII<=><=><=><=><=>码：x3A~0x40)
     * [\]^_`              (ASCII码：x5B~0x60)
     * {|}~                (ASCII码：x7B~0x7E)
     */
    private static void demo5() {

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String password = scanner.next();

            getPasswordDesc(password);
        }
    }

    private static void getPasswordDesc(String password) {
        int lengthScore = getLengthScore(password);

        int caseScore = getCaseScore(password);

        int numberScore = getNumberScore(password);

        int signScore = getSignScore(password);

        int bountyScore = getBountyScore(caseScore, numberScore, signScore);

        int totalCount = lengthScore + caseScore + numberScore + signScore + bountyScore;

        if (totalCount >= 90) {
            System.out.println("VERY_SECURE");
        } else if (totalCount >= 80) {
            System.out.println("SECURE");
        } else if (totalCount >= 70) {
            System.out.println("VERY_STRONG");
        } else if (totalCount >= 60) {
            System.out.println("STRONG");
        } else if (totalCount >= 50) {
            System.out.println("AVERAGE");
        } else if (totalCount >= 25) {
            System.out.println("WEAK");
        }
    }

    private static int getBountyScore(int caseScore, int numberScore, int signScore) {
        if (caseScore == 20 && numberScore > 0 && signScore > 0) {
            return 5;
        }
        if (caseScore == 10 && numberScore > 0 && signScore > 0) {
            return 3;
        }
        if (caseScore > 0 && numberScore > 0 && signScore == 0) {
            return 2;
        }

        return 0;
    }

    private static int getSignScore(String password) {
        String signStr = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        int count = 0;
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if (signStr.contains(Character.toString(aChar))) {
                count++;
                if (count > 1) {
                    return 25;
                }
            }

        }
        return count == 1 ? 10 : 0;
    }

    private static int getNumberScore(String password) {
        int count = 0;
        String number = "0123456789";
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if (number.contains(Character.toString(aChar))) {
                count++;
                if (count > 1) {
                    return 20;
                }
            }

        }
        return count == 1 ? 10 : 0;
    }

    private static int getCaseScore(String password) {

        String lowerCase = "qwertyuiopasdfghjklzxcvbnm";
        String upperCase = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String caseStr = lowerCase + upperCase;

        boolean lowerCaseContains = false;
        boolean allLowerCaseContains = true;
        boolean upperCaseContains = false;
        boolean allUpperCaseContains = true;
        char[] chars = password.toCharArray();
        for (char aChar : chars) {

            String charStr = Character.toString(aChar);
            if (lowerCaseContains && upperCaseContains) {
                return 20;
            }
            if (!caseStr.contains(charStr)) {
                continue;
            }
            boolean lcc = lowerCase.contains(charStr);
            if (!lowerCaseContains && lcc) {
                lowerCaseContains = true;

            } else if (!lcc) {
                allLowerCaseContains = false;
            }

            boolean ucc = upperCase.contains(charStr);
            if (!upperCaseContains && ucc) {
                upperCaseContains = true;
            } else if (!ucc) {
                allUpperCaseContains = false;
            }
        }

        if (lowerCaseContains && upperCaseContains) {
            return 20;
        }

        if ((allLowerCaseContains && !allUpperCaseContains) || (allUpperCaseContains && !allLowerCaseContains)) {
            return 10;
        }

        return 0;
    }

    private static int getLengthScore(String password) {
        int length = password.length();
        if (length <= 4) {
            return 4;
        }

        if (length <= 7) {
            return 10;
        }
        return 25;
    }

    /**
     * 请编写一个函数（允许增加子函数），计算n x m的棋盘格子（n为横向的格子数，m为竖向的格子数）
     * 沿着各自边缘线从左上角走到右下角，
     * 总共有多少种走法，要求不能走回头路，
     * 即：只能往右和往下走，不能往左和往上走。
     */
    private static void demo4() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            int m = scanner.nextInt();
            int n = scanner.nextInt();

            function(m, n);
        }
    }

    private static int function(int m, int n) {
        if (m == 0 || n == 0) {
            return 1;
        }

        return function(m - 1, n) + function(m, n - 1);
    }

    /**
     * 功能:等差数列 2，5，8，11，14。。。。
     * 输入:正整数N >0
     * 输出:求等差数列前N项和
     * 返回:转换成功返回 0 ,非法输入与异常返回-1
     * 本题为多组输入，请使用while(cin>>)等形式读取数据
     * <p>
     * 0 0
     * 1 2+(1-1)*3=2
     * 2 2+(2-1)*3=5
     * 3 2+(3-1)*3=8
     */
    private static void demo3() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int arrFirst = 2;
            int count = 0;

            int i = scanner.nextInt();

            for (int j = 1; j <= i; j++) {
                count += (arrFirst + (j - 1) * 3);
            }
            System.out.println(count);
        }
    }

    /**
     * 将一个字符串str的内容颠倒过来，并输出。str的长度不超过100个字符。
     * 如：输入“I am a student”，输出“tneduts a ma I”。
     */
    private static void demo2() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            StringBuilder builder = new StringBuilder(str);
            StringBuilder reverse = builder.reverse();
            System.out.println(reverse.toString());
        }
    }

    /**
     * 正整数A和正整数B 的最小公倍数是指 能被A和B整除的最小的正整数值，设计一个算法，求输入A和B的最小公倍数。
     * <p>
     * 解：使用扩大法：如果两数不是互质，也没有倍数关系时，可以把较大数依次扩大2倍、3倍;……看扩大到哪个数时最先成为较小数的倍数时，这个数就是这两个数的最小公倍数。
     */
    private static void demo1() {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            int max = a > b ? a : b;
            int min = a > b ? b : a;

            for (int i = max; i <= a * b; i += max) {
                if (i % min == 0) {
                    System.out.println(i);
                }
            }
            System.out.println(1);

        }

    }

}
