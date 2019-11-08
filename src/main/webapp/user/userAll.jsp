<%@page pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#userTable").jqGrid({
            url : "${pageContext.request.contextPath}/user/findAll",
            datatype : "json",
            colNames : [ '编号', '电话', '名字',  '密码','盐',"艺名",'省份','城市','签名','头像'],
            colModel : [
                {name : 'id',hidden: true},
                {name : 'phone'},
                {name : 'username' },
                {name : 'password'},
                {name : 'sait'},
                {name : 'yname'},
                {name : 'province'},
                {name : 'city'},
                {name : 'sign'},
                {name : 'headphoto',eidittype:"file",formatter:function (value,option,rows) {{
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/starAnduser/img/"+rows.headphoto+"'>";}
                    }}
            ],
            autowidth:true,
            styleUI:"Bootstrap",
            rowNum: 3,
            rowList: [3, 5, 10,],
            pager: '#page',
            viewrecords: true,
            subGrid: true,
            caption: "用户管理",
        }).navGrid("#page", {edit : false,add : false,del : false,search:false});

    })
    function openModal(oper,id) {
        if ("add" == oper) {
            $("#user-id").val("");
            $("#user-username").val("");
            $("#user-password").val("");
            $("#user-photo").val("");
            $("#user-province").val("");
            $("#user-city").val("");
            $("#user-sign").val("");
        }
        $("#user-modal").modal("show");
    }

    function save(id){
        var id = $("#user-id").val();
        var url = "${pageContext.request.contextPath}/article/edit";

        $.ajax({
            url:url,
            type:"post",
            data:$("#user-form").serialize(),
            datatype:"json",
            success:function () {

            }
        })
    }

</script>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#">所有用户</a></li>
    <li role="presentation"><a href="${pageContext.request.contextPath}/user/export" >导出用户</a></li>
    <li role="presentation"><a href="#" onclick="openModal('add','')">添加文章</a></li>
</ul>
<table id="userTable"></table>
<div id="page" style="height: 40px"></div>

<div id="user-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 683px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户注册</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="user-form">
                    <input type="hidden" name="id" id="user-id">
                    <div class="form-group">
                        <label for="user-username" class="col-sm-2 control-label">文章简介</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" class="form-control" id="user-username" placeholder="请输入文章简介">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="article-yname" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" name="yname" class="form-control" id="article-yname" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user-password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="text" name="password" class="form-control" id="user-password" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user-phone" class="col-sm-2 control-label">电话</label>
                        <div class="col-sm-10">
                            <input type="text" name="phone" class="form-control" id="user-phone" placeholder="请输入文章作者">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user-province" class="col-sm-2 control-label">省份</label>
                        <div class="col-sm-10">
                            <input type="text" name="province" class="form-control" id="user-province" placeholder="请输入文章简介">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user-city" class="col-sm-2 control-label">城市</label>
                        <div class="col-sm-10">
                            <input type="text" name="city" class="form-control" id="user-city" placeholder="请输入城市">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="user-sign" class="col-sm-2 control-label">文章简介</label>
                        <div class="col-sm-10">
                            <input type="text" name="sign" class="form-control" id="user-sign" placeholder="请输入文章简介">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="save()" >提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->