<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <div class="title">
                        ${top1.title}
                    </div>
                    <div class="image">
                        <img src="images/${top1.image}" alt=""/>
                    </div>
                    <div class="text">
                        ${top1.description}
                    </div>
                    <div class="signature">
                        By ${top1.author} | ${top1.timePost}
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
