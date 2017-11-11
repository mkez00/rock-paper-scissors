package com.mkez00.configuration;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by michaelkezele on 2017-11-10.
 */
@Component
public class BeanDefinitionRegistryPostProcessorImpl implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private static final String ACTION_REPOSITORY = "actionRepository";

    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String implementation = environment.getProperty(ACTION_REPOSITORY);
        configurableListableBeanFactory.getBeanDefinition(implementation).setPrimary(true);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
