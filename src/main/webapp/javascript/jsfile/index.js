A.controller("indexController", ["$scope", "$http", "$timeout", function ($scope, $http, $timeout) {

    $(function () {
        $scope.douclk = false;
    	$scope.openmean = 1;
    	$scope.bacmac = 0;
    	$scope.ishowCon = false;
    	var poID = getQueryVariable("poID");
    	if(poID){
    		$scope.hasPoID = false;
            $scope.getIsOpenPlots(poID)
    	}else{
    		$scope.hasPoID = true;
    		$scope.indexshow(getQueryVariable("id"));
    	}
    	$timeout(function(){
            var myElement = document.getElementById('imgBs')
            var imgft = document.getElementById('imgft')
            var hm = new Hammer(myElement);
            var oImg = new Hammer(imgft);
            hm.get('pinch').set({
                enable: true
            });
            oImg.get('pan').set({
                direction: Hammer.DIRECTION_ALL
            });
            oImg.on("panstart", function(e) {
                var left = imgft.offsetLeft;
                var tp = imgft.offsetTop;
                oImg.on("panmove", function(e) {
                    imgft.style.left = left + e.deltaX + 'px';
                    imgft.style.top = tp + e.deltaY + 'px';
                });
                $scope.douclk = true;
                $scope.$apply()
            })
            var scaleIndex = 1;
            hm.on('pinchmove', function (e) {
                if(e.scale < 0.6){
                    imgft.style.left = '0px';
                    imgft.style.top = '0px';
                    imgft.style.transform = "scale(1)";
                }else{
                    imgft.style.transform = "scale("+scaleIndex * e.scale+")";
                }
                $scope.douclk = false;
                $scope.$apply()
            });
            hm.on('pinchend', function (e) {
                if(e.scale < 1){
                    scaleIndex = 1;
                }else{
                    scaleIndex = e.scale;
                }
            });
            hm.on('doubletap', function(e){
                imgft.style.left = '0px';
                imgft.style.top = '0px';
                imgft.style.transform = "scale(1)";
                $scope.douclk = false;
                $scope.$apply()
            })
        },1000)
    })
    
    var pobID = 0;
    
    $scope.indexshow = function(id){
        $scope.getIsOpenPlots(id)
    }
    
    /*$scope.getAllplotsisopen = function(){
    	$http({
            method: "GET",
            url: "/api/getAllplotsisopen",
        }).then(function (res) {
        	console.log(res.data)
        	$scope.pobjects = res.data.Plotsobjects_info
        })
    }*/

    /*$scope.showMean = function(type){
    	$scope.openmean = type;
    	$scope.getAllplotsisopen()
    	if(type == 0){
    		$scope.bacmac = 1;
    		$(".istrfrom").addClass("tflateY");
    	}else if(type == 1){
    		$scope.bacmac = 0;
    		$(".istrfrom").removeClass("tflateY");
    	}
    }*/
    
    /*$scope.choseinfo = function(id){
    	$scope.openmean = 1;
    	$scope.bacmac = 0;
		$(".istrfrom").removeClass("tflateY");
    	$scope.getIsOpenPlots(id)
    }*/
    
    $scope.getIsOpenPlots = function (id) {
        $http({
            method: "POST",
            url: "/api/getIsOpenPlots",
            data: {
            	id:id
            }
        }).then(function (res) {
        	console.log(res.data)
        	pobID = id;
            document.title = res.data.Plotsobjects_name
            $scope.indexlogo = res.data.Plotsobjects_logo;
        	$scope.indeximgUrl = res.data.Plotsobjects_imguel;
            $scope.Plotsobjects_info = res.data.Plotsobjects_info;
            $scope.ishowCon = true;
        })
    }

    $scope.plotsinfo = function (id, name) {
        window.location.href = "/plotsinfo?id=" + id +"&name=" + name +"&poid=" + pobID
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
}])