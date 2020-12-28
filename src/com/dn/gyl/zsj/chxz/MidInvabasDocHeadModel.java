package com.dn.gyl.zsj.chxz;
/**
 * 表名MID_INVABASDOC           
 * @Filename: MidInvabasDocHeadModel
 * @Description: 
 * @Version: 1.0
 * @History:<br>
 *<li>Author:jun</li>
 *<li>Date: 2018-11-28</li>
 *<li>Version: 1.0</li>
 *<li>Content: create</li>
 */
public class MidInvabasDocHeadModel {
	 /**主键*/
	 private String INVABASDOCID;
	 /**传输状态，是否传输*/
	 private String FTHANSFERSTATUS ;
	 /**上传成功标记*/
	 private String SUCCESSFLAG;
	 /**上传情况*/
	 private String VMESSAGE;
	 /**设备卡片管理例：N*/
	 private String ASSET ;    
	 /**辅计量管理  例：Y*/
	 private String ASSISTUNIT;
	 /**自动平衡主辅计量      例：N*/
	 private String AUTOBALANCEMEAS;
	 /**创建时间  例：2011-09-02 15:00:25*/
	 private String CREATETIME;
	 /**创建人*/
	 private String CREATOR ;
	 /**价格折扣    例：N*/
	 private String DISCOUNTFLAG;
	 /**自由项1   可空        例：001*/
	 private String FREE1; 
	 /**自由项2   可空        例：002*/
	 private String FREE2;
	 /**自由项3   可空        例： 厚 */
	 private String FREE3; 
	 /**自由项4   可空*/
	 private String FREE4;
	 /**自由项5   可空*/
	 private String FREE5;  
	 /**存货编码    例：DN0101010102*/
	 private String INVCODE;
	 /**存货名称    例：Q235B钢板*/
	 private String INVNAME;  
	 /**规格      可空        例：11.75MM*/
	 private String INVSPEC ; 
	 /**按毛重管理库存        例：N CHAR(1) DEFAULT 'N' */
	 private String ISMNGSTOCKBYGRSWT;
	 /**是否零售    例：N CHAR(1) DEFAULT 'N'*/
	 private String ISRETAIL;  
	 /**换算率结存  例：N CHAR(1) DEFAULT 'N'*/
	 private String ISSTOREBYCONVERT;
	 /**应税劳务    例：N CHAR(1) DEFAULT 'N'*/
	 private String LABORFLAG; 
	 /**公司主键    例：0001*/
	 private String PK_CORP;       
	 /**存货分类主键例：DN01010101*/
	 private String PK_INVCL;          
	 /**主计量单位主键        例:J001*/
	 private String PK_MEASDOC;           
	 /**销售默认单位  可空        例:J001*/
	 private String PK_MEASDOC1;         
	 /**采购默认单位  可空        例:J001*/
	 private String PK_MEASDOC2;      
	 /**库存默认单位  可空        例:J001 */
	 private String PK_MEASDOC3;      
	 /**生产默认单位  可空        例:J001*/
	 private String PK_MEASDOC5;        
	 /**税目税率主键  例：9000     固定*/
	 private String PK_TAXITEMS;      
	 /**成套件        例：N CHAR(1) DEFAULT 'N*/
	 private String SETPARTSFLAG;
	 /**单位体积      例：0.00000000*/
	 private String UNITVOLUME;  
	 /**单位重量      例：0.00000000*/
	 private String UNITWEIGHT;
	 /***/
	 private String DR;
	 /***/
	 private String TS ;
	 /**备注*/
	 private String VNOTE;
	 /***/
	 private String VDEF1;
	 /***/
	 private String VDEF2;
	 /***/
	 private String VDEF3;
	 /***/
	 private String VDEF4;
	 /***/
	 private String VDEF5;
	 /***/
	 private String VDEF6;
	 /**最小起订量*/
	 private String VDEF7;   
	 /**N（供应链处理标识）*/
	 private String VDEF8;   
	 /***/
	 private String VDEF9;
	 /***/
	 private String VDEF10;
	 /***/
	 private String VDEF11;         
	 /***/
	 private String VDEF12;        
	 /***/
	 private String VDEF13;
	 /***/
	 private String VDEF14;         
	 /***/
	 private String VDEF15;
	 /***/
	 private String VDEF16;  
	 /***/
	 private String VDEF17;
	 /***/
	 private String VDEF18;
	 /***/
	 private String VDEF19;
	 /***/
	 private String VDEF20;
	public String getINVABASDOCID() {
		return INVABASDOCID;
	}
	public void setINVABASDOCID(String iNVABASDOCID) {
		INVABASDOCID = iNVABASDOCID;
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
	public String getASSET() {
		return ASSET;
	}
	public void setASSET(String aSSET) {
		ASSET = aSSET;
	}
	public String getASSISTUNIT() {
		return ASSISTUNIT;
	}
	public void setASSISTUNIT(String aSSISTUNIT) {
		ASSISTUNIT = aSSISTUNIT;
	}
	public String getAUTOBALANCEMEAS() {
		return AUTOBALANCEMEAS;
	}
	public void setAUTOBALANCEMEAS(String aUTOBALANCEMEAS) {
		AUTOBALANCEMEAS = aUTOBALANCEMEAS;
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
	public String getDISCOUNTFLAG() {
		return DISCOUNTFLAG;
	}
	public void setDISCOUNTFLAG(String dISCOUNTFLAG) {
		DISCOUNTFLAG = dISCOUNTFLAG;
	}
	public String getFREE1() {
		return FREE1;
	}
	public void setFREE1(String fREE1) {
		FREE1 = fREE1;
	}
	public String getFREE2() {
		return FREE2;
	}
	public void setFREE2(String fREE2) {
		FREE2 = fREE2;
	}
	public String getFREE3() {
		return FREE3;
	}
	public void setFREE3(String fREE3) {
		FREE3 = fREE3;
	}
	public String getFREE4() {
		return FREE4;
	}
	public void setFREE4(String fREE4) {
		FREE4 = fREE4;
	}
	public String getFREE5() {
		return FREE5;
	}
	public void setFREE5(String fREE5) {
		FREE5 = fREE5;
	}
	public String getINVCODE() {
		return INVCODE;
	}
	public void setINVCODE(String iNVCODE) {
		INVCODE = iNVCODE;
	}
	public String getINVNAME() {
		return INVNAME;
	}
	public void setINVNAME(String iNVNAME) {
		INVNAME = iNVNAME;
	}
	public String getINVSPEC() {
		return INVSPEC;
	}
	public void setINVSPEC(String iNVSPEC) {
		INVSPEC = iNVSPEC;
	}
	public String getISMNGSTOCKBYGRSWT() {
		return ISMNGSTOCKBYGRSWT;
	}
	public void setISMNGSTOCKBYGRSWT(String iSMNGSTOCKBYGRSWT) {
		ISMNGSTOCKBYGRSWT = iSMNGSTOCKBYGRSWT;
	}
	public String getISRETAIL() {
		return ISRETAIL;
	}
	public void setISRETAIL(String iSRETAIL) {
		ISRETAIL = iSRETAIL;
	}
	public String getISSTOREBYCONVERT() {
		return ISSTOREBYCONVERT;
	}
	public void setISSTOREBYCONVERT(String iSSTOREBYCONVERT) {
		ISSTOREBYCONVERT = iSSTOREBYCONVERT;
	}
	public String getLABORFLAG() {
		return LABORFLAG;
	}
	public void setLABORFLAG(String lABORFLAG) {
		LABORFLAG = lABORFLAG;
	}
	public String getPK_CORP() {
		return PK_CORP;
	}
	public void setPK_CORP(String pK_CORP) {
		PK_CORP = pK_CORP;
	}
	public String getPK_INVCL() {
		return PK_INVCL;
	}
	public void setPK_INVCL(String pK_INVCL) {
		PK_INVCL = pK_INVCL;
	}
	public String getPK_MEASDOC() {
		return PK_MEASDOC;
	}
	public void setPK_MEASDOC(String pK_MEASDOC) {
		PK_MEASDOC = pK_MEASDOC;
	}
	public String getPK_MEASDOC1() {
		return PK_MEASDOC1;
	}
	public void setPK_MEASDOC1(String pK_MEASDOC1) {
		PK_MEASDOC1 = pK_MEASDOC1;
	}
	public String getPK_MEASDOC2() {
		return PK_MEASDOC2;
	}
	public void setPK_MEASDOC2(String pK_MEASDOC2) {
		PK_MEASDOC2 = pK_MEASDOC2;
	}
	public String getPK_MEASDOC3() {
		return PK_MEASDOC3;
	}
	public void setPK_MEASDOC3(String pK_MEASDOC3) {
		PK_MEASDOC3 = pK_MEASDOC3;
	}
	public String getPK_MEASDOC5() {
		return PK_MEASDOC5;
	}
	public void setPK_MEASDOC5(String pK_MEASDOC5) {
		PK_MEASDOC5 = pK_MEASDOC5;
	}
	public String getPK_TAXITEMS() {
		return PK_TAXITEMS;
	}
	public void setPK_TAXITEMS(String pK_TAXITEMS) {
		PK_TAXITEMS = pK_TAXITEMS;
	}
	public String getSETPARTSFLAG() {
		return SETPARTSFLAG;
	}
	public void setSETPARTSFLAG(String sETPARTSFLAG) {
		SETPARTSFLAG = sETPARTSFLAG;
	}
	public String getUNITVOLUME() {
		return UNITVOLUME;
	}
	public void setUNITVOLUME(String uNITVOLUME) {
		UNITVOLUME = uNITVOLUME;
	}
	public String getUNITWEIGHT() {
		return UNITWEIGHT;
	}
	public void setUNITWEIGHT(String uNITWEIGHT) {
		UNITWEIGHT = uNITWEIGHT;
	}
	public String getDR() {
		return DR;
	}
	public void setDR(String dR) {
		DR = dR;
	}
	public String getTS() {
		return TS;
	}
	public void setTS(String tS) {
		TS = tS;
	}
	public String getVNOTE() {
		return VNOTE;
	}
	public void setVNOTE(String vNOTE) {
		VNOTE = vNOTE;
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
	 
}
