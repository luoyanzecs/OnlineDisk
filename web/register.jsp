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
            .remind-corr{
                color: #00a500;
            }

            #span-err{
                float: right;
                font-style: inherit;
                padding-top: 60px;
                text-align: center;
            }

        </style>

        <script>
            flagUsername = false;
            flagPassword = false;
            flagTel = false;
            reg_username = /[0-9A-Za-z-_]{6,12}$/;
            reg_tel = /0?(13|14|15|18|17)[0-9]{9}/;
            reg_password =/[a-zA-Z0-9]{6,16}/;




            $(function () {
                $("#inputName").blur(function () {
                    var value = $(this).val();
                    if (value != "") {
                        flagUsername = reg_username.test(value);
                        if (flagUsername) {
                            $.get("registerCheck", {username:value}, function (data) {
                                if (data.isExsit){
                                    $("#pName").removeClass().addClass("remind-err");
                                    $("#pName").addClass("glyphicon glyphicon-remove");
                                    $("#pName").text(data.msg);
                                }else{
                                    console.log(data.msg);
                                    $("#pName").text("");
                                    $("#pName").removeClass();
                                    $("#pName").addClass("glyphicon glyphicon-ok");
                                    $("#pName").addClass("remind-corr");
                                }

                            }, "json");

                        } else {
                            $("#pName").removeClass().addClass("remind-err");
                            $("#pName").addClass("glyphicon glyphicon-remove");
                        }
                    }
                });

                $("#inputName").focus(function () {
                    $("#pName").removeClass();
                    $("#pName").text("6-12位的字符(包括'-'和'_')和数字");
                    $("#pName").addClass("remind-init")
                });

                $("#inputPassword").blur(function () {
                    var value = $("#inputPassword").val();
                    if (value != "") {
                        flagPassword = reg_password.test(value);
                        if (flagPassword) {
                            $("#pPassword").text("");
                            $("#pPassword").removeClass();
                            $("#pPassword").addClass("glyphicon glyphicon-ok");
                            $("#pPassword").addClass("remind-corr");
                        } else {
                            $("#pPassword").removeClass().addClass("remind-err");
                            $("#pPassword").addClass("glyphicon glyphicon-remove");
                        }
                    }
                });

                $("#inputPassword").focus(function () {
                    $("#pPassword").removeClass();
                    $("#pPassword").text("6到16位字母和数字");
                    $("#pPassword").addClass("remind-init")
                });

                $("#inputTel").blur(function () {
                    var value = $("#inputTel").val();
                    if (value != "") {
                        flagTel = reg_tel.test(value);
                        if (flagTel) {
                            $("#pTel").text("");
                            $("#pTel").removeClass();
                            $("#pTel").addClass("glyphicon glyphicon-ok");
                            $("#pTel").addClass("remind-corr");
                        } else {
                            $("#pTel").removeClass().addClass("remind-err");
                            $("#pTel").addClass("glyphicon glyphicon-remove");
                        }
                    }

                });

                $("#inputTel").focus(function () {
                    $("#pTel").removeClass();
                    $("#pTel").text("中国大陆手机号");
                    $("#pTel").addClass("remind-init")
                });

                $("#inputPasswordCheck").blur(function () {
                    var value1 = $("#inputPasswordCheck").val();
                    if (value1 != "") {
                        var value2 = $("#inputPassword").val();
                        if (value1 == value2) {
                            $("#pPasswordCheck").text("");
                            $("#pPasswordCheck").removeClass();
                            $("#pPasswordCheck").addClass("glyphicon glyphicon-ok");
                            $("#pPasswordCheck").addClass("remind-corr");
                            flagPassword = true;
                        } else {
                            $("#pPasswordCheck").removeClass().addClass("remind-err");
                            $("#pPasswordCheck").addClass("glyphicon glyphicon-remove");
                            flagPassword = false;
                        }
                    }
                });

                $("#inputPasswordCheck").focus(function () {
                    $("#pPasswordCheck").removeClass();
                    $("#pPasswordCheck").text("请重新输入密码");
                    $("#pPasswordCheck").addClass("remind-init")
                });

                $("#form-all").submit(function () {
                    console.log(flagPassword);
                    console.log(flagPassword);
                    console.log(flagTel);
                    return flagUsername && flagPassword && flagTel;
                })

            })
        </script>
    </head>
    <body class="">
        <div id="div-left" class="" >
            <h1>用户注册</h1>
            <p></p>
        </div>
        <div >
            <form action="${pageContext.request.contextPath}/register" id = "form-all"  method="post">
                <div class="form-group">
                    <label for="inputName">用户名</label>
                    <input type="text" class="form-control" id="inputName" placeholder="Username" name="username" required value="${users.username}">
                    <p>&nbsp;<span class="remind-init" id="pName">6-12位的字符(包括'-'和'_')和数字</span></p>
                </div>
                <div class="form-group">
                    <label for="inputPassword">密码</label>
                    <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" required >
                    <p>&nbsp;<span class="remind-init" id="pPassword">6到16位字母和数字</span></p>
                </div>
                <div class="form-group">
                    <label for="inputPasswordCheck">确认密码</label>
                    <input type="password" class="form-control" id="inputPasswordCheck" placeholder="Password" required >
                    <p>&nbsp;<span class="remind-init" id="pPasswordCheck">请重新输入密码</span></p>
                </div>
                <div class="form-group">
                    <label for="inputTel">电话</label>
                    <input type="text" class="form-control" id="inputTel" placeholder="Telephone" name="tel" required value="${users.tel}">
                    <p>&nbsp;<span class="remind-init" id="pTel">中国大陆手机号</span></p>
                </div>

                <button type="submit" class="btn btn-primary btn-submit " id="btn">
                    <span id="span-text">提交</span>
                </button>
                <c:if test="${not empty users}">
                    <span class="remind-err glyphicon glyphicon-comment" id = "span-err" >注册失败</span>
                </c:if>
                <div id = "div-blank" ></div>
            </form>
        </div>

    </body>

    <footer>
        <p>为保障您的隐私,服务器的所有信息将会进行加密</p>
        <p>联系我 : 1421286905@qq.com</p>
    </footer>

</html>
