<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<div data-role="configGrid"></div>
<script>
	$(function() {
		var tabData = $('[data-role="configGrid"]').closest('.tab-pane.active').data();
		var columns = [{
			title : "功能名称",
			name : "name",
			width : 100
		}, {
			title : "抓取地址",
			name : "url",
			width : 300
		}, {
			title : "类型",
			name : "code",
			width : 80
		}, {
			title : "时间表达式",
			name : "cycle",
			width : 150
		}, {
			title : "菜单地址",
			name : "menuUrl",
			width : 150
		},{
			title:'状态',
			name:'status',
			width:150,
			render : function(item, name, index) {
				return item[name] == 1 ? '<span class="glyphicon" style="color:#5CB85C;margin-left:15px;">正常</span>' : '<span class="glyphicon" style="color:#D9534F;margin-left:15px;">暂停</span>';
			}
		}];
		var getButtons = function() {
			return [{
				content : '<button class="btn btn-primary" type="button"><span class="glyphicon glyphicon-plus"><span>添加</button>',
				action : 'add'
			}, {
				content : '<button class="btn btn-success" type="button"><span class="glyphicon glyphicon-edit"><span>修改</button>',
				action : 'modify'
			}/* , {
				content : '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>删除</button>',
				action : 'delete'
			} */, {
				content : '<button class="btn btn-danger" type="button"><span class="glyphicon glyphicon-remove"><span>暂停或恢复</button>',
				action : 'pause'
			}, {
				content : '<button class="btn btn-info" type="button"><span class="glyphicon glyphicon-th-large"><span>立即运行</button>',
				action : 'run'
			}];
		};
		var url = contextPath + '/config';
		$('[data-role="configGrid"]').off().grid({
			identity : 'id',
			columns : columns,
			buttons : getButtons(),
			querys : [{
				title : '功能名称',
				value : 'name'
			}, {
				title : '功能类型',
				value : 'code'
			}],
			url : url
		}).on({
			'add' : function() {
				add($(this));
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
			'delete' : function(event, data) {
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
			},
			'pause' : function(event, data) {
				var indexs = data.data;
				var $this = $(this);
				if (indexs.length == 0) {
					$this.message({
						type : 'warning',
						content : '请选择一条记录进行操作'
					});
					return;
				}
				pause(data.item,$this);
			},
			'run' : function(event, data) {
				var indexs = data.data;
				var $this = $(this);
				if (indexs.length == 0) {
					$this.message({
						type : 'warning',
						content : '请选择一条记录进行立即运行'
					});
					return;
				}
				run(data.item,$this);
			}
		});
		
		var add = function(title,grid) {
			var html = '<form class="form-horizontal" id="form1" role="form" style="padding:10px;">';
				html += '<input id="id" value="" name="id" type="hidden">';
				html += '<div class="form-group"><label for="name" class="col-sm-2 control-label">功能名称:</label><div class="col-sm-10"><input type="text" class="form-control" name="name" id="name"></div></div>';
				html += '<div class="form-group"><label for="url" class="col-sm-2 control-label">数据地址:</label><div class="col-sm-10"><input type="text" class="form-control" name="url" id="url"></div></div>';
				html += '<div class="form-group"><label for="code" class="col-sm-2 control-label">类型:</label><div class="col-sm-10"><input type="text" class="form-control" name="code" id="code"></div></div>';
				html += '<div class="form-group"><label for="cycle" class="col-sm-2 control-label">时间表达式:</label><div class="col-sm-10"><input type="text" class="form-control" name="cycle" id="cycle"></div></div>';
				html += '<div class="form-group"><label for="menuUrl" class="col-sm-2 control-label">系统地址:</label><div class="col-sm-10"><input type="text" class="form-control" name="menuUrl" id="menuUrl"></div></div></form>';
			
			var addIndex = $.layer({
				type: 1,
				title: title ? title : "修改信息",
				area: ['570px', '360px'],
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
			$("#code").val(item.code);
			$("#cycle").val(item.cycle);
			$("#url").val(item.url);
			$("#data").val(item.data);
			$("#menuUrl").val(item.menuUrl);
		}
		/*
		 *   保存数据 id存在则为修改 否则为新增
		 */
		var save = function(addIndex,loadIndex,grid) {
			if (!validate()) {
				layer.close(loadIndex);
				return false;
			}
			$.post(url + '/update', $("#form1").serialize()).done(function(data) {
				layer.close(addIndex);
				layer.close(loadIndex);
				grid.grid('refresh');
			});
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
				layer.tips('请输入功能名称','#name',{time:3,guide:1});
				return false;
			}
			if (!$("#url").val()) {
				layer.tips('请输入功能抓取地址','#url',{time:3,guide:1});
				return false;
			}
			if (!$("#code").val()) {
				layer.tips('请输入类型地址','#code',{time:3,guide:1});
				return false;
			}
	        if (!$("#cycle").val()) {
				layer.tips('请输入时间表达式','#cycle',{time:3,guide:1});
				return false;
			}
	        if (!$("#menuUrl").val()) {
				layer.tips('请输入菜单地址','#menuUrl',{time:3,guide:1});
				return false;
			}
			return true;
		};

		//暂停
		var pause = function(data,grid){
			pauseOrRun(data,"pause");
			grid.grid('refresh');
		}
		//立即运行
		var run = function(data,grid){
			pauseOrRun(data,"run");
			grid.grid('refresh');
		}
		//获取提交数据
		var pauseOrRun = function(item,fun){
			var loadIndex = layer.load('数据加载中....');

			var postData ="[";
			$.each(item,function(){
				postData += '{"id":'+this.id+',"status":'+this.status+',"code":'+this.code+'},'
			});
			if(postData.length > 1){
				postData = postData.substr(0,postData.length-1);
			}
			postData += "]";

			$.post(url+"/"+fun,{"data":postData}).done(function(data){
				layer.close(loadIndex);
			});
		}
		
	});
</script>
<style type="text/css">
<!--
 .form-group {margin-left : 0;margin-right: 0;}
 .xuboxPageHtml{width: 550px;}
-->
</style>