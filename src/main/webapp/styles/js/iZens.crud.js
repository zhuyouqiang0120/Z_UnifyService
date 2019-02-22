/**
 *  @author vector
 *  @date 2014-04-25
 */
$(document).ready(function(){
	
	$("#selectAllId").click(function(){
		iZens.selectAllId();
	});
	
	$("input[name='submit']").click(function(){
		iZens.onsubmit();
	});
	
	$("#selectAll").click(function(){
		var status = $(this).attr("checked");
		if(status) {
			$("input[name='orderIndexs']").attr("checked",true);
		} else {
			$("input[name='orderIndexs']").attr("checked",false);
		}    
	});
});
//--------------------------------------
//命名空间
//--------------------------------------
var Namespace = new Object();
Namespace.register = function(fullNS) {
	var arrNS = fullNS.split('.');
	var sEval = "";
	var sNS = "";

	for (var i = 0; i < arrNS.length; i++) {
		if (i != 0)
			sNS += ".";
		sNS += arrNS[i];
		sEval += "if( typeof(" + sNS + ") == 'undefined') " + sNS + " = new Object();";
	}
	if( sEval ) eval( sEval );
};

//--------------------------------------
//Zens 主类
//--------------------------------------
Namespace.register( "com.zens.utils.Zens" );
com.zens.utils.Zens = function(){
	this.version = "com.zens.utils.Zens, v1.0.0.0 (20140430) @vector";
};
com.zens.utils.Zens.prototype = {
	verify:{notEmpty:"该值不允许为空","email":"请填写正确的E-mail","isNaN":"该值只能是数字"},
	//全选中/取消全选中
	selectAllId : function(){
		var sad = $("#selectAllId");
		if(sad.attr("checked")){
			$("input[type='checkbox'][name=id]").attr("checked","checked");
		}else{
			$("input[type='checkbox'][name=id]").removeAttr("checked");
		}
	},
	//提交前验证
	onsubmit : function(){
		$("font[class='_tips_']").remove();
		//校验属性verify
		var verifys = $("[verify]");
		
		var bool = true;
		//遍历所有对象
		$.each(verifys,function(i,v){
			v = $(v);
			var attr = v.attr("verify");
			
			//值
			var val = v.val();
			if(val){
				if(attr == "email"){
					if(!val.match(/\w+@\w+.\w+/)){
						v.after("<font color='red' class='_tips_'>" + iZens.verify.email + "</font>");
						bool = false;
					}
				}else if(attr == 'isNaN'){
					if(!val.match(/[0-9]+/))
						v.after("<font color='red' class='_tips_'>" + iZens.verify.isNaN + "</font>");
					bool = false;
				}
			}else{
				v.after("<font color='red' class='_tips_'>" + iZens.verify.notEmpty + "</font>");
				bool = false;
			}
		});
		$("form").attr("onsubmit","return "+bool+";");
		if(bool && editwbox){
			editwbox.close();
		}
			
	},
	
	//删除
	_delete : function(){
		var mainForm = $("#mainForm");
		var action = mainForm.attr("action");
		var uri = action + "/batchDelete";
		mainForm.attr("action",uri);
		mainForm.attr("method","post");
		mainForm.submit();
	},
	//查看
	viewById : function(nodeid,uri){
 		$("#" + nodeid).wBox({
 			requestType: "iframe",
			iframeWH:{width:700,height:300},
			title:"查看",
			show: true,
			target:uri
		});
	},
	md5 : function(str,docId){
		$("#"+docId).val($.md5(str));
	},
	alert : function(id,tip,ctx){
		var wBox=$("#"+id).wBox({
			title:"友情提示",
			html : "<div style='width:200px;height:40px;background:url("+ctx+"/styles/js/icons.png) no-repeat -140px -2px;'><h5 style='padding-left:40px;'>"+tip+"</h5></div>"
		});
		wBox.showBox();
	}
};


//--------------------------------------
//全局实例
//--------------------------------------
var iZens = new com.zens.utils.Zens();