package me.cresterida;


import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "users")
public class CreatorEntity extends PanacheMongoEntity
{
    public String name;
    public String email;
}
