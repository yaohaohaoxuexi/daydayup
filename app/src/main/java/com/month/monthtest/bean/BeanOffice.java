package com.month.monthtest.bean;

public class BeanOffice {
	
	private String name;
	private String jobs;
	private String auth_type;
	private String good_at;
	private String headimg;
	private String hos_name;
	private String office_name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	public String getAuth_type() {
		return auth_type;
	}
	public void setAuth_type(String auth_type) {
		this.auth_type = auth_type;
	}
	public String getGood_at() {
		return good_at;
	}
	public void setGood_at(String good_at) {
		this.good_at = good_at;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getHos_name() {
		return hos_name;
	}
	public void setHos_name(String hos_name) {
		this.hos_name = hos_name;
	}
	public String getOffice_name() {
		return office_name;
	}
	public void setOffice_name(String office_name) {
		this.office_name = office_name;
	}
	public BeanOffice(String name, String jobs, String auth_type,
			String good_at, String headimg, String hos_name, String office_name) {
		super();
		this.name = name;
		this.jobs = jobs;
		this.auth_type = auth_type;
		this.good_at = good_at;
		this.headimg = headimg;
		this.hos_name = hos_name;
		this.office_name = office_name;
	}
	public BeanOffice() {
		super();
	}
	
	
	

}
