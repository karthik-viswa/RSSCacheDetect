package sh.ebx.rsscachedetection;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RSSFeedCacheEstimator {

    private RSSTestData data;

    public RSSFeedCacheEstimator(RSSTestData data) {
        this.data = data;
    }

    public RSSFeedCacheEstimate getCacheEstimate() {

        RSSFeedCacheEstimate estimate = null;

        switch (data.getRssType())
        {
            case NORMAL: estimate = getCacheEstimateForNormalFeed();
                         break;
            case BAD_PUBLISH_TIME: estimate = getCacheEstimateForBadFeed();
        }

        return estimate;
    }

    private RSSFeedCacheEstimate getCacheEstimateForNormalFeed() {
        Long maximumLag = getMaximumLag().orElse(0L);

        return getCacheEstimateForLag(maximumLag);
    }

    private RSSFeedCacheEstimate getCacheEstimateForBadFeed() {
        Long averageLag = getAverageLag();

        return getCacheEstimateForLag(averageLag);
    }

    private RSSFeedCacheEstimate getCacheEstimateForLag(final Long lag)
    {
        Long lagInSeconds = TimeUnit.MILLISECONDS.toSeconds(lag);

        Long estimatedCachingInterval = getEstimatedCachingInterval(lagInSeconds);

        boolean cached = estimatedCachingInterval > EstimationConfig.NON_CACHING_TIME_SECS;

        return new RSSFeedCacheEstimate(data.getRssFeedId(), cached, estimatedCachingInterval);
    }

    private Optional<Long> getMaximumLag() {
        return data.getAccountArticleSources().stream()
                .map(AccountArticleSource::getArticleCreationLag)
                .max(Long::compare);
    }

    private Optional<Long> getMinimumLag() {
        return data.getAccountArticleSources().stream()
                        .map(AccountArticleSource::getArticleCreationLag)
                        .min(Long::compare);
    }

    private Long getAverageLag() {
        Long maximumLag = getMaximumLag().orElse(0L);

        if(maximumLag == 0L) {
            return maximumLag;
        }

        Long minimumLag = getMinimumLag().orElse(0L);

        return (minimumLag + maximumLag) / 2;
    }

    Long getEstimatedCachingInterval(Long estimateInSeconds) {
        return (estimateInSeconds + 30) / 60 * 60;
    }
}
