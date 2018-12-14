<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Game Catalogue</title>
    <link rel="stylesheet" href="src/css/bulma-0.7.2/css/bulma.css">
    <link rel="stylesheet" href="src/css/hero.css">
    <link rel="stylesheet" href="src/css/bodyIndex.css">
</head>
<body>
<%@ include file="pages/header.jsp" %>
<%@ include file="pages/hero.jsp" %>
<section class="section">
    <div class="container">
        <div class="bd-snippet bd-is-vertical bd-is-2">
            <div class="columns">
                <div class="column">
                    <div class="box right-box">
                        <div class="box has-background-dark is-radiusless">
                            <div class="white-letter">Most wished games</div>
                        </div>
                        <table class="table is-fullwidth is-striped">
                            <c:forEach var="games" items="${wishedAndOfferedGames[0]}">
                                <tr onclick="link('<c:out value="${games.platform.platformId}" />', '<c:out value="${games.gameId}" />');" class="link">
                                    <td><img class="mini-cover" src="<c:out value="${games.cover}" />" /></td>
                                    <td><img src="src/img/platforms/<c:out value="${games.platform.platformLogo}" />" /></td>
                                    <td><c:out value="${games.gameName}" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

                <div class="column">
                    <div class="box right-box">
                        <div class="box has-background-dark is-radiusless">
                            <div class="white-letter">Most offered games</div>
                        </div>
                        <table class="table is-fullwidth is-striped">
                            <c:forEach var="games" items="${wishedAndOfferedGames[1]}">
                                <tr onclick="link('<c:out value="${games.platform.platformId}" />', '<c:out value="${games.gameId}" />');" class="link">
                                    <td><img class="mini-cover" src="<c:out value="${games.cover}" />" /></td>
                                    <td><img src="src/img/platforms/<c:out value="${games.platform.platformLogo}" />" /></td>
                                    <td><c:out value="${games.gameName}" /></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<%@ include file="pages/footer.jsp" %>
</body>
<script>
    function link(platformId, gameId)
    {
        window.location.href = "game/game.jsp?gameId="+gameId+"&platformId="+platformId;
    }
</script>
</html>
