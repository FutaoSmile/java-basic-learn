package com.futao.basic.learn.javassist;

import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import javassist.*;
import javassist.util.HotSwapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 修改运行中的类/方法，并重新load该程序
 *
 * @author futao
 * @date 2020/4/22
 */
@Slf4j
public class _3 {
    public static void main(String[] args) throws IOException, IllegalConnectorArgumentsException, NotFoundException, CannotCompileException, InterruptedException {

        _3_1 p = new _3_1();

        new Thread(() -> {

            while (true) {
                try {
                    Thread.sleep(500L);
                    log.info("{}", p.printContent());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        HotSwapper hotSwapper = new HotSwapper(9999);
        ClassPool classPool = ClassPool.getDefault();

        CtClass ctClass = classPool.getCtClass(_3_1.class.getName());
        CtMethod printMethod = ctClass.getDeclaredMethod("printContent");

        printMethod.setBody("{return 0;}");

        Thread.sleep(2000L);
        log.info("gogogogogogogogogo");
        hotSwapper.reload(_3_1.class.getName(), ctClass.toBytecode());

    }
}
