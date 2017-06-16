package com.jx372.mysite.vo;

public class SiteVo {
	private int no;
	private String title;
	private String welcomeMsg;
	private String profileImg;
	private String description;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcomeMsg() {
		return welcomeMsg;
	}
	public void setWelcomeMsg(String welcomeMsg) {
		this.welcomeMsg = welcomeMsg;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "SiteVo [no=" + no + ", title=" + title + ", welcomeMsg=" + welcomeMsg + ", profileImg=" + profileImg
				+ ", description=" + description + "]";
	}
	
	

}
