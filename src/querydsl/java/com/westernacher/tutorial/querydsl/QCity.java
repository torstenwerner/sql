package com.westernacher.tutorial.querydsl;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCity is a Querydsl query type for QCity
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCity extends com.querydsl.sql.RelationalPathBase<QCity> {

    private static final long serialVersionUID = 1029147059;

    public static final QCity city = new QCity("city");

    public final StringPath countrycode = createString("countrycode");

    public final StringPath district = createString("district");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> population = createNumber("population", Integer.class);

    public final com.querydsl.sql.PrimaryKey<QCity> cityPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<QCountry> _countryCapitalFkey = createInvForeignKey(id, "capital");

    public QCity(String variable) {
        super(QCity.class, forVariable(variable), "public", "city");
        addMetadata();
    }

    public QCity(String variable, String schema, String table) {
        super(QCity.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCity(String variable, String schema) {
        super(QCity.class, forVariable(variable), schema, "city");
        addMetadata();
    }

    public QCity(Path<? extends QCity> path) {
        super(path.getType(), path.getMetadata(), "public", "city");
        addMetadata();
    }

    public QCity(PathMetadata metadata) {
        super(QCity.class, metadata, "public", "city");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(countrycode, ColumnMetadata.named("countrycode").withIndex(3).ofType(Types.CHAR).withSize(3).notNull());
        addMetadata(district, ColumnMetadata.named("district").withIndex(4).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(name, ColumnMetadata.named("name").withIndex(2).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(population, ColumnMetadata.named("population").withIndex(5).ofType(Types.INTEGER).withSize(10).notNull());
    }

}

