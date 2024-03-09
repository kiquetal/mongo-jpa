package me.cresterida;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/collections")
public class ExampleResource
{

    @Inject ShortCodeRepo shortCodeRepo;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShortCodeEntity> list()
    {
        return shortCodeRepo.listAll();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public ShortCodeEntity add(ShortCodeEntity shortCodeEntity)
    {
        shortCodeRepo.persist(shortCodeEntity);
        return shortCodeEntity;
    }
}
