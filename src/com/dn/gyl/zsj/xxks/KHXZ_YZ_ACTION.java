package com.dn.gyl.zsj.xxks;

import com.dn.common.CommonUtil;
import com.dn.gyl.zsj.chxz.CHXXFPGS_YZ_ACTION;
import com.weaver.file.Prop;
import com.weaver.general.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @className: XZKH_YZ_ACTION
 * @author: jun
 * @date: 2020-12-28 15:21
 * @Depiction:客户新增 验证接口
 **/
public class KHXZ_YZ_ACTION implements Action {
    private Log log = LogFactory.getLog(KHXZ_YZ_ACTION.class.getName());
    public String execute(RequestInfo requestInfo) {
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        /**流程名称*/
        String workFlowName= CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        String tableName=CommonUtil.getWorkFlowTableName(workFlowId);
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";开始!");
        try{
            String returnMessage="";
            boolean returnFlag=false;
            RecordSetDataSource rsnc = new RecordSetDataSource(Prop.getPropValue("GYL","ncds"));
            RecordSet recordSet = new RecordSet();
            String sql1="select * from " + tableName + "_dt1  where  mainid in (select id from "+tableName+" where requestid='"+requestId+"')";
            log.info("执行sql1---->"+sql1);
            recordSet.execute(sql1);
            while(recordSet.next()){
                /**客户名称*/
                String khmc= Util.null2String(recordSet.getString("khmc"));
                String sql2="select  count(*) count  from bd_customer  where pk_org = (select pk_group from org_group where code = '001') and name='"+khmc+"'";
                log.info("执行sql2---->"+sql2);
                rsnc.execute(sql2);
                rsnc.next();
                int  count= rsnc.getInt("count");
                if(count>0){
                    returnFlag=true;
                    returnMessage="该客户名称为:"+khmc+"在NC已存在,请忽重新新增该客户信息,修改信息后,重新提交!";
                    break;
                }
            }
            String sql3="select khmc,count(*) count from " + tableName + "_dt1  where  mainid in (select id from "+tableName+" where requestid='"+requestId+"') group by khmc";
            log.info("执行sql3---->"+sql3);
            recordSet.execute(sql3);
            while(recordSet.next()){
                int count= recordSet.getInt("count");
                String khmc=Util.null2String(recordSet.getString("khmc"));
                if(count>1){
                    returnMessage="明细客户名称:"+khmc+"重复,请删除重复信息后，重新提交!";
                    returnFlag=true;
                    break;
                }
            }
            if(returnFlag){
                requestInfo.getRequestManager().setMessagecontent(returnMessage);
                return Action.FAILURE_AND_CONTINUE;
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
