package com.westernacher.tutorial;

import com.querydsl.sql.SQLQueryFactory;
import com.westernacher.tutorial.querydsl.QAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class QueryDslTest {
    @Autowired
    private SQLQueryFactory queryFactory;

    @Test
    public void attribute() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final List<String> userIds = queryFactory.select(attribute.userid).from(attribute).fetch();
        System.out.println(userIds);
    }
}
