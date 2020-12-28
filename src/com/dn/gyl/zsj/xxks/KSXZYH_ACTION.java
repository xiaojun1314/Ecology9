package com.dn.gyl.zsj.xxks;

import com.dn.common.CommonUtil;
import com.weaver.file.Prop;
import com.weaver.general.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @className: KSXZYH_ACTION
 * @author: jun
 * @date: 2020-12-28 16:49
 * @Depiction:客商新增银行信息
 **/
public class KSXZYH_ACTION implements Action {
    private Log log = LogFactory.getLog(KSXZYH_ACTION.class.getName());
    public String execute(RequestInfo requestInfo) {
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        /**流程名称*/
        String workFlowName= CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        String tableName=CommonUtil.getWorkFlowTableName(workFlowId);
        String loginId = requestInfo.getRequestManager().getUserId() + "";
        String workCode = CommonUtil.getHRidBYCode(loginId);
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";开始!");
        try{
            RecordSet recordSet = new RecordSet();
            String sql1 = "select * from " + tableName + " where requestid = " + requestId;
            log.info("执行sql1:" + sql1 + "-->成功");
            recordSet.execute(sql1);
            recordSet.next();
            String mainid=Util.null2String(recordSet.getString("id"));
            String lcbh=Util.null2String(recordSet.getString("lcbh"));
            String gsmc=Util.null2String(recordSet.getString("gsmc"));
            String sql2 = "select * from " + tableName + "_dt1 where mainid ='" + mainid + "'";
            recordSet.execute(sql2);
            log.info("执行sql2:" + sql2 + "-->成功");
            NCMidCubasDocDataProcessor ncMidCubasDocDataProcessor=new NCMidCubasDocDataProcessor();
            String dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            while(recordSet.next()){
                MidCubasDocYhDetail yhmid=new MidCubasDocYhDetail();
                String CUBASDOCYHID=UUID.randomUUID().toString().replaceAll("-", "");
                String CUBASDOCID= UUID.randomUUID().toString().replaceAll("-", "");
                /**主键*/
                yhmid.setCUBASDOCYHID(CUBASDOCYHID);
                /**外键*/
                yhmid.setCUBASDOCID(CUBASDOCID);
                /**是否成功 默认0*/
                yhmid.setFTHANSFERSTATUS("0");
                /**上传成功标记*/
                yhmid.setSUCCESSFLAG("N");
                /**创建日期*/
                yhmid.setCREATETIME(dateFormate);
                /**创建人*/
                yhmid.setCREATOR(workCode);
                RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));


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
