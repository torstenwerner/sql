package com.westernacher.tutorial;

import com.querydsl.core.types.dsl.BooleanExpression;
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
import java.util.stream.Collectors;

import static java.lang.String.format;

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
                        .and(a1.value.likeIgnoreCase("%Berlin%")))
                .orderBy(a1.userid.asc());
        logger.info("query: {}", query.getSQL().getSQL());
        logAttributes(query.fetch());
    }

    @Test
    public void query02() throws Exception {
        final QAttribute a1 = new QAttribute("a1");
        final SQLQuery<String> subQuery = SQLExpressions.select(a1.userid)
                .from(a1)
                .where(a1.key.eq("l")
                        .and(a1.value.likeIgnoreCase("%Berlin%")));
        final QAttribute a0 = new QAttribute("a0");
        final SQLQuery<Attribute> query = queryFactory.selectFrom(a0)
                .from(a0)
                .where(a0.userid.in(subQuery))
                .orderBy(a0.userid.asc());
        logger.info("query: {}", query.getSQL().getSQL());
        logAttributes(query.fetch());
    }

    @Test
    public void query03() throws Exception {
        final QAttribute a1 = new QAttribute("a1");
        final QAttribute a2 = new QAttribute("a2");
        final SQLQuery<Integer> subSubQuery = SQLExpressions.select(Expressions.ONE)
                .from(a2)
                .where(a2.key.eq("sn")
                        .and(a2.value.likeIgnoreCase("%Muster%"))
                        .and(a2.userid.eq(a1.userid)));
        final SQLQuery<String> subQuery = SQLExpressions.select(a1.userid)
                .from(a1)
                .where(a1.key.eq("l")
                        .and(a1.value.likeIgnoreCase("%Berlin%"))
                        .and(subSubQuery.exists()));
        final QAttribute a0 = new QAttribute("a0");
        final SQLQuery<Attribute> query = queryFactory.selectFrom(a0)
                .from(a0)
                .where(a0.userid.in(subQuery))
                .orderBy(a0.userid.asc());
        logger.info("query: {}", query.getSQL().getSQL());
        logAttributes(query.fetch());
    }

    @Test
    public void query04() throws Exception {
        final QAttribute a1 = new QAttribute("a1");
        final QAttribute a3 = new QAttribute("a3");
        final SQLQuery<Integer> subSubSubQuery = SQLExpressions.select(Expressions.ONE)
                .from(a3)
                .where(a3.key.eq("givenName")
                        .and(a3.value.likeIgnoreCase("%Hans%"))
                        .and(a3.userid.eq(a1.userid)));
        final QAttribute a2 = new QAttribute("a2");
        final SQLQuery<Integer> subSubQuery = SQLExpressions.select(Expressions.ONE)
                .from(a2)
                .where(a2.key.eq("sn")
                        .and(a2.value.likeIgnoreCase("%Muster%"))
                        .and(a2.userid.eq(a1.userid)
                                .and(subSubSubQuery.exists())));
        final SQLQuery<String> subQuery = SQLExpressions.select(a1.userid)
                .from(a1)
                .where(a1.key.eq("l")
                        .and(a1.value.likeIgnoreCase("%Berlin%"))
                        .and(subSubQuery.exists()));
        final QAttribute a0 = new QAttribute("a0");
        final SQLQuery<Attribute> query = queryFactory.selectFrom(a0)
                .from(a0)
                .where(a0.userid.in(subQuery))
                .orderBy(a0.userid.asc());
        logger.info("query: {}", query.getSQL().getSQL());
        logAttributes(query.fetch());
    }

    @Test
    public void query05() throws Exception {
        final QAttribute a0 = new QAttribute("a0");
        final QAttribute a1 = new QAttribute("a1");
        final QAttribute a2 = new QAttribute("a2");
        final QAttribute a3 = new QAttribute("a3");
        final QAttribute s0 = new QAttribute("s0");
        final QAttribute s1 = new QAttribute("s1");

        final BooleanExpression filterA3 = a3.userid.eq(a1.userid)
                .and(a3.key.eq("givenName"))
                .and(a3.value.likeIgnoreCase("%a%"));
        final SQLQuery<Integer> subQueryA3 = SQLExpressions.select(Expressions.ONE)
                .from(a3)
                .where(filterA3);

        final BooleanExpression filterA2 = a2.userid.eq(a1.userid)
                .and(a2.key.eq("sn"))
                .and(a2.value.likeIgnoreCase("%Muster%"))
                .and(subQueryA3.exists());
        final SQLQuery<Integer> subQueryA2 = SQLExpressions.select(Expressions.ONE)
                .from(a2)
                .where(filterA2);

        final BooleanExpression filterA1 = s1.key.eq("givenName")
                .and(a1.key.eq("l"))
                .and(a1.value.likeIgnoreCase("%Berlin%"))
                .and(subQueryA2.exists());
        final SQLQuery<String> subQueryA1 = SQLExpressions.select(a1.userid)
                .from(a1)
                .join(s1).on(a1.userid.eq(s1.userid))
                .where(filterA1)
                .orderBy(s1.value.desc())
                .offset(0)
                .limit(2);

        final BooleanExpression filterA0 = s0.key.eq("givenName").
                and(a0.userid.in(subQueryA1));
        final SQLQuery<Attribute> query = queryFactory.selectFrom(a0)
                .join(s0).on(a0.userid.eq(s0.userid))
                .where(filterA0)
                .orderBy(s0.value.desc());

        logger.info("query: {}", query.getSQL().getSQL());
        logAttributes(query.fetch());
    }

    private void logAttributes(List<Attribute> attributes) {
        final String message = attributes.stream()
                .map(attribute -> format("[%s - %s: %s]", attribute.getUserid(), attribute.getKey(), attribute.getValue()))
                .collect(Collectors.joining("\n"));
        logger.info("{} attributes:\n{}", attributes.size(), message);
    }
}
