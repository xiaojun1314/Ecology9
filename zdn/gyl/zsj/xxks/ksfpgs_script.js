jQuery(document).ready(function() {

	WfForm.bindDetailFieldChangeEvent("field" + kslx_dt1,function(id,rowIndex,value){
		WfForm.changeFieldValue("field"+ksbm_dt1+"_"+rowIndex, {value: "", specialobj:[]});
	});






});
