jQuery(document).ready(function() {

	WfForm.registerCheckEvent(WfForm.OPER_ADDROW+"1", function(callback){
		var gsmcVal = WfForm.getFieldValue("field"+gsmc);
		if(gsmcVal===''){
			alert("公司名称信息不能为空,请选择后在添加行信息!")
		}else{
			callback();//允许继续添加行调用callback，不调用代表阻断添加
		}
	});

	// 物料类型发生变化 清空明细表
	WfForm.bindFieldChangeEvent("field" + gsmc, function(obj,id,value){
		console.log("WfForm.bindFieldChangeEvent--",obj,id,value);
		// 删除所有明细行
		WfForm.delDetailRow("detail_1", "all");
	});
    // 客商名称 变更
	WfForm.bindDetailFieldChangeEvent("field" + ksbm_dt1,function(id,rowIndex,value){
		// 主表公司名称
		var gsmcVal = WfForm.getFieldValue("field"+gsmc);
		jQuery.ajax({
			url : "/zdn/gyl/zsj/xxks/ksxzyh_script_ys.jsp",
			type : "POST",
			cache : false,
			async : false,
			data : {'gsmcVal' : gsmcVal,'ksbmVal' : value},
			dataType : "json",
			success : function(msg) {
				WfForm.changeFieldValue("field"+ksmc_dt1+"_"+rowIndex, {value:msg.ksmcVal});
				WfForm.changeFieldValue("field"+kslx_dt1+"_"+rowIndex, {value:msg.kslxVal});
			}
		});
	});




});
