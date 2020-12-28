package com.dn.gyl.zsj.chxz;

import com.dn.common.CommonUtil;
import com.weaver.file.Prop;
import com.weaver.formmodel.util.StringHelper;
import com.weaver.general.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @className: CHXXFPGS_YZ_ACTION
 * @author: jun
 * @date: 2020-12-26 15:46
 * @Depiction:
 **/
public class CHXXFPGS_YZ_ACTION implements Action {
    private Log log = LogFactory.getLog(CHXXFPGS_YZ_ACTION.class.getName());
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
                /**已分配公司*/
                String yfpgsbh= Util.null2String(recordSet.getString("yfpgsbh"));
                /**待分配公司*/
                String dfpgsmc=Util.null2String(recordSet.getString("dfpgsmc"));
                for(int i=0;i<yfpgsbh.split(",").length;i++){
                    for(int j=0;j<dfpgsmc.split(",").length;j++){
                        if(yfpgsbh.split(",")[i].equals(dfpgsmc.split(",")[j])){
                            returnMessage="分配公司:"+yfpgsbh.split(",")[i]+"在已分配公司中存在,请乎重复选择,修改待分配公司信息后,重新提交!";
                            returnFlag=true;
                            break;
                        }
                    }
                    if(returnFlag){
                        break;
                    }
                }
                if(returnFlag){
                    break;
                }
            }
            String sql2="select chbm,count(*) wlsl from " + tableName + "_dt1  where  mainid in (select id from "+tableName+" where requestid='"+requestId+"') group by chbm";
            log.info("执行sql2---->"+sql2);
            recordSet.execute(sql2);
            while(recordSet.next()){
                int wlsl= recordSet.getInt("wlsl");
                String chbm=Util.null2String(recordSet.getString("chbm"));
                if(wlsl>1){
                    returnMessage="明细存货编码:"+chbm+"重复,请删除重复信息后，重新提交!";
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
