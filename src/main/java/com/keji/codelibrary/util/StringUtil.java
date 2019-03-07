package com.keji.codelibrary.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author keji
 * @version $Id: StringUtil.java, v 0.1 2018-10-18 16:42 keji Exp $$
 */
public class StringUtil {

    public static String getExceptionStr(Throwable t) {
        StringWriter sw = new StringWriter();

        try(PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            return sw.toString();
        }
    }

    public static void main(String[] args) {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            String exceptionStr = getExceptionStr(e);
            System.out.println(exceptionStr);
        }

    }
}
