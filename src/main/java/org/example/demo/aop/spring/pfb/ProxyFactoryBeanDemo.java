package org.example.demo.aop.spring.pfb;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProxyFactoryBeanDemo {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ProxyFactoryBeanDemo.class);
    }

    @Autowired
    ApplicationContext context;

    @Bean
    public CommandLineRunner runner(){
        return args -> {
            System.out.println(">> D one ");
            Documentarist one = (Documentarist) context.getBean("documentaristOne");
            one.execute();

            System.out.println(">> D two ");
            Documentarist two = (Documentarist) context.getBean("documentaristTwo");
            two.execute();
        };
    }

    @Bean
//    @Primary
    public SingerIImpl oneMoreSinger(){
        return new SingerIImpl();
    }

    @Bean
    public AuditAdvice advice(){
        return new AuditAdvice();
    }

    @Bean
    public Documentarist documentaristOne(SingerI proxyOne){
        return new Documentarist(proxyOne);
    }

    @Bean(name = "proxyOne")
    public SingerI proxyOne(BeanFactory beanFactory){
        final ProxyFactoryBean proxy =
                createProxyReturnFactoryBean(new Class[]{SingerI.class}, oneMoreSinger(), beanFactory);
        proxy.addAdvice(advice());
        return (SingerI) proxy.getObject();
    }

    <T> ProxyFactoryBean createProxyReturnFactoryBean(final Class<?>[] proxyInterfaces, final T target, final BeanFactory beanFactory) {
        final ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setBeanFactory(beanFactory);
        proxy.setInterfaces(proxyInterfaces);
        proxy.setTarget(target);
        return proxy;
    }

    @Bean(name = "proxyTwo")
    public SingerI proxyTwo(){
        ProxyFactoryBean factoryBean = new ProxyFactoryBean();
        factoryBean.setTarget(oneMoreSinger());
        factoryBean.addAdvisor(advisor());
        factoryBean.setInterfaces(SingerI.class);
        return (SingerI) factoryBean.getObject();
    }

    @Bean
    public Documentarist documentaristTwo(){
        return new Documentarist(proxyTwo());
    }

    @Bean
    public DefaultPointcutAdvisor advisor(){
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setAdvice(advice());
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* sing*(..))");
        advisor.setPointcut(pointcut);
        return advisor;
    }
}
