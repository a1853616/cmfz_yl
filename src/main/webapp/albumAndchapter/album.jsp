<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    $(function () {
        $("#albumTable").jqGrid(
            {
                url: '${pageContext.request.contextPath}/album/findAll',
                datatype: "json",
                height:500,
                colNames: ['编号', '标题',  '章节数', '得分','作者','简介','创建时间','封面'],
                colModel: [
                    {name: 'id',hidden:true},
                    {name: 'title',editable:true},
                    {name: 'count'},
                    {name: 'score', editable:true},
                    {name: 'author', editable:true,edittype:"select",editoptions:{dataUrl:"${pageContext.request.contextPath}/start/getAllStarForSelect"},formatter:function (value,option,rows) {
                        return rows.start.nickname;

                        }},
                    {name: 'brief',editable:true },
                    {name: 'creat_date'},
                    {name: 'cover', editable:true,edittype:"file",formatter:function (value,option,rows) {
                            return "<img style='width:100px;height:60px;' src='${pageContext.request.contextPath}/albumAndchapter/img/"+rows.cover+"'>";}
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
                editurl:"${pageContext.request.contextPath}/album/edit",
                caption: "专辑管理",
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
                            url : "${pageContext.request.contextPath}/chapter/findAll?albumId=" + id,
                            datatype : "json",
                            colNames : [ '编号', '名称','歌手', '大小', '时长','上传时间','在线播放'],
                            colModel : [
                                {name:"id",hidden: true},
                                {name : "title", editable:true,edittype:"file" },
                                {name:'name',editable:true},
                                {name : "size",},
                                {name : "duration",},
                                {name : "creat_date" },
                                {name:"redio",width:200,formatter:function (value,option,rows) {
                                      return"<audio  controls>\n"+
                                      "<source src='${pageContext.request.contextPath}/albumAndchapter/files/"+rows.title+"'>\n"+
                                         "</audio>";
                                    }}
                            ],
                            styleUI:"Bootstrap",
                            rowNum : 2,
                            pager : pager_id,
                            autowidth:true,
                            height : '100%',
                            editurl:"${pageContext.request.contextPath}/chapter/edit?albumId="+id
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : true,
                            del : false,
                            search:false
                    },{},{
                        //控制添加
                            closeAfterAdd:true,
                            afterSubmit:function(data){
                                var status = data.responseJSON.status;
                                if(status){
                                    var message = data.responseJSON.message;
                                    $.ajaxFileUpload({
                                        url:"${pageContext.request.contextPath}/chapter/upload",
                                        data:{id:message,albumId:id},
                                        fileElementId:"title",
                                        type:"post",
                                        success:function(){
                                            $("#albumTable").trigger("reloadGrid");

                                        }
                                    })
                                }
                                return "123";
                            }
                        }
                        );
                },

            }).jqGrid('navGrid', '#page', {
            add : true,
            edit : false,
            del : false,
            search:false
        },{},{
            closeAfterAdd:true,
            afterSubmit:function(data){
                var status = data.responseJSON.status;
                var message = data.responseJSON.message;
                if(status){
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/album/upload",
                        data:{id:message},
                        fileElementId:"cover",
                        type:"post",
                        success:function(){
                            $("#albumTable").trigger("reloadGrid");

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
    <h1>专辑管理</h1>
</div>
<table id="albumTable"></table>
<div id="page" style="height: 40px"></div>