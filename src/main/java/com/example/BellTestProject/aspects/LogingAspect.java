package com.example.BellTestProject.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogingAspect {

    @Before("@annotation(com.example.BellTestProject.annotation.Logging)")
    public void BeforeAdvice(JoinPoint joinPoint){

        System.out.println("BeforeAdvice " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.BellTestProject.service.OrganizationService.*(..))")
    public void AfterAdvice(JoinPoint joinPoint){

        System.out.println("AfterAdvice " + joinPoint.getSignature().getName());
    }
    @After("@annotation(com.example.BellTestProject.annotation.Logging)")
    public void AfterAdviceOrg(JoinPoint joinPoint){
        System.out.println("AfterAdvice " + joinPoint.getSignature().getName());
    }

    @Around("execution(* com.example.BellTestProject.service.OrganizationService.*(..))")
    public Object AroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("AroundBeforeAdvice " + proceedingJoinPoint.getSignature().getName());
        Object object = proceedingJoinPoint.proceed();
        //System.out.println("AroundResult " + object.toString());
        System.out.println("AroundAfterAdvice " + proceedingJoinPoint.getSignature().getName());
        return object;
    }
}
