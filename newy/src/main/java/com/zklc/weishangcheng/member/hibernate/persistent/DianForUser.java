package com.zklc.weishangcheng.member.hibernate.persistent;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="dian_user")
public class DianForUser implements Serializable{

	public Integer dfuid;
	
	public Integer userId;
	
	public Integer dian1;
	
	public Integer dian2;
	
	public Integer dian31;
	
	public Integer dian32;
	
	public Integer dian41;
	
	public Integer dian42;
	
	public Integer dian5;
	
	public Integer dian6;
	
	public Integer dian7;
	
	public Integer dian8;
	
	public Integer dian91;
	
	public Integer dian92;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "dfuid", unique = true, nullable = false)
	public Integer getDfuid() {
		return dfuid;
	}

	public void setDfuid(Integer dfuid) {
		this.dfuid = dfuid;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDian1() {
		return dian1;
	}

	public void setDian1(Integer dian1) {
		this.dian1 = dian1;
	}

	public Integer getDian2() {
		return dian2;
	}

	public void setDian2(Integer dian2) {
		this.dian2 = dian2;
	}


	public Integer getDian5() {
		return dian5;
	}

	public void setDian5(Integer dian5) {
		this.dian5 = dian5;
	}

	public Integer getDian6() {
		return dian6;
	}

	public void setDian6(Integer dian6) {
		this.dian6 = dian6;
	}

	public Integer getDian7() {
		return dian7;
	}

	public void setDian7(Integer dian7) {
		this.dian7 = dian7;
	}

	public Integer getDian8() {
		return dian8;
	}

	public void setDian8(Integer dian8) {
		this.dian8 = dian8;
	}

	public Integer getDian31() {
		return dian31;
	}

	public void setDian31(Integer dian31) {
		this.dian31 = dian31;
	}

	public Integer getDian32() {
		return dian32;
	}

	public void setDian32(Integer dian32) {
		this.dian32 = dian32;
	}

	public Integer getDian41() {
		return dian41;
	}

	public void setDian41(Integer dian41) {
		this.dian41 = dian41;
	}

	public Integer getDian42() {
		return dian42;
	}

	public void setDian42(Integer dian42) {
		this.dian42 = dian42;
	}

	public Integer getDian91() {
		return dian91;
	}

	public void setDian91(Integer dian91) {
		this.dian91 = dian91;
	}

	public Integer getDian92() {
		return dian92;
	}

	public void setDian92(Integer dian92) {
		this.dian92 = dian92;
	}

	
}
