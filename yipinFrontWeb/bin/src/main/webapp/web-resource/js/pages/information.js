/**
 * Creator: yipin
 * Describe: 修改密码
 */
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
	//修改和提交切换
	yipin.change=function(){
		$("#changeBtn").on("click",function(){
			$(".J_detail").hide();
			$(".my_data input,.my_data select").show();
			$(".gender").show();
			$("#saveBtn").addClass("show");
			$(".J_tip").show();
			$(this).hide();
		});
	}
	 // 配置日历插件
    yipin.Date=function () {
        var $startTime = $('#feteDay'),
            $dateTip = $('#feteDayTip');
        // 设置 起始日期
        $startTime.on('click', function () {
            WdatePicker({
                "isShowClear": true, // 是否显示清空按钮
                "readOnly": true, // 是否只读
                "doubleCalendar": false                    // 双月历
            });
        });
    }
    yipin.ajaxValidator = {
        email: {valid: false, complete: false}
    };
	// 验证
    yipin.checkCode = function () {
       $.formValidator.initConfig({
				validatorGroup: '1',
				formID: 'myFindForm',
				theme: 'yto',
				errorFocus: false
		});
		//姓名
        $("#name").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":'',
            "empty": true,
            "onEmpty": ''
        }).inputValidator({
                "max":40,
                "onErrorMax":'请输入小于 20个字符组成的姓名'
            }).functionValidator({
                fun: function (val, el) {
                	 var val=val.replace(/\s+/g,"");
                     $("#name").val(val);
               	 var reg=/(^\s+)|(\s+$)/;
                   if(reg.test(val)){
                   	return '前后不能输入空格';
                   	
                   }  
                   if(val.length>20){
                   	 return '请输入小于 20个字符组成的姓名';
                   }
               }
           });;
		//手机
        $("#cellphone").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
        }).inputValidator({
                "min":11,
                "max":11,
                "onErrorMin":'请输入正确的手机号码',
                "onErrorMax":'请输入正确的手机号码'
            }).functionValidator({
                fun: function (val, el) {
                    //val = $.trim(val);
                    //验证
                    var reg=/^[0-9]{11}$/;
                    if(!reg.test(val)){
                    	return '请输入正确的手机号码';
                    }  
                }
            });;
      //固话
        $("#dixphone").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":'',
            "onEmpty":"",
            "empty":true
        }).inputValidator({
                "max":13,
                "onErrorMax":'请输入正确的固定电话'
            }).functionValidator({
                fun: function (val, el) {
                    //val = $.trim(val);
                    //验证
                    var reg=/^([0-9]{3,4}-)?[0-9]{7,8}$/;
                    if(!reg.test(val)){
                    	return '请输入正确的固定电话';
                    }  
                }
            });
		//详细地址
        $("#detailDress").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":'',
            "onEmpty":"",
            "empty":true
        }).inputValidator({
                "max":100,
                "onErrorMax":'请填写 0 — 50 个字符组成的地址'
            }).functionValidator({
                fun: function (val, el) {
                	 var val=val.replace(/\s+/g,"");
                     $("#detailDress").val(val);
               	 var reg=/(^\s+)|(\s+$)/;
                   if(reg.test(val)){
                   	return '前后不能输入空格';
                   	
                   }  
                   if(val.length>50){
                     	 return '请填写 0 — 50 个字符组成的地址';
                     }
               }
           });;
		//邮箱
        $("#mail").formValidator({
            "onShow":'',
            "onFocus":'',
            "onCorrect":''
        }).inputValidator({
            "min":1,
            "onErrorMin":'邮箱不能为空'
        }).regexValidator({
			regExp:"email",
			dataType:"enum",
			onError:"邮箱格式不正确"
		}).functionValidator({
            fun: function() {
            	 var email = $.trim($("#mail").val());
            	 yipin.ajaxValidator["email"]["complete"] = false;
            	 yipin.ajaxValidator["email"]["valid"] = false;
            	$.ajax({
            		type:"post",
            		url: _ctxPath+'/user/user-checkEmailVaild.htm',
            		data:{
            			"userinfo.email":email
            		},
            		success:function(data){
            			if(data.info=='true'){
            				 yipin.ajaxValidator["email"]["valid"] = true;
            			}
            			else if(data.info=='false'){
            				yipin.showIcon.error($("#mailTip"), '该邮箱已被注册！');
            				return false;
            			}
            			else if(data.info=='error'){
            				yipin.showIcon.error($("#mailTip"), '操作失败，稍后重试！');
            				return false;
            			}
            		},
            		complete: function(){
                        yipin.ajaxValidator["email"]["complete"] = true;
                    }
            		
            	});
             }
         });
        


        
    };
    
    var prov = $("#area_prov").val(), city = $("#area_city").val(), district = $("#area_region").val();
    var linkage = new linkageSelect();
    linkage.init({
        "oneSel":['#province','请选择',prov],
        "twoSel":['#city', '请选择',city],
        "threeSel":['#region', '请选择',district]
    });
    
	yipin.Form=function(){
		$("#saveBtn").on("click",function(){
			var index=0;
			//省
			if($('#province').val()==0){
				yipin.showIcon.error($("#dressTip"), '请选择省份');
				index++;
			}
			// 市
			if($('#province').find("option:selected").text()!="请选择"){
				if($("#city").val()==0){
					yipin.showIcon.error($("#dressTip"), '请选择市');
					index++;
				}
			}
			// 区
			if($("#city").find("option:selected").text()!="请选择"){
				if($("#region").val()==0){
					yipin.showIcon.error($("#dressTip"), '请选择区');
					index++;
				}
			}
			setInterval(function(){
				cancelF($('#province'),$("#city"),$("#region"),$("#dressTip"));
			},1000);
			//验证表单
			$("#regionId").val($("#region").val());
            var result = $.formValidator.pageIsValid('1');
			if(!result||index>0){
				return false;
			}
			 $.dialog({
					"title":false,
					"lock":true,
					"content":"<P class='dialogDiv'>用户资料修改成功,请重新登陆才生效</p>",
//					"okValue":"确定",
//					"ok":true,
					"time":2000
				});
			$(".d-titleBar").remove();
			//存在ajax 等待ajax响应完成
			var timer = setInterval(function(){
                var complete = true,valid = true;
                for(prop in yipin.ajaxValidator){
                    if(!yipin.ajaxValidator[prop]["complete"]){
                        complete = false;
                    }
                    if(!yipin.ajaxValidator[prop]["valid"]){
                        valid = false;
                    }
                }
                if(complete) {                 //AJAX已验证,
                    clearInterval(timer);      //去除定时循环
                }else {
                    return;                    //未验证,继续
                }

                if(!valid) {   //验证不通过,跳出
                    return;
                }
    			$("#myDataForm").submit();
            },500);
		});
	}
	//20130813 add 去除验证提示
	function cancelF(province,city,area,tip){
		var tip=tip;
		if(province.val()!=0){
			if(tip.find("span").text()=="请选择省份"){
				tip.html('');
			}
		}
		if(city.val()!=0){
			if(tip.find("span").text()=="请选择市"){
				tip.html('');
			}
		}
		if(area.val()!=0){
			if(tip.find("span").text()=="请选择区"){
				tip.html('');
			}
		}
	}
    // 启动配置
    yipin.init = (function () {
	    yipin.change();
		yipin.checkCode();
		yipin.Form();
		yipin.Date();
		if($("input").size()){
			$("input").inputFocus();
		}
       $("#myFindText").input();
    })();
});



	
