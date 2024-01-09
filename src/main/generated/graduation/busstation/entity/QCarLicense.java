package graduation.busstation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarLicense is a Querydsl query type for CarLicense
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCarLicense extends EntityPathBase<CarLicense> {

    private static final long serialVersionUID = 1770138270L;

    public static final QCarLicense carLicense = new QCarLicense("carLicense");

    public final StringPath License = createString("License");

    public QCarLicense(String variable) {
        super(CarLicense.class, forVariable(variable));
    }

    public QCarLicense(Path<? extends CarLicense> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarLicense(PathMetadata metadata) {
        super(CarLicense.class, metadata);
    }

}

