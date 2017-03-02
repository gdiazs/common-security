package com.guillermods.common.security.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "authority")
  private String authority;

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;



  /**
   * 
   */
  public Authority() {
    super();
  }

  /**
   * @param id
   * @param authority
   * @param enabled
   * @param createdAt
   * @param updatedAt
   */
  public Authority(Long id, String authority, Boolean enabled, Date createdAt, Date updatedAt) {
    super();
    this.id = id;
    this.authority = authority;
    this.enabled = enabled;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the authority
   */
  public String getAuthority() {
    return authority;
  }

  /**
   * @param authority the authority to set
   */
  public void setAuthority(String authority) {
    this.authority = authority;
  }

  /**
   * @return the enabled
   */
  public Boolean isEnabled() {
    return enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @return the createdAt
   */
  public Date getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return the updatedAt
   */
  public Date getUpdatedAt() {
    return updatedAt;
  }

  /**
   * @param updatedAt the updatedAt to set
   */
  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "Authority [id=" + id + ", authority=" + authority + ", enabled=" + enabled
        + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((authority == null) ? 0 : authority.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Authority other = (Authority) obj;
    if (authority == null) {
      if (other.authority != null)
        return false;
    } else if (!authority.equals(other.authority))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (enabled == null) {
      if (other.enabled != null)
        return false;
    } else if (!enabled.equals(other.enabled))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    return true;
  }



}
