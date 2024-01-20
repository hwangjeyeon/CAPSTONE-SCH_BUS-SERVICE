package graduation.busstation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBusStation is a Querydsl query type for BusStation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBusStation extends EntityPathBase<BusStation> {

    private static final long serialVersionUID = -143782043L;

    public static final QBusStation busStation = new QBusStation("busStation");

    public final DateTimePath<java.time.LocalDateTime> arrivedDateTime = createDateTime("arrivedDateTime", java.time.LocalDateTime.class);

    public final StringPath busStationName = createString("busStationName");

    public final DateTimePath<java.time.LocalDateTime> departedDateTime = createDateTime("departedDateTime", java.time.LocalDateTime.class);

    public final StringPath deviceMacAddress = createString("deviceMacAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<graduation.busstation.enums.StationStatus> stationStatus = createEnum("stationStatus", graduation.busstation.enums.StationStatus.class);

    public QBusStation(String variable) {
        super(BusStation.class, forVariable(variable));
    }

    public QBusStation(Path<? extends BusStation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBusStation(PathMetadata metadata) {
        super(BusStation.class, metadata);
    }

}

