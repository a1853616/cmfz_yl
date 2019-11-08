<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--jqgrid--%>
<script>
    $(function () {
        $("#bannerTable").jqGrid({
            url : '${pageContext.request.contextPath}/banner/findAll',
            datatype : "json",
            colNames : [ '编号', '名称', '封面',  '状态','上传日期'],
            colModel : [
                {name : 'id',editable:false},
                {name : 'bannername',editable:true},
                {name : 'cover',editable:true,edittype:"file",formatter:function (value,option,rows) {
                        return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/banner/imger/"+rows.cover+"'>";}
                    },
                {name : 'status',editable:true,edittype:"select",editoptions:{value:"正常:正常;冻结:冻结"}},
                {name : 'create_date'}
            ],
            height:250,
            autowidth:true,
            styleUI:"Bootstrap",
            rowNum : 3,
            rowList : [ 3,5,10 ],
            pager : '#pager',
            sortname : 'id',
            viewrecords : true,
            caption : "轮播图列表",
            editurl : "${pageContext.request.contextPath}/banner/edit"
        }).navGrid("#pager", {edit : true,add : true,del : false,search:false},{
            //控制修改
            closeAfterEdit:true,
            beforeShowForm:function (fmt) {
                fmt.find("#cover").attr("disabled",true);
            }
        },{
            //控制添加
            closeAfterAdd:true,
            afterSubmit:function (data) {
                console.log(data);
                var status = data.responseJSON.status;
                var id = data.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/banner/upload",
                        type:"post",
                        fileElementId:"cover",
                        data:{id:id},
                        success:function (response) {
                            //自动刷新jqgrid表格
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    });
                }
                return "123";
            }
        });
    })
</script>

<!--页头-->
<div class="page-header" style="margin-top: -20px;">
    <h1>轮播图管理管理</h1>
</div>
<!--创建表格-->
<table id="bannerTable"></table>
<!--分页-->
<div id="pager" style="height: 40px"></div>
