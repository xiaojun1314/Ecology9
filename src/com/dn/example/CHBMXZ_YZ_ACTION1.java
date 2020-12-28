package com.dn.example;

import com.dn.common.CommonUtil;
import com.weaver.file.Prop;
import com.weaver.formmodel.util.StringHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import weaver.conn.RecordSet;
import weaver.conn.RecordSetDataSource;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

/**
 * @className: CHBMXZ_YZ_ACTION
 * @author: jun
 * @date: 2020-12-21 09:56
 * @Depiction: MD-01-存货编码新增申请单 验证接口
 */
public class CHBMXZ_YZ_ACTION1 implements Action {
    private Log log = LogFactory.getLog(CHBMXZ_YZ_ACTION1.class.getName());

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
                /**存货编码*/
                String chmc= StringHelper.null2String(recordSet.getString("chmc"));
                /**规格*/
                String gg=StringHelper.null2String(recordSet.getString("gg"));
                /**是否钢板入库*/
                String sffwdrk=StringHelper.null2String(recordSet.getString("sffwdrk"));
                /**辅单位管控*/
                String sffjlgl=StringHelper.null2String(recordSet.getString("sffjlgl"));
                // 是钢板入库 需要辅计量管控 辅单位不能为空
                if("0".equals(sffwdrk)){
                    // 不管控
                    if("1".equals(sffjlgl)){
                        returnMessage="该存货名称为:"+chmc+",规格为:"+gg+";已钢板入库,需要辅单位管控,请修改辅单位管控类型，重新提交!";
                        returnFlag=true;
                        break;
                    }
                }

                String sql2="select  *  from bd_material where name = '"+chmc+"'  and materialspec = '"+gg+"'";
                log.info("执行sql2---->"+sql2);
                rsnc.execute(sql2);
                if(rsnc.getCounts()>0){
                    returnMessage="该存货名称为:"+chmc+";规格为:"+gg+";在NC中已经存在,请修改后重新提交!";
                    returnFlag=true;
                    break;
                }
            }
            if(returnFlag){
                requestInfo.getRequestManager().setMessage("111100");
                requestInfo.getRequestManager().setMessagecontent(returnMessage);
                return Action.FAILURE_AND_CONTINUE;
            }
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
