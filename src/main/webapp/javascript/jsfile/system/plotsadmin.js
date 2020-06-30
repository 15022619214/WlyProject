A.controller("plotsadminController", ["$scope", "$http", "$timeout","$filter", function ($scope, $http, $timeout,$filter) {
	
	$scope.poimgurl = "https://timgsa.baidu.com/timg?" +
			"image&quality=80&size=b9999_10000&sec=1590575902495&di=8cc673ddf8885733c229e73917d174db&imgtype=0" +
			"&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D384725901%2C2537931323%26fm%3D214%26gp%3D0.jpg"
    var _editor;
    var _editorlogo;
    $(function () {
        $scope.getAlllplots("")
        _editor = UE.getEditor('upload_ue',{
        	zIndex:1500
        });
        _editor.ready(function () {
            //设置编辑器不可用
            //_editor.setDisabled();
            //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
            _editor.hide();
            //侦听图片上传
            _editor.addListener('beforeinsertimage', function (t, arg) {
                //将地址赋值给相应的input,只去第一张图片的路径
                $scope.poimgurl = arg[0].src
                $scope.poimgurlv = arg[0].src
                $scope.$apply()
                //图片预览
            })
            //侦听文件上传，取上传文件列表中第一个上传的文件的路径
            _editor.addListener('afterUpfile', function (t, arg) {
                $scope.poimgurlv = _editor.options.filePath + arg[0].url;
                $scope.$apply()
            })
        });

        _editorlogo = UE.getEditor('upload_logo',{
            zIndex:1500
        });
        _editorlogo.ready(function () {
            //设置编辑器不可用
            //_editor.setDisabled();
            //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
            _editorlogo.hide();
            //侦听图片上传
            _editorlogo.addListener('beforeinsertimage', function (t, arg) {
                //将地址赋值给相应的input,只去第一张图片的路径
                $scope.poimgurllogo = arg[0].src
                $scope.poimgurlogo = arg[0].src
                $scope.$apply()
                //图片预览
            })
            //侦听文件上传，取上传文件列表中第一个上传的文件的路径
            _editorlogo.addListener('afterUpfile', function (t, arg) {
                $scope.poimgurlogo = _editorlogo.options.filePath + arg[0].url;
                $scope.$apply()
            })
        });
    })
    

    $scope.getAlllplots = function (name) {
        $http({
            method: "POST",
            url: "/api/getAllplots",
            data: {
                name:name
            }
        }).then(function (res) {
            console.log(res.data)
            $scope.Plotsobjects_info = res.data.Plotsobjects_info
            $timeout(function(){
                $(".loading").addClass("invisible")
            },5000)
        })
    }

    $scope.search = function(name){
        $scope.getAlllplots(name)
    }

    $scope.refresh = function(){
        $scope.searchPoname = ""
        $scope.getAlllplots("")
    }

    $scope.indexinfo = function () {
    	$scope.indexedit = 1;
        $("#index_info").modal("toggle")
    }

    $scope.close = function(){
    	$scope.poname = ""
    		$scope.poimgurl = "https://timgsa.baidu.com/timg?" +
			"image&quality=80&size=b9999_10000&sec=1590575902495&di=8cc673ddf8885733c229e73917d174db&imgtype=0" +
			"&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D384725901%2C2537931323%26fm%3D214%26gp%3D0.jpg"
        $scope.poimgurlv = ""
        $scope.poimgurllogo = "https://timgsa.baidu.com/timg?" +
            "image&quality=80&size=b9999_10000&sec=1590575902495&di=8cc673ddf8885733c229e73917d174db&imgtype=0" +
            "&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D384725901%2C2537931323%26fm%3D214%26gp%3D0.jpg"
        $scope.poimgurlogo = ""
    }
    
    $scope.upImage = function() {
        var myImage = _editor.getDialog("insertimage");
        myImage.open();
    }

    $scope.upImagelogo = function() {
        var myImage = _editorlogo.getDialog("insertimage");
        myImage.open();
    }
    
    $scope.saveinfo = function(){
    	if($scope.poname == ""){
            $scope.sys_ifnos = "请填写项目名称"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
    		return
    	}
        if($scope.poimgurlogo == ""){
            $scope.sys_ifnos = "请上传展示图"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }

    	if($scope.poimgurlv == ""){
            $scope.sys_ifnos = "请上传项目封图"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
    		return
    	}
    	$http({
    		method: "POST",
    		url: "/api/savePlotsobject",
    		data:{
    			name: $scope.poname,
    			imgurl: $scope.poimgurlv,
                imgurllogo: $scope.poimgurlogo
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "上传成功"
    			$("#sys_info").modal('toggle')
    		}else{
    			$scope.sys_ifnos = "上传失败，请联系开发人员"
        		$("#sys_info").modal('toggle')
    		}
    		$timeout(function(){
    			$("#sys_info").modal('hide')
    			$scope.getAlllplots()
    			$scope.close()
            },2000)
    	})
    }
    
    
    $scope.editinfo = function(id){
        if($scope.poname == ""){
            $scope.sys_ifnos = "请填写项目名称"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }
        if($scope.poimgurlogo == ""){
            $scope.sys_ifnos = "请上传展示图"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }

        if($scope.poimgurlv == ""){
            $scope.sys_ifnos = "请上传项目封图"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }
    	$http({
    		method: "POST",
    		url: "/api/editPlotsobject",
    		data:{
    			id: id,
    			name: $scope.poname,
    			imgurl: $scope.poimgurlv,
                imgurllogo: $scope.poimgurlogo
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "修改成功"
    			$("#sys_info").modal('toggle')
    		}else{
    			$scope.sys_ifnos = "上传失败，请联系开发人员"
        		$("#sys_info").modal('toggle')
    		}
    		$timeout(function(){
    			$("#sys_info").modal('hide')
    			$scope.getAlllplots()
    			$scope.close()
            },2000)
    	})
    }
    
    $scope.plusplots = function(id){
    	window.location.href = "/plotsinfoadmin?id=" + id
    }
    
	$scope.pencilplots = function(item){
		console.log(item)
		$scope.indexedit = 0;
		$scope.itemid = item.id
		$scope.poname = item.poname
		$scope.poimgurl = item.poimgurl
		$scope.poimgurlv = item.poimgurl
        $scope.poimgurllogo = item.po_logo_imgurl
        $scope.poimgurlogo = item.po_logo_imgurl
	    $("#index_info").modal("toggle")
	}
	
	$scope.isopens = function(id, type){
		$http({
    		method: "POST",
    		url: "/api/isopens",
    		data:{
    			id: id,
    			type: type
    		}
    	}).then(function(res){
    		if(res.data['info'] == "isOne"){
    			$scope.sys_ifnos = "当前仅有一个项目，无法禁用"
    			$("#sys_info").modal('toggle')
    		}else if(res.data['info'] == "close"){
    			$scope.sys_ifnos = "禁用成功"
            		$("#sys_info").modal('toggle')
        	}else if(res.data['info'] == "noclose"){
    			$scope.sys_ifnos = "当前仅启用了一个个项目，无法禁用"
        		$("#sys_info").modal('toggle')
    		}else if(res.data['info'] == "noopen"){
    			$scope.sys_ifnos = "当前项目未添加地块信息，请添加后启用"
            	$("#sys_info").modal('toggle')
        	}else if(res.data['info'] == "open"){
    			$scope.sys_ifnos = "启用成功"
            	$("#sys_info").modal('toggle')
        	}
    		$timeout(function(){
    			$("#sys_info").modal('hide')
    			$scope.getAlllplots()
    			$scope.close()
            },2000)
    	})
	}
	
	$scope.trash = function(id, isopen){
        if(isopen == 1){
            $scope.sys_ifnos = "该项目启用中，无法删除！"
            $("#sys_info").modal('toggle');
            $timeout(function(){
                $("#sys_info").modal('hide');
            },2000)
            return
        }
		$http({
    		method: "POST",
    		url: "/api/trash",
    		data:{
    			id: id
    		}
    	}).then(function(res){
    		if(res.data['info'] == "hasFall"){
    			$scope.sys_ifnos_btn = "当前项目含有子项，是否确认删除？";
    			$scope.trashid = id;
    			$("#sys_info_btn").modal('toggle');
    		}else if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "已删除"
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	    			$scope.getAlllplots();
	    			$scope.close();
	            },2000)
    		}
    	})
	}
	
	$scope.trashAll = function(id){
		$http({
    		method: "POST",
    		url: "/api/trashAll",
    		data:{
    			id: id
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "已删除";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	    			$scope.getAlllplots();
	    			$scope.close();
	            },2000)
    		}
    	})
	}
}])