package com.dn.gyl.zsj.xxks;

import com.dn.common.CommonUtil;
import com.dn.gyl.zsj.chxz.NCMidInvabasDocDataProcessor;
import com.weaver.general.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @className: KSFPGS_ACTION
 * @author: jun
 * @date: 2020-12-29 15:34
 * @Depiction:客商分配公司 接口
 **/
public class KSFPGS_ACTION implements Action {
    private Log log = LogFactory.getLog(KSFPGS_ACTION.class.getName());
    public String execute(RequestInfo requestInfo) {
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        /**流程名称*/
        String workFlowName= CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        String tableName=CommonUtil.getWorkFlowTableName(workFlowId);
        /**登录人ID*/
        String loginId = requestInfo.getRequestManager().getUserId() + "";
        /**登录人编码*/
        String workCode = CommonUtil.getHRidBYCode(loginId);
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";开始!");
        try{
            RecordSet recordSet = new RecordSet();
            String sql1 = "select * from " + tableName + " where requestid = " + requestId;
            log.info("执行sql1:" + sql1 + "-->成功");
            recordSet.execute(sql1);
            recordSet.next();
            /**流程编号*/
            String lcbh = Util.null2String(recordSet.getString("lcbh"));
            /**主表mainid*/
            String mainid = Util.null2String(recordSet.getString("id"));
            String sql2 = "select * from " + tableName + "_dt1 where mainid ='" + mainid + "'";
            log.info("执行sql2---->" + sql2);
            recordSet.execute(sql2);
            String dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            List<MidCubasDocCompanyDetail> imList=new ArrayList<MidCubasDocCompanyDetail>();
            while (recordSet.next()) {
                /**分配公司名称*/
                String fpgs = Util.null2String(recordSet.getString("fpgs"));
                for (String fpgsCode : fpgs.split(",")) {
                    MidCubasDocCompanyDetail mid=new MidCubasDocCompanyDetail();
                    String CUBASDOCCOMPANYID= UUID.randomUUID().toString().replaceAll("-", "");
                    String CUBASDOCID=UUID.randomUUID().toString().replaceAll("-", "");
                    /**主键*/
                    mid.setCUBASDOCCOMPANYID(CUBASDOCCOMPANYID);
                    /**外键*/
                    mid.setCUBASDOCID(CUBASDOCID);
                    /**是否成功 默认0*/
                    mid.setFTHANSFERSTATUS("0");
                    /**上传成功标记*/
                    mid.setSUCCESSFLAG("N");
                    /**创建日期*/
                    mid.setCREATETIME(dateFormate);
                    /**创建人*/
                    mid.setCREATOR(workCode);
                    /**客商名称*/
                    mid.setCUSTNAME(Util.null2String(recordSet.getString("ksmc")));
                    /**客商编码*/
                    mid.setCUSTCODE(Util.null2String(recordSet.getString("ksbm")));
                    /**客商简称*/
                    mid.setCUSTSHORTNAME(Util.null2String(recordSet.getString("ksmc")));
                    /**客商类型*/
                    mid.setCUSTPROP(Util.null2String(recordSet.getString("kslx")));
                    /**所属公司 编码*/
                    mid.setPK_CORP(fpgsCode);
                    /**删除标识*/
                    mid.setDR("0");
                    /**地区分类 */
                    mid.setPK_AREACL(Util.null2String(recordSet.getString("dqbm")));
                    /**时间戳*/
                    mid.setTS(dateFormate);
                    /**备注流程编号信息*/
                    mid.setVNOTE(lcbh+"_"+Util.null2String(recordSet.getString("id")));
                    /**供应链处理标识N*/
                    mid.setVDEF8("N");
                    imList.add(mid);
                }
                NCMidCubasDocDataProcessor ncMidCubasDocDataProcessor=new NCMidCubasDocDataProcessor();
                ncMidCubasDocDataProcessor.midCubasDocCompanyNCData(imList);
            }
        }catch(Exception e){
            requestInfo.getRequestManager().setMessagecontent("系统异常,请联系系统管理员!");
            log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";程序异常!");
            return Action.FAILURE_AND_CONTINUE;
        }
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";执行结束!");
        return Action.SUCCESS;
    }

}
