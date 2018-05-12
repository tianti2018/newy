package com.tw.web.hibernate.persistent;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="user_role")
public class UserRole extends AbstractSymBasePO {
	
	private int URId;
	
	private String userId;
	private String roleId;
	
	private AdminUser user;
	private Role role;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getURId() {
		return URId;
	}
	public void setURId(int id) {
		URId = id;
	}
	
	@Column(name="userId", insertable = false, updatable = false)
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="roleId", insertable = false, updatable = false)
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	@ManyToOne(cascade={},fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	public AdminUser getUser() {
		return user;
	}
	public void setUser(AdminUser user) {
		this.user = user;
	}
	
	@ManyToOne(cascade={},fetch = FetchType.LAZY)
	@JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
