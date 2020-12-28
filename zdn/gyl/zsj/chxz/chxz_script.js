jQuery(document).ready(function() {
	// 物料类型发生变化 清空明细表
	WfForm.bindFieldChangeEvent("field" + wllx, function(obj,id,value){
		console.log("WfForm.bindFieldChangeEvent--",obj,id,value);
		// 删除所有明细行
		WfForm.delDetailRow("detail_1", "all");
	});
    // 是否辅计量管控 不管控清空辅单位的值
	WfForm.bindDetailFieldChangeEvent("field" + sffjlgl_dt1,function(id,rowIndex,value){
		// 不管控辅计量单位 清空辅计量单位
		if(value==='1'){
			WfForm.changeFieldValue("field" + fjldw_dt1+"_"+rowIndex, {value:""});
		}
	});
    // 是否钢板变更 触发时间
	WfForm.bindDetailFieldChangeEvent("field" + sffwdrk_dt1,function(id,rowIndex,value){
		console.log("value",value);
		// 402880e85c108608015c1113fad601d3钢板入库 自由项变更 必填
		if(value==='402880e85c108608015c1113fad601d3'){
			WfForm.changeFieldAttr("field" + zyx_dt1+"_"+rowIndex, 3);
		}else{
			WfForm.changeFieldAttr("field" + zyx_dt1+"_"+rowIndex, 2);
		}
	});
});
