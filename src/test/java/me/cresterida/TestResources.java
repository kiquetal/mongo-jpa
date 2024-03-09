package me.cresterida;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

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
}
