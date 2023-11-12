package io.project.app.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;

import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;

@Slf4j
class SpringBuiltInEventsListener implements ApplicationListener<SpringApplicationEvent>, ApplicationContextAware {

    @Override
    public void onApplicationEvent(SpringApplicationEvent event) {
        log.debug("Global Event Received - " + event);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    }

}
