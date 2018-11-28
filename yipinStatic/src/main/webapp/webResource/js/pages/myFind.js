/**
 * Creator: yipin
 * Describe: 修改密码
 */
var types = $("#types").val();
//我的发现页面内容修改
   if(types==0){
	document.title = "我的发现"
   	$(".ucr_hd").html("我的发现");
   	$("#tilt").html("发现内容/管理员回复");
   	$("#sub").html("我的发现");
   	$("#myFindText").attr('data-default','在“一城一品”的品质之旅还未尽兴？请写下您想要的特色商品或者为我们推荐特色商品，全国6000家分公司将努力为您搜寻，只要目标出现您将获得高额积分，赶快行动起来吧。');
   	$("#sendBtn").html("分享品质生活<br /><i>提交我的发现</i>");
   	$("#sendBtn").addClass("sendFindBtn");
   }
$(function () {
    // 定义一个全局类
    var yipin = yipin || {};
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
				formID: 'mySuggestForm',
				theme: 'yto',
				errorFocus: false
		});
		//发现吐槽内容
        $("#myFindText").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
        }).inputValidator({
                "min":1,
                "max":2000,
                "onErrorMin":'请输入1-1000字内容',
                "onErrorMax":'最大不能超过1000个字符'
            }).functionValidator({
                    fun: function(val, el) {
                        var val= $.trim(val);
                        var reg=/(^\s+)|(\s+$)/;
                        if(val.length<=0||val==$("#myFindText").attr("data-default")){
                            return '内容不能为空或者全部是空格';
                        }
                        if(val.length>1000){
                        	return '最大不能超过1000个字符';
                        }
                    }
                });
    };
    //返回值
    yipin.valBack = function(){
    	$("#myFindText").on({
    		'focus': function(){
    			$.ajax({
    		        type:'POST',
    		        dataType : "json",
    		        url: _ctxPath+'/suggest/checkIsLogin.htm',
    		        data:{'suggest.type':types},
    		        success:function(data){
    		        	if(data!=null){
    		        		if(data.code == "false"){
    		        			var url = location.href;
    		        			if(url.indexOf("myFind.htm") > 0||url.indexOf("mySpitslot.htm") > 0){
    		        				$(".find").show();
    		        			}
       		        		 return false;
                           }
    		        	}
                    }
    		    });
    		},
    		'blur':function(){
    			$("#myFindText").attr("data-default")
    		}
    	});
    }
	
	  //Ajax提交发现吐槽
    yipin.ajaxForm=function (){
    	$("#sendBtn").on("click",function(){
    		var bFlag = true;
			//验证表单
            var result = $.formValidator.pageIsValid('1');
			if(!result){
				return false;
			}else{
				$('#sendBtn').unbind("click");
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
    								data:$("#mySuggestForm").serialize(),
    								success : function(data) {
    									$.dialog({
    										title: false,
    										content: data.info,
    										time: 3000
    									});
    									$(".d-close").hide();
    										window.setTimeout(function(){window.location.href=_ctxPath+"/suggest/searchSuggest-"+types+".htm"},3000);
    										bFlag = true;
    										$('#sendBtn').on("click");
    									},error:function (textStatus, errorThrown) {
    										$.dialog({
    											title: false,
    											content: "服务器异常，请稍候重试……",
    											time: 3000,
    											lock:true
    										});
    										$(".d-close").hide();
    										window.setTimeout(function(){window.location.href=_ctxPath+"/suggest/searchSuggest-"+types+".htm"},3000);
    										bFlag = true;
    										$('#sendBtn').on("click");
    									}
    							});
                           }else{
                        	   window.location.href=_ctxPath+"/show-login.htm?index=login"
                           }
    		        	}
                    }
    		    });
		}
		});
    }
    
    // 启动配置
    yipin.init = (function () {
		yipin.checkCode();
		//yipin.Form();
		yipin.valBack();
		yipin.ajaxForm();
     //修改页面部分信息
       $("#myFindText").input();
	   $("#myFindText").textarea();
    })();
    
  
});