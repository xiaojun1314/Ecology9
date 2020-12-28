package com.dn.gyl.zsj.xxks;
/**
 * 表名MID_CUBASDOC_YH           
 * @Filename: MidCubasDocYhDetail
 * @Description: 
 * @Version: 1.0
 * @History:<br>
 *<li>Author:jun</li>
 *<li>Date: 2018-11-28</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 */
public class MidCubasDocYhDetail {
	 private String CUBASDOCYHID ; 
	  /**中间表主键*/
	  private String CUBASDOCID ; 
	  /**default 0,--传输状态，是否传输*/
	  private String FTHANSFERSTATUS ; 
	  /**default 'N', --上传成功标记*/
	  private String  SUCCESSFLAG;
	  /**上传情况*/
	  private String  VMESSAGE;
	  /**创建时间  例：2011-09-20 15:29:36*/
	  private String  CREATETIME; 
	  /**创建人    例：0001A210000000000OY4*/
	  private String CREATOR;
	  /**客商编号  例：00563*/
	  private String CUSTCODE;
	  /**客商名称  例：大丰市曙光铸造机械厂*/
	  private String CUSTNAME;
	  /**客商类别 0 客户 1 客商 */
	  private String CUSTPROP; 
	  /**客商简称  例：大丰市曙光铸造机械厂*/
	  private String CUSTSHORTNAME;   
	  /**default 0, --删除标记 */
	  private String DR;
	  /**公司编码例：0001 统一4*/
	  private String PK_CORP ;  
	  /**CHAR(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')*/
	  private String TS;
	  /**备注*/
	  private String VNOTE;
	  /**备注*/
	  private String VDEF1;
	  /***/
	  private String VDEF2;
	  /**纳税人登记好*/
	  private String VDEF3;
	  /**自由项1*/
	  private String VDEF4;
	  /**联系人*/
	  private String VDEF5;
	  /**自由项1*/
	  private String VDEF6;
	  /**联系电话*/
	  private String VDEF7;
	  /**银行类别 */
	  private String VDEF8;   
	  /**开户行*/
	  private String VDEF9;
	  /**账号*/
	  private String VDEF10;
	  /***/
	  private String VDEF11;      
	  private String VDEF12;     
	  private String VDEF13;       
	  private String VDEF14;      
	  private String VDEF15;       
	  private String VDEF16;         
	  private String VDEF17;       
	  private String VDEF18;       
	  private String VDEF19;       
	  private String VDEF20;
	public String getCUBASDOCID() {
		return CUBASDOCID;
	}
	public void setCUBASDOCID(String cUBASDOCID) {
		CUBASDOCID = cUBASDOCID;
	}
	public String getFTHANSFERSTATUS() {
		return FTHANSFERSTATUS;
	}
	public void setFTHANSFERSTATUS(String fTHANSFERSTATUS) {
		FTHANSFERSTATUS = fTHANSFERSTATUS;
	}
	public String getSUCCESSFLAG() {
		return SUCCESSFLAG;
	}
	public void setSUCCESSFLAG(String sUCCESSFLAG) {
		SUCCESSFLAG = sUCCESSFLAG;
	}
	public String getVMESSAGE() {
		return VMESSAGE;
	}
	public void setVMESSAGE(String vMESSAGE) {
		VMESSAGE = vMESSAGE;
	}
	public String getCREATETIME() {
		return CREATETIME;
	}
	public void setCREATETIME(String cREATETIME) {
		CREATETIME = cREATETIME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCUSTCODE() {
		return CUSTCODE;
	}
	public void setCUSTCODE(String cUSTCODE) {
		CUSTCODE = cUSTCODE;
	}
	public String getCUSTNAME() {
		return CUSTNAME;
	}
	public void setCUSTNAME(String cUSTNAME) {
		CUSTNAME = cUSTNAME;
	}
	public String getCUSTPROP() {
		return CUSTPROP;
	}
	public void setCUSTPROP(String cUSTPROP) {
		CUSTPROP = cUSTPROP;
	}
	public String getCUSTSHORTNAME() {
		return CUSTSHORTNAME;
	}
	public void setCUSTSHORTNAME(String cUSTSHORTNAME) {
		CUSTSHORTNAME = cUSTSHORTNAME;
	}
	public String getDR() {
		return DR;
	}
	public void setDR(String dR) {
		DR = dR;
	}
	public String getPK_CORP() {
		return PK_CORP;
	}
	public void setPK_CORP(String pK_CORP) {
		PK_CORP = pK_CORP;
	}
	public String getTS() {
		return TS;
	}
	public void setTS(String tS) {
		TS = tS;
	}
	public String getVDEF1() {
		return VDEF1;
	}
	public void setVDEF1(String vDEF1) {
		VDEF1 = vDEF1;
	}
	public String getVDEF2() {
		return VDEF2;
	}
	public void setVDEF2(String vDEF2) {
		VDEF2 = vDEF2;
	}
	public String getVDEF3() {
		return VDEF3;
	}
	public void setVDEF3(String vDEF3) {
		VDEF3 = vDEF3;
	}
	public String getVDEF4() {
		return VDEF4;
	}
	public void setVDEF4(String vDEF4) {
		VDEF4 = vDEF4;
	}
	public String getVDEF5() {
		return VDEF5;
	}
	public void setVDEF5(String vDEF5) {
		VDEF5 = vDEF5;
	}
	public String getVDEF6() {
		return VDEF6;
	}
	public void setVDEF6(String vDEF6) {
		VDEF6 = vDEF6;
	}
	public String getVDEF7() {
		return VDEF7;
	}
	public void setVDEF7(String vDEF7) {
		VDEF7 = vDEF7;
	}
	public String getVDEF8() {
		return VDEF8;
	}
	public void setVDEF8(String vDEF8) {
		VDEF8 = vDEF8;
	}
	public String getVDEF9() {
		return VDEF9;
	}
	public void setVDEF9(String vDEF9) {
		VDEF9 = vDEF9;
	}
	public String getVDEF10() {
		return VDEF10;
	}
	public void setVDEF10(String vDEF10) {
		VDEF10 = vDEF10;
	}
	public String getVDEF11() {
		return VDEF11;
	}
	public void setVDEF11(String vDEF11) {
		VDEF11 = vDEF11;
	}
	public String getVDEF12() {
		return VDEF12;
	}
	public void setVDEF12(String vDEF12) {
		VDEF12 = vDEF12;
	}
	public String getVDEF13() {
		return VDEF13;
	}
	public void setVDEF13(String vDEF13) {
		VDEF13 = vDEF13;
	}
	public String getVDEF14() {
		return VDEF14;
	}
	public void setVDEF14(String vDEF14) {
		VDEF14 = vDEF14;
	}
	public String getVDEF15() {
		return VDEF15;
	}
	public void setVDEF15(String vDEF15) {
		VDEF15 = vDEF15;
	}
	public String getVDEF16() {
		return VDEF16;
	}
	public void setVDEF16(String vDEF16) {
		VDEF16 = vDEF16;
	}
	public String getVDEF17() {
		return VDEF17;
	}
	public void setVDEF17(String vDEF17) {
		VDEF17 = vDEF17;
	}
	public String getVDEF18() {
		return VDEF18;
	}
	public void setVDEF18(String vDEF18) {
		VDEF18 = vDEF18;
	}
	public String getVDEF19() {
		return VDEF19;
	}
	public void setVDEF19(String vDEF19) {
		VDEF19 = vDEF19;
	}
	public String getVDEF20() {
		return VDEF20;
	}
	public void setVDEF20(String vDEF20) {
		VDEF20 = vDEF20;
	}
	public String getCUBASDOCYHID() {
		return CUBASDOCYHID;
	}
	public void setCUBASDOCYHID(String cUBASDOCYHID) {
		CUBASDOCYHID = cUBASDOCYHID;
	}
	public String getVNOTE() {
		return VNOTE;
	}
	public void setVNOTE(String vNOTE) {
		VNOTE = vNOTE;
	}
	
}
