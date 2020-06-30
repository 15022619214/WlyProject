var ctx = ""; 
$(function(){
		//该方法比onloas先执行
	 if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) { 
			
		} else { 
			 
		}
	});

function formPostSubmit(url,jsondata) {
	//创建form表单
    var temp_form = document.createElement("form");
    temp_form.action = url;
    //如需打开新窗口，form的target属性要设置为'_blank'
    temp_form.target = "_blank";
    temp_form.method = "post";
    temp_form.style.display = "none";
    //添加参数 
    var opt = document.createElement("textarea");
    opt.name = 'params';
    opt.value = JSON.stringify(jsondata);
    temp_form.appendChild(opt); 
    document.body.appendChild(temp_form);
    //提交数据
    temp_form.submit(); 
}

function getQueryString(name) { 
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
    var r = window.location.search.substr(1).match(reg); 
    if (r != null) return unescape(r[2]); 
    return null; 
} 
/*A.controller("firstController",["$scope","$http",'$location',function ($scope,$http,$location) {

	    //跳转加密
	    $scope.firstselect= function (val,val2,val3,val4){   	 
	    	 var _adminNmae= CryptoJS.AES.encrypt(val, 'hhhh');
	    	 var _adminNmae2= CryptoJS.AES.encrypt(val2, 'hhhh');
	    	 var _adminNmae3= CryptoJS.AES.encrypt(val3, 'hhhh');
	    	 var _adminNmae4= CryptoJS.AES.encrypt(val4, 'hhhh');
	     	 window.location.href = "/showallgoods?lvtone="+_adminNmae+"&lvthreecode="+_adminNmae2+"&pinpai="+_adminNmae3+"&name="+_adminNmae4;
	    }
}])*/

A.filter('autofilter', function() {
    return function(value,len) {
    	if (len == null) {
    		len = 50;
    	}
    	if(value.length > len){
    		value =value.substring(0,len)+"..";
    	}
      return value;
    }
  });
A.filter('datefilter', function() {
    return function(value,len) {
    	if (len == null) {
    		len = 10;
    	}
    	if(value.length > len){
    		value =value.substring(0,len);
    	}
      return value;
    }
  });
Array.prototype.remove = function (val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};
A.filter('PercentValue', function () {
    return function (o) {
        if (o != undefined && /(^(-)*\d+\.\d*$)|(^(-)*\d+$)/.test(o)) {
            var v = parseFloat(o);
            return Number(Math.round(v * 10000) / 100).toFixed(2) + "%";
        } else {
            return "0";
        }
    }
});
A.filter('bankaccountfilter', function() {
    return function(value,len) {
    	if (len == null) {
    		len = 8;
    	}
    	if (value == null) {
    		value = '';
    	}
    	if(value.length > len){
    		value =value.substring(0,4)+"****"+value.substring(value.length-4,value.length);
    	} 
      return value;
    }
  });