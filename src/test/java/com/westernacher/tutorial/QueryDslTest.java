package com.westernacher.tutorial;

import com.querydsl.core.Tuple;
import com.querydsl.sql.SQLQueryFactory;
import com.westernacher.tutorial.querydsl.QAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class QueryDslTest {
    @Autowired
    private SQLQueryFactory queryFactory;

    @Test
    public void userId() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final List<String> userIds = queryFactory.select(attribute.userid).from(attribute).fetch();
        System.out.println(userIds);
    }

    @Test
    public void tuple() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final List<Tuple> userIds = queryFactory.select(attribute.userid, attribute.key, attribute.value)
                .from(attribute).fetch();
        System.out.println(userIds);
    }
}
