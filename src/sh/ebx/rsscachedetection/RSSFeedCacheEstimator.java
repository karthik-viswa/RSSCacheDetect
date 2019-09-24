package sh.ebx.rsscachedetection;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RSSFeedCacheEstimator {

    RSSTestData data;

    public RSSFeedCacheEstimator(RSSTestData data) {
        this.data = data;
    }

    public RSSFeedCacheEstimate getCacheEstimate() {

        Long maximumLag = getMaximumLag().orElse(0L);

        Long maximumLagInSeconds = TimeUnit.MILLISECONDS.toSeconds(maximumLag);

        Long estimatedCachingInterval = getEstimatedCachingInterval(maximumLagInSeconds);

        boolean cached = estimatedCachingInterval > 0L;

        return new RSSFeedCacheEstimate(data.getRssFeedId(), cached, estimatedCachingInterval);
    }

    Optional<Long> getMaximumLag() {
        return data.getAccountArticleSources().stream()
                .map(source -> source.getArticleCreationLag())
                .max(Long::compare);
        //.max(Comparator.comparingLong(AccountArticleSource::getArticleCreationLag)).get();
    }

    private Long getEstimatedCachingInterval(Long maximumLagInSeconds) {
        Long lagDifference = maximumLagInSeconds - EstimationConfig.NON_CACHING_TIME_SECS;

        if(lagDifference > 0L) {
            return (lagDifference + 5) / 10 * 10;
        }

        return 0L;
    }
}
