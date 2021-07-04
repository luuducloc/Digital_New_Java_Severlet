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
                        ${detail.title}
                    </div>
                    <div class="image">
                        <img src="images/${detail.image}" alt=""/>
                    </div>
                    <div class="text">
                        ${detail.description}
                    </div>
                    <div class="signature">
                        By ${detail.author} | ${detail.timePost}
                    </div>
                </div>
                <jsp:include page="Right.jsp"/>
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
