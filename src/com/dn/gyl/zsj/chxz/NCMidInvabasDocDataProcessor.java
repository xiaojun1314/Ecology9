package com.dn.gyl.zsj.chxz;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSetDataSource;
import weaver.file.Prop;

public class NCMidInvabasDocDataProcessor {
	private Log log = LogFactory.getLog(NCMidInvabasDocDataProcessor.class.getName());
	/**新增存货信息*/
	public boolean midInvabasDocNCData(MidInvabasDocModel im) {
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		try{
		List<String> ncsqlList=new ArrayList<String>();
		String mainSql = "insert into MID_INVABASDOC(INVABASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,VMESSAGE,ASSET,ASSISTUNIT,AUTOBALANCEMEAS,"
				+ "CREATETIME,CREATOR,DISCOUNTFLAG,FREE1,FREE2,FREE3,FREE4,FREE5,INVCODE,INVNAME,INVSPEC,ISMNGSTOCKBYGRSWT,ISRETAIL,"
				+ "ISSTOREBYCONVERT,LABORFLAG,PK_CORP,PK_INVCL,PK_MEASDOC,PK_MEASDOC1,PK_MEASDOC2,PK_MEASDOC3,PK_MEASDOC5,PK_TAXITEMS,"
				+ "SETPARTSFLAG,UNITVOLUME,UNITWEIGHT,DR,TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
				+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] mainParam = new Object[] {
				im.getHead().getINVABASDOCID(),im.getHead().getFTHANSFERSTATUS(),im.getHead().getSUCCESSFLAG(),im.getHead().getVMESSAGE(),im.getHead().getASSET(),im.getHead().getASSISTUNIT(),im.getHead().getAUTOBALANCEMEAS(),
				im.getHead().getCREATETIME(),im.getHead().getCREATOR(),im.getHead().getDISCOUNTFLAG(),im.getHead().getFREE1(),im.getHead().getFREE2(),im.getHead().getFREE3(),im.getHead().getFREE4(),im.getHead().getFREE5(),
				im.getHead().getINVCODE(),im.getHead().getINVNAME(),im.getHead().getINVSPEC(),im.getHead().getISMNGSTOCKBYGRSWT(),im.getHead().getISRETAIL(),im.getHead().getISSTOREBYCONVERT(),im.getHead().getLABORFLAG(),
				im.getHead().getPK_CORP(),im.getHead().getPK_INVCL(),im.getHead().getPK_MEASDOC(),im.getHead().getPK_MEASDOC1(),im.getHead().getPK_MEASDOC2(),im.getHead().getPK_MEASDOC3(),im.getHead().getPK_MEASDOC5(),
				im.getHead().getPK_TAXITEMS(),im.getHead().getSETPARTSFLAG(),im.getHead().getUNITVOLUME(),im.getHead().getUNITWEIGHT(),im.getHead().getDR(),im.getHead().getTS(),im.getHead().getVNOTE(),im.getHead().getVDEF1(),
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
		String dtlSql = "insert into MID_INVABASDOC_COMPANY(INVABASDOCCOMPANYID,INVABASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,CREATETIME,CREATOR,FREE1,FREE2,FREE3,FREE4,FREE5,"
				+ "INVCODE,INVNAME,INVSPEC,PK_CORP,PK_INVCL,TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
				+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		List<MidInvabasDocCompanyDetail> imd = im.getDetail();
		for (MidInvabasDocCompanyDetail idm : imd) {
			Object[] para = new Object[] {
					idm.getINVABASDOCCOMPANYID(),idm.getINVABASDOCID(),idm.getFTHANSFERSTATUS(),idm.getSUCCESSFLAG(),idm.getCREATETIME(),idm.getCREATOR(),
					idm.getFREE1(),idm.getFREE2(),idm.getFREE3(),idm.getFREE4(),idm.getFREE5(),idm.getINVCODE(),idm.getINVNAME(),idm.getINVSPEC(),idm.getPK_CORP(),
					idm.getPK_INVCL(),idm.getTS(),idm.getVNOTE(),idm.getVDEF1(),
					idm.getVDEF2(),idm.getVDEF3(),idm.getVDEF4(),idm.getVDEF5(),idm.getVDEF6(),idm.getVDEF7(),idm.getVDEF8(),idm.getVDEF9(),idm.getVDEF10(),
					idm.getVDEF11(),idm.getVDEF12(),idm.getVDEF13(),idm.getVDEF14(),idm.getVDEF15(),idm.getVDEF16(),idm.getVDEF17(),idm.getVDEF18(),
					idm.getVDEF19(),idm.getVDEF20()
			};
			String newDtlSql = dtlSql;
			for (Object object:para) {
				newDtlSql = newDtlSql.substring(0, newDtlSql.indexOf("?")) + "'"
						+ (object == null ? "" : object.toString()) + "'"
						+ newDtlSql.substring(newDtlSql.indexOf("?") + 1);
			}
			log.info("存货明细表newDtlSql---->>"+newDtlSql);
			ncsqlList.add(newDtlSql);
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
	public boolean midInvabasDocCompanyNCData(List<MidInvabasDocCompanyDetail> imList) {
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		try{
			List<String> ncsqlList=new ArrayList<String>();
		   String mainSql = "insert into MID_INVABASDOC_COMPANY(INVABASDOCCOMPANYID,INVABASDOCID,FTHANSFERSTATUS,SUCCESSFLAG,CREATETIME,CREATOR,FREE1,FREE2,FREE3,FREE4,FREE5,"
				+ "INVCODE,INVNAME,INVSPEC,PK_CORP,PK_INVCL,TS,VNOTE,VDEF1,VDEF2,VDEF3,VDEF4,VDEF5,VDEF6,VDEF7,VDEF8,VDEF9,VDEF10,VDEF11,"
				+ "VDEF12,VDEF13,VDEF14,VDEF15,VDEF16,VDEF17,VDEF18,VDEF19,VDEF20"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		   for (MidInvabasDocCompanyDetail idm : imList) {			   
		        Object[] para = new Object[] {idm.getINVABASDOCCOMPANYID(),idm.getINVABASDOCID(),idm.getFTHANSFERSTATUS(),idm.getSUCCESSFLAG(),idm.getCREATETIME(),idm.getCREATOR(),
				idm.getFREE1(),idm.getFREE2(),idm.getFREE3(),idm.getFREE4(),idm.getFREE5(),idm.getINVCODE(),idm.getINVNAME(),idm.getINVSPEC(),idm.getPK_CORP(),
				idm.getPK_INVCL(),idm.getTS(),idm.getVNOTE(),idm.getVDEF1(),
				idm.getVDEF2(),idm.getVDEF3(),idm.getVDEF4(),idm.getVDEF5(),idm.getVDEF6(),idm.getVDEF7(),idm.getVDEF8(),idm.getVDEF9(),idm.getVDEF10(),
				idm.getVDEF11(),idm.getVDEF12(),idm.getVDEF13(),idm.getVDEF14(),idm.getVDEF15(),idm.getVDEF16(),idm.getVDEF17(),idm.getVDEF18(),idm.getVDEF19(),idm.getVDEF20()
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
