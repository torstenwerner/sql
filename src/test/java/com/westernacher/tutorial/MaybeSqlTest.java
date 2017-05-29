package com.westernacher.tutorial;

import com.querydsl.sql.SQLExpressions;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import com.westernacher.tutorial.querydsl.Attribute;
import com.westernacher.tutorial.querydsl.QAttribute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaybeSqlTest {
    private final static Logger logger = LoggerFactory.getLogger(QueryDslTest.class);

    @Autowired
    private SQLQueryFactory queryFactory;

    @Test
    public void query01() throws Exception {
        final QAttribute a1 = new QAttribute("a1");
        final SQLQuery<Attribute> query = queryFactory.selectFrom(a1)
                .from(a1)
                .where(a1.key.eq("l")
                        .and(a1.value.likeIgnoreCase("%Berlin%")));
        logger.info("query: {}", query.getSQL().getSQL());
        logger.info("attributes: {}", query.fetch());
    }

    @Test
    public void query02() throws Exception {
        final QAttribute a0 = new QAttribute("a0");
        final QAttribute a1 = new QAttribute("a1");
        final SQLQuery<Attribute> query = queryFactory.selectFrom(a0)
                .from(a0)
                .where(a0.userid.in(
                        SQLExpressions.select(a1.userid)
                                .from(a1)
                                .where(a1.key.eq("l")
                                        .and(a1.value.likeIgnoreCase("%Berlin%")))
                ));
        logger.info("query: {}", query.getSQL().getSQL());
        logger.info("attributes: {}", query.fetch());
    }
}
