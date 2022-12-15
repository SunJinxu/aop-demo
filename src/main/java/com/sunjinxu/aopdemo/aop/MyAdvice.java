package com.sunjinxu.aopdemo.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: sunjinxu
 * @date: 2022/12/14 01:02
 * @description: Aspect切面，包含前半部分的连接点joinpoint定义，以及后面的通知advice定义
 */
@Aspect
@Component
public class MyAdvice {
    private Logger logger = LoggerFactory.getLogger(MyAdvice.class);

    /**
     * 定义切点PointCut
     */
    @Pointcut(value = "execution( * com.sunjinxu.aopdemo.controller.*.*(..))")  // 切点作用的位置
    public void myPointCut() {
    }

    /*++++++++++++++++++++++++++++++++++++++++++*/

    /**
     * 定义前置Advice
     */
    @Before("myPointCut()")
    public void beforeLogger() {
        System.out.println("方法开始执行的时间：" + new Date(System.currentTimeMillis()));
    }

    /**
     * 定义后置Advice
     */
    @After("myPointCut()")
    public void afterLogger() {
        System.out.println("方法执行后时间：" + new Date(System.currentTimeMillis()));
    }


    /**
     * 定义Around类型Advice
     */
    @Around("myPointCut()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().toString();
        String methodName = pjp.getSignature().getName();

        ObjectMapper objectMapper = new ObjectMapper();
        Object[] array = pjp.getArgs(); // 获取目标方法参数
        logger.info("调用前: " + className + " : " + methodName + " 方法args: " + objectMapper.writeValueAsString(array));
        Object proceed = pjp.proceed(); // 被增强目标类中的目标方法执行，并获取返回值
        logger.info("调用后: " + className + " : " + methodName + " 方法return: " + objectMapper.writeValueAsString(proceed));

        return proceed;
    }
}
