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

import java.util.List;

/**
 * @author MarcF
 *
 */
public class RSSTestData {

  /**
   * The respective RSS feed id
   */
  private RSSFeedId rssFeedId;
  /**
   * The type of RSS feed
   */
  private RSSType rssType;
  /**
   * The account article sources for this RSS feed. These are sparsely loaded from test data where
   * only the required data has been set.
   */
  private List<AccountArticleSource> accountArticleSources;

  /**
   * Create a new instance of RSSTestData
   * 
   * @param rssFeedId The respective RSS feed id
   * @param rssType The type of RSS feed
   * @param accountArticleSources The account article sources for this RSS feed. These are
   *        sparsely loaded from test data where only the required data has been set.
   */
  public RSSTestData(RSSFeedId rssFeedId, RSSType rssType,
      List<AccountArticleSource> accountArticleSources) {
    this.rssFeedId = rssFeedId;
    this.rssType = rssType;
    this.accountArticleSources = accountArticleSources;
  }

  /**
   * @return The respective RSS feed id
   */
  public RSSFeedId getRssFeedId() {
    return rssFeedId;
  }

  /**
   * @return The type of RSS feed
   */
  public RSSType getRssType() {
    return rssType;
  }

  /**
   * @return The account article sources for this RSS feed. These are sparsely loaded from test
   *         data where only the required data has been set.
   */
  public List<AccountArticleSource> getAccountArticleSources() {
    return accountArticleSources;
  }

  @Override
  public String toString() {
    return "rssFeedId=" + rssFeedId + ", rssType=" + rssType + ", #accountArticleSources="
        + accountArticleSources.size();
  }
}
