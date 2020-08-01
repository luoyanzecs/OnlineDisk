<%--
  Created by IntelliJ IDEA.
  User: luoyanze
  Date: 2020/7/29
  Time: 9:52 下午
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
        <title>主页面</title>

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
            $(function () {
                $("#del-select").click(function () {
                    $("#form-table").submit()
                });


                $("#firstCheckbox").click(function () {
                    var elementsByName = document.getElementsByName("filename");
                    for (let i = 0; i < elementsByName.length; i++) {
                        elementsByName[i].checked = this.checked;
                    }
                });
            })
        </script>

        <style>
            body{
                background-color: rgb(5,22,38);
            }

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

            #div-center{
                width: 80%;
                background-color: rgb(242,162,65);
                border-radius: 15px ;
                padding: 20px 20px 40px;
                margin: auto;
            }

            #div-head{
                margin: 60px 10%;
                width: 100%;
                color: #C8C8C8;
            }

            #div-table{
                background-color: white;
                padding: 10px 25px;
                border-radius: 15px;
                border: solid rgb(253,255,252);
            }

            .btn-comm{
                font-size: 25px;
                margin: 7px;
                height: 40px;
                width: 45px;
                border-radius: 15px;
                border: solid 2px rgb(215,55,62);
                background-color: rgb(215,55,62);
                color: white;
            }


            .span-li-right{
                padding-top: 7px;
                padding-right: 2px;;
                border-radius: 100%;
                background-color: rgb(215,55,62);
                width: 30px;
                height: 30px;
                margin: 5px 30px 5px 5px;
                text-align: center;
                float: left;
                font-size: 18px;
                color: white;
            }

            .span-li{
                padding-top: 4px;
                border-radius: 100%;
                background-color: rgb(215,55,62);
                width: 30px;
                height: 30px;
                margin: 5px;
                text-align: center;
                list-style-type:none;
                float: left;
                font-size: 20px;
                color: white;
            }

            a{
                color: white;
            }

            th{
                height: 35px;
                border-bottom: solid grey 3px;
                font-size: 20px;
            }

            td{
                height: 30px;
                border-bottom: solid #aeaeae 1px;
            }

            input[type="checkbox"] {
                width: 20px;
                height: 20px;
                text-align: center;
                vertical-align: middle;
            }

            #div-li{
                width: 100%;
            }

            #div-blank{
                height:200px;
            }


        </style>
    </head>

    <body>

        <div id = "div-head">
            <h1>${users.username},您好</h1>
        </div>

        <div id = "div-center">
            <div class="btn-group"  >
                <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/selectAll'" class="btn-comm" id="btn-look"><span class="glyphicon glyphicon-search"></span></button>
                <button type="button"  class="btn-comm" id="btn-upload"><span class="glyphicon glyphicon glyphicon-upload"></span></button>
                <%--<button type="button"  class="btn-comm" id="btn-mod"><span class="glyphicon glyphicon-pencil"></span></button>--%>
            </div>
            <br>
            <br>
            <div id = "div-table">
                <form action="${pageContext.request.contextPath}/deleteFile" method="get" id="form-table">
                    <table width="100%">
                        <tr>
                            <th width="10%"><input type="checkbox" id="firstCheckbox"></th>
                            <th width="15%"><span class="glyphicon glyphicon-triangle-bottom"></span></th>
                            <th width="50%"><span class="glyphicon glyphicon-file"></span></th>
                            <th width="25%"><span class="glyphicon glyphicon-wrench"></span></th>
                        </tr>


                        <c:forEach items="${fileList}" var="file" varStatus="f">
                            <tr>
                                <td><input type="checkbox" value="${file.filename}" name="filename"></td>
                                <td>${f.count}</td>
                                <td>${file.filename}</td>
                                <td>
                                    <a href="#"><span class="glyphicon glyphicon-trash span-li-right"></span></a>
                                    <a href="#"><span class="glyphicon glyphicon-download-alt span-li-right"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
            <%--提交一个filename数组--%>
            <button type="button"  class="btn-comm" id="del-select">
                <span class="glyphicon glyphicon-trash"></span>
            </button>
            <br>
            <br>
            <br>
            <div id = "div-li">
                <%--currentPage表示的是当前的页面--%>
                <c:if test="${currentPage !=1 }">
                    <a ref="${pageContext.request.contextPath}/searchPage?page=${currentPage-1}&rows=10" >
                        <span class="glyphicon glyphicon-chevron-left span-li"></span>
                    </a>
                </c:if>
                <c:if test="${currentPage ==1 }">
                    <span class="glyphicon glyphicon-chevron-left span-li"></span>
                    </a>
                </c:if>

                <%--传入对象size 里面存折当前页面的文件个数--%>
                <c:forEach begin="1" end="${totalPage}" var="i">
                    <c:if test="${totalPage == i}">
                        <span class="span-li"><a class="active" href="${pageContext.request.contextPath}/searchPage?page=${i}&rows=10">${i}</a></span>
                    </c:if>
                    <c:if test="${totalPage != i}">
                        <span class="span-li"><a href="${pageContext.request.contextPath}/searchPage?page=${i}&rows=10">${i}</a></span>
                    </c:if>
                </c:forEach>



                <c:if test="${currentPage != maxPage }">
                    <a href="${pageContext.request.contextPath}/searchPage?page=${currentPage+1}&rows=10" >
                        <span class="glyphicon glyphicon-chevron-right span-li"></span>
                    </a>
                </c:if>
                <c:if test="${currentPage == maxPage }">
                    <span class="glyphicon glyphicon-chevron-right  span-li"></span>
                    </a>
                </c:if>
            </div>
            <br>
        </div>

        <div id="div-blank"></div>

    </body>

    <footer>
        <p>为保障您的隐私,服务器的所有信息将会进行加密</p>
        <p>联系我 : 1421286905@qq.com</p>
    </footer>
</html>
