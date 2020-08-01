<%--
  Created by IntelliJ IDEA.
  User: luoyanze
  Date: 2020/7/31
  Time: 8:40 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>onlineDisk.register</title>

        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="js/bootstrap.min.js"></script>

        <style>
            footer{
                padding: 10px;
                color: grey;
                position:fixed;
                bottom:0;
                width:100%;
                height:50px;
                line-height:13px;
                text-align:center;
                background-color: rgb(96,193,182);
            }

            body{
                background-color: rgb(5,22,38);
            }

            #form-all{
                padding: 20px;
                border-radius: 15px ;
                float: right;
                margin-top: 150px;
                width: 350px;
                height: 500px;
                margin-right: 15%;
                background-color: rgb(242,162,65);
            }

            .btn-submit{
                text-align: center;
                padding: 6px;
                margin-top: 35px;
                width: 80px;
                height: 40px;
            }


            #span-text{
                font-size: 20px;
            }

            #div-left{
                width: 450px;
                color: #C8C8C8;
                margin-top: 120px;
                float: left;
                margin-left: 35px;
            }

            .remind-init{
                color: rgb(115,115,115);
            }

            #div-blank{
                height:200px;
            }

            .remind-err{
                color: rgb(213,55,62);
            }

            #span-err{
                float: right;
                font-style: inherit;
                padding-top: 60px;
                text-align: center;
            }

        </style>

    </head>
    <body class="">
        <div id="div-left" class="" >
            <h1>登陆后进入网盘</h1>
            <p></p>
        </div>
        <div >
            <form action="${pageContext.request.contextPath}/login" id = "form-all" onsubmit="check()" method="post">
                <div class="form-group">
                    <label for="inputName">用户名</label>
                    <input type="text" class="form-control" id="inputName" placeholder="Username" name="username" required value="${users.username}">
                    <p>&nbsp;<span class="remind-init" id="pName">请输入用户名</span></p>
                </div>
                <div class="form-group">
                    <label for="inputPassword">密码</label>
                    <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" required >
                    <p>&nbsp;<span class="remind-init" id="pPassword">请输入密码</span></p>
                </div>

                <button type="submit" class="btn btn-primary btn-submit " id="btn">
                    <span id="span-text">提交</span>
                </button>
                <c:if test="${not empty users}">
                    <span class="remind-err glyphicon glyphicon-comment" id = "span-err" >登陆失败</span>
                </c:if>
            </form>
            <div id = "div-blank" ></div>
        </div>

    </body>

    <footer>
        <p>为保障您的隐私,服务器的所有信息将会进行加密</p>
        <p>联系我 : 1421286905@qq.com</p>
    </footer>

</html>

