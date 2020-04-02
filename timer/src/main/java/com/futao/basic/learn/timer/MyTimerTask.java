package com.futao.basic.learn.timer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * @author futao
 * @date 2020/3/15.
 */
@Getter
@Setter
@Slf4j
public class MyTimerTask extends TimerTask {

    private String taskName;

    public MyTimerTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        log.info("当前任务:[{}]", taskName);
    }
}
