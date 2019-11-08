<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
    <head>
        <meta charset="UTF-8">
        <!--当前页面更好支持移动端-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
        <!--bootstrapcss样式-->
        <link rel="stylesheet" href="statics/boot/css/bootstrap.min.css">
        <link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
        <%--js核心jia--%>
        <script src="statics/boot/js/jquery-3.3.1.min.js"></script>
        <%--bootstrap的核心js--%>
        <script src="statics/boot/js/bootstrap.min.js"></script>
        <%--引入i18njs--%>
        <script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
        <script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
        <script src="statics/jqgrid/js/ajaxfileupload.js"></script>
        <script charset="utf-8" src="kindeditor/kindeditor-all-min.js"></script>
        <script charset="utf-8" src="kindeditor/lang/zh-CN.js"></script>
    <%----%>
    </head>
    <body>
    <%--导航栏--%>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
                   <!--导航标题-->
                   <div class="navbar-header">
                       <a class="navbar-brand" href="#">吃明发周 <small>v1.0</small></a>
                   </div>
                    <div>
                   <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎：</a></li>
                    <li class="dropdown">
                        <a href="${pageContext.request.contextPath}/admin/logout" >退出登录<span class="caret"></span></a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <!--页面主体内容-->
    <div class="container">
        <div class="row">
            <div class="col-sm-2">
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true" align="center">

                    <div class="panel panel-success">
                        <div class="panel-heading" role="tab" id="headingOne5">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne5"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    轮播图管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne5" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne5">
                            <ul class="nav nav-pills nav-stacked">
                                <br>
                                <li>
                                    <button class="btn btn-success">
                                        <a href="javascript:$('#content').load('banner/banner1.jsp')">轮播图列表</a>
                                        <br>
                                    </button>
                                </li>
                                <br>
                            </ul>
                        </div>
                    </div>

                    <div class="panel panel-warning">
                        <div class="panel-heading" role="tab" id="headingOne4">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne4"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    专辑管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne4" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne4">

                            <ul class="nav nav-pills nav-stacked">
                                <br>
                                <li>
                                    <button class="btn btn-warning">
                                        <a href="javascript:$('#content').load('albumAndchapter/album.jsp')">专辑列表</a>
                                        <br>
                                    </button>
                                </li>
                                <br>
                            </ul>
                        </div>
                    </div>

                    <div class="panel panel-info">
                        <div class="panel-heading" role="tab" id="headingOne1">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne1"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    用户管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne1" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne1">

                            <ul class="nav nav-pills nav-stacked">
                                <br>
                                <li>
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#content').load('user/userAll.jsp')">用户列表</a>
                                    </button>
                                    <br>
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#content').load('user/user.jsp')">用户注册趋势</a>
                                    </button>
                                </li>
                                <br>
                            </ul>
                        </div>
                    </div>

                    <div class="panel panel-danger">
                        <div class="panel-heading" role="tab" id="headingOne3">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne3"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    章节管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne3" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne3">

                            <ul class="nav nav-pills nav-stacked">
                                <br>
                                <li>
                                    <button class="btn btn-danger">
                                        <a href="javascript:$('#content').load('article/article.jsp')">章节列表</a>

                                        <br>
                                    </button>
                                </li>
                                <br>
                            </ul>
                        </div>
                    </div>


                    <div class="panel panel-primary">
                        <div class="panel-heading" role="tab" id="headingOne2">
                            <h4 class="panel-title">
                                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne2"
                                   aria-expanded="true" aria-controls="collapseOne">
                                    明星管理
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne2" class="panel-collapse collapse" role="tabpanel"
                             aria-labelledby="headingOne2">
                            <ul class="nav nav-pills nav-stacked">
                                <br>
                                <li>
                                    <button class="btn btn-info">
                                        <a href="javascript:$('#content').load('starAnduser/start.jsp')">明星列表</a></button>
                                    <br>
                                    </button>
                                </li>
                                <br>
                            </ul>
                        </div>
                    </div>
                </div>
                </br></br></br></br></br></br></br></br></br></br></br></br></br></br>
            </div>
            <div id="content" class="col-sm-10">

                <div class="jumbotron">
                    <h3 class="text-nowrap">欢迎来到持名法州后台管理系统</h3>


                </div>


                <div>
                    <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                        <!-- Indicators -->
                        <ol class="carousel-indicators">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        </ol>

                        <!-- Wrapper for slides -->
                            <div class="item">
                                <img style="width: 950px;height: 470px"
                                     src="${pageContext.request.contextPath}/banner/imger/g.jpg" alt="...">
                                <div class="carousel-caption">
                                </div>
                            </div>
                        </div>

                        <!-- Controls -->
                        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                            <span class="sr-only">Previous</span>
                        </a>
                        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                            <span class="sr-only">Next</span>
                        </a>
                    </div>
                </div>


            </div>

        </div>


    </div>

    <nav class="navbar navbar-default navbar-bottom">
        <div class="container ">

            <h6 class="text-center">@百知教育baizhi@zparkhr.com.cn</h6>

        </div>
    </nav>

    </body>
</html>