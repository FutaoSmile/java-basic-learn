/**
 * Oracle
 * 官方文档：Java中实现多线程的方式有两种，第一种是继承Thread类，第二种是实现Runnable接口，（FutureTask也是实现了Runnable接口)
 * *** 创建线程的方式比较:
 * 1. 【单继承】由于Java只能单继承，所以从这个角度来看，实现Runnable接口比较好
 * 2. 【新建线程的损耗】如果使用继承的方式，需要每次都新建一个对象来运行线程，而实现Runnable接口的方式可以重复使用同一个Runnable子类对象
 * 3. 【资源共享】实现Runnable接口的方式因为只需要一个Runnable的子类对象，所以容易实现线程之间的资源共享
 *
 * @author futao
 * Created on 2019/11/16.
 */
package com.futao.basic.learn.thread.imooc._1_1创建;