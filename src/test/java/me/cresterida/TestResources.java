package me.cresterida;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;
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
}
