package com.futao.basic.learn.thread._6线程通信._1生产者消费者问题;

import com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.core.Clerk;
import com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.core.Customer;
import com.futao.basic.learn.thread._6线程通信._1生产者消费者问题.core.Producer;

/**
 * @author futao
 * Created on 2019/11/15.
 */
public class Test {

    public static void main(String[] args) {

        Clerk clerk = new Clerk();

        new Thread(new Producer(clerk), "生产者1").start();

        new Thread(new Customer(clerk), "消费者1").start();
        new Thread(new Customer(clerk), "消费者2").start();
        new Thread(new Customer(clerk), "消费者3").start();

    }

}
