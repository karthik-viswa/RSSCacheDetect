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
 * A decorator for account article source ids
 * 
 * @author MarcF
 */
@SuppressWarnings("serial")
public class AccountArticleSourceId extends IdBase {

  /**
   * A static reference to a null instance of AccountArticleSourceId. i.e. account_article_source_id
   * = null
   */
  public static final AccountArticleSourceId NULL = new AccountArticleSourceId();

  /**
   * Private constructor for initialising the default value
   */
  private AccountArticleSourceId() {
    super();
  }

  /**
   * Full constructor
   * @param accountArticleSourceId
   */
  public AccountArticleSourceId(Long accountArticleSourceId) {
    super(accountArticleSourceId);
  }

  /**
   * Full constructor
   * @param accountArticleSourceId
   */
  public AccountArticleSourceId(AccountArticleSourceId accountArticleSourceId) {
    super(accountArticleSourceId.getId());
  }
}
