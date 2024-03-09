package me.cresterida;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.bson.codecs.pojo.annotations.BsonProperty;
import javax.persistence.PrePersist;
import java.time.LocalDate;

@MongoEntity(collection = "short_codes")
@RegisterForReflection
public class ShortCodeEntity extends PanacheMongoEntity
{
    public String url;
    @BsonProperty("created_at")
    public LocalDate createdAt;

    @BsonProperty("updated_at")
    public LocalDate updatedAt;

    @BsonProperty("short_code")
    public String shortCode;

    @Override
    public void persist()
    {
        System.out.println("Persisting new short code entity");
        this.createdAt = LocalDate.now();

        this.updatedAt = LocalDate.now();
        super.persist();
    }
}
