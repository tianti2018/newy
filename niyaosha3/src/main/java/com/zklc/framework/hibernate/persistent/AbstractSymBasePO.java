
/** ----------------------------------------------------------
 * FILE		    	: AbstractSymBasePO.java
 * CREATEUSER   : Anston
 * CREATEDATE   : 2009/5/30
 * FILENAME     : AbstractSymBasePO
 * DESCRIPTION  : 
 * MODIFIES	    : 
 * MODIFIER     : 
 * MODIFIEDDATE : 
 * COMMENT      : 
 * ----------------------------------------------------------
 */

package com.zklc.framework.hibernate.persistent;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@SuppressWarnings("unchecked")
public abstract class AbstractSymBasePO implements Serializable, Comparable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3617080192514589322L;
	
	private java.io.Serializable id;

	public AbstractSymBasePO() {
		super();
	}

	public java.io.Serializable getId() {
		return id;
	}

	public void setId(java.io.Serializable id) {
		this.id = id;
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).hashCode();
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof AbstractSymBasePO)) {
			return false;
		}
		
		AbstractSymBasePO o = (AbstractSymBasePO) obj;
		return new EqualsBuilder().appendSuper(this.getClass().equals(obj.getClass()))
			.append(this.getId(), o.getId())
			.isEquals();
	}

	public int compareTo(Object o) {
		return 0;
	}
}
