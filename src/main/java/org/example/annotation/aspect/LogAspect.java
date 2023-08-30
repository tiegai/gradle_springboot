package org.example.annotation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 操作日志切面处理
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 配置编织入点
     * pointcut表示一个切点，@annotation表示这个切点切到一个注释上，后面带上这个注释的全类名
     */
    @Pointcut("@annotation(org.example.annotation.aspect.Log)")
    public void logPointCut(){};

    @Before("logPointCut()")
    public void doBefore(){
        log.info("===切面之前会执行");
    }

    @After("logPointCut()")
    public void doAfter(){
        log.info("===切面之后会执行");
    }



    /**
     * 环绕通知
     */
    @AfterReturning("logPointCut()")
    public void logAround(JoinPoint joinPoint){
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //获取入参
        Object[] params = joinPoint.getArgs();

        StringBuilder builder = new StringBuilder();
        for (Object o : params){
            builder.append(o + ";");
        }
        log.info("进入方法["+methodName+"],,,参数为：" + builder.toString());

        //获得注解
        Log testlog = getAnnotationLog(joinPoint);
        getControllerMethodDescription(testlog);

        log.info(methodName + "方法执行结束");

    }

    /**
     * 是否存在注解，存在就获取
     * @param joinPoint
     * @return
     */
    private Log getAnnotationLog(JoinPoint joinPoint){
        Signature signature =joinPoint.getSignature();
        MethodSignature methodSignature =(MethodSignature)signature;
        Method method =methodSignature.getMethod();
        if (method != null){
            return method.getAnnotation(Log.class);
        }
        return null;
    }

    /**
     *  获取注解中对方法的描述信息 注解Log中的各个参数
     * @param testlog
     */
    private void getControllerMethodDescription(Log testlog) {
        //此处仅用来测试用，实际操作过程中，可能会有日志插表等逻辑
        String operator = testlog.operator();
        String state = testlog.state();
        log.info("注解在方法内的参数 operator ： " + operator + ", state :" + state);
    }

}
