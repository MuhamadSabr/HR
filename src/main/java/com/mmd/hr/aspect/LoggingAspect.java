package com.mmd.hr.aspect;

import com.mmd.hr.entity.Logging;
import com.mmd.hr.reposiroty.LoggingRepository;
import com.mmd.hr.util.LoggingTypes;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

	private LoggingRepository loggingRepository;

	public LoggingAspect(LoggingRepository loggingRepository){
		this.loggingRepository = loggingRepository;
	}

	@Pointcut("execution(* com.mmd.hr.controller.EmployeeController.*(..))")
	private void employeeController(){};
	@Pointcut("execution(* com.mmd.hr.controller.UserController.*(..))")
	private void userController(){};
	@Pointcut("employeeController() || userController()")
	private void criticalControllers(){};

	@Pointcut("execution(* com.mmd.hr.controller.EmployeeController.*delete*(..))")
	private void employeeDanger(){}
	@Pointcut("execution(* com.mmd.hr.controller.DepartmentController.*delete*(..))")
	private void departmentDanger(){}
	@Pointcut("execution(* com.mmd.hr.controller.JobController.*delete*(..))")
	private void jobDanger(){}
	@Pointcut("execution(* com.mmd.hr.controller.UserController.*delete*(..))")
	private void userDanger(){}
	@Pointcut("employeeDanger() || departmentDanger() || userDanger() || jobDanger()")
	private void dangerActions(){}

	@Pointcut("execution(* com.mmd.hr.controller.EmployeeController.saveEmployee(..))")
	private void employeeWarning(){}
	@Pointcut("execution(* com.mmd.hr.controller.DepartmentController.saveDepartment(..))")
	private void departmentWarning(){}
	@Pointcut("execution(* com.mmd.hr.controller.JobController.saveJob(..))")
	private void jobWarning(){}
	@Pointcut("execution(* com.mmd.hr.controller.UserController.saveUser(..))")
	private void userWarning(){}
	@Pointcut("execution(* com.mmd.hr.controller.UserController.*update*(..))")
	private void userWarning2(){}
	@Pointcut("employeeWarning() || departmentWarning() || userWarning() || userWarning2() || jobWarning()")
	private void warningActions(){}


	@Before("criticalControllers()")
	public void beforeAdvice(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String accessed = sra.getRequest().getRequestURI() + (sra.getRequest().getQueryString()!=null ? "/" + sra.getRequest().getQueryString() : "");
		loggingRepository.save(new Logging(LoggingTypes.INFO.name(), username, accessed));
		}

	@AfterReturning("dangerActions()")
	public void afterReturningDanger(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String accessed = sra.getRequest().getRequestURI() + (sra.getRequest().getQueryString()!=null ? "/" + sra.getRequest().getQueryString() : "");
		loggingRepository.save(new Logging(LoggingTypes.DANGER.name(), username, accessed));
	}

	@AfterReturning("warningActions()")
	public void afterReturningWarning(){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String accessed = sra.getRequest().getRequestURI() + (sra.getRequest().getQueryString()!=null ? "/" + sra.getRequest().getQueryString() : "");
		loggingRepository.save(new Logging(LoggingTypes.WARN.name(), username, accessed));
	}

	@AfterThrowing(pointcut = "execution(* com..*.*(..))", throwing = "exc")
	public void afterThrowingException(JoinPoint joinPoint, Throwable exc){
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String accessed = sra.getRequest().getRequestURI() + (sra.getRequest().getQueryString()!=null ? "/" + sra.getRequest().getQueryString() : "");
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		exc.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();

		loggingRepository.save(new Logging(LoggingTypes.EXCEPTION.name(), username, accessed, stackTrace));
	}
}
