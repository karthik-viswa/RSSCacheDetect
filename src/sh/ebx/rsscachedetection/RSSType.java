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

import java.security.InvalidParameterException;

/**
 * RSS feeds have many small differences. To categorise these differences we specify an RSSType.
 * 
 * @author MarcF
 */
public enum RSSType {
  /**
   * A normal RSS feed. We take all data directly from the feed. If there is invalid data the
   * article may not be detected.
   */
  NORMAL(1),
  /**
   * An RSS feed in which the publish time may be incorrect. For such articles we assume they are
   * published when we first see the article.
   */
  BAD_PUBLISH_TIME(2);

  private final int id;

  private RSSType(int id) {
    this.id = id;
  }

  /**
   * @return The id corresponding with the selected type
   */
  public int getId() {
    return id;
  }

  /**
   * Convert the provided id into a RSSType
   * 
   * @param id
   * @return if successful the desired RSSType
   */
  public static RSSType fromId(int id) {
    for (RSSType typе : RSSType.values()) {
      if (typе.getId() == id) {
        return typе;
      }
    }

    throw new InvalidParameterException("Unknown enum id :" + id);
  }
}
