package com.dn.example;

import com.dn.common.CommonUtil;
import com.weaver.file.Prop;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.hrm.User;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @className: CHBMXZ_YZ_ACTION
 * @author: jun
 * @date: 2020-12-21 09:56
 * @Depiction: MD-01-存货编码新增申请单 验证接口
 */
public class EXAMPLE_01_ACTION implements Action {
    private Log log = LogFactory.getLog(EXAMPLE_01_ACTION.class.getName());

    public String execute(RequestInfo requestInfo) {

        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        //请求紧急程度
        String requestLevel = requestInfo.getRequestlevel();
        //当前操作类型 submit:提交/reject:退回
        String src = requestInfo.getRequestManager().getSrc();
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        //表单名称
        String tableName = requestInfo.getRequestManager().getBillTableName();
        //表单数据ID
        int billid = requestInfo.getRequestManager().getBillid();
        //获取当前操作用户对象
        User user = requestInfo.getRequestManager().getUser();
        //请求标题
        String requestName = requestInfo.getRequestManager().getRequestname();
        //当前用户提交时的签字意⻅
        String remark = requestInfo.getRequestManager().getRemark();
        //表单ID
        int formid = requestInfo.getRequestManager().getFormid();
        //是否是自定义表单
        int isbill = requestInfo.getRequestManager().getIsbill();

        /**流程名称*/
        String workFlowName= CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        //String tableName=CommonUtil.getWorkFlowTableName(workFlowId);

        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";开始!");
        try{
            RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
            RecordSet recordSet = new RecordSet();



        }catch(Exception e){
            requestInfo.getRequestManager().setMessage("111100");
            requestInfo.getRequestManager().setMessagecontent("系统异常,请联系系统管理员!");
            log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";程序异常!");
            return Action.FAILURE_AND_CONTINUE;
        }
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";执行结束!");
        return Action.SUCCESS;
    }

}
