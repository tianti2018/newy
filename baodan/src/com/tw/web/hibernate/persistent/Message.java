package com.tw.web.hibernate.persistent;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

//@SuppressWarnings("serial")
//@Entity
//@Table(name="message")
public class Message extends AbstractSymBasePO {
	
	private int messageId;
	private Integer fromUserId;
	private Integer toUserId;
	private String title;
	private String contents;
	private String replyContents;
	private Date replyDate;

	private Date createDate;
	private User fromUser;
	private AdminUser toUser;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "messageId", unique = true, nullable = false)
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	@Column(name="fromUserId", insertable = false, updatable = false)
	public Integer getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}
	@Column(name="toUserId", insertable = false, updatable = false)
	public Integer getToUserId() {
		return toUserId;
	}
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne
	@JoinColumn(name="fromUserId")
	public User getFromUser() {
		return fromUser;
	}
	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}
	@ManyToOne
	@JoinColumn(name="toUserId")
	public AdminUser getToUser() {
		return toUser;
	}
	public void setToUser(AdminUser toUser) {
		this.toUser = toUser;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReplyContents() {
		return replyContents;
	}
	public void setReplyContents(String replyContents) {
		this.replyContents = replyContents;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	
}
