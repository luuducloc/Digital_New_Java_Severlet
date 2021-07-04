<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <c:choose>
                        <c:when test="${count == 0}">
                            <h3>Cant found with key "${txt}"</h3>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${list}" var="o">
                                <div class="tittle">
                                    <a href="Detail?did=${o.id}">      
                                        ${o.title}
                                    </a>
                                </div>
                                <div class="image_search">
                                    <img src="images/${o.image}" alt=""/>
                                </div>
                                <div class="text_search">
                                    ${o.shortDes}
                                </div>
                                <br>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <div class="paging">
                        <c:forEach begin="1" end="${lastPage}" var="i">
                            <a class="${index== i?"active":""}" href="search?index=${i}&txtSearch=${txt}">${i}</a>
                        </c:forEach>
                    </div>
                </div> 
                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
