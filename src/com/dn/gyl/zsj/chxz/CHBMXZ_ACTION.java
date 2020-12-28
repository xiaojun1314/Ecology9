package com.dn.gyl.zsj.chxz;

import com.dn.common.CommonUtil;
import com.dn.example.CHBMXZ_YZ_ACTION;
import com.weaver.file.Prop;
import com.weaver.formmodel.util.StringHelper;
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
 * @className: CHBMXZ_ACTION
 * @author: jun
 * @date: 2020-12-22 09:25
 * @Depiction:
 **/
public class CHBMXZ_ACTION implements Action {
    private Log log = LogFactory.getLog(CHBMXZ_ACTION.class.getName());

    public String execute(RequestInfo requestInfo) {
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        /**流程名称*/
        String workFlowName= CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        String tableName=requestInfo.getRequestManager().getBillTableName();
        RecordSetDataSource rswms = new RecordSetDataSource("wms");
        log.info("触发流程:"+workFlowName+";执行接口类名:"+this.getClass().getName()+";发起请求RequestId:" + requestId + ";发起流程ID:" + workFlowId+";开始!");
        try{
            RecordSet recordSet = new RecordSet();
            String sql1="select * from "+tableName+" where requestid = "+requestId;
            log.info("执行sql1:"+sql1+"-->成功");
            recordSet.execute(sql1);
            recordSet.next();
            /**流程编号*/
            String lcbh = Util.null2String(recordSet.getString("lcbh"));
            /**主表mainid*/
            String mainid=Util.null2String(recordSet.getString("id"));
            /**NC创建人*/
            String nccjr = Util.null2String(recordSet.getString("nccjr"));
            String sql2="select * from "+tableName+"_dt1 where mainid ='"+mainid+"'";
            recordSet.execute(sql2);
            log.info("执行sql2:"+sql2+"-->成功");
            NCMidInvabasDocDataProcessor ncMidInvabasDocDataProcessor=new NCMidInvabasDocDataProcessor();
            String dateformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            while(recordSet.next()){
                MidInvabasDocModel im=new MidInvabasDocModel();
                String INVABASDOCID=UUID.randomUUID().toString().replaceAll("-", "");
                /**主键*/
                im.getHead().setINVABASDOCID(INVABASDOCID);
                /**是否成功 默认0*/
                im.getHead().setFTHANSFERSTATUS("0");
                /**上传成功标记*/
                im.getHead().setSUCCESSFLAG("N");
                /**设备卡片管理*/
                im.getHead().setASSET("N");
                /**自动平衡主辅计量 */
                im.getHead().setAUTOBALANCEMEAS("N");
                /**按毛重管理库存*/
                im.getHead().setISMNGSTOCKBYGRSWT("N");
                /**是否零售*/
                im.getHead().setISRETAIL("N");
                /**换算率结存*/
                im.getHead().setISSTOREBYCONVERT("N");
                /**应税劳务*/
                im.getHead().setLABORFLAG("N");
                /**成套件*/
                im.getHead().setSETPARTSFLAG("N");
                /**创建日期*/
                im.getHead().setCREATETIME(dateformate);
                /**创建人*/
                im.getHead().setCREATOR(nccjr);
                String zyx=Util.null2String(recordSet.getString("zyx"));
                if(!"".equals(zyx)){
                    String[] zyxArray=zyx.split(",");
                    for(int i=0;i<zyxArray.length;i++){
                        if(zyxArray[i].equals("001")){
                            im.getHead().setFREE1(zyxArray[i]);
                        }else if(zyxArray[i].equals("002")){
                            im.getHead().setFREE2(zyxArray[i]);
                        }else if(zyxArray[i].equals("003")){
                            im.getHead().setFREE3(zyxArray[i]);
                        }
                    }
                }
                /**存货名称*/
                im.getHead().setINVNAME(Util.null2String(recordSet.getString("chmc")));
                /**存货规格*/
                im.getHead().setINVSPEC(Util.null2String(recordSet.getString("gg")));
                /**单位重量 换算率*/
                im.getHead().setUNITWEIGHT(Util.null2String(recordSet.getString("dwzl")));
                /**辅计量单位*/
                im.getHead().setPK_MEASDOC1(Util.null2String(recordSet.getString("fjldw")));
                /**辅计量单位入库*/
                im.getHead().setASSISTUNIT(Util.null2String(recordSet.getString("sffjlgl")).equals("0")?"Y":"N");
                // 存货分类编码
                im.getHead().setPK_INVCL(Util.null2String(recordSet.getString("chfl")));
                /**主计量单位*/
                im.getHead().setPK_MEASDOC(Util.null2String(recordSet.getString("zjldw")));
                /**时间戳*/
                im.getHead().setTS(dateformate);
                /**OA流程编号*/
                im.getHead().setVDEF1(lcbh+"_"+Util.null2String(recordSet.getString("id")));
                /**是否质检*/
                im.getHead().setVDEF2(Util.null2String(recordSet.getString("sfzj")));
                /**是否过磅*/
                im.getHead().setVDEF3(Util.null2String(recordSet.getString("sfgb")));
                /**是否辅单位入库(是否钢板)*/
                im.getHead().setVDEF4(Util.null2String(recordSet.getString("sffwdrk")));
                /**单位重量（主计量单位与吨之间的换算率）*/
                im.getHead().setVDEF6(Util.null2String(recordSet.getString("dwzl")));
                /**最小起订量*/
                im.getHead().setVDEF7(Util.null2String(recordSet.getString("zxqdl")));
                /**供应链处理标识  WMS用*/
                im.getHead().setVDEF8("N");
                /**分配公司 默认集团*/
                String[] fpgsArr=Util.null2String(recordSet.getString("fpgs")+",1").split(",");
                for(String fpgsCode:fpgsArr){
                    MidInvabasDocCompanyDetail mid=new MidInvabasDocCompanyDetail();
                    String INVABASDOCCOMPANYID=UUID.randomUUID().toString().replaceAll("-", "");
                    /**主键*/
                    mid.setINVABASDOCCOMPANYID(INVABASDOCCOMPANYID);
                    /**外键*/
                    mid.setINVABASDOCID(INVABASDOCID);
                    /**是否成功 默认0*/
                    mid.setFTHANSFERSTATUS("0");
                    /**上传成功标记*/
                    mid.setSUCCESSFLAG("N");
                    /**创建日期*/
                    mid.setCREATETIME(dateformate);
                    /**创建人*/
                    mid.setCREATOR(nccjr);

                    if(!"".equals(zyx)){
                        String[] zyxArray=zyx.split(",");
                        for(int i=0;i<zyxArray.length;i++){
                            if(zyxArray[i].equals("001")){
                                im.getHead().setFREE1(zyxArray[i]);
                            }else if(zyxArray[i].equals("002")){
                                im.getHead().setFREE2(zyxArray[i]);
                            }else if(zyxArray[i].equals("003")){
                                im.getHead().setFREE3(zyxArray[i]);
                            }
                        }
                    }
                    /**存货名称*/
                    mid.setINVNAME(Util.null2String(recordSet.getString("chmc")));
                    /**规格*/
                    mid.setINVSPEC(Util.null2String(recordSet.getString("gg")));
                    /**公司编码*/
                    mid.setPK_CORP(fpgsCode);
                    /**时间戳*/
                    mid.setTS(dateformate);
                    /**备注*/
                    mid.setVNOTE(lcbh+"_"+Util.null2String(recordSet.getString("id")));
                    /**供应链处理标识  WMS用*/
                    mid.setVDEF8("N");
                    im.getDetail().add(mid);
                }
                ncMidInvabasDocDataProcessor.midInvabasDocNCData(im);
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
