package sh.ebx.rsscachedetection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RSSFeedCacheEstimatorTest
{
    @Test
    public void testEstimateInNearestMinute() {
        Long estimateInSeconds = 1234L;
        assertEquals(new Long(1260), new RSSFeedCacheEstimator(null).getEstimatedCachingInterval(estimateInSeconds));

        estimateInSeconds = 100L;
        assertEquals(new Long(120), new RSSFeedCacheEstimator(null).getEstimatedCachingInterval(estimateInSeconds));

        estimateInSeconds = 30L;
        assertEquals(new Long(60), new RSSFeedCacheEstimator(null).getEstimatedCachingInterval(estimateInSeconds));

        estimateInSeconds = 1L;
        assertEquals(new Long(0), new RSSFeedCacheEstimator(null).getEstimatedCachingInterval(estimateInSeconds));
    }

}
