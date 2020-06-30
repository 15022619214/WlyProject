A.controller("plotsshareController", ["$scope", "$http", "$timeout", function ($scope, $http, $timeout) {

    $(function(){
        $scope.getAllplotsisopen()
    })

    $scope.getAllplotsisopen = function(){
        $http({
            method: "GET",
            url: "/api/getAllplotsisopen",
        }).then(function (res) {
            console.log(res.data)
            $scope.pobjects = res.data.Plotsobjects_info
            $timeout(function(){
                $(".loading2").addClass("invisible")
            },5000)
        })
    }

    $scope.choseinfo = function(id){
    	window.location.href= window.location.origin + "?id=" + id
    }
}])