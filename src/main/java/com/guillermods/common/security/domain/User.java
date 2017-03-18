package com.guillermods.common.security.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "user_name")
  private String username;

  @Column(name = "password")
  private String password;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_authorities", joinColumns = {@JoinColumn(name = "user_id")},
          inverseJoinColumns = {@JoinColumn(name = "authority_id")})
  private List<Authority> authorities;

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "fathers_name")
  private String fathersName;

  @Column(name = "mothers_name")
  private String mothersName;

  @Column(name = "date_birth")
  private Date dateBirth;

  @Column(name = "account_non_expired")
  private boolean accountNonExpired;

  @Column(name = "account_non_locked")
  private boolean accountNonLocked;

  @Column(name = "credentials_non_expired")
  private boolean credentialsNonExpired;

  @Column(name = "last_password_reset_date")
  private Date lastPasswordResetDate;

  @Column(name = "enabled")
  private boolean enabled;

  @Column(name = "created_at")
  private Date createdAt;

  @Column(name = "updated_at")
  private Date updatedAt;

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
   * @return the authorities
   */
  public List<Authority> getAuthorities() {
    return authorities;
  }

  /**
   * @param authorities the authorities to set
   */
  public void setAuthorities(List<Authority> authorities) {
    this.authorities = authorities;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the fathersName
   */
  public String getFathersName() {
    return fathersName;
  }

  /**
   * @param fathersName the fathersName to set
   */
  public void setFathersName(String fathersName) {
    this.fathersName = fathersName;
  }

  /**
   * @return the mothersName
   */
  public String getMothersName() {
    return mothersName;
  }

  /**
   * @param mothersName the mothersName to set
   */
  public void setMothersName(String mothersName) {
    this.mothersName = mothersName;
  }

  /**
   * @return the dateBirth
   */
  public Date getDateBirth() {
    return dateBirth;
  }

  /**
   * @param dateBirth the dateBirth to set
   */
  public void setDateBirth(Date dateBirth) {
    this.dateBirth = dateBirth;
  }

  /**
   * @return the accountNonExpired
   */
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  /**
   * @param accountNonExpired the accountNonExpired to set
   */
  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  /**
   * @return the accountNonLocked
   */
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  /**
   * @param accountNonLocked the accountNonLocked to set
   */
  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  /**
   * @return the credentialsNonExpired
   */
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  /**
   * @param credentialsNonExpired the credentialsNonExpired to set
   */
  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  /**
   * @return the enabled
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  /**
   * @return the lastPasswordResetDate
   */
  public Date getLastPasswordResetDate() {
    return lastPasswordResetDate;
  }

  /**
   * @param lastPasswordResetDate the lastPasswordResetDate to set
   */
  public void setLastPasswordResetDate(Date lastPasswordResetDate) {
    this.lastPasswordResetDate = lastPasswordResetDate;
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
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", authorities="
        + authorities + ", email=" + email + ", name=" + name + ", fathersName=" + fathersName
        + ", mothersName=" + mothersName + ", dateBirth=" + dateBirth + ", accountNonExpired="
        + accountNonExpired + ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired="
        + credentialsNonExpired + ", lastPasswordResetDate=" + lastPasswordResetDate + ", enabled="
        + enabled + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (accountNonExpired ? 1231 : 1237);
    result = prime * result + (accountNonLocked ? 1231 : 1237);
    result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + (credentialsNonExpired ? 1231 : 1237);
    result = prime * result + ((dateBirth == null) ? 0 : dateBirth.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (enabled ? 1231 : 1237);
    result = prime * result + ((fathersName == null) ? 0 : fathersName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result =
        prime * result + ((lastPasswordResetDate == null) ? 0 : lastPasswordResetDate.hashCode());
    result = prime * result + ((mothersName == null) ? 0 : mothersName.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    User other = (User) obj;
    if (accountNonExpired != other.accountNonExpired)
      return false;
    if (accountNonLocked != other.accountNonLocked)
      return false;
    if (authorities == null) {
      if (other.authorities != null)
        return false;
    } else if (!authorities.equals(other.authorities))
      return false;
    if (createdAt == null) {
      if (other.createdAt != null)
        return false;
    } else if (!createdAt.equals(other.createdAt))
      return false;
    if (credentialsNonExpired != other.credentialsNonExpired)
      return false;
    if (dateBirth == null) {
      if (other.dateBirth != null)
        return false;
    } else if (!dateBirth.equals(other.dateBirth))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (enabled != other.enabled)
      return false;
    if (fathersName == null) {
      if (other.fathersName != null)
        return false;
    } else if (!fathersName.equals(other.fathersName))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (lastPasswordResetDate == null) {
      if (other.lastPasswordResetDate != null)
        return false;
    } else if (!lastPasswordResetDate.equals(other.lastPasswordResetDate))
      return false;
    if (mothersName == null) {
      if (other.mothersName != null)
        return false;
    } else if (!mothersName.equals(other.mothersName))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (updatedAt == null) {
      if (other.updatedAt != null)
        return false;
    } else if (!updatedAt.equals(other.updatedAt))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

}
