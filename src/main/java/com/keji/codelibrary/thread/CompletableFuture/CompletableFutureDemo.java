package com.keji.codelibrary.thread.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * Copyright (c) 2020 Choice, Inc.
 * All Rights Reserved.
 * Choice Proprietary and Confidential.
 *
 * @author keji
 * @since 2020/6/16
 */
public class CompletableFutureDemo {

    public static CompletableFuture<Integer> compute() {
        return new CompletableFuture<>();
    }

    public static void main(String[] args) throws Exception {
        final CompletableFuture<Integer> f = compute();

        class Client extends Thread {
            final CompletableFuture<Integer> f;
            Client(String threadName, CompletableFuture<Integer> f) {
                super(threadName);
                this.f = f;
            }
            @Override
            public void run() {
                try {
                    System.out.println(this.getName() + ": " + f.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        new Client("Client1", f).start();
        new Client("Client2", f).start();
        System.out.println("waiting");
        f.complete(100);
        //f.completeExceptionally(new Exception());
        System.in.read();
    }
}
