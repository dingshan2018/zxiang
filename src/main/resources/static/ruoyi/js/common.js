/**
 * 通用方法封装处理
 */

$(function(){
	// 复选框事件绑定
	if ($.fn.select2 !== undefined) {
		$("select.form-control:not(.noselect2)").each(function () {
			$(this).select2().on("change", function () {
				$(this).valid();
			})
		})
	}
	if ($(".i-checks").length > 0) {
	    $(".i-checks").iCheck({
	        checkboxClass: "icheckbox_square-green",
	        radioClass: "iradio_square-green",
	    })
	}
	if ($(".radio-box").length > 0) {
	    $(".radio-box").iCheck({
	        checkboxClass: "icheckbox_square-green",
	        radioClass: "iradio_square-green",
	    })
	}
	if ($(".select-time").length > 0) {
		layui.use('laydate', function() {
		    var laydate = layui.laydate;
		    laydate.render({ elem: '#startTime', theme: 'molv' });
		    laydate.render({ elem: '#endTime', theme: 'molv' });
		});
	}
});

/** 创建选项卡 */
function createMenuItem(dataUrl, menuName) {
    dataIndex = $.common.random(1,100),
    flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;
    var topWindow = $(window.parent.document);
    // 选项卡菜单已存在
    $('.menuTab', topWindow).each(function() {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.menuTab').removeClass('active');
                $('.page-tabs-content').animate({ marginLeft: ""}, "fast");
                // 显示tab对应的内容区
                $('.mainContent .RuoYi_iframe', topWindow).each(function() {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('.RuoYi_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
            return false;
        }
    });
    // 选项卡菜单不存在
    if (flag) {
        var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + ' <i class="fa fa-times-circle"></i></a>';
        $('.menuTab', topWindow).removeClass('active');

        // 添加选项卡对应的iframe
        var str1 = '<iframe class="RuoYi_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        $('.mainContent', topWindow).find('iframe.RuoYi_iframe').hide().parents('.mainContent').append(str1);

        // 添加选项卡
        $('.menuTabs .page-tabs-content', topWindow).append(str);
    }
    return false;
}

/** 设置全局ajax超时处理 */
$.ajaxSetup({
    complete: function(XMLHttpRequest, textStatus) {
        if (textStatus == "parsererror") {
        	$.modal.confirm("登录超时！请重新登陆！", function() {
        		window.location.href = ctx + "login";
        	})
        }
    }
});


/**设备下拉框选择*/
function selectDeviceList(){
	var deviceList = [];
	 $.ajax({
     	//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法
         url: ctx + "business/device/getDropBoxDeviceList",
         type: "get",
		//接受数据格式
         dataType: "json",
			//要传递的数据
         data: 'data',
         async:false,
         success: function (data) {
        	 deviceList = data.rows;
         },
         error: function (data) {
             alert("查询设备下拉列表失败");
         }
     })
     return deviceList;
}

/**根据status选择设备列表*/
function selectDevByStatus(status){
	var deviceList = [];
	 $.ajax({
         url: ctx + "business/device/getDropBoxDeviceList",
         type: "post",
         //接受数据格式
         contentType:"application/json",
         dataType: "json",
			//要传递的数据
         data: JSON.stringify({ "status": status}),
         async:false,
         success: function (data) {
        	 deviceList = data.rows;
         },
         error: function (data) {
             alert("查询设备下拉列表失败");
         }
     })
     return deviceList;
}

/**场所下拉框选择*/
function selectPlaceList(){
	var placeList = [];
	$.ajax({
        url: ctx + "business/place/getDropBoxPlaceList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	placeList = data.rows;
        },
        error: function (data) {
            alert("查询场所下拉列表失败");
        }
    })
    return placeList;
}


/**终端下拉框选择*/
function selectTerminalList(){
	var terminalList = [];
	$.ajax({
        url: ctx + "business/terminal/getDropBoxTerminalList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	terminalList = data.rows;
        },
        error: function (data) {
            alert("查询终端设备下拉列表失败");
        }
    })
    return terminalList;
}

/**用户下拉框选择*/
function selectUserList(){
	var userList = [];
	$.ajax({
        url: ctx + "system/user/getDropBoxUserList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	userList = data.rows;
        },
        error: function (data) {
            alert("查询用户下拉列表失败");
        }
    })
    return userList;
}

/**场所选择服务商员工下拉框选择*/
function selectUserBycounty(city,county){
	var userList = [];
	$.ajax({
    	type: "post",
    	contentType:"application/json",
        dataType: "json",
        url: ctx + "system/user/selectUserByCity",
        data: JSON.stringify({ "city": city,"county":county}),
        async: false,
        success: function (data) {
        	userList = data.rows;
        },
        error: function () { alert("获取用户信息失败"); }
    });
    return userList;
}

/**地区下拉框,根据父级地区获取*/
function selectAreaListByPid(parentAreaId){
	var areaList = [];
	$.ajax({
    	type: "post",
    	contentType:"application/json",
        dataType: "json",
        url: ctx + "system/area/getDropBoxAreaList",
        data: JSON.stringify({ "parentId": parentAreaId}),
        async: false,
        success: function (data) {
        	areaList = data.rows;
        },
        error: function () { alert("Error:获取地区信息失败"); }
    });
    return areaList;
}

/**服务商下拉框选择*/
function selectRepairList(){
	var repairList = [];
	$.ajax({
        url: ctx + "client/repair/getDropBoxRepairList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	repairList = data.rows;
        },
        error: function (data) {
            alert("查询服务商下拉列表失败");
        }
    })
    return repairList;
}

/**代理商下拉框选择*/
function selectAgentList(){
	var agentList = [];
	$.ajax({
        url: ctx + "client/agent/getDropBoxAgentList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	agentList = data.rows;
        },
        error: function (data) {
            alert("查询代理商下拉列表失败");
        }
    })
    return agentList;
}

/**机主下拉框选择*/
function selectJoinList(){
	var joinList = [];
	$.ajax({
        url: ctx + "client/join/getDropBoxJoinList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	joinList = data.rows;
        },
        error: function (data) {
            alert("查询机主下拉列表失败");
        }
    })
    return joinList;
}

/**广告商下拉框选择*/
function selectAdvertiseList(){
	var advertiseList = [];
	$.ajax({
        url: ctx + "client/advertise/getDropBoxAdvertiseList",
        type: "post",
        dataType: "json",
        data: 'data',
        async:false,
        success: function (data) {
        	advertiseList = data.rows;
        },
        error: function (data) {
            alert("查询广告商下拉列表失败");
        }
    })
    return advertiseList;
}