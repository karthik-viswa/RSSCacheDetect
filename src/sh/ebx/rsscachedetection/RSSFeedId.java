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
 * A decorator for rss feed ids
 * @author MarcF
 */
@SuppressWarnings("serial")
public class RSSFeedId extends IdBase {

  /**
   * A static reference to a null instance of RSSFeedId. i.e. rss_feed_id = null
   */
  public static final RSSFeedId NULL = new RSSFeedId();

  /**
   * Private constructor for initialising the default value
   */
  private RSSFeedId() {
    super();
  }

  /**
   * Full constructor 
   * @param rssFeedId
   */
  public RSSFeedId(Long rssFeedId) {
    super(rssFeedId);
  }

  /**
   * Full constructor
   * @param rssFeedId
   */
  public RSSFeedId(RSSFeedId rssFeedId) {
    super(rssFeedId.getId());
  }
}
