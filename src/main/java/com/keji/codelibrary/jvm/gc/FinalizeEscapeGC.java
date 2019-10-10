package com.keji.codelibrary.jvm.gc;

/**
 * 被判断为可回收的对象并不会立马被回收。要宣告一个对象死亡，至少要经历两次标记过程:如果对象在进行可达性分析后被发现没有与GC Roots相连的引用链，那它将被第一次标记并且进行一次筛选，筛选的条件是此对象是否有必要执行finalize()方法。当对象没有覆盖finalize()方法，或者finalize()方法已经被虚拟机调用过，虚拟机将这两种情况都视为“没有必要执行”。
 *
 * 如果判断为没有必要执行finalize()方法，那第二次标记后，便会被回收。
 *
 * 如果判断为有必要执行finalize()方法，那么在finalize()方法执行过程中，该对象可以完成自我救赎--只要重新与引用链上的任何一个对象建立关联即可，譬如把自己(this关键字)赋值给某个类变量或者对象的成员变量，那在第二次标记时它将被移出"即将回收"集合。
 *
 * @author keji
 * @version $Id: FinalizeEscapeGC.java, v 0.1 2018/12/15 2:09 PM keji Exp $
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("hei,i am still alive!!!");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象成功拯救自己
        SAVE_HOOK = null;
        System.gc();

        //因为finalize方法优先级很低，所以暂停0.5秒等待它
        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i am dead");
        }

        //下面代码和上面一样，但是这次自救会失败，因为任何一个对象的finalize()方法，只会被调用一次。
        SAVE_HOOK = null;
        System.gc();

        //因为finalize方法优先级很低，所以暂停0.5秒等待它
        Thread.sleep(500);

        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("no,i am dead");
        }
    }
}
