package com.westernacher.tutorial;

import com.querydsl.sql.PostgreSQLTemplates;
import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import com.querydsl.sql.spring.SpringExceptionTranslator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class QueryDslConfiguration {

    @Bean
    public SQLQueryFactory queryFactory(DataSource dataSource) {
//        Provider<Connection> provider = new SpringConnectionProvider(dataSource);
//        return new SQLQueryFactory(querydslConfiguration(), provider);
        return new SQLQueryFactory(querydslConfiguration(), dataSource);
    }

    private com.querydsl.sql.Configuration querydslConfiguration() {
        SQLTemplates templates = PostgreSQLTemplates.builder().build();
        com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(templates);
        configuration.setExceptionTranslator(new SpringExceptionTranslator());
        return configuration;
    }
}
