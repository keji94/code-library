package com.keji.codelibrary.sync;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/7/1
 */
public class AccountingSync implements Runnable{

    static final AccountingSync instance = new AccountingSync();

    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (instance) {
                i++;
            }
        }
    }
}
