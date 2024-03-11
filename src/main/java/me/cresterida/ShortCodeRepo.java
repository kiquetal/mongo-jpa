package me.cresterida;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class ShortCodeRepo implements PanacheMongoRepository<ShortCodeEntity>
{
    @Inject CreatorRepo creatorRepo;
    public ShortCodeEntity findByShortCode(String shortCode)
    {
        return find("shortCode", shortCode).firstResult();
    }

    @Override
    public void persist(ShortCodeEntity shortCodeEntity)
    {
        if (shortCodeEntity.createdAt == null)
        shortCodeEntity.createdAt = Instant.now();
        if (shortCodeEntity.updatedAt == null)
        shortCodeEntity.updatedAt = Instant.now();

        shortCodeEntity.offset= ZonedDateTime.now(ZoneId.of("America/Asuncion")).getOffset().getId();
        //find creator is already exists,if not create a new one
        CreatorEntity creatorEntity = creatorRepo.findByEmail(shortCodeEntity.creator.email);
        if(creatorEntity == null)
        {
            creatorRepo.persist(shortCodeEntity.creator);
        }
        else
        {
            shortCodeEntity.creator = creatorEntity;
        }


        PanacheMongoRepository.super.persist(shortCodeEntity);
    }

    public List<ShortCodeEntity> findByCreator(String email)
    {
        return find("creator.email", email).list();
    }
}
