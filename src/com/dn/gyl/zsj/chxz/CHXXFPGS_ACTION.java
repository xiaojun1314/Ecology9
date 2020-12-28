package com.dn.gyl.zsj.chxz;

import com.dn.common.CommonUtil;
import com.weaver.file.Prop;
import com.weaver.general.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @className: CHXXFPGS_ACTION
 * @author: jun
 * @date: 2020-12-26 15:52
 * @Depiction:
 **/
public class CHXXFPGS_ACTION implements Action {
    private Log log = LogFactory.getLog(CHXXFPGS_ACTION.class.getName());

    public String execute(RequestInfo requestInfo) {
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        /**流程名称*/
        String workFlowName = CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        String tableName = CommonUtil.getWorkFlowTableName(workFlowId);
        String loginId = requestInfo.getRequestManager().getUserId() + "";
        String workCode = CommonUtil.getHRidBYCode(loginId);
        log.info("触发流程:" + workFlowName + ";执行接口类名:" + this.getClass().getName() + ";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId + ";开始!");
        try {
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
            List<MidInvabasDocCompanyDetail> imList = new ArrayList<MidInvabasDocCompanyDetail>();
            while (recordSet.next()) {
                /**分配公司名称*/
                String dfpgsmc = Util.null2String(recordSet.getString("dfpgsmc"));
                for (String fpgsCode : dfpgsmc.split(",")) {
                    MidInvabasDocCompanyDetail mid = new MidInvabasDocCompanyDetail();
                    String INVABASDOCCOMPANYID = UUID.randomUUID().toString().replaceAll("-", "");
                    String INVABASDOCID = UUID.randomUUID().toString().replaceAll("-", "");
                    /**主键*/
                    mid.setINVABASDOCCOMPANYID(INVABASDOCCOMPANYID);
                    /**外键(无)*/
                    mid.setINVABASDOCID(INVABASDOCID);
                    /**是否成功 默认0*/
                    mid.setFTHANSFERSTATUS("0");
                    /**上传成功标记*/
                    mid.setSUCCESSFLAG("N");
                    /**创建日期*/
                    mid.setCREATETIME(dateFormate);
                    /**创建人*/
                    mid.setCREATOR(workCode);
                    /**存货编码*/
                    mid.setINVCODE(Util.null2String(recordSet.getString("chbm")));
                    /**存货名称*/
                    mid.setINVNAME(Util.null2String(recordSet.getString("chmc")));
                    /**规格*/
                    mid.setINVSPEC(Util.null2String(recordSet.getString("gg")));
                    /**公司编码*/
                    mid.setPK_CORP(fpgsCode);
                    /**时间戳*/
                    mid.setTS(dateFormate);
                    /**备注*/
                    mid.setVNOTE(lcbh + "_" + Util.null2String(recordSet.getString("id")));
                    /**供应链处理标识  WMS用*/
                    mid.setVDEF8("N");
                    imList.add(mid);
                }
                NCMidInvabasDocDataProcessor ncMidInvabasDocDataProcessor = new NCMidInvabasDocDataProcessor();
                ncMidInvabasDocDataProcessor.midInvabasDocCompanyNCData(imList);
            }
        } catch (Exception e) {
            requestInfo.getRequestManager().setMessagecontent("系统异常,请联系系统管理员!");
            log.info("触发流程:" + workFlowName + ";执行接口类名:" + this.getClass().getName() + ";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId + ";程序异常!");
            return Action.FAILURE_AND_CONTINUE;
        }
        log.info("触发流程:" + workFlowName + ";执行接口类名:" + this.getClass().getName() + ";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId + ";执行结束!");
        return Action.SUCCESS;
    }
}
