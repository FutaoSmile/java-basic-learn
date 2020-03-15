package com.futao.basic.learn.thread._10synchronized;

/**
 * 《synchronized 是可重入锁》
 * <p>
 * synchronized 是可重入锁(粒度为线程，如果当前线程拿到了锁，那么该线程再去访问其他被同一把锁修饰的方法，是不需要重新获取锁的)
 * m1和m2的同步监视器都是同一个对象，但是m1获取到同步监视器之后，其实代表的是当前线程已经获取到了该锁，当前线程再去调用m2，不需要等待m1释放锁，可以直接拿到锁
 *
 * @author futao
 * @date 2020/3/14.
 */
public class _1可重入 {
    public static void main(String[] args) {
        _1可重入 sync = new _1可重入();
        sync.m1();
    }

    public synchronized void m1() {
        System.out.println("1");
        m2();
    }

    public synchronized void m2() {
        System.out.println("2");
    }
}
