package com.dn.gyl.zsj.xxks;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSetDataSource;
import weaver.file.Prop;
public class NCMidCubasDocDataProcessor {
	private Log log = LogFactory.getLog(NCMidCubasDocDataProcessor.class.getName());
	/**新增存货信息*/
	public boolean midCubasDocNCData(MidCubasDocModel im) {
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		try{
		List<String> ncsqlList=new ArrayList<String>();
		String mainSql = "insert into MID_CUBASDOC(CUBASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,VMESSAGE,CREATETIME,CREATOR,"
				+ "CUSTCODE,CUSTNAME,CUSTPROP,CUSTSHORTNAME,DR,DRPNODEFLAG,FREECUSTFLAG,ISCONNFLAG,PK_AREACL,PK_CORP,TS,VNOTE,"
				+ "VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
				+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] mainParam = new Object[] {
				im.getHead().getCUBASDOCID(),im.getHead().getFTHANSFERSTATUS(),im.getHead().getSUCCESSFLAG(),im.getHead().getVMESSAGE(),
				im.getHead().getCREATETIME(),im.getHead().getCREATOR(),im.getHead().getCUSTCODE(),im.getHead().getCUSTNAME(),
				im.getHead().getCUSTPROP(),im.getHead().getCUSTSHORTNAME(),im.getHead().getDR(),im.getHead().getDRPNODEFLAG(),
				im.getHead().getFREECUSTFLAG(),im.getHead().getISCONNFLAG(),im.getHead().getPK_AREACL(),im.getHead().getPK_CORP(),im.getHead().getTS(),im.getHead().getVNOTE(),im.getHead().getVDEF1(),
				im.getHead().getVDEF2(),im.getHead().getVDEF3(),im.getHead().getVDEF4(),im.getHead().getVDEF5(),im.getHead().getVDEF6(),im.getHead().getVDEF7(),im.getHead().getVDEF8(),im.getHead().getVDEF9(),im.getHead().getVDEF10(),
				im.getHead().getVDEF11(),im.getHead().getVDEF12(),im.getHead().getVDEF13(),im.getHead().getVDEF14(),im.getHead().getVDEF15(),im.getHead().getVDEF16(),im.getHead().getVDEF17(),im.getHead().getVDEF18(),
				im.getHead().getVDEF19(),im.getHead().getVDEF20()	
		};
		String newMainSql = mainSql;
		for (Object object : mainParam) {
			newMainSql = newMainSql.substring(0, newMainSql.indexOf("?")) + "'"
					+ (object == null ? "" : object.toString()) + "'"
					+ newMainSql.substring(newMainSql.indexOf("?") + 1);
		}		
		log.info("存货主表mainSql---->>"+newMainSql);
		ncsqlList.add(newMainSql);
		String dtlSql1 = "insert into MID_CUBASDOC_COMPANY(CUBASDOCCOMPANYID,CUBASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,CREATETIME,CREATOR,"
				+ "CUSTCODE,CUSTNAME,CUSTPROP,CUSTSHORTNAME,DR,PK_AREACL,PK_CORP,"
				+ "TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
				+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<MidCubasDocCompanyDetail> imd = im.getGsdetail();
		for (MidCubasDocCompanyDetail idm : imd) {
			Object[] para = new Object[] {
					idm.getCUBASDOCCOMPANYID(),idm.getCUBASDOCID(),idm.getFTHANSFERSTATUS(),idm.getSUCCESSFLAG(),idm.getCREATETIME(),idm.getCREATOR(),
					idm.getCUSTCODE(),idm.getCUSTNAME(),idm.getCUSTPROP(),idm.getCUSTSHORTNAME(),idm.getDR(),idm.getPK_AREACL(),idm.getPK_CORP(),idm.getTS(),idm.getVNOTE(),
					idm.getVDEF1(),idm.getVDEF2(),idm.getVDEF3(),idm.getVDEF4(),idm.getVDEF5(),idm.getVDEF6(),idm.getVDEF7(),idm.getVDEF8(),idm.getVDEF9(),idm.getVDEF10(),
					idm.getVDEF11(),idm.getVDEF12(),idm.getVDEF13(),idm.getVDEF14(),idm.getVDEF15(),idm.getVDEF16(),idm.getVDEF17(),idm.getVDEF18(),
					idm.getVDEF19(),idm.getVDEF20()
			};
			String newDtlSql1 = dtlSql1;
			for (Object object:para) {
				newDtlSql1 = newDtlSql1.substring(0, newDtlSql1.indexOf("?")) + "'"
						+ (object == null ? "" : object.toString()) + "'"
						+ newDtlSql1.substring(newDtlSql1.indexOf("?") + 1);
			}
			log.info("存货明细表newDtlSql1---->>"+newDtlSql1);
			ncsqlList.add(newDtlSql1);
		   }
          /**新增银行信息*/
		  String dtlSql2 = "insert into MID_CUBASDOC_YH(CUBASDOCYHID,CUBASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,CREATETIME,CREATOR,"
				+ "CUSTCODE,CUSTNAME,CUSTPROP,CUSTSHORTNAME,DR,PK_CORP,"
				+ "TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
				+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			List<MidCubasDocYhDetail> imd2 = im.getYhdetail();
			for (MidCubasDocYhDetail idm : imd2) {
					Object[] para = new Object[] {
							idm.getCUBASDOCYHID(),idm.getCUBASDOCID(),idm.getFTHANSFERSTATUS(),idm.getSUCCESSFLAG(),idm.getCREATETIME(),idm.getCREATOR(),
							idm.getCUSTCODE(),idm.getCUSTNAME(),idm.getCUSTPROP(),idm.getCUSTSHORTNAME(),idm.getDR(),idm.getPK_CORP(),idm.getTS(),idm.getVNOTE(),
							idm.getVDEF1(),idm.getVDEF2(),idm.getVDEF3(),idm.getVDEF4(),idm.getVDEF5(),idm.getVDEF6(),idm.getVDEF7(),idm.getVDEF8(),idm.getVDEF9(),idm.getVDEF10(),
							idm.getVDEF11(),idm.getVDEF12(),idm.getVDEF13(),idm.getVDEF14(),idm.getVDEF15(),idm.getVDEF16(),idm.getVDEF17(),idm.getVDEF18(),
							idm.getVDEF19(),idm.getVDEF20()
					};
					String newDtlSql2 = dtlSql2;
					for (Object object:para) {
						newDtlSql2 = newDtlSql2.substring(0, newDtlSql2.indexOf("?")) + "'"
								+ (object == null ? "" : object.toString()) + "'"
								+ newDtlSql2.substring(newDtlSql2.indexOf("?") + 1);
					}
					log.info("存货明细表newDtlSql1---->>"+newDtlSql2);
					ncsqlList.add(newDtlSql2);
				}
		  /**添加标识*/
		  boolean addFlag=false;
		  for(String ncsql:ncsqlList){
			   addFlag=rsnc.execute(ncsql);
			   log.info("执行ncsql---->>"+ncsql+":返回标识:"+addFlag);
		  }
		}catch(Exception e){
			log.info("程序出现异常!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**新增公司信息*/
	public boolean midCubasDocCompanyNCData(List<MidCubasDocCompanyDetail> imList) {
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		try{
			List<String> ncsqlList=new ArrayList<String>();
			String mainSql = "insert into MID_CUBASDOC_COMPANY(CUBASDOCCOMPANYID,CUBASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,CREATETIME,CREATOR,"
					+ "CUSTCODE,CUSTNAME,CUSTPROP,CUSTSHORTNAME,DR,PK_AREACL,PK_CORP,"
					+ "TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
					+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20"
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   for (MidCubasDocCompanyDetail idm : imList) {			   
				Object[] para = new Object[] {
						idm.getCUBASDOCCOMPANYID(),idm.getCUBASDOCID(),idm.getFTHANSFERSTATUS(),idm.getSUCCESSFLAG(),idm.getCREATETIME(),idm.getCREATOR(),
						idm.getCUSTCODE(),idm.getCUSTNAME(),idm.getCUSTPROP(),idm.getCUSTSHORTNAME(),idm.getDR(),idm.getPK_AREACL(),idm.getPK_CORP(),idm.getTS(),idm.getVNOTE(),
						idm.getVDEF1(),idm.getVDEF2(),idm.getVDEF3(),idm.getVDEF4(),idm.getVDEF5(),idm.getVDEF6(),idm.getVDEF7(),idm.getVDEF8(),idm.getVDEF9(),idm.getVDEF10(),
						idm.getVDEF11(),idm.getVDEF12(),idm.getVDEF13(),idm.getVDEF14(),idm.getVDEF15(),idm.getVDEF16(),idm.getVDEF17(),idm.getVDEF18(),
						idm.getVDEF19(),idm.getVDEF20()
				};
		        String newMainSql = mainSql;
		        for (Object object : para) {
		        	newMainSql = newMainSql.substring(0, newMainSql.indexOf("?")) + "'"+ (object == null ? "" : object.toString()) + "'"+ newMainSql.substring(newMainSql.indexOf("?") + 1);
		        }
		        log.info("存货明细表newMainSql---->>"+newMainSql);
		        ncsqlList.add(newMainSql);
		   }
		      boolean addFlag=false;
			  for(String ncsql:ncsqlList){
				   addFlag=rsnc.execute(ncsql);
				   log.info("执行ncsql---->>"+ncsql+":返回标识:"+addFlag);
			  }
		}catch(Exception e){
			log.info("程序出现异常!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**新增银行信息*/
	public boolean midCubasDocYhNCData(List<MidCubasDocYhDetail> imList) {
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		try{
			List<String> ncsqlList=new ArrayList<String>();
			String mainSql = "insert into MID_CUBASDOC_YH(CUBASDOCYHID,CUBASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,CREATETIME,CREATOR,"
					+ "CUSTCODE,CUSTNAME,CUSTPROP,CUSTSHORTNAME,DR,PK_CORP,"
					+ "TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
					+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20"
					+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			   for (MidCubasDocYhDetail idm : imList) {			   
					Object[] para = new Object[] {
							idm.getCUBASDOCYHID(),idm.getCUBASDOCID(),idm.getFTHANSFERSTATUS(),idm.getSUCCESSFLAG(),idm.getCREATETIME(),idm.getCREATOR(),
							idm.getCUSTCODE(),idm.getCUSTNAME(),idm.getCUSTPROP(),idm.getCUSTSHORTNAME(),idm.getDR(),idm.getPK_CORP(),idm.getTS(),idm.getVNOTE(),
							idm.getVDEF1(),idm.getVDEF2(),idm.getVDEF3(),idm.getVDEF4(),idm.getVDEF5(),idm.getVDEF6(),idm.getVDEF7(),idm.getVDEF8(),idm.getVDEF9(),idm.getVDEF10(),
							idm.getVDEF11(),idm.getVDEF12(),idm.getVDEF13(),idm.getVDEF14(),idm.getVDEF15(),idm.getVDEF16(),idm.getVDEF17(),idm.getVDEF18(),
							idm.getVDEF19(),idm.getVDEF20()
					};
			        String newMainSql = mainSql;
			        for (Object object : para) {
			        	newMainSql = newMainSql.substring(0, newMainSql.indexOf("?")) + "'"+ (object == null ? "" : object.toString()) + "'"+ newMainSql.substring(newMainSql.indexOf("?") + 1);
			        }
			        log.info("存货明细表newMainSql---->>"+newMainSql);
			        ncsqlList.add(newMainSql);
			   }
			   boolean addFlag=false;
			   for(String ncsql:ncsqlList){
					   addFlag=rsnc.execute(ncsql);
					   log.info("执行ncsql---->>"+ncsql+":返回标识:"+addFlag);
			   }
		}catch(Exception e){
			log.info("程序出现异常!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
