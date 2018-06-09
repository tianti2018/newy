<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html style="font-size:23px;"><head>
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-control" content="no-cache,no-store,must-revalidate">
    <meta http-equiv="Expires" content="0">
    <meta name="viewport" content="width=device-width" charset="utf-8">
    <meta name="format-detection" content="telephone=no">
    <meta name="robots" content="none">
    <title>快递查询</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
   
    <link href="http://wljl.lntjs.cn/weixin/zhongtong/css/1.css"  type="text/css" rel="stylesheet">
    <link href="http://wljl.lntjs.cn/weixin/zhongtong/css/2.css"  type="text/css" rel="stylesheet">
    <link href="http://wljl.lntjs.cn/weixin/zhongtong/css/3.css"  type="text/css" rel="stylesheet">
    <link href="http://wljl.lntjs.cn/weixin/zhongtong/css/4.css"  type="text/css" rel="stylesheet">
<style>
        img {
            border-style: none;
            border-color: inherit;
            border-width: 0px;
            height: 40px;
            width: 46px;
        }

        table.u-record tr td.img {
            background: url(../images/shutiao.jpg) center top no-repeat #fff;
        }

        table.u-record tr td.imj {
            background: url(../images/shutiao.jpg) center bottom no-repeat #fff;
            background-size: 2px 44%;
        }

        table.u-record tr td.imh {
            background: url(../images/shutiao.jpg) center top no-repeat #fff;
            background-size: 2px 44%;
        }
        /*width:10%;padding:20px 0;color:#666666; font-size:.65rem;background: url(../images/shutiao.jpg) center top no-repeat #fff; background-size:2px 50%*/
        i.u-middle-icon1 {
            color: #D6D6D6;
            font-size: .45rem;
            padding: 4px 0;
            background: #fff;
            display: block;
            margin: 0 auto;
            width: .9rem;
            height: .9rem;
            color: #348bff;
            font-size: .85rem;
            background: url("../images/st_icon.png") no-repeat;
            background-size: 100% 100%;
        }

        i.u-middle-icon2 {
            color: #D6D6D6;
            font-size: .45rem;
            padding: 4px 0;
            background: #fff;
            display: block;
            margin: 0 auto;
            width: .9rem;
            height: .9rem;
            color: #348bff;
            font-size: .85rem;
            background: url("../images/right_icon.png") no-repeat;
            background-size: 100% 100%;
        }

        .scroller {
            position: absolute;
            z-index: 1;
            -webkit-tap-highlight-color: rgba(0,0,0,0);
            width: 100%;
            padding: 0;
        }

        .u_room {
            width: 100%;
            position: absolute;
            left: 0;
            top: 110px;
            bottom: 60px;
            overflow: hidden;
            background: #fff;
            z-index: 2;
        }

        .ss {
            width: .625rem;
            height: .625rem;
            position: absolute;
            left: 1.8rem;
            top: -.1rem;
            color: #ff0000;
            display: none;
        }

        .codelb {
            left: 10px;
        }

        .abcdefgh {
            height: 100%;
            border: none;
        }
    </style>
</head>
<body>
<div class="u-search-c bodycont">
 	<div style="background: #c53f24; line-height: 2rem; color: #fff">快递单号：<span id="codelb" class="codelb">${orderNo }</span></div>
		<div id="over" style="overflow-y: scroll; width: 100%" class="abcdefgh">
			<c:if test="${not empty array}">
				<c:forEach items="${array }" var="item" varStatus="i">
                    <table border="0" cellpadding="0" cellspacing="0" class="u-record">
                        <tbody>
                            <tr class="">
                                <c:if test="${i.count ==1 }"><td class="imj"><span><i class="u-middle-icon1"></i></span></td></c:if>
                                <c:if test="${i.count !=1&&!i.last }"><td class="img"><span><i class="u-middle-icon1"></i></span></td></c:if>
                                <c:if test="${i.last }"><td class="imh"><span><i class="u-middle-icon2"></i></span></td></c:if>
                                <th style="width: 100%">
                                    <em class="detail_item">
                                        <i class="arrow"></i>
                                        <span class="left0">
                                            <span id="repeater_ctl00_label2">${item.AcceptStation }</span></span>
                                        <br>
                                        <em class="tx_1">
                                            <span id="repeater_ctl00_label3">${item.AcceptTime }</span></em>
                                    </em>
                                </th>
                            </tr>

                        </tbody>
                    </table>
                </c:forEach>
            </c:if>
            <c:if test="${empty array}">暂无物流信息!</c:if>
                   
        </div>
    </div>
    
    </body>
</html>