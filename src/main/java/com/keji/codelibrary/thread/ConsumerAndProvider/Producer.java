package com.keji.codelibrary.thread.ConsumerAndProvider;

import com.keji.codelibrary.thread.ConsumerAndProvider.WaitAndNotify.Storage;

/**
 * Created by wb-ny291824 on 2017/8/21.
 * 生产者类Producer继承线程类Thread
 */
public class Producer extends Thread {
    /**
     * 每次生产的产品数量
     */
    private int num;

    /**
     * 所在放置的仓库
     */
    private Storage storage;

    /**
     * 构造函数，设置仓库
     * @param storage 仓库
     */
    //
    public Producer(Storage storage) {
        this.storage = storage;
    }


    public void run() {
        produce(num);
    }

    // 调用仓库Storage的生产函数
    public void produce(int num) {
        storage.produce(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}
