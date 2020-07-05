package com.hbzf.draw.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author luosai
 * @date 2020/5/5
 */
@Service("springUtil")
public class SpringUtil implements ApplicationContextAware {

    private SpringUtil() {
    }

    private ApplicationContext appContext;

    public static <T> T getBean(String name, Class<T> requiredType) {
        return ContextHolder.instance.appContext.getBean(name, requiredType);
    }

    @Override
    public void setApplicationContext(ApplicationContext appContext) {
        ContextHolder.instance.appContext = appContext;
    }

    private static class ContextHolder {
        private static final SpringUtil instance = new SpringUtil();
    }
}
