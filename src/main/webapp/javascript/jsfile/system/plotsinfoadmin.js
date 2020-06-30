A.controller("plotsinfoadminController", ["$scope", "$http", "$timeout","$filter","$sce", function ($scope, $http, $timeout,$filter,$sce) {

	var trafficImg_ys_e;
	var orbitalImg_ys_e;
	var businessImg_ys_e;
	var educationImg_ys_e;
	var medicalImg_ys_e;
	var landscapeImg_ys_e;
    var tipImg_ys_e;

    var editor;
	var poid = getQueryVariable("id");
    $(function () {
    	$("#My_carousel").carousel({
			interval:false
		})
        window.onresize = function(){
            $(".Cardheight").css("height",document.documentElement.clientHeight - 160)
        };
		$scope.actindex = -1
        $scope.showHtml = false
		editorYStraffic()
        // editorYSorbital()
        // editorYSbusiness()
        // editorYSeducation()
        // editorYSmedical()
        // editorYSlandscape()
        // editorYStipImg()
        // editorJS()
    })

    $scope.focus = 0;
    $scope.editpcname = "";
    $scope.navOnOff = {
        UperMapCheack: true,
        PlotsYsCheack: true,
        PlotsJsCheack: true,
        ServiceCheack: true,
        MapPainCheack: true,
        LetourCheack: true
    }
    $scope.map = {
        allMap: "",
        trafficMap: "",
        businessMap: "",
        landscapeMap: "",
        educationMap: "",
        medicalMap: ""
    }
    $scope.img = {
        tipImg:"",
        trafficImg: "",
        orbitalImg: "",
        businessImg: "",
        educationImg: "",
        medicalImg: "",
        landscapeImg: ""
    }
    $scope.pain = {
        painUrl: "",
        PainMap: "",
        PainMapurl: "",
    }
    $scope.tour = {
        letourMapurl: "",
        letourMap: "",
    }
    //初始化
	var uperMaplist = [
		{
			index: 0,
			kname: "",
			url: ""
		},{
			index: 1,
			kname: "",
			url: ""
		},{
			index: 2,
			kname: "",
			url: ""
		},{
			index: 3,
			kname: "",
			url: ""
		},{
			index: 4,
			kname: "",
			url: ""
		},{
			index: 5,
			kname: "",
			url: ""
		}
	];
	var ysImglist = [
		{
			index: 0,
			kname: "",
			url: ""
		},{
			index: 1,
			kname: "",
			url: ""
		},{
			index: 2,
			kname: "",
			url: ""
		},{
			index: 3,
			kname: "",
			url: ""
		},{
			index: 4,
			kname: "",
			url: ""
		},{
			index: 5,
			kname: "",
			url: ""
		}
	];

    $scope.getIsOpenPlots = function (id) {
        $http({
            method: "POST",
            url: "/api/getIsOpenPlots",
            data: {
                id: id
            }
        }).then(function (res) {
            $(".Cardheight").css("height",document.documentElement.clientHeight - 160)
			if(res.data.Plotsobjects_info.length > 0){
				$scope.Plotsobjects_info = res.data.Plotsobjects_info;
                for (var i = 0; i < $scope.Plotsobjects_info.length; i++) {
                    if($scope.Plotsobjects_info[i].Plots_list.length > 0){
                        $scope.checkdots($scope.Plotsobjects_info[i].Plots_list[0].id, $scope.Plotsobjects_info[i].Plots_list[0].plots);
                        break
                    }
                }
				$timeout(function(){
					$(".loading").addClass("invisible")
				},1000)
			}else{
                $timeout(function(){
                    $(".loading").addClass("invisible")
                },1000)
            }
        })
    }

    $scope.checkdots = function(id, name){
        $scope.focus = id
        $scope.editpcname = name
        clearAll()
        $scope.getplotsinfoByplotsid($scope.focus)
    }
    
    $scope.getplotsinfoByplotsid = function(pid){
    	$http({
    		method: "POST",
    		url: "/api/getplotsinfoByplotsid",
    		data: {
    			id: pid
    		}
    	}).then(function(res){
            $scope.getServices(pid)
            $scope.getBottomBtn(pid)
    		if(res.data.plotsinfo!=null){
                $scope.img.tipImg = res.data.plotsinfo.plosttipimg
    			if(res.data.plotsinfo_map.length > 0){
					var p_map = res.data.plotsinfo_map
					for (var i = 0; i < p_map.length; i++) {
						var result = {
							index: p_map[i].mapindex,
							kname: p_map[i].mapname,
							url: p_map[i].mapurl
						}
						if(uperMaplist.in_arry(result)){
							uperMaplist.splice(p_map[i].mapindex,1);
							uperMaplist.splice(p_map[i].mapindex, 0, result)
						}
						if(p_map[i].mapindex == 0){
							$scope.map.allMap = p_map[i].mapurl;
						}else if(p_map[i].mapindex == 1){
							$scope.map.trafficMap = p_map[i].mapurl;
						}else if(p_map[i].mapindex == 2){
							$scope.map.businessMap = p_map[i].mapurl;
						}else if(p_map[i].mapindex == 3){
							$scope.map.landscapeMap = p_map[i].mapurl;
						}else if(p_map[i].mapindex == 4){
							$scope.map.educationMap = p_map[i].mapurl;
						}else if(p_map[i].mapindex == 5){
							$scope.map.medicalMap = p_map[i].mapurl;
						}
					}
					maplist(uperMaplist)
				}
				if(res.data.plotsinfo_img.length > 0){
					var p_img = res.data.plotsinfo_img
					for (var i = 0; i < p_img.length; i++) {
						var result = {
							index: p_img[i].imgindex,
							kname: p_img[i].imgname,
							url: p_img[i].imgurl
						}
						if(ysImglist.in_arry(result)){
							ysImglist.splice(p_img[i].imgindex,1);
							ysImglist.splice(p_img[i].imgindex, 0, result)
						}
						if(p_img[i].imgindex == 0){
							$scope.img.trafficImg = p_img[i].imgurl;
						}else if(p_img[i].imgindex == 1){
							$scope.img.orbitalImg = p_img[i].imgurl;
						}else if(p_img[i].imgindex == 2){
							$scope.img.businessImg = p_img[i].imgurl;
						}else if(p_img[i].imgindex == 3){
							$scope.img.educationImg = p_img[i].imgurl;
						}else if(p_img[i].imgindex == 4){
							$scope.img.medicalImg = p_img[i].imgurl;
						}else if(p_img[i].imgindex == 5){
							$scope.img.landscapeImg = p_img[i].imgurl;
						}
					}
					imglist(ysImglist)
				}

                if(res.data.plotsinfo.plostintroduce != ""){
                    $scope.showHtml = true
                    $scope.showjsHtml =  $sce.trustAsHtml(res.data.plotsinfo.plostintroduce)
                    $scope.plotsjs = res.data.plotsinfo.plostintroduce;
                    editor.setContent(res.data.plotsinfo.plostintroduce)
                }


            	$scope.pain.painUrl = $sce.trustAsResourceUrl(res.data.plotsinfo.plostpain);
            	$scope.pain.PainMapurl = res.data.plotsinfo.plostpain;
            	$scope.pain.PainMap = res.data.plotsinfo.plostpainname;

                $scope.tour.letourMapurl = $sce.trustAsResourceUrl(res.data.plotsinfo.plostletour);
                $scope.tour.letourMap = res.data.plotsinfo.plostletour;
    		}else{
    			clearAll()
    		}
            $timeout(function(){
            	$(".loading").addClass("invisible")
            },1000)
    	})
    }
    
    //新增分类
    $scope.addpc = function(){
    	$scope.pcedit = 1;
    	$("#pc_info").modal("toggle")
    }
    
    
    $scope.editpc = function(item){
    	$scope.pcedit = 0;
    	$scope.pctemid = item.Plotsclassify_id;
    	$scope.pcname = item.Plotsclassify_name
    	$("#pc_info").modal("toggle")
    }
    
    $scope.trashpc = function(id){
    	$http({
    		url: "/api/trashpc",
    		method: "POST",
    		data: {
    			id: id,
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "删除成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	    			$scope.closepc()
					$scope.getIsOpenPlots(poid)
	            },2000)
    		}else if(res.data['info'] == "error"){
    			$scope.sys_ifnos = "该分类下包含子类，无法删除";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	            },2000)
    		}
    	})
    }
    
    
    $scope.savepcinfo = function(){
    	$http({
    		url: "/api/saveplotsClassify",
    		method: "POST",
    		data: {
    			poid: poid,
    			pcname: $scope.pcname
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "添加成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
                    $scope.getIsOpenPlots(poid)
	    			$scope.closepc()
	            },2000)
    		}else if(res.data['info'] == "error"){
                $scope.sys_ifnos = "已存在该分类";
                $("#sys_info").modal('toggle');
                $timeout(function(){
                    $("#sys_info").modal('hide');
                    $scope.closepc()
                },2000)
            }
    	})
    }
    
    
    $scope.editpcinfo = function(id){
    	$http({
    		url: "/api/editplotsClassify",
    		method: "POST",
    		data: {
    			id: id,
    			pcname: $scope.pcname
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "修改成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
                    $scope.getIsOpenPlots(poid)
	    			$scope.closepc()
	            },2000)
    		}
    	})
    }
    
    $scope.closepc = function(){
    	$scope.pcname = ""
    }
    
  //新增地块
    $scope.addpl = function(id){
    	$scope.pledit = 1;
    	$scope.plid = id
    	$("#pl_info").modal("toggle")
    }
    
    
    $scope.editpl = function(item){
    	$scope.pledit = 0;
    	$scope.pltemid = item.id;
    	$scope.plname = item.plots
    	$("#pl_info").modal("toggle")
    }
    
    $scope.trashpl = function(id){
    	$scope.trashid = id;
    	$scope.sys_ifnos_btn = "这将删除该地块下的所有详细信息，是否确认删除？"
    	$("#sys_info_btn").modal("toggle")
    }
    
    $scope.trashAll = function(id){
    	$http({
    		url: "/api/trashpl",
    		method: "POST",
    		data: {
    			id: id,
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "删除成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	    			$scope.closepl()
                    $scope.editpcname = "";
                    clearAll()
					$scope.getIsOpenPlots(poid)
	            },2000)
    		}
    	})
    }
    
    $scope.saveplinfo = function(plid){
    	$http({
    		url: "/api/savePlots",
    		method: "POST",
    		data: {
    			pcid: plid,
    			plname: $scope.plname
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "添加成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
                    $scope.getIsOpenPlots(poid)
	    			$scope.closepl()
	            },2000)
    		}
    	})
    }
    
    
    $scope.editplinfo = function(id){
    	$http({
    		url: "/api/editPlots",
    		method: "POST",
    		data: {
    			id: id,
    			plname: $scope.plname
    		}
    	}).then(function(res){
    		if(res.data['info'] == "success"){
    			$scope.sys_ifnos = "修改成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
                    $scope.getIsOpenPlots(poid)
	    			$scope.closepl()
	            },2000)
    		}
    	})
    }
    
    $scope.closepl = function(){
    	$scope.plname = ""
    }

    /*导航开关*/
    $scope.navCk = function(index, $event){
        switch (index){
            case 0:
                $scope.navOnOff.UperMapCheack = $event.target.checked;
                break
            case 1:
                $scope.navOnOff.PlotsYsCheack = $event.target.checked;
                break
            case 2:
                $scope.navOnOff.PlotsJsCheack = $event.target.checked;
                break
            case 3:
                $scope.navOnOff.ServiceCheack = $event.target.checked;
                break
            case 4:
                $scope.navOnOff.MapPainCheack = $event.target.checked;
                break
            case 5:
                $scope.navOnOff.LetourCheack = $event.target.checked;
                break
        }
    }

    $scope.getBottomBtn = function(id){
        $http({
            url: "/api/getBottomBtn",
            method: "POST",
            data: {
                id:id,
            }
        }).then(function (res) {
            if(res.data.plotsinfonav != null){
                $scope.navOnOff = {
                    UperMapCheack: Boolen(res.data.plotsinfonav.uperMapCheack),
                    PlotsYsCheack: Boolen(res.data.plotsinfonav.plotsYsCheack),
                    PlotsJsCheack: Boolen(res.data.plotsinfonav.plotsJsCheack),
                    ServiceCheack: Boolen(res.data.plotsinfonav.serviceCheack),
                    MapPainCheack: Boolen(res.data.plotsinfonav.mapPainCheack),
                    LetourCheack: Boolen(res.data.plotsinfonav.letourCheack)
                }
            }
        })
    }

    //上传信息
	/*全景地图开始*/
	$scope.addUperMap = function(index, kname, url){
		var result = {
			index: index,
			kname: kname,
			url: url
		}
		if(url!=""){
			if(uperMaplist.in_arry(result)){
				uperMaplist.splice(index,1);
				uperMaplist.splice(index, 0, result)
			}
		}
		maplist(uperMaplist)
	}

	$scope.removeMap = function(index){
		var result = {
			index: index,
			kname: "",
			url: ""
		}
		uperMaplist.splice(index, 1, result)
		lefremoveMap(index)
		maplist(uperMaplist)
	}

	function lefremoveMap(index) {
		switch (index) {
			case 0:
				$scope.map.allMap = "";
				break
			case 1:
				$scope.map.trafficMap = "";
				break
			case 2:
				$scope.map.businessMap = "";
				break
			case 3:
				$scope.map.landscapeMap = "";
				break
			case 4:
				$scope.map.educationMap = "";
				break
			case 5:
				$scope.map.medicalMap = "";
				break
		}
		if($scope.map.allMap == ""
			&& $scope.map.trafficMap == ""
			&& $scope.map.businessMap == ""
			&& $scope.map.landscapeMap == ""
			&& $scope.map.educationMap == ""
			&& $scope.map.medicalMap == ""){
			$scope.UperMapin = "";
			$("#uperMaps").removeAttr('src')
		}
	}

	function maplist(list){
		$scope.mapMean = list;
		for (var i = 0; i < list.length; i++) {
			if(list[i].url != ""){
				$scope.UperMapin = $sce.trustAsResourceUrl(list[i].url)
				break
			}
		}
	}

	$scope.showUmap = function(url){
		$scope.UperMapin = $sce.trustAsResourceUrl(url)
	}
	/*全景地图结束*/

	/*区位优势开始*/
	$scope.addYsImg = function(index) {
		var myImage;
		switch (index) {
			case 0:
				myImage = trafficImg_ys_e.getDialog("insertimage");
				break
			case 1:
				myImage = orbitalImg_ys_e.getDialog("insertimage");
				break
			case 2:
				myImage = businessImg_ys_e.getDialog("insertimage");
				break
			case 3:
				myImage = educationImg_ys_e.getDialog("insertimage");
				break
			case 4:
				myImage = medicalImg_ys_e.getDialog("insertimage");
				break
			case 5:
				myImage = landscapeImg_ys_e.getDialog("insertimage");
				break
		}
		myImage.open();
	}

    $scope.addYstipImg = function() {
        var myImage = tipImg_ys_e.getDialog("insertimage");
        myImage.open();
    }

    $scope.removeYstipImg = function(){
	    $scope.img.tipImg = ""
    }

	$scope.removeImg = function(index){
		var result = {
			index: index,
			kname: "",
			url: ""
		}
		ysImglist.splice(index, 1, result)
		lefremoveImg(index)
		imglist(ysImglist)
	}

	function lefremoveImg(index) {
		switch (index) {
			case 0:
				$scope.img.trafficImg = "";
				break
			case 1:
				$scope.img.orbitalImg = "";
				break
			case 2:
				$scope.img.businessImg = "";
				break
			case 3:
				$scope.img.educationImg = "";
				break
			case 4:
				$scope.img.medicalImg = "";
				break
			case 5:
				$scope.img.landscapeImg = "";
				break
		}
		if($scope.img.trafficImg == ""
			&& $scope.img.orbitalImg == ""
			&& $scope.img.businessImg == ""
			&& $scope.img.educationImg == ""
			&& $scope.img.medicalImg == ""
			&& $scope.img.landscapeImg == ""){
			$scope.actindex = -1
		}
	}

	function imglist(list){
        var imgMean_new = [];
        var side = 0;
        for (var i = 0; i < list.length; i++) {
            if(list[i].url != ""){
                var result = {
                    index: side,
                    kname: list[i].kname,
                    url: list[i].url
                }
                imgMean_new.push(result)
                side ++;
            }
        }
        $scope.imgMean = imgMean_new;
		for (var i = 0; i < list.length; i++) {
			if(list[i].url != ""){
				$scope.actindex = list[i].index
				break
			}
		}
	}
	/*区位优势结束*/

    /*定位*/
    $scope.handPain = function(){
        $("#MapPain").modal("toggle")
    }

    var h_lat;
    var h_lng;
    var h_title;
    var h_addr;
    window.addEventListener('message', function(event) {
        // 接收位置信息，用户选择确认位置点后选点组件会触发该事件，回传用户的位置信息
        var loc = event.data;
        if (loc && loc.module == 'locationPicker') {//防止其他应用也会向该页面post信息，需判断module是否为'locationPicker'
            h_lat = loc.latlng.lat;
            h_lng = loc.latlng.lng;
            h_title = loc.poiaddress;
            h_addr = loc.poiname;
        }
    }, false);

    $scope.saveMapPain = function(){
        var url = "https://apis.map.qq.com/tools/poimarker?type=0&marker=coord:" + h_lat + "," + h_lng +
            ";title:"+ h_title +";addr:"+ h_addr +"&key=WBVBZ-N7TRU-FMJV5-4YTU4-JOQEF-DOBBL&referer=myapp"
        $scope.pain.PainMapurl = url;
        $scope.pain.painUrl = $sce.trustAsResourceUrl(url);
        $scope.pain.PainMap = h_title
    }

    $scope.closeMapPain = function(){
        $scope.pain = {
            painUrl: "",
            PainMap: "",
            PainMapurl: ""
        }
    }

    $scope.pinBool = false;
    $scope.editPain = function(map){
	    $.ajax({
	        type: "GET",
	        url: "https://apis.map.qq.com/ws/geocoder/v1",
	        async: false,
	        data: {
	        	address: map,
				key: "WBVBZ-N7TRU-FMJV5-4YTU4-JOQEF-DOBBL",
				output: "jsonp"
	        },
	        dataType: "jsonp",
	        success: function (res) {
                if(res.status == 0){
                    $scope.pinBool = false;
                    var lat = res.result.location.lat
                    var lng = res.result.location.lng
                    var title = res.result.title
                    var addr = res.result.address_components.province + res.result.address_components.city + res.result.address_components.district
                    var url = "https://apis.map.qq.com/tools/poimarker?type=0&marker=coord:" + lat + "," + lng +
                        ";title:"+ title +";addr:"+ addr +"&key=WBVBZ-N7TRU-FMJV5-4YTU4-JOQEF-DOBBL&referer=myapp"
                    $scope.pain.PainMapurl = url
                    $scope.pain.painUrl = $sce.trustAsResourceUrl(url)
                    $scope.$apply();
                }else{
                    $scope.pinBool = true;
                    $scope.pinInfo = res.message;
                    $scope.$apply();
                }
	        }
	    });
    }

    $scope.removePain = function(){
        $scope.pain = {
            painUrl: "",
            PainMap: "",
            PainMapurl: ""
        }
        $scope.pinBool = false;
        $("#mapPain").removeAttr('src');
    }

    /*介绍*/
    $scope.upImagejs = function() {
        $scope.showjsHtml =  $sce.trustAsHtml(editor.getContent())
        $scope.showHtml = true;
        $scope.plotsjs = editor.getContent();
    }

    $scope.removejs = function(){
        $scope.showHtml = false;
        $scope.plotsjs = ""
        editor.setContent("")
    }


    /*服务*/
    $scope.getServices = function(id){
        $http({
            url: "/api/getServices",
            method: "POST",
            data: {
                infoid:id,
            }
        }).then(function (res) {
            $scope.serTopname = res.data.Plotsinfoservice_name;
            $scope.Plotsinfoservice_name = res.data.Plotsinfoservice_name;
            $scope.Plotsinfoservice_list = res.data.Plotsinfoservice_list;
        })
    }

    $scope.addSerTop = function(){
        $scope.serTid = $scope.focus;
        $("#service_name").modal("toggle")
    }

    $scope.addService = function(){
        $scope.serid = $scope.focus;
        $scope.seredit = 1;
        $("#service_info").modal("toggle")
    }

    $scope.editser = function(item){
        $scope.sertemid = item.id;
        $scope.sername = item.sertitle;
        $scope.serline = item.serurl;
        $scope.sertype = item.sertype;
        $scope.seredit = 0;
        $("#service_info").modal("toggle")
    }

    $scope.trashser = function(id){
        $http({
            url: "/api/delServices",
            method: "POST",
            data: {
                id:id,
            }
        }).then(function (res) {
            if(res.data["info"]=="success"){
                $scope.sys_ifnos = "删除成功";
                $("#sys_info").modal('toggle');
                $timeout(function(){
                    $scope.getServices($scope.focus)
                    $("#sys_info").modal('hide');
                },2000)
            }
        })
    }

    $scope.saveserT = function(id){
        if($scope.serTopname == ""){
            $scope.sys_ifnos = "请输入标题"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }
        $http({
            url: "/api/saveServicesT",
            method: "POST",
            data: {
                infoid:id,
                serTopname: $scope.serTopname,
            }
        }).then(function (res) {
            if(res.data["info"]=="success"){
                $scope.sys_ifnos = "提交成功";
                $("#sys_info").modal('toggle');
                $timeout(function(){
                    $scope.closeSer()
                    $scope.getServices($scope.focus)
                    $("#sys_info").modal('hide');
                },2000)
            }
        })
    }

    $scope.saveSerinfo = function(id){
        if($scope.sername == ""){
            $scope.sys_ifnos = "请输入服务"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }
        if($scope.serline == ""){
            $scope.sys_ifnos = "请输入链接"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }
        if($scope.sertype == ""){
            $scope.sys_ifnos = "请选择类型"
            $("#sys_info").modal('toggle')
            $timeout(function(){
                $("#sys_info").modal('hide')
                $scope.close()
            },2000)
            return
        }
        $http({
            url: "/api/saveServices",
            method: "POST",
            data: {
                infoid:id,
                sername: $scope.sername,
                serline: $scope.serline,
                sertype: $scope.sertype
            }
        }).then(function (res) {
            if(res.data["info"]=="success"){
                $scope.sys_ifnos = "提交成功";
                $("#sys_info").modal('toggle');
                $timeout(function(){
                    $scope.closeSer()
                    $scope.getServices($scope.focus)
                    $("#sys_info").modal('hide');
                },2000)
            }
        })
    }

    $scope.editSerinfo = function(id){
        $http({
            url: "/api/editServices",
            method: "POST",
            data: {
                id:id,
                sername: $scope.sername,
                serline: $scope.serline,
                sertype: $scope.sertype
            }
        }).then(function (res) {
            if(res.data["info"]=="success"){
                $scope.sys_ifnos = "修改成功";
                $("#sys_info").modal('toggle');
                $timeout(function(){
                    $scope.closeSer()
                    $scope.getServices($scope.focus)
                    $("#sys_info").modal('hide');
                },2000)
            }
        })
    }

    $scope.closeSer = function(){
        $scope.sername = "";
        $scope.serline = "";
        $scope.sertype = "";
    }

    $scope.closeserT = function(){
        $scope.serTopname = "";
    }
    /*乐游*/
    $scope.editLetour = function(url){
        $scope.tour.letourMapurl = $sce.trustAsResourceUrl(url)
    }

    $scope.removeLetour = function(){
        $scope.tour = {
            letourMapurl: "",
            letourMap: "",
        }
        $("#letourmap").removeAttr('src');
    }

    /*保存信息*/
    $scope.savePlotsinfo = function(){
        // console.info("ID",$scope.focus)
        // console.info("开关",$scope.navOnOff)
        // console.info("全景",uperMaplist)
        // console.log("固定",$scope.img.tipImg)
        // console.info("优势",ysImglist)
        // console.info("介绍",$scope.plotsjs)
        // console.info("定位",$scope.pain.PainMap)
        // console.info("定位",$scope.pain.PainMapurl)
        // console.info("乐游",$scope.tour.letourMap)
    	if($scope.focus== 0){
			$scope.sys_ifnos = "请选择地块项目";
			$("#sys_info").modal('toggle');
			$timeout(function(){
				$("#sys_info").modal('hide');
			},2000)
    		return
    	}
    	$http({
    		url: "/api/savePlotsinfo",
    		method: "POST",
    		data: {
    			id: $scope.focus,
                navOnOff: JSON.stringify($scope.navOnOff),
				paramsMap: JSON.stringify(uperMaplist),
                tipimg:$scope.img.tipImg,
				paramsImg: JSON.stringify(ysImglist),
				plotsjs: $scope.plotsjs,
    			PainMapurl: $scope.pain.PainMapurl,
    			plostpainname: $scope.pain.PainMap,
                letour: $scope.tour.letourMap
    		}
    	}).then(function(res){
    		if(res.data["info"]=="success"){
    			$scope.sys_ifnos = "提交成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	            },2000)
    		}else if(res.data["info"]=="edit"){
    			$scope.sys_ifnos = "修改成功";
        		$("#sys_info").modal('toggle');
        		$timeout(function(){
	    			$("#sys_info").modal('hide');
	            },2000)
    		}
    	})
    }
    
    function getQueryVariable(variable){
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }
    /*区位优势图*/
    function editorYStraffic(){
		trafficImg_ys_e = UE.getEditor('trafficImg_ys',{
        	zIndex:1500
        });
		trafficImg_ys_e.ready(function () {
			trafficImg_ys_e.hide();
            //侦听图片上传
			trafficImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
				var result = {
					index: 0,
					kname: "交通",
					url: arg[0].src
				}
				if(ysImglist.in_arry(result)){
					ysImglist.splice(0,1);
					ysImglist.splice(0, 0, result)
				}
                $scope.img.trafficImg = arg[0].src;
				imglist(ysImglist)
                $scope.$apply()
            })
            //侦听文件上传，取上传文件列表中第一个上传的文件的路径
			trafficImg_ys_e.addListener('afterUpfile', function (t, arg) {
                $scope.img.trafficImg = trafficImg_ys_e.options.filePath + arg[0].url;
                $scope.$apply()
            })
            console.info("editorYStraffic 加载完成")
            editorYSorbital()
        });
    }

	function editorYSorbital(){
		orbitalImg_ys_e = UE.getEditor('orbitalImg_ys',{
			zIndex:1500
		});
		orbitalImg_ys_e.ready(function () {
			orbitalImg_ys_e.hide();
			//侦听图片上传
			orbitalImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
				var result = {
					index: 1,
					kname: "轨道",
					url: arg[0].src
				}
				if(ysImglist.in_arry(result)){
					ysImglist.splice(1,1);
					ysImglist.splice(1, 0, result)
				}
				$scope.img.orbitalImg = arg[0].src;
				imglist(ysImglist)
				$scope.$apply()
			})
			//侦听文件上传，取上传文件列表中第一个上传的文件的路径
			orbitalImg_ys_e.addListener('afterUpfile', function (t, arg) {
				$scope.img.orbitalImg = orbitalImg_ys_e.options.filePath + arg[0].url;
				$scope.$apply()
			})
            console.info("editorYSorbital 加载完成")
            editorYSbusiness()
		});
	}
	function editorYSbusiness(){
		businessImg_ys_e = UE.getEditor('businessImg_ys',{
			zIndex:1500
		});
		businessImg_ys_e.ready(function () {
			businessImg_ys_e.hide();
			//侦听图片上传
			businessImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
				var result = {
					index: 2,
					kname: "商业",
					url: arg[0].src
				}
				if(ysImglist.in_arry(result)){
					ysImglist.splice(2, 1);
					ysImglist.splice(2, 0, result)
				}
				$scope.img.businessImg = arg[0].src;
				imglist(ysImglist)
				$scope.$apply()
			})
			//侦听文件上传，取上传文件列表中第一个上传的文件的路径
			businessImg_ys_e.addListener('afterUpfile', function (t, arg) {
				$scope.img.businessImg = businessImg_ys_e.options.filePath + arg[0].url;
				$scope.$apply()
			})
            console.info("editorYSbusiness 加载完成")
            editorYSeducation()
		});
	}
	function editorYSeducation(){
		educationImg_ys_e = UE.getEditor('educationImg_ys',{
			zIndex:1500
		});
		educationImg_ys_e.ready(function () {
			educationImg_ys_e.hide();
			//侦听图片上传
			educationImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
				var result = {
					index: 3,
					kname: "教育",
					url: arg[0].src
				}
				if(ysImglist.in_arry(result)){
					ysImglist.splice(3, 1);
					ysImglist.splice(3, 0, result)
				}
				$scope.img.educationImg = arg[0].src;
				imglist(ysImglist)
				$scope.$apply()
			})
			//侦听文件上传，取上传文件列表中第一个上传的文件的路径
			educationImg_ys_e.addListener('afterUpfile', function (t, arg) {
				$scope.img.educationImg = educationImg_ys_e.options.filePath + arg[0].url;
				$scope.$apply()
			})
            console.info("editorYSeducation 加载完成")
            editorYSmedical()
		});
	}
	function editorYSmedical(){
		medicalImg_ys_e = UE.getEditor('medicalImg_ys',{
			zIndex:1500
		});
		medicalImg_ys_e.ready(function () {
			medicalImg_ys_e.hide();
			//侦听图片上传
			medicalImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
				var result = {
					index: 4,
					kname: "医疗",
					url: arg[0].src
				}
				if(ysImglist.in_arry(result)){
					ysImglist.splice(4, 1);
					ysImglist.splice(4, 0, result)
				}
				$scope.img.medicalImg = arg[0].src;
				imglist(ysImglist)
				$scope.$apply()
			})
			//侦听文件上传，取上传文件列表中第一个上传的文件的路径
			medicalImg_ys_e.addListener('afterUpfile', function (t, arg) {
				$scope.img.medicalImg = medicalImg_ys_e.options.filePath + arg[0].url;
				$scope.$apply()
			})
            console.info("editorYSmedical 加载完成")
            editorYSlandscape()
		});
	}
	function editorYSlandscape(){
		landscapeImg_ys_e = UE.getEditor('landscapeImg_ys',{
			zIndex:1500
		});
		landscapeImg_ys_e.ready(function () {
			landscapeImg_ys_e.hide();
			//侦听图片上传
			landscapeImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
				var result = {
					index: 5,
					kname: "景观",
					url: arg[0].src
				}
				if(ysImglist.in_arry(result)){
					ysImglist.splice(5, 1);
					ysImglist.splice(5, 0, result)
				}
				$scope.img.landscapeImg = arg[0].src;
				imglist(ysImglist)
				$scope.$apply()
			})
			//侦听文件上传，取上传文件列表中第一个上传的文件的路径
			landscapeImg_ys_e.addListener('afterUpfile', function (t, arg) {
				$scope.img.landscapeImg = landscapeImg_ys_e.options.filePath + arg[0].url;
				$scope.$apply()
			})
            console.info("editorYSlandscape 加载完成")
            editorYStipImg()
		});
	}

    function editorYStipImg(){
        tipImg_ys_e = UE.getEditor('tipImg_ys',{
            zIndex:1500
        });
        tipImg_ys_e.ready(function () {
            tipImg_ys_e.hide();
            //侦听图片上传
            tipImg_ys_e.addListener('beforeinsertimage', function (t, arg) {
                $scope.img.tipImg = arg[0].src;
                $scope.$apply()
            })
            //侦听文件上传，取上传文件列表中第一个上传的文件的路径
            tipImg_ys_e.addListener('afterUpfile', function (t, arg) {
                $scope.img.tipImg = tipImg_ys_e.options.filePath + arg[0].url;
                $scope.$apply()
            })
            console.info("editorYStipImg 加载完成")
            editorJS()
        });
    }
	/*区位优势图end*/
    function editorJS(){
        editor = UE.getEditor('myplotsjsEditor');
        editor.ready(function() {
            editor.setHeight(295);
            console.info("editorJS 加载完成")
            $scope.getIsOpenPlots(poid)
         });
    }

	Array.prototype.in_arry = function(e){
		for(var i=0;i<this.length;i++){
			if(this[i].index == e.index){
				return true;
			}
		}
		return false
	}

	function Boolen(val) {
        if(val === "true"){
            return true
        }else if(val === "false"){
            return false
        }
    }
	
    function clearAll(){
        $scope.actindex = -1;
        $scope.showHtml = false;
        $scope.navOnOff = {
            UperMapCheack: true,
            PlotsYsCheack: true,
            PlotsJsCheack: true,
            ServiceCheack: true,
            MapPainCheack: true,
            LetourCheack: true
        }

    	$scope.UperMapin = "";
        $("#uperMaps").removeAttr('src');
        $scope.map = {
            allMap: "",
            trafficMap: "",
            businessMap: "",
            landscapeMap: "",
            educationMap: "",
            medicalMap: ""
        }

        $scope.img = {
            tipImg:"",
            trafficImg: "",
            orbitalImg: "",
            businessImg: "",
            educationImg: "",
            medicalImg: "",
            landscapeImg: ""
        }


		$scope.mapMean = "";
        $scope.imgMean = "";

        $scope.plotsjs = ""

        $("#mapPain").removeAttr('src');
        $scope.pain = {
            painUrl: "",
            PainMap: "",
            PainMapurl: "",
        }

        $("#letourmap").removeAttr('src');
        $scope.tour = {
            letourMapurl: "",
            letourMap: "",
        }
		uperMaplist = [
			{
				index: 0,
				kname: "",
				url: ""
			},{
				index: 1,
				kname: "",
				url: ""
			},{
				index: 2,
				kname: "",
				url: ""
			},{
				index: 3,
				kname: "",
				url: ""
			},{
				index: 4,
				kname: "",
				url: ""
			},{
				index: 5,
				kname: "",
				url: ""
			}
		];
		ysImglist = [
			{
				index: 0,
				kname: "",
				url: ""
			},{
				index: 1,
				kname: "",
				url: ""
			},{
				index: 2,
				kname: "",
				url: ""
			},{
				index: 3,
				kname: "",
				url: ""
			},{
				index: 4,
				kname: "",
				url: ""
			},{
				index: 5,
				kname: "",
				url: ""
			}
		];
        editor.ready(function() {
            editor.setContent("");  //赋值给UEditor
        });
    }
}])