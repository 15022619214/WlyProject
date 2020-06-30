A.controller("plotsController", ["$scope", "$http", "$timeout","$sce" , function ($scope, $http, $timeout, $sce) {

    var plots_id = getQueryVariable("id");
    var poID = getQueryVariable("poid");
    $scope.plotsname = decodeURI(getQueryVariable("name"))
    document.title = $scope.plotsname
    $(function(){
        $('#My_carousel').carousel({
            interval:false,
            touch:false,
            wrap:false
        })
        var myElement= document.getElementById('My_carousel')
        var hm=new Hammer(myElement);
        hm.get('swipe').set({ direction: Hammer.DIRECTION_VERTICAL });
        hm.on("swipeup",function(){
            $('#My_carousel').carousel('next')
        })
        hm.on("swipedown",function(){
            $('#My_carousel').carousel('prev')
        })
        $('#My_carousel').on('slide.bs.carousel', function (e) {
            var from = "#"+ e.from;
            var to = "#"+ e.to;
            $(from).removeClass('active');
            $(to).addClass('active');
        })
        var clipboard = new ClipboardJS('.copybtn');
        clipboard.on('success', function(e) {
            $scope.sys_ifnos = "复制成功";
            $("#sys_info").modal('toggle');
            $timeout(function(){
                $("#sys_info").modal('hide');
            },2000)
            $scope.$apply()
        });
        clipboard.on('error', function(e) {
            $scope.sys_ifnos = "复制失败";
            $("#sys_info").modal('toggle');
            $timeout(function(){
                $("#sys_info").modal('hide');
            },2000)
            $scope.$apply()
        });
        $scope.getplotsinfoByplotsid(plots_id);
    })

    $scope.navOnOff = {
        UperMapCheack: true,
        PlotsYsCheack: true,
        PlotsJsCheack: true,
        ServiceCheack: true,
        MapPainCheack: true,
        LetourCheack: true
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

    $scope.getplotsinfoByplotsid = function(id){
    	$http({
    		url: "/api/getplotsinfoByplotsid",
    		method: "POST",
    		data: {
    			id: id
    		}
    	}).then(function(res){
            $(".embed-responsive").css("height",window.innerHeight - document.getElementById("navbottom").offsetHeight);
            $scope.imgheight = window.innerHeight - document.getElementById("navbottom").offsetHeight + "px";
            $("#showjsHtml").css("height",window.innerHeight - document.getElementById("navbottom").offsetHeight);
            $("#tabheight").css("height",window.innerHeight - document.getElementById("navbottom").offsetHeight-30-42);
            /*$('body').css("paddingTop",document.getElementById("navtop").offsetHeight)*/
            /*$(".embed-responsive").css("height",window.innerHeight- document.getElementById("navtop").offsetHeight - document.getElementById("navbottom").offsetHeight);
            $scope.imgheight = window.innerHeight- document.getElementById("navtop").offsetHeight - document.getElementById("navbottom").offsetHeight + "px";
            $("#showjsHtml").css("height",window.innerHeight- document.getElementById("navtop").offsetHeight - document.getElementById("navbottom").offsetHeight);
            $("#tabheight").css("height",window.innerHeight- document.getElementById("navtop").offsetHeight - document.getElementById("navbottom").offsetHeight-30);*/
            $scope.getBottomBtn(id)
            if(res.data.plotsinfo!=null){
                console.log("plotsinfo",res.data.plotsinfo)
                $scope.plosttipimg = res.data.plotsinfo.plosttipimg
                if(res.data.plotsinfo_map.length > 0){
                    var p_map = res.data.plotsinfo_map
                    console.log("p_map",p_map)
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
                    }
                    maplist(uperMaplist)
                }
                if(res.data.plotsinfo_img.length > 0){
                    var p_img = res.data.plotsinfo_img
                    console.log("p_img",p_img)
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
                    }
                    imglist(ysImglist)
                }

                $scope.showjsHtml =  $sce.trustAsHtml(res.data.plotsinfo.plostintroduce)

                $scope.plostpain = $sce.trustAsResourceUrl(res.data.plotsinfo.plostpain);

                $scope.letourMapurl = $sce.trustAsResourceUrl(res.data.plotsinfo.plostletour);
            }
    	})
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
                var nav = [
                    res.data.plotsinfonav.uperMapCheack,
                    res.data.plotsinfonav.plotsYsCheack,
                    res.data.plotsinfonav.plotsJsCheack,
                    res.data.plotsinfonav.serviceCheack,
                    res.data.plotsinfonav.mapPainCheack,
                    res.data.plotsinfonav.letourCheack
                ]
                var truenum = 0;
                for (var i = 0; i < nav.length; i++){
                    if(nav[i] === "true"){
                        truenum ++
                    }
                }
                $scope.navOnOff = {
                    UperMapCheack: Boolen(res.data.plotsinfonav.uperMapCheack),
                    PlotsYsCheack: Boolen(res.data.plotsinfonav.plotsYsCheack),
                    PlotsJsCheack: Boolen(res.data.plotsinfonav.plotsJsCheack),
                    ServiceCheack: Boolen(res.data.plotsinfonav.serviceCheack),
                    MapPainCheack: Boolen(res.data.plotsinfonav.mapPainCheack),
                    LetourCheack: Boolen(res.data.plotsinfonav.letourCheack)
                }
                $(".navWidth").css("width",(100 / truenum).toFixed(3)+"%")
                var btnAddactive = [
                    {
                        bool: res.data.plotsinfonav.uperMapCheack,
                        active: '#navbottom a[href="#plotsmap"]'
                    },{
                        bool: res.data.plotsinfonav.plotsYsCheack,
                        active: '#navbottom a[href="#plotsys"]'
                    },{
                        bool: res.data.plotsinfonav.plotsJsCheack,
                        active: '#navbottom a[href="#plotsjs"]'
                    },{
                        bool: res.data.plotsinfonav.serviceCheack,
                        active: '#navbottom a[href="#plotsser"]'
                    },{
                        bool: res.data.plotsinfonav.mapPainCheack,
                        active: '#navbottom a[href="#plotsdh"]'
                    },{
                        bool: res.data.plotsinfonav.letourCheack,
                        active: '#navbottom a[href="#plotslt"]'
                    }
                ]
                for (var i = 0; i < btnAddactive.length; i++) {
                    if(btnAddactive[i].bool == 'true'){
                        if(btnAddactive[i].active == '#navbottom a[href="#plotsser"]'){
                            $scope.getServices()
                        }
                        $(btnAddactive[i].active).tab('show');
                        break
                    }
                }
            }
        })
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

    $scope.getServices = function(){
        $http({
            url: "/api/getServices",
            method: "POST",
            data: {
                infoid:plots_id,
            }
        }).then(function (res) {
            $('#navContent a[href="#all"]').tab('show');
            $scope.Plotsinfoservice_name = res.data.Plotsinfoservice_name;
            $scope.Plotsinfoservice_list = res.data.Plotsinfoservice_list
        })
    }

    $scope.getServicesType = function(type){
        $http({
            url: "/api/getServicesType",
            method: "POST",
            data: {
                id:plots_id,
                type:type,
            }
        }).then(function (res) {
            $scope.Plotsinfoservice_list = res.data.Plotsinfoservice_list
        })
    }

    $scope.showUmap = function(url){
        $scope.UperMapin = $sce.trustAsResourceUrl(url)
    }

    $scope.backs = function(){
    	window.location.href = "/?poID=" + poID
    }

    $scope.iscopy = function(url){
        console.log(url)
        window.clipboardData.setData("Text",url);
    }

    $('#navbottom a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $(e.target).addClass("dots")// newly activated tab
        $(e.relatedTarget).removeClass("dots")// previous active tab
    })

    $('#navContent a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        $(e.target).addClass("dots")// newly activated tab
        $(e.relatedTarget).removeClass("dots")// previous active tab
    })

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