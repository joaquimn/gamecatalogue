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
            <div class="column is-one-fifth">
                <div class="has-text-centered"><img src="<c:out value="${game.cover}" />"/></div>
                <div class="has-text-centered">
                    <img src="../src/img/platforms/<c:out value="${game.platform.platformLogo}" />"/>
                </div>
                <div class="columns">
                    <c:if test="${loggedUser.userId != null}">
                        <div class="column is-full has-text-centered">
                            <a class="button is-dark" href="${pageContext.request.contextPath}/game/gameAdd.jsp?statusId=<c:out value="${wishStatus}" />&gameId=<c:out value="${game.gameId}" />&platformId=<c:out value="${game.platform.platformId}" />">I wish</a>
                            <a class="button is-dark" href="${pageContext.request.contextPath}/game/gameAdd.jsp?statusId=<c:out value="${haveStatus}" />&gameId=<c:out value="${game.gameId}" />&platformId=<c:out value="${game.platform.platformId}" />">I have</a>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="column">
                <div class="box left-box">
                    <div class="box has-background-dark is-radiusless">
                        <div class="white-letter"><c:out value="${game.gameName}" /></div>
                    </div>
                    <div class="description-box"><c:out value="${game.description}" /></div>
                </div>
            </div>
            <div class="column">
                <div class="box right-box">
                    <div class="box has-background-dark is-radiusless">
                        <div class="white-letter">About</div>
                    </div>
                    <div class="description-box">
                        <table class="table is-fullwidth">
                            <tr>
                                <td>Platform: </td>
                                <td><c:out value="${game.platform.platformName}" /></td>
                            </tr>                            <tr>
                            <td>Released: </td>
                            <td><c:out value="${game.released}" /></td>
                        </tr>
                            <tr>
                                <td>Produced by: </td>
                                <td><c:out value="${game.producer.producerName}" /></td>
                            </tr>
                            <tr>
                                <td>Genre: </td>
                                <td><c:out value="${game.genre.genreName}" /></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="section">
    <div class="container">
        <div class="bd-snippet bd-is-vertical bd-is-2">
            <div class="columns">
                <div class="column">
                    <div class="box right-box">
                        <div class="box has-background-dark is-radiusless">
                            <div class="white-letter">People who have</div>
                        </div>
                        <div class="description-box">
                            <div class="error has-text-centered"><c:out value="${hUserError}" /></div>
                            <table class="table is-fullwidth">
                                <c:forEach var="user" items="${hUser}">
                                    <tr>
                                        <td rowspan="3" class="has-text-centered"><a href="${pageContext.request.contextPath}/user/user.jsp?id=<c:out value="${user.userId}" />"><img class="mini-cover" src="${pageContext.request.contextPath}/src/img/users/<c:out value="${user.picture}" />" /></a></td>
                                        <td>Name: </td>
                                        <td><c:out value="${user.userName}" /></td>
                                    </tr>
                                    <tr>
                                        <td>City </td>
                                        <td><c:out value="${user.userCity.cityName}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Province</td>
                                        <td><c:out value="${user.userCity.province.provinceName}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="column">
                    <div class="box right-box">
                        <div class="box has-background-dark is-radiusless">
                            <div class="white-letter">People who wish</div>
                        </div>
                        <div class="description-box">
                            <div class="error has-text-centered"><c:out value="${wUserError}" /></div>
                            <table class="table is-fullwidth">
                                <c:forEach var="user" items="${wUser}">
                                    <tr onclick="" class="link">
                                        <td rowspan="3" class="has-text-centered"><a href="${pageContext.request.contextPath}/user/user.jsp?id=<c:out value="${user.userId}" />"><img class="mini-cover" src="${pageContext.request.contextPath}/src/img/users/<c:out value="${user.picture}" />" /></a></td>
                                        <td>Name: </td>
                                        <td><c:out value="${user.userName}" /></td>
                                    </tr>
                                    <tr>
                                        <td>City </td>
                                        <td><c:out value="${user.userCity.cityName}" /></td>
                                    </tr>
                                    <tr>
                                        <td>Province</td>
                                        <td><c:out value="${user.userCity.province.provinceName}" /></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
