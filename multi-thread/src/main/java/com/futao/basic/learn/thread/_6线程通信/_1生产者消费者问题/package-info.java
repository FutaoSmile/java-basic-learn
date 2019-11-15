/**
 * 问题描述：
 * 生产者Producer将产品交给店员Clerk，消费者Customer从店员处取走产品，店员一次只能持有固定数量（20)个产品
 * * 如果生产者试图生产更多的产品，店员会叫生产者停一下，等店中有空位再通知生产者继续生产;
 * * 如果店中没有产品了，店员会告诉消费者等一下，如果店中有产品再通知消费者来取走产品;
 *
 * @author futao
 * Created on 2019/11/15.
 */
package com.futao.basic.learn.thread._6线程通信._1生产者消费者问题;