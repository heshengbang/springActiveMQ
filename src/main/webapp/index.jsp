<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>springActiveMQ</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#queue").click(function(){
                var message = $("#queueMessage").val();
                console.log(message);
                if (message !== "") {
                    $.post('<c:url value="/springActiveMQ/activemq/queueSender"/>', {
                        message: message
                    }, function (data, status) {
                        console.log("数据: \n" + data + "\n状态: " + status);
                        if (status === 'success') {
                            $("#queueMessage").val("");
                        } else {
                            alert("发送失败：" + "状态 " + status);
                        }
                    });
                }
            });

            $("#topics").click(function(){
                var message = $("#topicsMessage").val();
                console.log(message);
                if (message !== "") {
                    $.post('<c:url value="/springActiveMQ/activemq/topicSender"/>', {
                        message: message
                    }, function (data, status) {
                        console.log("数据: \n" + data + "\n状态: " + status);
                        if (status === 'success') {
                            $("#topicsMessage").val("");
                        } else {
                            alert("发送失败：" + "状态 " + status);
                        }
                    });
                }
            });
        });
    </script>
</head>
<body>
<h1>Hello ActiveMQ</h1>
<form role="form">
    <div class="row">
        <div class="col-lg-6">
            <div class="input-group">
                <input id="queueMessage" type="text" class="form-control">
                <span class="input-group-btn">
						<button id="queue" class="btn btn-info" type="button">
							发送Queue消息
						</button>
                </span>
            </div>
            <br>
            <div class="input-group">
                <input id="topicsMessage" type="text" class="form-control">
                <span class="input-group-btn">
						<button id="topics" class="btn btn-info" type="button">
							发送Topic消息
						</button>
                </span>
            </div>
        </div>
    </div>
</form>
</body>
</html>