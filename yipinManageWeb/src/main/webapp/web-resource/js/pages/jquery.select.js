/* 
 * 基于jquery级联选择
 * target: 下一级的jquery选择器
 * urlOrData: ajax请求的url或用于刷选的Data
 * options: 配置
 */
;(function($) {
    var defaultOptions = {
        after: null,
        before: null,
        usePost: false,
        defaultValue: null,
        filter: null,
        preValue: '-1',
        preText: '请选择',
        groupSort: 'desc', 
        order: 'name asc',
        params: {},
        dataMap: {'text': 'text', 'value': 'value', 'group': 'group'}
    };
    var missGroup = '__MISS__';
    var dataToOption = function(data, op) {
        var d = [], vs = [], hasFilter = op.filter && $.isFunction(op.filter);
        $.each(data, function(i, v) {
            if(!hasFilter || (hasFilter && op.filter(v, i))) {
                if($.inArray(v[op.dataMap.value], vs) == -1) {
                    d.push($.extend({}, v));
                    vs.push(v[op.dataMap.value]);
                }
            }
        });
        if(op.order) {
            var sp = op.order.split(' '), col = sp[0], sort = sp[1].toLowerCase() == 'asc' ? 1 : -1;
            d.sort(function(a, b) {
                if(a[col] > b[col]) {
                    return sort;
                } else if(a[col] < b[col]) {
                    return -sort;
                } else {
                    return 0;
                }
            });
        }
        var ops = '', gps = groupData(mapData(d, op.dataMap));
        var createOption = function(items) {
            var _ops = '';
            if(!$.isArray(items)) {
                items = [items];
            }
            $.each(items, function(i, v) {
            	_ops += '<option value="' + v['value'] + '">' + v['text'] + '</option>';
            });
            return _ops;
        };
        var createGroup = function(group, options) {
            return '<optgroup label="' + group + '"></optgroup>' + options;
        };
        if(op.preValue || op.preText) {
        	ops += createOption({'value': op.preValue, 'text': op.preText});
        }
        if(gps[missGroup] != undefined) {
            ops += createOption(gps[missGroup]);
            delete gps[missGroup];
        }
        if(op.groupSort == 'desc') {
            gps['keys'].sort().reverse();
        } else if(op.groupSort == 'asc') {
            gps['keys'].sort();
        }
        $.each(gps['keys'], function(i, v) {
            ops += createGroup(v, createOption(gps[v]));
        });
        return ops;
    };
    var mapData = function(data, map) { 
        $.each(data, function(i, v) {
            $.each(map, function(j, k) {
                if(v[j] == undefined) {
                    data[i][j] = v[k] == undefined ? '' : v[k];
                    delete data[i][k];
                }
            });
        });
        return data;
    };
    var groupData = function(data) {
        var gps = {};
        gps['keys'] = [];
        var pushData = function(group, item) {
            if(gps[group] == undefined) {
                gps[group] = [];
            }
            gps[group].push(item);
        };
        var pushKey = function(key) {
            if($.inArray(key, gps['keys']) == -1) {
                gps['keys'].push(key);
            }
        };
        $.each(data, function(i, v) {
            if(v['group']) {
                pushKey(v['group']);
                pushData(v['group'], v);
            } else {
                pushData(missGroup, v);
            }
        });
        return gps;
    };
    $.fn.fillselect = function(urlOrData, options) {
        return this.each(function() {
            var $t = $(this), op, dataReadyCallback, ajaxXHR = null;
            op = $.extend(true, {}, defaultOptions, options);
            $t.data('fillselectOptions', op);
            if(op.before && $.isFunction(op.before)) {
                op.before.apply($t);
            }
            dataReadyCallback = function(data) {
                $t.html(dataToOption(data, op));
                if(op.defaultValue) {
                    $t.val(op.defaultValue);
                }
                if(op.after && $.isFunction(op.after)) {
                    op.after.apply($t);
                }
                $t.trigger('change');
            };
            if(typeof urlOrData == 'string') {
                if(ajaxXHR) {
                    ajaxXHR.abort();
                }
                ajaxXHR = $.ajax({
                    'url': urlOrData,
                    'type': op.usePost ? 'post' : 'get',
                    'data': op.params,
                    'dataType': 'json',
                    'success': function(data) {
                        ajaxXHR = null;
                        var data = eval("("+data.info+")");
                        if(data.status == '200') {
                            dataReadyCallback(data.data || []);
                        }
                    },
                    'error': function() {
                        ajaxXHR = null;
                    }
                });
            } else {
                dataReadyCallback(urlOrData);
            }
        });
    };
    $.fn.chainselect = function(target, urlOrData, options) {
        return this.each(function() {
            $(this).bind('change', function() {
                var $t = $(this), op;
                op = $.extend(true, {}, defaultOptions , options);
//                console.log($t.val());
//                op.params[$t.attr('name') || $t.attr('id')] = $t.val();
                op.params['selectKey'] = $t.val();
                if($t.val() != $t.data('fillselectOptions').preValue) {
                    $(target).fillselect(urlOrData, op);
                } else {
                    op.after = null;
                    $(target).fillselect([], op);
                }
            });
        });
    };
})(jQuery);
;(function($){
	$.fn.serializeJson1=function(){
	var serializeObj={};
	var array=this.serializeArray();
	var str=this.serialize();
	$(array).each(function(){
		if(serializeObj[this.name]){
			if($.isArray(serializeObj[this.name])){
				serializeObj[this.name].push(this.value);
			}else{
				serializeObj[this.name]=[serializeObj[this.name],this.value];
			}
		}else{
			serializeObj[this.name]=this.value;
		}
		});
		return serializeObj;
	};
})(jQuery);