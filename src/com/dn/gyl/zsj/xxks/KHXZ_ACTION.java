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
 * @className: KHXZ_ACTION
 * @author: jun
 * @date: 2020-12-28 15:49
 * @Depiction: 客户新增 action
 **/
public class KHXZ_ACTION implements Action {
    private Log log = LogFactory.getLog(KHXZ_YZ_ACTION.class.getName());
    public String execute(RequestInfo requestInfo) {
        /**流程workflowid*/
        String workFlowId = requestInfo.getWorkflowid();
        /**流程requestid*/
        String requestId = requestInfo.getRequestid();
        /**流程名称*/
        String workFlowName = CommonUtil.getWorkFlowName(workFlowId);
        /**主表名称*/
        String tableName = CommonUtil.getWorkFlowTableName(workFlowId);
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
            /**NC创建人*/
            String nccjr = Util.null2String(recordSet.getString("nccjr"));
            String sql2 = "select * from " + tableName + "_dt1 where mainid ='" + mainid + "'";
            recordSet.execute(sql2);
            log.info("执行sql2:" + sql2 + "-->成功");
            NCMidCubasDocDataProcessor ncMidCubasDocDataProcessor = new NCMidCubasDocDataProcessor();
            String dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            while (recordSet.next()) {
                String CUBASDOCID = UUID.randomUUID().toString().replaceAll("-", "");
                MidCubasDocModel im = new MidCubasDocModel();
                /**主键*/
                im.getHead().setCUBASDOCID(CUBASDOCID);
                /**是否成功 默认0*/
                im.getHead().setFTHANSFERSTATUS("0");
                /**上传成功标记*/
                im.getHead().setSUCCESSFLAG("N");
                /**删除标识*/
                im.getHead().setDR("0");
                /**是否DRP结点  例：一致为N*/
                im.getHead().setDRPNODEFLAG("N");
                /**是否散户     例：一致为N */
                im.getHead().setFREECUSTFLAG("N");
                /**是否渠道成员 例：一致为N*/
                im.getHead().setISCONNFLAG("N");
                /**创建日期*/
                im.getHead().setCREATETIME(dateFormate);
                /**创建人*/
                im.getHead().setCREATOR(nccjr);
                /**客商名称*/
                im.getHead().setCUSTNAME(Util.null2String(recordSet.getString("khmc")));
                /**客商编码为空*/
                im.getHead().setCUSTCODE(Util.null2String(recordSet.getString("khbm")));
                /**客商简称*/
                im.getHead().setCUSTSHORTNAME(Util.null2String(recordSet.getString("khmc")));
                /**客商类型 0 客户*/
                im.getHead().setCUSTPROP("0");
                /**地区分类 */
                im.getHead().setPK_AREACL(Util.null2String(recordSet.getString("dq")));
                /**时间戳*/
                im.getHead().setTS(dateFormate);
                /**备注流程编号信息*/
                im.getHead().setVNOTE(lcbh + "_" + Util.null2String(recordSet.getString("id")) + "_" + requestId);
                /**客商属性 -外部单位*/
                im.getHead().setVDEF1("0");
                /**公司地址*/
                im.getHead().setVDEF2(Util.null2String(recordSet.getString("gsdz")));
                /**纳税人登记号*/
                im.getHead().setVDEF3(Util.null2String(recordSet.getString("nsrdjh")));
                /**供应链处理标识N*/
                im.getHead().setVDEF8("N");
                /**所属公司默认集团*/
                String[] ssgsArr=Util.null2String(recordSet.getString("ssgs")+",1").split(",");
                for(String ssgs:ssgsArr){
                    MidCubasDocCompanyDetail mid=new MidCubasDocCompanyDetail();
                    String CUBASDOCCOMPANYID=UUID.randomUUID().toString().replaceAll("-", "");
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
                    mid.setCREATOR(nccjr);
                    /**客商名称*/
                    /**客商名称*/
                    mid.setCUSTNAME(Util.null2String(recordSet.getString("khmc")));
                    /**客商编码为空*/
                    mid.setCUSTCODE(Util.null2String(recordSet.getString("khbm")));
                    /**客商简称*/
                    mid.setCUSTSHORTNAME(Util.null2String(recordSet.getString("khmc")));
                    /**客商类型*/
                    mid.setCUSTPROP("0");
                    /**删除标识*/
                    mid.setDR("0");
                    /**地区分类 */
                    mid.setPK_AREACL(Util.null2String(recordSet.getString("dq")));
                    /**时间戳*/
                    mid.setTS(dateFormate);
                    /**所属公司 编码*/
                    mid.setPK_CORP(ssgs);
                    /**备注流程编号信息*/
                    mid.setVNOTE(lcbh+"_"+Util.null2String(recordSet.getString("id")));
                    /**供应链处理标识N*/
                    mid.setVDEF8("N");
                    im.getGsdetail().add(mid);
                }
                /**==================银行信息==================*/
                if(!"".equals(Util.null2String(recordSet.getString("zh")))){
                    MidCubasDocYhDetail yhmid=new MidCubasDocYhDetail();
                    /**主键*/
                    String CUBASDOCYHID=UUID.randomUUID().toString().replaceAll("-", "");
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
                    yhmid.setCREATOR(nccjr);
                    /**客商名称*/
                    /**客商名称*/
                    yhmid.setCUSTNAME(Util.null2String(recordSet.getString("khmc")));
                    /**客商编码为空*/
                    yhmid.setCUSTCODE(Util.null2String(recordSet.getString("khbm")));
                    /**客商简称*/
                    yhmid.setCUSTSHORTNAME(Util.null2String(recordSet.getString("khmc")));
                    /**客商类型*/
                    yhmid.setCUSTPROP("0");
                    /**时间戳*/
                    yhmid.setTS(dateFormate);
                    /**所属公司 编码 默认集团*/
                    yhmid.setPK_CORP(Util.null2String(recordSet.getString("ssgs"))+",1");
                    /**删除标识*/
                    yhmid.setDR("0");
                    /**备注*/
                    yhmid.setVNOTE(lcbh+"_"+Util.null2String(recordSet.getString("id"))+ "_" + requestId);
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
                    im.getYhdetail().add(yhmid);
                }
                ncMidCubasDocDataProcessor.midCubasDocNCData(im);
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
