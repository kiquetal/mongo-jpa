package me.cresterida;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.Instant;
import java.time.ZonedDateTime;

@MongoEntity(collection = "short_codes")
@RegisterForReflection
public class ShortCodeEntity extends PanacheMongoEntity
{
    public String url;
    @BsonProperty("created_at")
    public Instant createdAt;

    @BsonProperty("updated_at")
    public Instant updatedAt;

    @BsonProperty("short_code")
    public String shortCode;


    @BsonProperty("offset")
    public String offset;

    @BsonProperty("creator")
    public CreatorEntity creator;
}
