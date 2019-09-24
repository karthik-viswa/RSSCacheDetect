package sh.ebx.rsscachedetection;

public class RSSFeedCacheEstimate {
    RSSFeedId feedId;

    boolean cached;

    long cacheInterval;

    public RSSFeedCacheEstimate(RSSFeedId feedId, boolean cached, long cacheInterval) {
        this.feedId = feedId;
        this.cached = cached;
        this.cacheInterval = cacheInterval;
    }

    public RSSFeedId getFeedId() {
        return feedId;
    }

    public boolean isCached() {
        return cached;
    }

    public long getCacheInterval() {
        return cacheInterval;
    }

    @Override
    public String toString() {
        return "{" +
                "feedId=" + feedId +
                ", cached=" + cached +
                ", cacheInterval=" + cacheInterval +
                '}';
    }
}
