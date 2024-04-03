package me.cresterida;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@QuarkusTest
public class TestResources
{
    @Inject
    ShortCodeRepo shortCodeRepo;
    @Test
    void testListShortCodes()
    {

        Assert.assertTrue(shortCodeRepo.findAll().count()>0);
    }

    @Test
    void testAddNewDocument()
    {
        ShortCodeEntity shortCodeEntity = new ShortCodeEntity();
        shortCodeEntity.url = "https://www.google.com";
        shortCodeEntity.shortCode = "google";
        shortCodeRepo.persist(shortCodeEntity);
        ShortCodeEntity shortCodeEntity1 = shortCodeRepo.findByShortCode("google");
        Assert.assertNotNull(shortCodeEntity1);
    }

    @Test
    void testReadEntityAndCOnvertToLocalTime()
    {
        ShortCodeEntity shortCodeEntity = shortCodeRepo.findByShortCode("ibm");
        Assert.assertNotNull(shortCodeEntity);
        System.out.println(shortCodeEntity.createdAt);
        System.out.println(shortCodeEntity.updatedAt);
        System.out.println(shortCodeEntity.offset);
        System.out.println(shortCodeEntity.createdAt.atOffset(ZoneOffset.of(shortCodeEntity.offset)));
        System.out.println(shortCodeEntity.updatedAt.atOffset(ZoneOffset.of(shortCodeEntity.offset)));
    }

    @Test
    void testDates()
    {
        ShortCodeEntity shortCodeEntity = new ShortCodeEntity();
        shortCodeEntity.url = "https://www.ibm.com";
        shortCodeEntity.shortCode = "ibm";
        shortCodeEntity.offset= ZonedDateTime.now().getOffset().toString();
        shortCodeRepo.persist(shortCodeEntity);

    }

    @Test
    void testAddShortCodeWithCreator()
    {
        ShortCodeEntity shortCodeEntity = new ShortCodeEntity();
        shortCodeEntity.url = "https://www.nissei.com";
        shortCodeEntity.shortCode = "nissei";
        shortCodeEntity.creator = new CreatorEntity();
        shortCodeEntity.creator.name = "Cresterida";
        shortCodeEntity.creator.email = "kiquetal@gmail.com";
        shortCodeRepo.persist(shortCodeEntity);

        //test new shortcode with the same creator

        ShortCodeEntity shortCodeEntity1 = new ShortCodeEntity();
        shortCodeEntity1.url = "https://www.nissei.com";
        shortCodeEntity1.shortCode = "nissei1";
        shortCodeEntity1.creator = shortCodeEntity.creator;
        shortCodeRepo.persist(shortCodeEntity1);

    }

    @Test
    void testNewShortCodeWithExistingCreator()
    {
        ShortCodeEntity shortCodeEntity = new ShortCodeEntity();
        shortCodeEntity.url = "https://www.larva.com";
        shortCodeEntity.shortCode = "larva";
        shortCodeEntity.creator = new CreatorEntity();
        shortCodeEntity.creator.name = "Cresterida";
        shortCodeEntity.creator.email = "kiquetal@gmail.com";
        shortCodeRepo.persist(shortCodeEntity);
    }

    @Test
    void testFindAllShortCodeByCreator()
    {
        var list = shortCodeRepo.findByCreator("kiquetal@gmail.com");
       list.forEach(shortCodeEntity ->
               {
                   System.out.println(shortCodeEntity.shortCode);
                   System.out.println(shortCodeEntity.url);
               }
                );

    }

    @Test
    void testAddTwoShortCodesWithExpiration()

    {
        ShortCodeEntity shortCodeEntity = new ShortCodeEntity();
        shortCodeEntity.url = "https://www.nissei.com";
        shortCodeEntity.shortCode = "nissei";
        shortCodeEntity.expiresAt = ZonedDateTime.now().plus(20, ChronoUnit.SECONDS).toInstant();
        shortCodeEntity.creator = new CreatorEntity();
        shortCodeEntity.creator.name = "Cresterida";
        shortCodeEntity.creator.email = "kiquetal@gmail.com";
        shortCodeRepo.persist(shortCodeEntity);

        ShortCodeEntity shortCodeEntity1 = new ShortCodeEntity();
        shortCodeEntity1.url = "https://www.nissei.com";
        shortCodeEntity1.shortCode = "nissei1";
        shortCodeEntity1.expiresAt = ZonedDateTime.now().plus(20, ChronoUnit.SECONDS).toInstant();
        shortCodeEntity1.creator = new CreatorEntity();
        shortCodeEntity1.creator.name = "Cresterida";
        shortCodeEntity1.creator.email = "kiquetal@gmail.com";
        shortCodeRepo.persist(shortCodeEntity1);

    }

    @Test
    void testGetAllUserWithCreatedShortedUrls()
    {
        var list = shortCodeRepo.findAll().list();
        list.forEach(shortCodeEntity ->
                {
                    System.out.println(shortCodeEntity.creator.email);
                }
        );
    }
}
