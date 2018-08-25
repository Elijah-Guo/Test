<html>
<head>
    <title>hello springboot</title>
</head>
<body>
<table border="1">
    <tr>
        <th>用户id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>姓名</th>
    </tr>
        <#list userList as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.name}</td>
            </tr>
        </#list>
</table>
</body>

<img src="/img/1.jpg">
</html>