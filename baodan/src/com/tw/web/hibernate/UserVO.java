package com.tw.web.hibernate;

import com.tw.web.hibernate.persistent.User;
import com.tw.web.hibernate.persistent.Usery;

public class UserVO {
	
	private Usery usery;
	private User user;
	public Usery getUsery() {
		return usery;
	}
	public void setUsery(Usery usery) {
		this.usery = usery;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
