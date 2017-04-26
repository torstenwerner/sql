package com.westernacher.tutorial;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class QueryDslTest {

    private final static Logger logger = LoggerFactory.getLogger(QueryDslTest.class);

    @Autowired
    private SQLQueryFactory queryFactory;

    @Test
    public void userId() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final List<String> userIds = queryFactory.select(attribute.userid).from(attribute).fetch();
        logger.info("userIds: {}", userIds);
    }

    @Test
    public void tuple() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final List<Tuple> tuples = queryFactory.select(attribute.userid, attribute.key, attribute.value)
                .from(attribute).fetch();
        logger.info("tuples: {}", tuples);
    }

    @Test
    public void dto() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final List<Attribute> attributes = queryFactory.selectFrom(attribute)
                .from(attribute).fetch();
        logger.info("attributes: {}", attributes);
    }

    @Test
    public void exists() throws Exception {
        final QAttribute attribute = new QAttribute("a");
        final SQLQuery<Attribute> query = queryFactory.selectFrom(attribute)
                .from(attribute)
                .where(SQLExpressions.select(Expressions.ONE).where(attribute.userid.eq("hans")).exists());
        logger.info("query: {}", query.getSQL().getSQL());
        logger.info("exists: {}", query.fetch());
    }
}
