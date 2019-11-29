<!doctype html>
<html lang="en">
<head>
</head>

<body>
<p>The name is ${name}</p>
<p>My age is ${age}</p>
My skills are:
<list>
    <#list skills as skill>
        <li>
            <p>
                ${skill}
            </p>
        </li>
    </#list>
</list>

</body>
</html>
