package com.kevin.instant.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 全局aop事务控制<br>
 * <p>
 * 备注:<br>
 * 1.扫描所有需要事务控制的service，包括mybatisplus的事务<br>
 * </p>
 * @author ling on 2019年3月1日<br>
 */
@Aspect
@Configuration
public class TransactionAdviceConfig {

	//事务扫描配置
	private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.kevin.instant.service.impl.*.*(..)) or (execution (* com.baomidou.mybatisplus.service.impl.*.*(..)))";

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor txAdvice() {
		//事务
		DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
		txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		//只读
		DefaultTransactionAttribute txAttr_REQUIRED_READONLY = new DefaultTransactionAttribute();
		txAttr_REQUIRED_READONLY.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		txAttr_REQUIRED_READONLY.setReadOnly(true);

		NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
		//加入扫描的方法的配置
		source.addTransactionalMethod("add*", txAttr_REQUIRED);
		source.addTransactionalMethod("insert*", txAttr_REQUIRED);
		source.addTransactionalMethod("save*", txAttr_REQUIRED);
		source.addTransactionalMethod("delete*", txAttr_REQUIRED);
		source.addTransactionalMethod("update*", txAttr_REQUIRED);
		source.addTransactionalMethod("exec*", txAttr_REQUIRED);
		source.addTransactionalMethod("set*", txAttr_REQUIRED);
		source.addTransactionalMethod("authorize*", txAttr_REQUIRED);
		source.addTransactionalMethod("check*", txAttr_REQUIRED);
		source.addTransactionalMethod("cancel*", txAttr_REQUIRED);//取消
		source.addTransactionalMethod("do*", txAttr_REQUIRED);
		source.addTransactionalMethod("login*", txAttr_REQUIRED);
		source.addTransactionalMethod("get*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("query*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("find*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("list*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("count*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("select*", txAttr_REQUIRED_READONLY);
		source.addTransactionalMethod("is*", txAttr_REQUIRED_READONLY);
		return new TransactionInterceptor(transactionManager, source);
	}

	@Bean
	public Advisor txAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}
