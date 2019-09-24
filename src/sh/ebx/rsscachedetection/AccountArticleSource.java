/**
 * ***********************************************************************
 *
 * ECHOBOX CONFIDENTIAL
 *
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of Echobox Ltd. and its
 * suppliers, if any. The intellectual and technical concepts contained herein are proprietary to
 * Echobox Ltd. and its suppliers and may be covered by Patents, patents in process, and are
 * protected by trade secret or copyright law. Dissemination of this information or reproduction of
 * this material, in any format, is strictly forbidden unless prior written permission is obtained
 * from Echobox Ltd.
 */
package sh.ebx.rsscachedetection;

/**
 * An account article source represents the detection of a new article in an RSS/Atom feed. This is
 * a 'lite' object that only contains the fields required to complete this challenge.
 * @author MarcF
 */
public class AccountArticleSource {
  /**
   * A unique identifier for this account article source id
   */
  AccountArticleSourceId accountArticleSourceId;

  /**
   * The RSS/Atom feed associated with this account article source
   */
  RSSFeedId rssFeedId;

  /**
   * The time at which the article was published. If the source feed is of type 'BAD_PUBLISH_TIME'
   * this value may not reflect the true time at which this article was published.
   */
  long timePublished;

  /**
   * The time at which this article was first spotted in the respective feed.
   */
  long timeCreated;

  /**
   * Create a new instance of AccountArticleSource
   * 
   * @param accountArticleSourceId A unique identifier for this account article source id
   * @param rssFeedId The RSS/Atom feed associated with this account article source
   * @param timePublished The time at which the article was published. If the source feed is of type
   *        'BAD_PUBLISH_TIME' this value may not reflect the true time at which this article was
   *        published.
   * @param timeCreated The time at which this article was first spotted in the respective feed.
   */
  public AccountArticleSource(AccountArticleSourceId accountArticleSourceId, RSSFeedId rssFeedId,
      long timePublished, long timeCreated) {
    this.accountArticleSourceId = accountArticleSourceId;
    this.rssFeedId = rssFeedId;
    this.timePublished = timePublished;
    this.timeCreated = timeCreated;
  }

  /**
   * @return A unique identifier for this account article source id
   */
  public AccountArticleSourceId getAccountArticleSourceId() {
    return accountArticleSourceId;
  }

  /**
   * @return The RSS/Atom feed associated with this account article source
   */
  public RSSFeedId getRssFeedId() {
    return rssFeedId;
  }

  /**
   * @return The time at which the article was published. If the source feed is of type
   *         'BAD_PUBLISH_TIME' this value may not reflect the true time at which this article was
   *         published.
   */
  public long getTimePublished() {
    return timePublished;
  }

  /**
   * @return The time at which this article was first spotted in the respective feed.
   */
  public long getTimeCreated() {
    return timeCreated;
  }

  @Override
  public String toString() {
    return "{" +
            "accountArticleSourceId=" + accountArticleSourceId +
            ", timePublished=" + timePublished +
            ", timeCreated=" + timeCreated +
            '}';
  }

  public long getArticleCreationLag() {
    return timeCreated - timePublished;
  }
}
