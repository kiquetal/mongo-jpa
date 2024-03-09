package me.cresterida;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

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
        shortCodeEntity.createdAt = java.time.LocalDate.now();
        shortCodeEntity.updatedAt = java.time.LocalDate.now();
        PanacheMongoRepository.super.persist(shortCodeEntity);
    }
}
