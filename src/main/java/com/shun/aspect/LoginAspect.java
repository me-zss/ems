package com.shun.aspect;

import com.shun.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author shun
 */
@Aspect
@Component
public class LoginAspect {
    @Around(value = "within(com.shun.controller.DeptController) || within(com.shun.controller.EmpController)")
    public Object surroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            HttpServletResponse response = null;
            for (Object param : proceedingJoinPoint.getArgs()) {
                if (param instanceof HttpServletResponse) {
                    response = (HttpServletResponse) param;
                    break;
                }
            }
            if (response == null) {
                System.out.println("response为空！");
            } else {
                response.sendRedirect(request.getContextPath() + "/user/login.jsp");
            }
        } else {
            System.out.println("环绕通知：" + user);
        }
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }
}
