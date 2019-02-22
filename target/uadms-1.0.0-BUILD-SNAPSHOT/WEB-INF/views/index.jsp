<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <title>Zens UnifyInterfaceService</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${ctx}/styles/bootstrap/3.2.0/css/bootstrap.min.css"   rel="stylesheet">
    <link href="${ctx}/styles/css/index.css" rel="stylesheet">
    <link href="${ctx}/styles/css/koala.css" rel="stylesheet">
    <script>
        var contextPath = '${ctx}';
    </script>
    <style type="text/css">
    	.col-xs-10,.g-mainc{width:80%}
    </style>
</head>
<body>
	<div class="g-body">
	    <div class="col-xs-2 g-sidec">
	        <ul class="nav nav-stacked first-level-menu">
	       		
	        </ul>
	    </div>
	    <div class="col-xs-10 g-mainc">
	        <ul class="nav nav-tabs" id="navTabs">
	            <li class="active"><a href="#home" data-toggle="tab">主页</a></li>
	        </ul>
	        <div class="tab-content" id="tabContent">
	            <div id="home" class="tab-pane active"></div>
	        </div>
	    </div>
	</div>
	<script src="${ctx}/styles/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${ctx}/styles/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${ctx}/styles/layer/layer.min.js" type="text/javascript"></script>
	<script src="${ctx}/styles/js/koala-ui.plugin.js" type="text/javascript"></script>	
	<script src="${ctx}/styles/js/index.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			$.get(contextPath + "/config/getAll",null,null,"json").done(function(data){
                var $li = $('<li class="folder"><a data-toggle="collapse" href="#menuMark-1"><span class="glyphicon  glyphicon-list-alt"></span>&nbsp;管理菜单&nbsp;'+
                      '<i class="glyphicon glyphicon-chevron-left" style=" float: right;font-size: 12px;position: relative;right: 8px;top: 3px;"></i></a><ul id="menuMark-1" class="second-level-menu in"></ul></li>');
                $('.first-level-menu').append($li);
                    
                renderSubMenu(data, $li);
                  
                /*
                * 菜单收缩样式变化
                 */
                var firstLevelMenu = $('.first-level-menu');
                firstLevelMenu.find('[data-toggle="collapse"]').each(function(){
                    var $this = $(this);
                    firstLevelMenu.find($(this).attr('href')).on({
                    	'shown.bs.collapse': function(e){
                            $this.find('i:last').addClass('glyphicon-chevron-left').removeClass('glyphicon-chevron-right');
                        },
                        'hidden.bs.collapse': function(e){
                            $this.find('i:last').removeClass('glyphicon-chevron-left').addClass('glyphicon-chevron-right');
                        }
                    });
                });
			});
			var renderSubMenu = function(data, $menu){
				$.each(data, function(){
					if(this.menuType == "2"){
                        var $li = $('<li class="folder"><a data-toggle="collapse" href="#menuMark'+this.id+'"><span class="glyphicon  glyphicon-list-alt"></span>&nbsp;'+this.name+'&nbsp;'+
                            '<i class="glyphicon glyphicon-chevron-right pull-right" style="position: relative; right: 12px;font-size: 12px;"></i></a><ul id="menuMark'+this.id+'" class="second-level-menu collapse"></ul></li>');
                        $li.appendTo($menu.find('.second-level-menu:first')).find('a').css('padding-left', 2*18+'px');
                        renderSubMenu(this.children, $li);
                    }else{
                        var $li = $(' <li class="submenu" data-role="openTab" data-target="'+this.menuUrl+'" data-title="'+this.name+'" ' +
                            'data-mark="menuMark'+this.id+'"><a ><span class="glyphicon  glyphicon-list-alt"></span>&nbsp;'+this.name+'</a></li>');
                        $li.appendTo($menu.find('.second-level-menu:first')).find('a').css('padding-left', 2*18+'px');
                    }
				});
                 $menu.find('[data-toggle="collapse"]').each(function(){
                     var $this = $(this);
                     $menu.find($(this).attr('href')).on({
                         'shown.bs.collapse': function(e){
                         	e.stopPropagation();
                         	e.preventDefault();
                             $this.find('i:last').addClass('glyphicon-chevron-left').removeClass('glyphicon-chevron-right');
                         },
                         'hidden.bs.collapse': function(e){
                         	e.stopPropagation();
                         	e.preventDefault();
                             $this.find('i:last').removeClass('glyphicon-chevron-left').addClass('glyphicon-chevron-right');
                         }
                     });
                 });
				$menu.find('li.submenu').on('click', function(){
					var $this = $(this);
					$('.first-level-menu').find('li').each(function(){
						var $menuLi = $(this);
						$menuLi.hasClass('active') && $menuLi.removeClass('active').parent().parent().removeClass('active');
					});
					$this.addClass('active').parents().filter('.folder').addClass('active');
					var target = $this.data('target');
					var title = $this.data('title');
					var mark = $this.data('mark');
					if(target && title && mark ){
						openTab(target, title, mark);
					}
				});
			};
		});
	</script>
</body>
</html>