package com.kkwriter.gallery.entity.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kkwriter.gallery.entity.BaseEntity;

@Entity
public class SysUser extends BaseEntity implements UserDetails {
	private static final long serialVersionUID = 7954747732590588192L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String username;
	private String password;
	private boolean isValid;
	private boolean isLocked;
	private boolean isCredentialsValid;
	private boolean isEnabled;
	@ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<SysRole> roles;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<SysRole> getRoles() {
		return roles;
	}

	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public boolean isCredentialsValid() {
		return isCredentialsValid;
	}

	public void setCredentialsValid(boolean isCredentialsValid) {
		this.isCredentialsValid = isCredentialsValid;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		List<SysRole> roles = this.getRoles();
		for (SysRole role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getName()));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.isValid;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !this.isLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.isCredentialsValid;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnabled;
	}
	
}
