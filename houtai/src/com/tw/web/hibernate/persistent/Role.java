package com.tw.web.hibernate.persistent;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@SuppressWarnings("serial")
@Entity
@Table(name="role") 
public class Role extends AbstractSymBasePO {
	
	private int roleId;
	private String roleName;
	private Set<RoleFunction> roleFunction = new HashSet<RoleFunction>(0);
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	
	private Set<AdminUser> adminUser = new HashSet<AdminUser>(0);
			
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "roleId", unique = true, nullable = false)
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="role")	
	@LazyCollection(LazyCollectionOption.FALSE) 
	@Fetch(FetchMode.SELECT) 
	@JoinColumn(name="roleId")
	@OrderBy("functionId ASC" )
	public Set<RoleFunction> getRoleFunction() {
		return roleFunction;
	}
	public void setRoleFunction(Set<RoleFunction> roleFunction) {
		this.roleFunction = roleFunction;
	}
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.EAGER, mappedBy="role")
	@LazyCollection(LazyCollectionOption.FALSE) 
	@Fetch(FetchMode.SELECT) 
	@JoinColumn(name="roleId")
	public Set<UserRole> getUserRole() {
		return userRole;
	}
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="role")	
	@LazyCollection(LazyCollectionOption.FALSE) 
	@Fetch(FetchMode.SELECT) 
	@JoinColumn(name="roleId")
	public Set<AdminUser> getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(Set<AdminUser> adminUser) {
		this.adminUser = adminUser;
	}
	
	
}
