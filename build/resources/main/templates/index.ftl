<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/static/style.css">
    <title>Ktor with FreeMarker</title>
</head>
<body>

    <h1>${message}</h1>

    <form action="/submit-name" method="post">
        <input type="text" name="username" placeholder="Enter Name" />
        <input type="submit" value="Submit" />
    </form>

</body>
</html>
