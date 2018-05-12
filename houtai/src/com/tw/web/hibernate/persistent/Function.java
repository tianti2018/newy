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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="function")
public class Function extends AbstractSymBasePO {
	
	private int functionId;
	private String functionName;
	private String url;
	private String module;
	
	private Set<RoleFunction> roleFunction = new HashSet<RoleFunction>(0);
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "functionId", unique = true, nullable = false)
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	
	@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY, mappedBy = "function")
	public Set<RoleFunction> getRoleFunction() {
		return roleFunction;
	}
	public void setRoleFunction(Set<RoleFunction> roleFunction) {
		this.roleFunction = roleFunction;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	
}
