package com.example.lightnovelrecommendation;

import com.example.lightnovelrecommendation.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RedisConfig.class })
public class test {

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void testCache() {
        // Create a cache key and value
        String cacheKey = "myKey";
        String cacheValue = "myValue";

        // Store the value in the cache
        cacheManager.getCache("myCache").put(cacheKey, cacheValue);

        // Retrieve the value from the cache
        String retrievedValue = cacheManager.getCache("myCache").get(cacheKey, String.class);

        // Verify that the retrieved value is not null
        assertNotNull(retrievedValue);

        // Retrieve the value from the cache again
        String retrievedValueAgain = cacheManager.getCache("myCache").get(cacheKey, String.class);

        // Verify that the retrieved value is the same as before
        assertEquals(retrievedValue, retrievedValueAgain);
    }
}


