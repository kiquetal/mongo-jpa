package me.cresterida;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreatorRepo implements PanacheMongoRepository<CreatorEntity>
{
    public CreatorEntity findByEmail(String email)
    {
        return find("email", email).firstResult();
    }
}
