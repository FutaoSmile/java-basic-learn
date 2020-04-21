/**
 * 线程安全问题：多个线程同时操作同一个共享数据
 * <p>
 * sleep()与wait()
 * (1).都可以使当前线程进入阻塞状态。
 * (2).sleep()任何地方都可以调用，wait()只能在同步代码块或者同步方法中
 * (3).在同步方法(同步代码块)中调用wait()会释放锁(同步监视器)，sleep()不会
 *
 * @author futao
 * Created on 2019/11/14.
 */
package com.futao.basic.learn.thread.imooc._4线程安全_线程同步;