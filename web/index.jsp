<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <title>onlineDisk</title>

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

        <script>
        </script>

        <style>

            #head{
                height: 80px;
            }
            body{
                background-color: rgb(5,22,38);
            }

            .btn-size-login{
                margin-top:300px;
                bottom: 20px;
                float: right;
                margin-right: 50px;
                border:2px solid rgb(215,55,62);
                background-color: rgb(215,55,62);
                box-shadow: 10px 10px 5px #6D3535;
            }

            .btn-size-register{
                margin-top:300px;
                float: right;
                background-color: rgb(96,193,182);
                border:2px solid rgb(96,193,182);
                box-shadow: 10px 10px 5px #246B75;

            }

            .btn{
                margin-left: 40px;
                padding-top :35px;
                border-radius: 25px;
                width: 100px;
                height: 100px;
            }

            span{
                color: white;
                font-size: 20px;
                font-style: unset;
            }

            #div-top{
                padding-left: 30px;
                padding-right: 30px;
            }

        </style>
    </head>
    <body>
        <div class="jumbotron" id = "div-top" >
            <h1 id = "head">欢迎来到您专属的在线网盘</h1>
            <p>完全免费的体验</p>
        </div>
        <a href="${pageContext.request.contextPath}/loginForward" class="btn btn-size-login" ><span class="glyphicon glyphicon-log-in">登陆</span ></a>
        <a href="${pageContext.request.contextPath}/registerForward" class="btn btn-size-register"><span class="glyphicon glyphicon-pencil" aria-hidden="true">注册</span></a>

    </body>
</html>






