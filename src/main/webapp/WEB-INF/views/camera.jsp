<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx}/styles/js/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/styles/js/loadRegions.js"></script>

<div data-role="dataGrid"></div>
<script type="text/javascript">
	$(function() {
		var columns = [{
			title : "名称",
			name : "name",
			width : 200
		},{
			title : "简称",
			name : "cnName",
			width : 150
		}, {
			title : "摄像头地址",
			name : "url",
			width : 250
		},{
			title : "区域",
			name : "regionName",
			width : 200
		},{
			title : "缩略图",
			name : "img",
			width : 100,
			render : function(item, name, index) {
				return item[name] ? '<span><a href="#" onclick=reviewImg("'+item[name]+'")>点击预览</a></span>' : '暂无';
			}
		}];
		var getButtons = function() {
			return [{
				content : '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>',
				action : 'add'
			}, {
				content : '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>',
				action : 'modify'
			}, {
				content : '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>',
				action : 'remove'
			}];
		};
		var url = contextPath + '/camera';
		$('[data-role="dataGrid"]').off().grid({
			identity : 'id',
			columns : columns,
			buttons : getButtons(),
			querys : [{title : '名称',value : 'name'},{title : '简称',value : 'cnName'}],
			url : url
		}).on({
			'add' : function() {
				add("",$(this));
			},
			'modify' : function(event, data) {
				var indexs = data.data;
				var $this = $(this);
				if (indexs.length == 0) {
					$this.message({
						type : 'warning',
						content : '请选择一条记录进行修改'
					});
					return;
				}
				if (indexs.length > 1) {
					$this.message({
						type : 'warning',
						content : '只能选择一条记录进行修改'
					})
					return;
				}
				modify(data.item[0],$this);
			},
			'remove' : function(event, data) {
				var indexs = data.data;
				var $this = $(this);
				if (indexs.length == 0) {
					$this.message({
						type : 'warning',
						content : '请选择要删除的记录'
					})
					return;
				}
				$this.confirm({
					content : '确定要删除所选记录吗?',
					callBack : function() {
						deleteData(data.item, $this);
					}
				});
			}
		});
		
		var add = function(title,grid) {
			var html = '<form class="form-horizontal" id="form1" role="form" style="padding:10px;" enctype="multipart/form-data">';
				html += '<input id="id" value="" name="id" type="hidden">';
				html += '<div class="form-group"><label for="name" class="col-sm-3 control-label">名称:</label><div class="col-sm-9"><input type="text" class="form-control" name="name" id="name"><span class="required">*</span></div></div>';
				html += '<div class="form-group"><label for="orgName" class="col-lg-3 control-label">所属区域:</label><div class="col-lg-9"><input type="hidden" id="parentRegionsId" name="regionsId"/><input type="text" class="form-control" id="parentRegionsName" readonly style="width:76%;"><button type="button" class="btn btn-primary" onclick="openRegion()" style="float:left;">区域信息</button></div></div>';
				html += '<div class="form-group"><label for="url" class="col-lg-3 control-label">摄像头地址:</label><div class="col-lg-9"><input type="text" class="form-control" id="url" name="url"/></div></div>';
				html += '<div class="form-group"><label for="url" class="col-lg-3 control-label">缩略图:</label><div class="col-lg-9"><input type="file" class="form-control" id="file" name="file"/></div></div>';
				html += '</form>';
			
			var addIndex = $.layer({
				type: 1,
				title: title ? title : " 新增信息",
				area: ['570px', '290px'],
				shade: [0.3, '#000'],
				shadeClose: false,
				shift: 'top', //从左动画弹出
				page: {
					html: html
				},
				btns : 2,
				btn: ['保存', '取消'],
				yes: function(){
					var loadIndex = layer.load('数据保存中');
					save(addIndex,loadIndex,grid);
		        }, 
		        no: function(){
		            layer.close(addIndex);
		        }
				
			});
		};
		/*
		 * 修改
		 */
		var modify = function(data,grid) {
			add("修改信息",grid);
			setData(data);
		};
		/*
		 *设置值
		 */
		var setData = function(item) {
			$("#id").val(item.id);
			$("#name").val(item.name);
			$("#url").val(item.url);
			$("#parentRegionsId").val(item.regionsId);
			$("#parentRegionsName").val(item.regionName);
		}
		/*
		 *   保存数据 id存在则为修改 否则为新增
		 */
		var save = function(addIndex,loadIndex,grid) {
			if (!validate()) {
				layer.close(loadIndex);
				return false;
			}
			$("#form1").ajaxSubmit({
					type : 'post',
                    url : url + '/update',
                    contentType: "multipart/form-data; charset=utf-8", 
                    success : function(data) {
                    	layer.close(addIndex);
        				layer.close(loadIndex);
        				grid.grid('refresh');
                    },
                    error : function(XmlHttpRequest, textStatus, errorThrown) {
                    	layer.close(addIndex);
        				layer.close(loadIndex);
                    }
                });
			/* $.post(url + '/update', $("#form1").serialize()).done(function(data) {
				layer.close(addIndex);
				layer.close(loadIndex);
				grid.grid('refresh');
			}); */
		};
		/*
		 *   保存数据 id存在则为修改 否则为新增
		 */
		var deleteData = function(id,loadIndex,grid) {
			$.post(url + '/delete', {"id":id}).done(function(data) {
				layer.close(loadIndex);
				grid.grid('refresh');
			});
		};
		
		/**
		 * 数据验证
		 */
		var validate = function() {
			if (!$("#name").val()) {
				layer.tips('请输入区域名称','#name',{time:3,guide:1});
				return false;
			}
			return true;
		};
	});
	/**
	 *预览
	 */
	var reviewImg = function(url){
		$.layer({
			title : false,
			type: 1,
			area: ['390px', '230px'],
			//shade: [0.3, '#000'],
			shadeClose: true,
			shift: 'left', //从左动画弹出
			page: {
				html: '<img src="'+url+'" alt="" width="390" height="230">'
			}
		});
	}
</script>
<style type="text/css">
<!--
 .form-group {margin-left : 0;margin-right: 0;}
 .xuboxPageHtml{width: 550px;}
-->
</style>