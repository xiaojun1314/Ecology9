package com.dn.gyl.zsj.xxks;

import java.util.ArrayList;
import java.util.List;


public class MidCubasDocModel {
	private MidCubasDocHeadModel head = new MidCubasDocHeadModel();
	private List<MidCubasDocCompanyDetail> gsdetail = new ArrayList<MidCubasDocCompanyDetail>();
	private List<MidCubasDocYhDetail> yhdetail = new ArrayList<MidCubasDocYhDetail>();
	public MidCubasDocHeadModel getHead() {
		return head;
	}
	public void setHead(MidCubasDocHeadModel head) {
		this.head = head;
	}
	public List<MidCubasDocCompanyDetail> getGsdetail() {
		return gsdetail;
	}
	public void setGsdetail(List<MidCubasDocCompanyDetail> gsdetail) {
		this.gsdetail = gsdetail;
	}
	public List<MidCubasDocYhDetail> getYhdetail() {
		return yhdetail;
	}
	public void setYhdetail(List<MidCubasDocYhDetail> yhdetail) {
		this.yhdetail = yhdetail;
	}
	
}
