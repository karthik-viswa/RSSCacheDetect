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

import java.io.Serializable;

/**
 * The base class for all data id classes
 * 
 * @author Karnifexx
 */
@SuppressWarnings("serial")
public abstract class IdBase implements Comparable<IdBase>, Serializable {

  /**
   * The primary id value
   */
  private final Long id;

  /**
   * Initialise an IdBase class where id = null
   */
  protected IdBase() {
    this.id = null;
  }

  /**
   * Initialise an IdBase class using the provided Long value
   * 
   * @param id The desired value of id
   */
  protected IdBase(Long id) {
    this.id = id;
  }

  /**
   * @return The primary id value
   */
  public Long getId() {
    return id;
  }

  @Override
  public String toString() {

    if (id != null) {
      return id.toString();
    } else {
      return "NULL";
    }
  }

  @Override
  public int compareTo(IdBase o) {
    
    if (o == null || o.id == null) {
      if (this.id == null) {
        return 0;
      } else {
        return 1;
      }
    } else if (this.id == null) {
      return -1;
    } else {
      return id.compareTo(o.id);
    }
  }

  @Override
  public boolean equals(Object obj) {

    if (obj instanceof IdBase) {
      // Ensure the classes are the same
      if (!obj.getClass().equals(this.getClass())) {
        return false;
      } else {

        IdBase other = (IdBase) obj;
        if (this.getId() == null && other.getId() == null) {
          return true;
        }
        if (this.getId() == null || other.getId() == null) {
          return false;
        }
        return this.getId().equals(other.getId());
      }

    } else {
      return super.equals(obj);
    }
  }

  @Override
  public int hashCode() {
    if (id != null) {
      return id.intValue();
    } else {
      return super.hashCode();
    }
  }
}
