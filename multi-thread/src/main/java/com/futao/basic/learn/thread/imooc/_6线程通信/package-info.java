/**
 * void notify()
 * Wakes up a single thread that is waiting on this object’s monitor.
 * 译：唤醒在此对象监视器上等待的单个线程
 * void notifyAll()
 * Wakes up all threads that are waiting on this object’s monitor.
 * 译：唤醒在此对象监视器上等待的所有线程
 * void wait( )--会释放锁，并且wait()方法后面的代码需要等到该线程被唤醒并获得锁，才能继续往下执行
 * Causes the current thread to wait until another thread invokes the notify() method or the notifyAll( ) method for this object.
 * 译：导致当前的线程等待，直到其他线程调用此对象的notify( ) 方法或 notifyAll( ) 方法
 * void wait(long timeout)
 * Causes the current thread to wait until either another thread invokes the notify( ) method or the notifyAll( ) method for this object, or a specified amount of time has elapsed.
 * 译：导致当前的线程等待，直到其他线程调用此对象的notify() 方法或 notifyAll() 方法，或者指定的时间过完。
 * void wait(long timeout, int nanos)
 * Causes the current thread to wait until another thread invokes the notify( ) method or the notifyAll( ) method for this object, or some other thread interrupts the current thread, or a certain amount of real time has elapsed.
 * 译：导致当前的线程等待，直到其他线程调用此对象的notify( ) 方法或 notifyAll( ) 方法，或者其他线程打断了当前线程，或者指定的时间过完。
 *
 * @author futao
 * Created on 2019/11/15.
 */
package com.futao.basic.learn.thread.imooc._6线程通信;