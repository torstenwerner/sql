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
 * QCity is a Querydsl query type for City
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCity extends com.querydsl.sql.RelationalPathBase<City> {

    private static final long serialVersionUID = 1833933396;

    public static final QCity city = new QCity("city");

    public final StringPath countrycode = createString("countrycode");

    public final StringPath district = createString("district");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> population = createNumber("population", Integer.class);

    public final com.querydsl.sql.PrimaryKey<City> cityPkey = createPrimaryKey(id);

    public final com.querydsl.sql.ForeignKey<Country> _countryCapitalFkey = createInvForeignKey(id, "capital");

    public QCity(String variable) {
        super(City.class, forVariable(variable), "public", "city");
        addMetadata();
    }

    public QCity(String variable, String schema, String table) {
        super(City.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCity(String variable, String schema) {
        super(City.class, forVariable(variable), schema, "city");
        addMetadata();
    }

    public QCity(Path<? extends City> path) {
        super(path.getType(), path.getMetadata(), "public", "city");
        addMetadata();
    }

    public QCity(PathMetadata metadata) {
        super(City.class, metadata, "public", "city");
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

