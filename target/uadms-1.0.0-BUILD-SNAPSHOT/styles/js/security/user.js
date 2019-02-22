var userManager = function() {
	var baseUrl = contextPath + '/security/user/';
	var orgUrl = contextPath + '/security/org/list?pageNo=';
	var roleUrl = contextPath + '/security/role/list';
	
	var id = $("#id");
	var userName = $("#userName");
	var fullName = $("#fullName");
	var email = $("#email");
	var age = $("#age");
	var orgName = $("#orgName");
	var address = $("#address");
	var sex = $("[name='sex']");
	var enabled = $("[name='enabled']");
	var roleId = $("#roleId");;
	var password = $("#password");
	/**
	 * 检查用户名是否已使用
	 */
	var checkUserName = function(){
		$.get(baseUrl + "checkUserName",{"userName":$("#userName").val()}).done(function(data){
			if(data == 1){
				layer.tips('用户名已被使用','#userName',{time:3,guide:0});
			}
		})
	}
	/*
	 *新增
	 */
	var add = function(title) {
		$.get(roleUrl,null,null,"json").done(function(data){
			var roleHtml = '';
			$.each(data.result,function(){
				roleHtml += '<option value="'+this.id+'">'+this.name+'('+this.description+')</option>';
			});
			var html = '<form class="form-horizontal" id="form1" role="form" style="padding:10px;"><div class="form-group"><label class="col-lg-3 control-label">用户名:</label><div class="col-lg-9"><input type="text" class="form-control" name="userName" id="userName" onchange="userManager().checkUserName()"><span class="required">*</span></div></div>';
				html += '<input id="id" value="" name="id" type="hidden">';
				html += '<div class="form-group"><label class="col-lg-3 control-label">姓名:</label><div class="col-lg-9"><input type="text" class="form-control" name="fullName" id="fullName"><span class="required">*</span></div></div>';
				html += '<div class="form-group"><label for="sex" class="col-lg-3 control-label">性别:</label><div class="col-lg-9"><label class="radio-inline"><input type="radio" name="sex" id="sex1" checked="checked" value="1"> 男</label><label class="radio-inline"><input type="radio" name="sex" id="sex2" value="2"> 女</label></div></div>';
				html += '<div class="form-group"><label for="email" class="col-lg-3 control-label">邮箱:</label><div class="col-lg-9"><input type="text" class="form-control" name="email" id="email"><span class="required">*</span></div></div>';
				html += '<div class="form-group"><label for="password" class="col-lg-3 control-label">密码:</label><div class="col-lg-9"><input type="password" class="form-control" name="password" id="password" value="123456" title="密码默认值为：123456"><span class="required">*</span></div></div>';
				html += '<div class="form-group"><label for="roleName" class="col-lg-3 control-label">角色:</label><div class="col-lg-9"><select class="form-control" name="roleId" name="roleId">'+roleHtml+'</select><span class="required">*</span></div></div>';
				html += '<div class="form-group"><label for="orgName" class="col-lg-3 control-label">所属部门:</label><div class="col-lg-9"><input type="hidden" id="orgId" name="orgId"/><input type="text" class="form-control" id="orgName" readonly style="width:67%;"><button type="button" class="btn btn-primary" onclick="openOrg()" style="float:left;">获取部门信息</button><span class="required">*</span></div></div>';
				html += '<div class="form-group"><label class="col-lg-3 control-label">是否启用:</label><div class="col-lg-9"><label class="radio-inline"><input type="radio" name="enabled" checked="checked" id="enabled1" value="1"> 是</label><label class="radio-inline"><input type="radio" name="enabled" id="enabled2" value="2"> 否</label></div></div>';
				html += '<div class="form-group"><label class="col-lg-3 control-label">年龄:</label><div class="col-lg-9"><input type="text" class="form-control" name="age" id="age"></div></div>';
				html += '<div class="form-group"><label class="col-lg-3 control-label">地址:</label><div class="col-lg-9"><input type="text" class="form-control" name="address" id="address"></div></div></form>';
			
			var addIndex = $.layer({
				type: 1,
				title: title ? title : "新增用户信息",
				area: ['520px', '560px'],
				shade: [0.3, '#000'],
				shadeClose: false,
				shift: 'top', //从左动画弹出
				page: {
					html: html
				},
				btns : 2,
				btn: ['添加', '取消'],
				yes: function(){
					var loadIndex = layer.load('数据保存中');
					save(addIndex,loadIndex);
		        }, 
		        no: function(){
		            layer.close(addIndex);
		        }
				
			});
		});
	};
	/*
	 * 修改
	 */
	var modify = function(data) {
		add("修改用户信息");
		setData(data);
	};
	
	/*
	 * 重置密码
	 */
	var resetPassword = function(item, grid) {
		var dataGrid = grid;
		$.post(baseUrl + 'resetPassword.koala?userId=' + item.id).done(function(data) {
			if (data.result == 'success') {
				dataGrid.message({
					type : 'success',
					content : '重置密码成功，初始密码为888888！'
				});
			} else {
				dataGrid.message({
					type : 'error',
					content : data.actionError
				});
			}
		}).fail(function(data) {
			dataGrid.message({
				type : 'error',
				content : '重置密码失败'
			});
		});
	};
	/*
	 删除方法
	 */
	var deleteUser = function(users, grid) {
		var data = {};
		for (var i = 0, j = users.length; i < j; i++) {
			var user = users[i];
			data['users[' + i + '].id'] = user.id;
		}
		dataGrid = grid;
		$.post(baseUrl + 'delete', data).done(function(data) {
			if (data.result == 'success') {
				dataGrid.message({
					type : 'success',
					content : '删除成功'
				});
				dataGrid.grid('refresh');
			} else {
				dataGrid.message({
					type : 'error',
					content : data.actionError
				});
			}
		}).fail(function(data) {
			dataGrid.message({
				type : 'error',
				content : '删除失败'
			});
		});
	};

	/*
	 *设置值
	 */
	var setData = function(item) {
		console.info(id,userName,fullName,email,age.orgName,address,sex,enabled,password);
		id.val(item.id);
		userName.val(item.userName);
		fullName.val(item.fullName);
		email.val(item.email);
		age.val(item.age);
		orgName.val(item.orgName);
		address.val(item.address);
		sex.removeAttr("checked");
		enabled.removeAttr("checked");
		$("[name='sex'][value='"+item.sex+"']").attr("checked",'checked');
		$("[name='enabled'][value='"+item.enabled+"']").attr("checked",'checked');
		$("[name='roleId'][value='"+item.roleId+"']").attr("selected",'selected');
		password.val(null);
		password.next().remove();
		password.on('click', function(){
			layer.tips('为空表示不修改','#password',{time:3,guide:0});
		});
	}
	/*
	 *   保存数据 id存在则为修改 否则为新增
	 */
	var save = function(addIndex,loadIndex) {
		if (!validate()) {
			layer.close(loadIndex);
			return false;
		}
		var url = baseUrl + 'update';
		$.post(url, $("#form1").serialize()).done(function(data) {
			layer.close(addIndex);
			layer.close(loadIndex);
		});
	};
	/**
	 * 数据验证
	 */
	var validate = function() {
		if (!$("#userName").val()) {
			layer.tips('请输入用户名','#userName',{time:3,guide:1});
			return false;
		}
		if (!$("#fullName").val()) {
			layer.tips('请输入用户姓名','#fullName',{time:3,guide:1});
			return false;
		}
		if (!$("#email").val()) {
			layer.tips('请输入用户邮箱','#email',{time:3,guide:1});
			return false;
		}
		var reg = new RegExp(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/);
        if (!reg.test($("#email").val())) {
			layer.tips('邮箱不合法','#email',{time:3,guide:1});
			return false;
		}
		return true;
	}
	/**
	 * 打开部门信息
	 */
	var layerIndex;
	var openOrg = function(){
		var html = '<div role="form" class="form-horizontal" style="padding:10px;"><table class="table table-responsive table-bordered table-hover table-striped" style="width:530px;" align="center">';
		html += '<thead><tr><td align=center width=45% class="td_list_1" nowrap>部门名称</td><td align=center width=45% class="td_list_1" nowrap>上级部门名称</td><td align=center width=10% class="td_list_1" nowrap>操作</td></tr></thead>';
		html += '<tbody id="orgDataTable"></tbody>'
		html += '</table></div>';
		layerIndex = $.layer({
			type: 1,
			title: "选择部门",
			area: ['550px', '600px'],
			shade: [0.3, '#000'],
			shift: 'left', //从左动画弹出
			page: {
				html: html
			},
			btns:1,
			btn: ["取消"]
		});
		loadOrgData(0);
	}
	/**
	 * 加载部门信息
	 */
	var loadOrgData = function(pageNo){
		$.get(orgUrl + pageNo,null,null,"json").done(function(data){
			var html = '';
			console.info($('#form1 > #orgName'));
			$('#form1 > #orgName').val(22);
			$.each(data.result,function(){
				html += '<tr><td class="td_list_2" align=left nowrap>'+this.name+'&nbsp;</td><td class="td_list_2" align=left nowrap>'+(this.parentOrgName ? this.parentOrgName : "")+'&nbsp;</td>';
				html += '<td class="td_list_2" align=left nowrap><a href="javascript:void(0)" class="glyphicon glyphicon-ok" title="选择" onclick="$(\'#orgName\').val(\''+this.name+'\');$(\'#orgId\').val(\''+this.id+'\');layer.close('+layerIndex+');"></a></td></tr>';
			});
			
			//分页
			html += '<tr class="table table-responsive table-bordered table-hover table-striped grid"><td colspan="3"><div class="records">显示:'+(data.pageNo)+'/'+(data.count)+', 共'+data.totalRecord+'条记录。 每页显示:'+data.pageSize+'条</div><div class="btn-group pages"><ul class="pagination pull-left">';
			if(data.pageNo != 1){
				html += '<li data-role="firstPage"><a href="#" onclick="loadOrgData(1)">«</a></li><li data-role="prev"><a href="#" onclick="loadOrgData('+(data.pageNo-1)+')">‹</a></li>';
			}else{
				html += '<li data-role="firstPage" class="disabled"><a href="#">«</a></li><li data-role="prev" class="disabled"><a href="#">‹</a></li>';
			}
			if(data.pageNo != data.count){
				html += '<li data-role="next"><a href="#" onclick="loadOrgData('+(data.pageNo+1)+')">›</a></li><li data-role="lastPage"><a href="#" onclick="loadOrgData('+(data.count-1)+')">»</a></li>';
			}else{
				html += '<li data-role="next" class="disabled"><a href="#">›</a></li><li data-role="lastPage" class="disabled"><a href="#">»</a></li>';
			}
			html += '</ul></div></td></tr>';
			
			$("#orgDataTable").html(html);
		});
	};
	
	return {
		add : add,
		modify : modify,
		deleteUser : deleteUser,
		resetPassword : resetPassword,
		openOrg : openOrg,
		checkUserName : checkUserName
	};
}; 