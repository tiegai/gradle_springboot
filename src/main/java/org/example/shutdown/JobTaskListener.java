package org.example.shutdown;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobTaskListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent){
        // 在spring bean容器销毁之前执行的事件，防止数据库连接池在任务终止前销毁
        if (applicationEvent instanceof ContextClosedEvent) {
            log.info("关闭相关的定时任务");
        }
    }
}
