/**
 * 如何正确停止线程
 * 使用interrupt来通知，而不是强制。（通知线程应该结束了，但是线程结不结束还由线程自己说了算->通过代码来响应终止请求）
 * <p>
 * <p>
 * #### 线程在什么场景下会停止
 * 1. run()方法执行完毕
 * 2. run()方法发生异常并且未捕获
 *
 * @author futao
 * Created on 2019/11/20.
 */
package com.futao.basic.learn.thread._1_2线程的终止;