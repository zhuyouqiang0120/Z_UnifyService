/**
 * 打开部门信息
 */
var layerIndex;
var openRegion = function(){
	var html = '<div role="form" class="form-horizontal" style="padding:10px;"><table class="table table-responsive table-bordered table-hover table-striped" style="width:530px;" align="center">';
	html += '<thead><tr><td align=center width=45% class="td_list_1" nowrap>区域名称</td><td align=center width=45% class="td_list_1" nowrap>上级区域名称</td><td align=center width=10% class="td_list_1" nowrap>操作</td></tr></thead>';
	html += '<tbody id="regionDataTable"></tbody>'
	html += '</table></div>';
	layerIndex = $.layer({
		type: 1,
		title: "选择区域",
		area: ['550px', '600px'],
		shade: [0.3, '#000'],
		shift: 'bottom', //从左动画弹出
		page: {html: html},
		btns:1,
		btn: ["关闭"]
	});
	loadRegionData(0);
}
/**
 * 加载部门信息
 */
var loadRegionData = function(pageNo){
	$.post(contextPath + '/regions',{"pageNo":pageNo},null,"json").done(function(data){
		var html = '';
		
		$.each(data.result,function(){
			html += '<tr><td class="td_list_2" align=left nowrap>'+this.name+'&nbsp;</td><td class="td_list_2" align=left nowrap>'+(this.parentRegionsName ? this.parentRegionsName : "")+'&nbsp;</td>';
			html += '<td class="td_list_2" align=left nowrap><a href="javascript:void(0)" class="glyphicon glyphicon-ok" title="选择" onclick="$(\'#parentRegionsName\').val(\''+this.name+'\');$(\'#parentRegionsId\').val(\''+this.id+'\');layer.close('+layerIndex+');"></a></td></tr>';
		});
		
		//分页
		html += '<tr class="table table-responsive table-bordered table-hover table-striped grid"><td colspan="3"><div class="records">显示:'+(data.pageNo)+'/'+(data.count)+', 共'+data.totalRecord+'条记录。 每页显示:'+data.pageSize+'条</div><div class="btn-group pages"><ul class="pagination pull-left">';
		if(data.pageNo != 1){
			html += '<li data-role="firstPage"><a href="#" onclick="loadOrgData(1)">«</a></li><li data-role="prev"><a href="#" onclick="loadRegionData('+(data.pageNo-1)+')">‹</a></li>';
		}else{
			html += '<li data-role="firstPage" class="disabled"><a href="#">«</a></li><li data-role="prev" class="disabled"><a href="#">‹</a></li>';
		}
		if(data.pageNo != data.count){
			html += '<li data-role="next"><a href="#" onclick="loadRegionData('+(data.pageNo+1)+')">›</a></li><li data-role="lastPage"><a href="#" onclick="loadRegionData('+(data.count-1)+')">»</a></li>';
		}else{
			html += '<li data-role="next" class="disabled"><a href="#">›</a></li><li data-role="lastPage" class="disabled"><a href="#">»</a></li>';
		}
		html += '</ul></div></td></tr>';
		
		$("#regionDataTable").html(html);
	});
};
