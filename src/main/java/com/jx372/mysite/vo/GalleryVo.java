package com.jx372.mysite.vo;

public class GalleryVo {
	private int no;
	private String url;
	private String comment;
	private String regDate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "GalleryVo [id=" + no + ", url=" + url + ", comment=" + comment + "]";
	}
	
}
