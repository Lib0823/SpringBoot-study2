package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect     // AOP를 정의하는 클래스에 할당
@Component      // 클래스 단위로 Bean 등록
public class ParameterAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")   // AOP를 적용시킬 지점 설정
    private void cut() {}

    @Before("cut()")    // 메서드 실행하기 이전
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("method name : "+method.getName());   // 실행 메서드 이름 가져오기

        Object[] args = joinPoint.getArgs();
        for(Object obj : args){
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }
    }

    @AfterReturning(value = "cut()", returning = "returnObj")    // 메서드 호출 성공시 실행
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("return obj");
        System.out.println(returnObj);
    }

}
