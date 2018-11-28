$(function(){
	// 定义一个全局类
	var yipin = yipin || {};
	
	//右侧挂件
	yipin.widget = function(){
		//拼装节点
		var dStr = '',
			ditem = $('.J_anchor');
		dStr = '<div class="d_widget" id="dWidget">' +
				'<div class="w_link"><ul>';
		ditem.each(function(){
			var _this = $(this),
				dAnc = _this.attr('id'),
				dName = _this.attr('data-name');
			if(dName!=""&&dName!=null){
				dStr += '<li><a href="#'+dAnc+'">'+dName+'</a></li>'
			}
		})
		dStr += '</ul></div>';
		if(dObject.dStatus){
			dStr += '<div class="w_beha"><a href="'+dObject.dUrl+'" class="buy">立即购买</a></div>';
		}else{
			dStr += '<div class="w_beha"><a href="'+dObject.dUrl+'" class="pre">预约购买</a></div>';
		}
		dStr += '<div class="w_gotop"><a href="javascript:;"></a></div>' +
				'</div>';
		$('.foot').after(dStr); //插入页面相应位置
		var Wdg = $('#dWidget');
		//非ie6浏览器执行
	    if($.browser.version != '6.0'){
	    	var wh = $(window).height(),
	    		WdgHt = Wdg.height();
	    	Wdg.css('top',(wh-WdgHt)/2); //给产品详情页右侧浮层垂直居中
	    }
		//判断到达指定区域显示
		$(window).scroll(function(){
			if($(window).scrollTop() > $('.focus').height()){
				Wdg.show(600);
			}else{
				Wdg.hide();
				$('.w_link li').removeClass('on');
			}
		})
		//选中当前项
		$('.w_link li').on('click',function(){
			var _this = $(this);
			_this.addClass('on').siblings().removeClass('on');
		})
		//返回顶部
		$('.w_gotop a').on('click',function(){
			$('html,body').animate({
				scrollTop : 0
			},1000)
		})
	}
	// 显示提示
	yipin.showIcon = {
		correct:function (el, text) {
			el.show().html('<span class="onError-txt">' + text + '</span>');
		},
		error:function (el, text) {
			el.show().html('<span class="onError-txt">' + text + '</span>');
		},
		show:function (el, text) {
			el.show().html('<span class="onError-txt">' + text + '</span>');
		}
	};
	// 验证
	yipin.checkCode = function () {
	   $.formValidator.initConfig({
				validatorGroup: '1',
				formID: 'mySpitslotForm',
				theme: 'yto',
				errorFocus: false
		});
		//发现吐槽内容
		$("#spitslotContent").formValidator({
			"onShow":'',
			"onFocus":'',
			"onCorrect":''
		}).inputValidator({
				"min":1,
				"max":2000,
				"onErrorMin":'请输入1-1000字内容'
			}).functionValidator({
					fun: function(val, el) {
						var val= $.trim(val);
						if(val.length<=0||val==$("#spitslotContent").attr("data-default")){
							return '内容不能为空';
						}
						if(val.length>1000){
                        	return '最大不能超过1000个字符';
                        }
					}
				});
	};
	var types = $("#types").val();
	//返回值
	yipin.valSpitslotBack = function(){
		$("#spitslotContent").on({
			'focus': function(){
				$.ajax({
					type:'POST',
					dataType : "json",
					url: _ctxPath+'/suggest/checkIsLogin.htm',
					data:{'suggest.type':types},
					success:function(data){
						if(data!=null){
							if(data.code == "false"){
							$("#spitslot").show();
							//$("#spitslotContent").val(data.info);
						   }
						}
					}
				});
			},
			'blur':function(){
			}
		});
	}
	
	//Ajax提交发现吐槽
	yipin.spitlotAjaxForm=function (){
		$("#sendSpitslot").on("click",function(){
			var bFlag = true;
			//验证表单
			var result = $.formValidator.pageIsValid('1');
			if(!result){
				return false;
			}else{
				$.ajax({
    		        type:'POST',
    		        dataType : "json",
    		        url: _ctxPath+'/suggest/checkIsLogin.htm',
    		        data:{'suggest.type':types},
    		        success:function(data){
    		        	if(data!=null){
    		        			if(data.code == "true"&&bFlag){
        		        			bFlag = false;
    		        			$.ajax({
    								type : 'POST',
    								url : _ctxPath+'/suggest/ajaxSaveSuggest.htm',
    								data:$("#mySpitslotForm").serialize(),
    								async:false,
    								success : function(data) {
    									$.dialog({
    										title: false,
    										content: data.info,
    										time: 3000
    									});
    									$(".d-close").hide();
    										window.setTimeout(function(){window.location.href=_ctxPath+"/suggest/searchSuggest-"+types+".htm"},3000);
    									},error:function (textStatus, errorThrown) {
    										$.dialog({
    											title: false,
    											content: "服务器异常，请稍候重试……",
    											time: 3000,
    											lock:true
    										});
    										$(".d-close").hide();
    										window.setTimeout(function(){window.location.href=_ctxPath+"/suggest/searchSuggest-"+types+".htm"},3000);
    									}
    							});
    		        			bFlag = true;
                           }else{
                        	   window.location.href=_ctxPath+"/show-login.htm?index=login&targetUrl="+encodeURI(location.href);
                           }
    		        	}
                    }
    		    });
		}
			/*if(!result){
				return false;
			}
			$.ajax({
				type : 'POST',
				url : _ctxPath+'/suggest/ajaxSaveSuggest.htm',
				data:$("#mySpitslotForm").serialize(),
				success : function(data) {
					if(data){
						$.dialog({
							title: false,
							content: data.info,
							time: 3000
						});
						$(".d-close").hide();
					}else{
						$("#spitslot").show();
					}
					}
			});*/
		});
	}
	// 启动配置
	yipin.init = (function(){
		yipin.widget();
		yipin.checkCode();
		yipin.valSpitslotBack();
		yipin.spitlotAjaxForm();
		$('.tc_con textarea').input();
	})()
})