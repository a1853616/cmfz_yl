<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
        $(function () {
            $("#startTable").jqGrid(
                {
                    url: '${pageContext.request.contextPath}/start/findAll',
                    datatype: "json",
                    height:200,
                    colNames: ['编号', '艺名', '真实姓名', '性别', '生日','头像'],
                    colModel: [
                        {name: 'id',hidden:true},
                        {name: 'nickname',editable:true},
                        {name: 'realname',editable:true},
                        {name: 'sex', editable:true,edittype:"select",editoptions:{value:"男:男;女:女"}},
                        {name: 'bir', editable:true,edittype:"date"},
                        {name: 'photo', editable:true,edittype:"file",formatter:function (value,option,rows) {
                                return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/starAnduser/img/"+rows.photo+"'>";}

                        },
                    ],
                    autowidth:true,
                    styleUI:"Bootstrap",
                    rowNum: 3,
                    rowList: [3, 5, 10,],
                    pager: '#page',
                    sortname: 'id',
                    viewrecords: true,
                    subGrid: true,
                    editurl:"${pageContext.request.contextPath}/start/edit",
                    caption: "明星管理",
                    subGridRowExpanded : function(subgrid_id, id) {
                        var subgrid_table_id, pager_id;
                        subgrid_table_id = subgrid_id + "_t";
                        pager_id = "p_" + subgrid_table_id;
                        $("#" + subgrid_id).html(
                            "<table id='" + subgrid_table_id
                            + "' class='scroll'></table><div id='"
                            + pager_id + "' class='scroll'></div>");
                        jQuery("#" + subgrid_table_id).jqGrid(
                            {
                                url : "${pageContext.request.contextPath}/user/findAll?startId=" + id,
                                datatype : "json",
                                colNames : [  '电话', '艺名', '省份','城市','头像','签名','性别' ],
                                colModel : [
                                    {name : "phone",  },
                                    {name : "yname",},
                                    {name : "province",},
                                    {name : "city",},
                                    {name : "headphoto",eidittype:"file",formatter:function (value,option,rows) {{
                                                return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/starAnduser/img/"+rows.headphoto+"'>";}
                                        }},
                                    {name:"sign"},
                                    {name:"sex"}
                                ],
                                styleUI:"Bootstrap",
                                rowNum : 2,
                                pager : pager_id,
                                autowidth:true,
                                height : '100%'
                                });
                        jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                            "#" + pager_id, {
                                edit : false,
                                add : false,
                                del : false,
                                search:false
                            });
                    },

                }).jqGrid('navGrid', '#page', {
                add : true,
                edit : false,
                del : false,
                search:false
            },{},{
                closeAfterAdd:true,
                afterSubmit:function(data){
                    console.log(data);
                    var status = data.responseJSON.status;
                    var message = data.responseJSON.message;
                    if(status){
                        $.ajaxFileUpload({
                            url:"${pageContext.request.contextPath}/start/upload",
                            data:{id:message},
                            fileElementId:"photo",
                            type:"post",
                            success:function(){
                                $("#startTable").trigger("reloadGrid");

                            }
                        })
                    }
                    return "123";
                         }
                    });

        })
</script>
<%--页头--%>
<div>
    <h1>明星管理管理</h1>
</div>
<table id="startTable"></table>
<div id="page" style="height: 40px"></div>