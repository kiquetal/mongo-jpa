package me.cresterida;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

@ApplicationScoped
public class ShortCodeRepo implements PanacheMongoRepository<ShortCodeEntity>
{
    public ShortCodeEntity findByShortCode(String shortCode)
    {
        return find("shortCode", shortCode).firstResult();
    }

    @Override
    public void persist(ShortCodeEntity shortCodeEntity)
    {
        shortCodeEntity.createdAt = Instant.now();
        shortCodeEntity.updatedAt = Instant.now();
        PanacheMongoRepository.super.persist(shortCodeEntity);
    }
}
