package com.dn.gyl.zsj.chxz;

import java.util.ArrayList;
import java.util.List;


public class MidInvabasDocModel {
	private MidInvabasDocHeadModel head = new MidInvabasDocHeadModel();
	private List<MidInvabasDocCompanyDetail> detail = new ArrayList<MidInvabasDocCompanyDetail>();
	public MidInvabasDocHeadModel getHead() {
		return head;
	}
	public void setHead(MidInvabasDocHeadModel head) {
		this.head = head;
	}
	public List<MidInvabasDocCompanyDetail> getDetail() {
		return detail;
	}
	public void setDetail(List<MidInvabasDocCompanyDetail> detail) {
		this.detail = detail;
	}
	
}
