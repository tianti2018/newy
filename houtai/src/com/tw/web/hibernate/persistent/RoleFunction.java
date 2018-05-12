package com.tw.web.hibernate.persistent;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="role_function")
public class RoleFunction extends AbstractSymBasePO {
	
	private int RFId;
	private int roleId;
	private int functionId;
	
	private Role role;
	private Function function;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getRFId() {
		return RFId;
	}
		
	public void setRFId(int id) {
		RFId = id;
	}
	
	
	@Column(name="roleId", insertable = false, updatable = false)
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Column(name="functionId", insertable = false, updatable = false)
	public int getFunctionId() {
		return functionId;
	}

	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="roleId")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE})
	@JoinColumn(name="functionId")	
	public Function getFunction() {
		return function;
	}
	public void setFunction(Function function) {
		this.function = function;
	}
		
}
