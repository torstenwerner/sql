package com.westernacher.tutorial.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;




/**
 * QCountry is a Querydsl query type for Country
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCountry extends com.querydsl.sql.RelationalPathBase<Country> {

    private static final long serialVersionUID = -1396686323;

    public static final QCountry country = new QCountry("country");

    public final NumberPath<Integer> capital = createNumber("capital", Integer.class);

    public final StringPath code = createString("code");

    public final StringPath code2 = createString("code2");

    public final StringPath continent = createString("continent");

    public final NumberPath<java.math.BigDecimal> gnp = createNumber("gnp", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> gnpold = createNumber("gnpold", java.math.BigDecimal.class);

    public final StringPath governmentform = createString("governmentform");

    public final StringPath headofstate = createString("headofstate");

    public final NumberPath<Short> indepyear = createNumber("indepyear", Short.class);

    public final NumberPath<Float> lifeexpectancy = createNumber("lifeexpectancy", Float.class);

    public final StringPath localname = createString("localname");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> population = createNumber("population", Integer.class);

    public final StringPath region = createString("region");

    public final NumberPath<Float> surfacearea = createNumber("surfacearea", Float.class);

    public final com.querydsl.sql.PrimaryKey<Country> countryPkey = createPrimaryKey(code);

    public final com.querydsl.sql.ForeignKey<City> countryCapitalFkey = createForeignKey(capital, "id");

    public final com.querydsl.sql.ForeignKey<Countrylanguage> _countrylanguageCountrycodeFkey = createInvForeignKey(code, "countrycode");

    public QCountry(String variable) {
        super(Country.class, forVariable(variable), "public", "country");
        addMetadata();
    }

    public QCountry(String variable, String schema, String table) {
        super(Country.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCountry(String variable, String schema) {
        super(Country.class, forVariable(variable), schema, "country");
        addMetadata();
    }

    public QCountry(Path<? extends Country> path) {
        super(path.getType(), path.getMetadata(), "public", "country");
        addMetadata();
    }

    public QCountry(PathMetadata metadata) {
        super(Country.class, metadata, "public", "country");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(capital, ColumnMetadata.named("capital").withIndex(14).ofType(Types.INTEGER).withSize(10));
        addMetadata(code, ColumnMetadata.named("code").withIndex(1).ofType(Types.CHAR).withSize(3).notNull());
        addMetadata(code2, ColumnMetadata.named("code2").withIndex(15).ofType(Types.CHAR).withSize(2).notNull());
        addMetadata(continent, ColumnMetadata.named("continent").withIndex(3).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(gnp, ColumnMetadata.named("gnp").withIndex(9).ofType(Types.NUMERIC).withSize(10).withDigits(2));
        addMetadata(gnpold, ColumnMetadata.named("gnpold").withIndex(10).ofType(Types.NUMERIC).withSize(10).withDigits(2));
        addMetadata(governmentform, ColumnMetadata.named("governmentform").withIndex(12).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(headofstate, ColumnMetadata.named("headofstate").withIndex(13).ofType(Types.VARCHAR).withSize(2147483647));
        addMetadata(indepyear, ColumnMetadata.named("indepyear").withIndex(6).ofType(Types.SMALLINT).withSize(5));
        addMetadata(lifeexpectancy, ColumnMetadata.named("lifeexpectancy").withIndex(8).ofType(Types.REAL).withSize(8).withDigits(8));
        addMetadata(localname, ColumnMetadata.named("localname").withIndex(11).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(population, ColumnMetadata.named("population").withIndex(7).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(region, ColumnMetadata.named("region").withIndex(4).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(surfacearea, ColumnMetadata.named("surfacearea").withIndex(5).ofType(Types.REAL).withSize(8).withDigits(8).notNull());
    }

}

