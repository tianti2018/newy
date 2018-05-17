<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="format-detection" content="telephone=no">
	<title>问题反馈</title>
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="http://webchat.7moor.com/javascripts/7moorSDKInit.js"></script>

<script type='text/javascript' src='http://webchat.7moor.com/javascripts/7moorInit.js?accessId=e9b00d80-f4e7-11e5-b08d-d1e469492502&autoShow=true' async='async'></script>
<script>
    var qimoAccessId = ""; //平台提供的接入号accessId
    //先加载sdk的js
    loadQimoSDK(qimoAccessId, function(){
        // todo 加载sdk完毕,可以弹出点击咨询的按钮了

       var peers = QimoSDK.getPeers(); //获取技能组列表

        // todo 图片发送初始化
        QimoSDK.initImageSendBtn({
            "btnId": "image-btn",  //必填
            "btnImage": "http://webchat.7moor.com/images/image-icon.png",  //可选，默认值：http://webchat.7moor.com/images/image-icon.png
            "btnWidth": "22px", //可选，按钮宽度

            "btnHeight": "20px", //可选，按钮高度

            'uploadProgress': function(evt){

            },

            'uploadComplete': function(result){
                if(result.errcode == 0){
                    // todo上传成功
                }else{
                    console.warn(result.errmsg);
                }
            },

            'uploadFailed': function(errObj){
                console.log(errObj.errmsg);
            },

            'uploadCanceled':function(evt){

            }
        });

        // todo表情初始化
        QimoSDK.initEmojiFaceSendBtn({
            "containerId":"face-con",  //必传，接收表情列表，回传<ul><li></li></ul>形式
            "textareaId": "chatbox_input",   //必传
            "faceClickFun":"" , //选填
        }, function(data){
            if(data.errcode == 0){
                //todo 点击按钮，弹出containerId所在层

            }else{
                
            }
        });

        //todo 点击咨询按钮，consultBtn为咨询按钮id
        $("#consultBtn").live("click", function(){
//多技能组时，默认进来的聊天框展示
QimoSDK.initDefaultConsultShow(function(result){
    if(result){
        $(".chattype").show();
        $("#mtypelist").html(result);
        $(".chatcontainer").show();
    }else{
        $(".chattype").hide();
        $("#mtypelist").html("");
        initChat();
    }
});
});

$("#mtypelist li").live("click", function(){
    $(".chattype").hide();
    var data = $(this).attr("ds_data");
    QimoSDK.setPeer(data);
    initChat();
});

//将原来的初始化方法提成一个方法调用
function initChat(){

            // todo 初始化聊天框信息
            QimoSDK.init(qimoAccessId, {fromUrl: window.location.href}, function(initData){
                $(".chatcontainer").show();
                //todo 初始化后的业务处理
                if(initData.errcode == 0){
                    console.info("连接成功");
                    /**
                     * 消息的监听，这个必须写在回调里头
                     */
                    QimoSDK.onMessage(function(data){
                        if(data.errcode == 0){
                            //todo 新消息过来的处理，数组
                            if(data.type == "newMsg"){

                                var msgArr = data.data;
                                for(var i =0; i<msgArr.length; i++){
                                    var msg = msgArr[i];
                                    if(msg.contentType == "text"){
                                        //todo 文本消息
                                        
                                    }else if(msg.contentType == "image"){
                                        //todo 图片消息

                                    }else if(msg.contentType == "video"){
                                        //todo 视频消息

                                    }else if(msg.contentType == "voice"){
                                        //todo 音频消息
                                    }

                                }

                            }
                            //todo 系统通知的处理
                            else if(data.type == "systemNotice"){
                                var notice = data.data;

                                //座席在线，弹出会话框
                                if(notice.type == "online"){
                                    // todo 客服在线
                                }
                                //座席不在线，转留言
                                else if(notice.type == "offline"){
                                    

                                }
                                //座席响应会话
                                else if(notice.type == "claim"){
                                    ;
                                }
                                //会话被座席结束，前端要有个响应
                                else if(notice.type == "finish"){
                                    // todo 会话被系统终结

                                }
                                //客服转接,即将由另外一位客服服务
                                else if(notice.type == "redirect"){
                                    // todo 会话转由另外一个座席处理
                                    

                                }

                                //机器人服务
                                else if(notice.type == "robot" && notice.kefu){
                                    // todo 机器人服务
                                   
                                }
                            } else if(data.type == "investigate"){ //满意度
			// todo 座席主动发起评价请求时，将选项显示在聊天框内，可参考demo内写法    
			//获取满意度选项：QimoSDK.webIMCsrDic，可自行组装样式
			//默认满意度样式:QimoSDK.defaultInvestigateStyle


                        }else{
                            console.info(data.errmsg);
                        }
                    });


                    QimoSDK.startChat(function(chatData){
                        // 获取初始化返回的数据{companyLogo:'xxxxxxxxxx', companyName:'xxxxxxxxxxxxxx'}
                        var companyLogo = chatData.data.companyLogo;
                        var companyName = chatData.data.companyName;

                        //todo 开启会话，获取基本信息
                        $("#logo").attr("src", companyLogo);
                        $("#ser-company").html(companyName);
                        QimoSDK.getHistoryMsg(function(data){
                            if(data.errcode == 0){
                                var msgs = data.data;
                                if(!msgs){msgs = []}
                                
                            }
                        });
                    });


                }else if(initData.errcode == 1){
                    console.info("连接认证失败");
                    pageUtils.showNoticeMsg("连接认证失败", 5000);
                }



            });

        });
}
            // 发送消息的接口
            // showHtml:是否支持html， notShow:用户端是否展示这条信息
            QimoSDK.sendTextMsg({content: content,showHtml: false, notShow: false}, function(data){
                if(data.errcode == 0){
                    console.info("发送成功");
                }else if(data.errcode == 1){
                    console.info("消息不能为空");
                }else if(data.errcode == 2){
                    console.info("会话已被关闭，不能回复");
                }else if(data.errcode == 3){
                    console.info("消息发送失败");
                }else if(data.errcode == -1){
                    console.info("请求失败");
                }
            });
        

    });

</script>


</head>
<body>
	
</body>
</html>
