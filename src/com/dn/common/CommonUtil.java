package com.dn.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.file.Prop;
import weaver.general.Util;

/**
 * @className: CommonUtil
 * @author:
 * @date: 2020-12-21 09:56
 * @Depiction: 工具类
 */
public class CommonUtil {
	/**获取发起流程的 table名称*/
	public static String getWorkFlowTableName(String workflowid) {
		   RecordSet rs = new RecordSet();
		   String tableName = "";
		   if (null == workflowid || "".equals(workflowid)) {
			   return tableName;
		   }
		   rs.execute("select tablename  from workflow_bill where id = (select formid from workflow_base where id= '" + workflowid + "' and isbill=1)");
		   if (rs.next()) {
			   tableName = Util.null2String(rs.getString("tablename"));
		   }
		   return tableName;
	}
	/**获取发起流程的名称*/
	public static String getWorkFlowName(String workflowid) {
		   RecordSet rs = new RecordSet();
		   String workflowName = "";
		   if (null == workflowid || "".equals(workflowid)) {
			   return workflowName;
		   }
		   rs.execute("select workflowname from workflow_base where id = " + workflowid);
		   if (rs.next()) {
			   workflowName = Util.null2String(rs.getString("workflowname"));
	       }
		   return workflowName;
    }
	/**获取人员名称*/
	public static String getHRNameBYid(String id){
		   String lastname = "";
		   RecordSet rs = new RecordSet();
		   rs.execute("select lastname from hrmresource where id='" + id + "'");
		   if (rs.next()) {
			    lastname = rs.getString("lastname");
	       }
		   return lastname;
	}
	/**获取公司名称*/
    public static String getSubCompNameByid(String id){
	       String subcompanyname = "";
	       RecordSet rs = new RecordSet();
	       rs.execute("select subcompanyname from hrmsubcompany  where id='" + id + "'");
	       if (rs.next()) {
	            subcompanyname = rs.getString("subcompanyname");
	       }
	       return subcompanyname;
	}
	/**获取部门名称*/
    public static String getDeptNameByid(String id){
	       String deptname = "";
	       RecordSet rs = new RecordSet();
	       rs.execute("select departmentname from hrmdepartment  where id='" + id + "'");
	       if (rs.next()) {
	            deptname = rs.getString("departmentname");
	       }
	       return deptname;
	}
    /**获取人员编码*/
	public static String getHRidBYCode(String id){
		   String workcode = "";
		   RecordSet rs = new RecordSet();
		   rs.execute("select workcode from hrmresource  where id='" + id + "'");
		   if (rs.next()) {
		    	 workcode = rs.getString("workcode");
		   }
		   return workcode;
	  }
    /**获取人员loginId*/
	public static String getHRLoginidBYId(String id){
		   String loginid = "";
		   RecordSet rs = new RecordSet();
		   rs.execute("select loginid from hrmresource  where id='" + id + "'");
		   if (rs.next()) {
			   loginid = rs.getString("loginid");
		   }
		   return loginid;
	  }
	/**获取部门编码*/
	public static int getDeptidBYcode(String code){
	       int id = 0;
	       RecordSet rs = new RecordSet();
	       rs.execute("select id from hrmdepartment where departmentcode='" + code + "'");
	       if (rs.next()) {
	             id = rs.getInt("id");
	       }
	       return id;
	}
	/**根据部门获取公司id*/
	public static String getDeptidBYSubCompId(String id){
	       RecordSet rs = new RecordSet();
	       String subcompId = "";
	       rs.execute("select subcompanyid1 from hrmdepartment  where id='" + id + "'");
	       if (rs.next()) {
	    	   subcompId = rs.getString("subcompanyid1");
	       }
	       return subcompId;
	}
	/**根据员工ID获取公司id*/
	public static String getHRidBYSubCompId(String id){
	       RecordSet rs = new RecordSet();
	       String subcompId = "";
	       rs.execute("select subcompanyid1 from hrmresource  where id='" + id + "'");
	       if (rs.next()) {
	    	   subcompId = rs.getString("subcompanyid1");
	       }
	       return subcompId;
	}
	/**根据员工ID获取部门id*/
	public static String getHRidBYDepartmentId(String id){
	       RecordSet rs = new RecordSet();
	       String departmentid = "";
	       rs.execute("select departmentid from hrmresource  where id='" + id + "'");
	       if (rs.next()) {
	    	   departmentid = rs.getString("departmentid");
	       }
	       return departmentid;
	}
	/**获取员工备用字段属性*/
	public static String getHrDepartDefinedById(String id){
	       RecordSet rs = new RecordSet();
	       String attribute = "";
	       rs.execute("select attribute from hrmdepartmentdefined where  deptid='"+id+"'");
	       if (rs.next()) {
	    	   attribute = rs.getString("attribute");
	       }
	       return attribute;
	}
	/**是否工地直发*/
	public static String getWmsSfgdzf(String sfzjfwgd){
		   if(sfzjfwgd.equals("0")){
			     return "402880e85c8bb502015c908c73620bb9";
		   }else if(sfzjfwgd.equals("1")){
		        return "402880e85c8bb502015c908c73620bb8";
	       }else{
		        return"";
	       }
	  }
	/**供应商编码*/
	public static String getSupplierCode(String requestid){
		RecordSetDataSource rsds = new RecordSetDataSource("wms");
		rsds.execute("select code from tb_mdm_supplier where requestid = '"+requestid+"'");
		String ret = "";
		rsds.next();
		ret = Util.null2String(rsds.getString("code"));
		return ret;
	}
	//去掉前后指定字符串
	public static String custom_trim(String str, char c) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int st = 0;
        while ( (st < len) && (chars[st] == c) ){
            st ++;
        }
        while ( (st < len) && (chars[len-1] == c) ){
            len --;
        }
        return (st >0) && (len<chars.length)? str.substring(st, len): str;
    }
	/**获取单位编码*/
	public static String getUnitCodeById(String unitId){
		RecordSetDataSource rswms = new RecordSetDataSource("wms");
		String sql2="select code from V_MDM_UNIT where requestid='"+unitId+"'";
		rswms.execute(sql2);
		rswms.next();
		String dwcode=Util.null2String(rswms.getString("code"));
		return dwcode;
	}
	/**获取工程名称*/
	public static String getgcmcBygcbm(String gcbm){
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		String sql2="select jobname from mid_jobbasfile_list_v where  seq='"+gcbm+"'";
		rsnc.execute(sql2);
		rsnc.next();
		String jobname=Util.null2String(rsnc.getString("jobname"));
		return jobname;
	}
	/**获取NC人员编码*/
	public static String getNcPersonCodeByCode(String workcode,String gsbm){
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		String sql2="select psncode1 from mid_person_v where  unitcode='"+gsbm+"' and psncode='"+workcode+"'";
		rsnc.execute(sql2);
		rsnc.next();
		String ncworkcode=Util.null2String(rsnc.getString("psncode1"));
		return ncworkcode;
	}
	/**仓库接收人*/
	public static String getjsrByck(String ck){
		RecordSetDataSource rswms = new RecordSetDataSource("wms");
		String sql2="select czy from tb_site where requestid='"+ck+"'";
		rswms.execute(sql2);
		rswms.next();
		String jsr=Util.null2String(rswms.getString("czy"));
		return jsr;
	}
	
	/**OA退货出库类型装换*/
    public static String getWmsthlb(String thlb){
		  if(thlb.equals("0")){
			  /**取消订单入库*/
			  thlb="402880e85c38d0c3015c39b790c60c73";
		  }else if(thlb.equals("1")){
			  /**订单变更退货*/
			  thlb="402880e85c38d0c3015c39b790c60c74";
		  }else if(thlb.equals("2")){
			  /**不合格品退货*/
			  thlb="402880e85c38d0c3015c39b790c60c75";
		  }
		  return thlb;
	  }
    
	/**验证集合里面数据是否重复*/
	public static boolean collectionIsrepeated(List<String> list){
		   int num = 0; 
		  for(int i=0;i<list.size()-1;i++){  
	            List<String> list2=new ArrayList<String>();  
	            for(int j=list.size()-1;j>i;j--){  
	                if(list.get(j).equals(list.get(i))){  
	                    list2.add(list.get(j));  
	                    list.remove(j);  
	                }  
	            }  
	            if((list2.size()+1) >1){  
	                num =(list2.size()+1);  
	            }      
	        }
		    if(num > 1){  
	            return true;  
	        }else{
	        	return false;
	        }
	}
	public static String getNowYear() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String dayString = sf.format(date);

		return dayString;
	}
	/**获取NC登录名*/
	public static String getNCcodeByOAcode(String workcode) {
		 RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		 String sql2 = "select user_code from sm_user where user_note='"+workcode+"'";
		 rsnc.execute(sql2);
		 rsnc.next();
		 String user_code=Util.null2String(rsnc.getString("user_code"));
		 return user_code; 
	}
	public static String fulltime() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String dayString = sf.format(date);
		return dayString;
	}
	public static String getNowDay() {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dayString = sf.format(date);

		return dayString;
	}
	
	public static String getNcBmbmByCode(String fycdrCode,String pk_corp){
		RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		String sql1="select * from mid_person_v where unitcode='"+pk_corp+"' and psncode='"+fycdrCode+"'";
		rsnc.execute(sql1);
		rsnc.next();
		String deptcode = Util.null2String(rsnc.getString("deptcode"));
		return deptcode;
	}
    /**获取库存代用工程名称*/
	public static String getKcdyGcmc(String seq) {
		RecordSetDataSource rsnc= new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
		String sql1 = "select jobname from mid_jobbasfil_tree_v where seq='"+seq+"'";
		rsnc.execute(sql1);
		rsnc.next();
		String kcdyGcmc = Util.null2String(rsnc.getString("jobname"));
		return kcdyGcmc;
		
	}
	
	public   static  List  removeDuplicate(List list)  {       
		  for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {       
		      for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {       
		           if  (list.get(j).equals(list.get(i)))  {       
		              list.remove(j);       
		            }        
		        }        
		      }        
		    return list;       
	} 
	/**获取wms物料流水号*/
	public static String getLSH(String qz, String lb,int ws) {
		RecordSetDataSource rswms = new RecordSetDataSource("wms");;
		String sql1="select ls from mdid where lb='" + lb+ "' and qz='" + qz + "'";
		rswms.execute(sql1);
		String lsh = "";
		if(rswms.next()){
			lsh=Util.null2String(rswms.getString("ls"));
			long lshL = Long.parseLong(lsh)+1;
			String sql2="update mdid set ls ='"+lshL+"' where lb='"+lb+"'and qz='"+qz+"'";
			rswms.execute(sql2);
			lsh = String.valueOf(lshL);
		}else{
			String sql2="insert into mdid(lb,qz,ls) values('"+lb+"','"+qz+"',1)";
			rswms.execute(sql2);
			lsh = "1";
		}
		String ret = "";
		for(int i=0;i<ws-lsh.length();i++){
			ret+="0";
		}
		ret+=lsh;
		return ret;
	}
}
