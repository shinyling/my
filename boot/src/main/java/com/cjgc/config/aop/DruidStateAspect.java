package com.cjgc.config.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author shiny
 * @since 2016/9/4 15:25
 *
 */
@Aspect
@Component
@Order(3)
public class DruidStateAspect {
    private static final Logger log= LoggerFactory.getLogger(DruidStateAspect.class);

    @Pointcut("execution( * com.cjgc.domain..*(..)) && execution(* com.cjgc.service..*(..) )")
    public void druidPointcut(){
        log.info("配置druid endpoint");
    }

    @After("druidPointcut()")
    public Object interceptor(){
        //todo 继续配置interceptor
        return null;
    }

}
