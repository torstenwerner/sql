package com.westernacher.tutorial.querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.sql.ColumnMetadata;

import javax.annotation.Generated;
import java.sql.Types;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QAttribute is a Querydsl query type for Attribute
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QAttribute extends com.querydsl.sql.RelationalPathBase<Attribute> {

    private static final long serialVersionUID = 769643347;

    public static final QAttribute attribute = new QAttribute("attribute");

    public final StringPath key = createString("key");

    public final StringPath userid = createString("userid");

    public final StringPath value = createString("value");

    public QAttribute(String variable) {
        super(Attribute.class, forVariable(variable), "public", "attribute");
        addMetadata();
    }

    public QAttribute(String variable, String schema, String table) {
        super(Attribute.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QAttribute(String variable, String schema) {
        super(Attribute.class, forVariable(variable), schema, "attribute");
        addMetadata();
    }

    public QAttribute(Path<? extends Attribute> path) {
        super(path.getType(), path.getMetadata(), "public", "attribute");
        addMetadata();
    }

    public QAttribute(PathMetadata metadata) {
        super(Attribute.class, metadata, "public", "attribute");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(key, ColumnMetadata.named("key").withIndex(2).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(userid, ColumnMetadata.named("userid").withIndex(1).ofType(Types.VARCHAR).withSize(2147483647).notNull());
        addMetadata(value, ColumnMetadata.named("value").withIndex(3).ofType(Types.VARCHAR).withSize(2147483647).notNull());
    }

}

