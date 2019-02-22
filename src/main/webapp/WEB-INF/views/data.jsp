<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${ctx}/styles/js/JSONFormat.js" type="text/javascript"></script>
<div data-role="dataGrid"></div>
<script>
$(function(){
	var url = '${ctx}/data/list/${type}';
	$.get(url,null,null,"json").done(function(data){
		
		data = data.result;
		data = data[0];
		var html = '<div class="table-responsive" height="100%"><table class="table table-responsive table-bordered grid">';
			//html += '<thead><tr><th><div class="btn-group buttons"><button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</span></span></button><button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</span></span></button></div></th></tr></thead>';
			html += '<tbody><tr><td><div class="colResizePointer"></div><div class="grid-body" style="width: 100%"><div class="grid-table-body"><table class="table table-bordered">';
			html += '<tr><th width="20%">更新时间<div class="colResize"></div></th><td width="auto">'+data.updateTime+'<div class="colResize"></div></td></tr>';
			html += '<tr><th>更新IP<div class="colResize"></div></th><td>'+data.updateIp+'<div class="colResize"></div></td></tr>';
			if(data.city)
				html += '<tr><th>地点<div class="colResize"></div></th><th>'+data.city+'<div class="colResize"></div></th></tr>';
			var viewData = "";
			try{
				viewData = eval(data.data);
			}catch (e) {
				viewData = eval("("+data.data+")");
			}
			html += '<tr><td width="auto" colspan="2">'+JsonUti.convertToString(viewData)+'<div class="colResize"></div></td></tr></tbody></table></div></div></td></tr></tbody></table></div>';
		$("[data-role='dataGrid']").html(html);
	});
});

</script>
<style type="text/css">
<!--
.form-group {
margin-left: 0;
margin-right: 0;
}
.grid-table-body{height:}
.xuboxPageHtml {
width: 500px;
}
-->
</style>