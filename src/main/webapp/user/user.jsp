<%@page pageEncoding="UTF-8" %>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    </head>
    <body>
    <div id="main" style="width: 600px;height:400px;"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '上半年用户注册趋势'
            },
            tooltip: {},
            legend: {
                data:['男','女']
            },
            xAxis: {
                data: ["一月","二月","三月","四月","五月","六月"]
            },
            yAxis: {},

        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        $.ajax({
            url:"${pageContext.request.contextPath}/user/findCound",
            type:"get",
            datatype:"json",
            success:function (data) {
                myChart.setOption({
                    series: [{
                        name:'男',
                        type:'bar',
                        data:data.men
                    },{
                        name:'女',
                        type:'bar',
                        data:data.women
                    }]
                })

            }





        })
    </script>
    </body>
</html>