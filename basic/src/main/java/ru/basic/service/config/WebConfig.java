package ru.basic.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(threadPoolTaskExecutor());
        configurer.setDefaultTimeout(60000); // Задержка по умолчанию в миллисекундах (опционально)
    }

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);           // Минимальное количество потоков
        executor.setMaxPoolSize(16);           // Максимальное количество потоков
        executor.setQueueCapacity(500);        // Емкость очереди для отложенных задач
        executor.setThreadNamePrefix("async-task-executor-");  // Префикс имени потока
        executor.initialize();                 // Инициализация пула потоков
        return executor;
    }
}
