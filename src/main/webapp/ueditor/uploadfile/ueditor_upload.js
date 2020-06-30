var editor;
var type = "";//表示显示哪一个的图片
editor = UE.getEditor("upload_ue");
console.log(editor,"editor");
editor.ready(function () {
	console.log(editor,"editor000000000--");
	editor.hide();
   // editor.hide();
    editor.addListener('beforeInsertImage', function (t, arg) {
    	console.log(arg,"arg");
       /* var imagePath = "";
        var imgelement = "";
        for (var i = 0; i < arg.length; i++) {
            imagePath += arg[i].src + "*";
        }
        $("#imgpath").val(imagePath);
    
        if ($("#imgpath").val() != "") {
            $("#alertinfo").modal("toggle");
           // showUploadImgs() ;//显示电子发票，清单
        	
        }*/
    })
    editor.addListener('afterUpfile', function (t, arg) {
      
        var filePath = "";
        var filename = "";//文件
        var fileOriginName = "";//文件原始名字
        var imagename = "";//图片
        var filelement = "";
        for (var i = 0; i < arg.length; i++) {
        console.log(arg[i],"arg[i]");
            filePath += arg[i].url + "*";
           
            if (isImages(arg[i].title)) {
            	imagename += arg[i].url + "*";
            } else {
            	filename += arg[i].url + "*";
            	fileOriginName += arg[i].title + "*";
            }  
        }
     
       /* $("#filepath").val(filePath);
        $("#contractNames").val(filename);//文件地址
        $("#contractOriginNames").val(fileOriginName);//文件原始名字
        $("#contractImages").val(imagename);//图片地址
        $("#uploadContractPath").val(filePath);
        if ($("#filepath").val() != "") {
            $("#alertinfo").modal("toggle");
        }*/
    })
});


//点击上传每一个的图片
function upImage(type) {
	console.log(type,"type");
	$("#uploadTypes").val(type);
    var myImage = editor.getDialog("insertimage");
    myImage.open();
}

function upFiles() {
	console.log(editor,"editor666666");
    var myFiles = editor.getDialog("attachment");
    myFiles.open();
}

function getbig(url) {
    $("#bigimg").empty();
    if(url!=""){
        var bightml = "<img src='" + url + "'class=\"am-thumbnail\"/>";
        $("#bigimg").append(bightml);
        $("#alertBimg").modal("toggle");
    }
}
//获取文件格式判断是否是图片
function isImages(upFileName){
	var index1=upFileName.lastIndexOf(".");
	var index2=upFileName.length;
	var suffix=upFileName.substring(index1+1,index2);//后缀名
	if (suffix == "png" || suffix == "jpeg" || suffix == "bmp" || suffix == "jpg" || suffix == "PNG" || suffix == "JPEG" || suffix == "BMP" || suffix == "JPG" ) {
	  return true;
	}
	return  false;
}