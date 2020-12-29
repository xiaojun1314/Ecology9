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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            List<MidCubasDocYhDetail> imList=new ArrayList<MidCubasDocYhDetail>();
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
                /**客户编码*/
                yhmid.setCUSTCODE(Util.null2String(recordSet.getString("ksbm")));
                /**客商名称*/
                yhmid.setCUSTNAME(Util.null2String(recordSet.getString("ksmc")));
                /**客商简称*/
                yhmid.setCUSTSHORTNAME(Util.null2String(recordSet.getString("ksmc")));
                /**客商类型*/
                yhmid.setCUSTPROP(Util.null2String(recordSet.getString("kslx")));
                /**时间戳*/
                yhmid.setTS(dateFormate);
                /**所属公司 编码*/
                yhmid.setPK_CORP(gsmc);
                /**删除标识*/
                yhmid.setDR("0");
                /**备注*/
                yhmid.setVNOTE(lcbh+"_"+Util.null2String(recordSet.getString("id")));
                /**联系人*/
                yhmid.setVDEF1(Util.null2String(recordSet.getString("lxr")));
                /**联系电话*/
                yhmid.setVDEF2(Util.null2String(recordSet.getString("lxdh")));
                /**银行类别*/
                yhmid.setVDEF3(Util.null2String(recordSet.getString("yhlb")));
                /**开户行*/
                yhmid.setVDEF4(Util.null2String(recordSet.getString("khh")));
                /**账号*/
                yhmid.setVDEF5(Util.null2String(recordSet.getString("zh")));
                /**供应链处理标识N*/
                yhmid.setVDEF8("N");
                imList.add(yhmid);
            }
            ncMidCubasDocDataProcessor.midCubasDocYhNCData(imList);
        }catch(Exception e){
            requestInfo.getRequestManager().setMessagecontent("系统异常,请联系系统管理员!");
            log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";程序异常!");
            return Action.FAILURE_AND_CONTINUE;
        }
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";执行结束!");
        return Action.SUCCESS;
    }
}
