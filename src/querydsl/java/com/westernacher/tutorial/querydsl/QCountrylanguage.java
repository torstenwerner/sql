package com.westernacher.tutorial.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCountrylanguage is a Querydsl query type for QCountrylanguage
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCountrylanguage extends com.querydsl.sql.RelationalPathBase<QCountrylanguage> {

    private static final long serialVersionUID = 580093862;

    public static final QCountrylanguage countrylanguage = new QCountrylanguage("countrylanguage");

    public final StringPath countrycode = createString("countrycode");

    public final BooleanPath isofficial = createBoolean("isofficial");

    public final StringPath language = createString("language");

    public final NumberPath<Float> percentage = createNumber("percentage", Float.class);

    public final com.querydsl.sql.PrimaryKey<QCountrylanguage> countrylanguagePkey = createPrimaryKey(countrycode, language);

    public final com.querydsl.sql.ForeignKey<QCountry> countrylanguageCountrycodeFkey = createForeignKey(countrycode, "code");

    public QCountrylanguage(String variable) {
        super(QCountrylanguage.class, forVariable(variable), "public", "countrylanguage");
        addMetadata();
    }

    public QCountrylanguage(String variable, String schema, String table) {
        super(QCountrylanguage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCountrylanguage(String variable, String schema) {
        super(QCountrylanguage.class, forVariable(variable), schema, "countrylanguage");
        addMetadata();
    }

    public QCountrylanguage(Path<? extends QCountrylanguage> path) {
        super(path.getType(), path.getMetadata(), "public", "countrylanguage");
        addMetadata();
    }

    public QCountrylanguage(PathMetadata metadata) {
        super(QCountrylanguage.class, metadata, "public", "countrylanguage");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(countrycode, ColumnMetadata.named("countrycode").withIndex(1).ofType(Types.CHAR).withSize(3).notNull());
        addMetadata(isofficial, ColumnMetadata.named("isofficial").withIndex(3).ofType(Types.BIT).withSize(1).notNull());
        addMetadata(language, ColumnMetadata.named("language").withIndex(2).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(percentage, ColumnMetadata.named("percentage").withIndex(4).ofType(Types.REAL).withSize(8).withDigits(8).notNull());
    }

}

