<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Game Catalogue</title>
    <link rel="stylesheet" href="../src/css/bulma-0.7.2/css/bulma.css">
    <link rel="stylesheet" href="../src/css/hero.css">
    <link rel="stylesheet" href="../src/css/bodyIndex.css">
    <link rel="stylesheet" href="../src/css/game.css">
</head>
<body>
<%@ include file="header.jsp" %>
<%@ include file="hero.jsp" %>
<section class="section">
    <div class="container">
        <div class="columns">

            <div class="column">
                <div class="box right-box">
                    <div class="box has-background-dark is-radiusless">
                        <div class="white-letter">Results for "<c:out value="${gameName}" />"</div>
                    </div>
                    <div class="description-box">
                        <div class="error has-text-centered"><c:out value="${warning}" /></div>

                        <table class="table is-fullwidth">
                            <c:forEach var="game" items="${games}">
                                <tr onclick="link('<c:out value="${game.platform.platformId}" />', '<c:out value="${game.gameId}" />');" class="link">
                                    <td><img src="<c:out value="${game.cover}" />" alt="<c:out value="${game.gameName}" />"></td>
                                    <td><c:out value="${game.gameName}" /></td>
                                    <td><img src="${pageContext.request.contextPath}/src/img/platforms/<c:out value="${game.platform.platformLogo}" />" alt="<c:out value="${map.key.platformName}" />" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>
</body>
</html>
<script>
    function link(platformId, gameId)
    {
        window.location.href = "game.jsp?gameId="+gameId+"&platformId="+platformId;
    }
</script>