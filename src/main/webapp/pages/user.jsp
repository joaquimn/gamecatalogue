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
                <div class="has-text-centered"><img src="<c:out value="${pageContext.request.contextPath}/src/img/users/${user.picture}" />"/></div>
            </div>
            <div class="column">
                <div class="box right-box">
                    <div class="box has-background-dark is-radiusless">
                        <div class="white-letter">About</div>
                    </div>
                    <div class="description-box">
                        <table class="table is-fullwidth">
                            <tr>
                                <td>Name: </td>
                                <td><c:out value="${user.userName}" /></td>
                            </tr>
                            <tr>
                                <td>Member since: </td>
                                <td><c:out value="${user.createdAtDate}" /></td>
                            </tr>
                            <tr>
                                <td>Province: </td>
                                <td><c:out value="${user.userCity.province.provinceName}" /></td>
                            </tr>
                            <tr>
                                <td>City: </td>
                                <td><c:out value="${user.userCity.cityName}" /></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
            <div class="column">
                <div class="box left-box">
                    <div class="box has-background-dark is-radiusless">
                        <div class="white-letter">Platforms</div>
                    </div>
                    <div class="description-box">
                        <div class="error has-text-centered"><c:out value="${userPlatforms}" /></div>
                        <table class="table is-fullwidth">
                            <c:forEach var="platforms" items="${platforms}">
                            <tr>
                                <td class="has-text-centered"><img src="${pageContext.request.contextPath}/src/img/platforms/<c:out value="${platforms.platformLogo}" />" alt="<c:out value="${platforms.platformName}" />" /></td>
                            </tr>
                            </c:forEach>
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
                            <div class="white-letter"><c:out value="${user.userFirstName}" />'s games</div>
                        </div>
                        <div class="description-box">
                            <div class="error has-text-centered"><c:out value="${gamesH}" /></div>
                            <table class="table is-fullwidth">
                                <c:forEach var="map" items="${platformGamesH}">
                                    <tr>
                                        <td><img src="${pageContext.request.contextPath}/src/img/platforms/<c:out value="${map.key.platformLogo}" />" alt="<c:out value="${map.key.platformName}" />" /></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <c:forEach var="game" items="${map.value}">
                                                <a href="${pageContext.request.contextPath}/game/game.jsp?gameId=<c:out value="${game.gameId}" />&platformId=<c:out value="${game.platform.platformId}" />"><img src="<c:out value="${game.cover}" />" alt="<c:out value="${game.gameName}" />"></a>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="columns">
                <div class="column">
                    <div class="box right-box">
                        <div class="box has-background-dark is-radiusless">
                            <div class="white-letter"><c:out value="${user.userFirstName}" />'s wishes</div>
                        </div>
                        <div class="description-box">
                            <div class="error has-text-centered"><c:out value="${gamesW}" /></div>

                            <table class="table is-fullwidth">
                                <c:forEach var="map" items="${platformGamesW}">
                                    <tr>
                                        <td><img src="${pageContext.request.contextPath}/src/img/platforms/<c:out value="${map.key.platformLogo}" />" alt="<c:out value="${map.key.platformName}" />" /></td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <c:forEach var="game" items="${map.value}">
                                                <a href="${pageContext.request.contextPath}/game/game.jsp?gameId=<c:out value="${game.gameId}" />&platformId=<c:out value="${game.platform.platformId}" />"><img src="<c:out value="${game.cover}" />" alt="<c:out value="${game.gameName}" />"></a>
                                            </c:forEach>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${loggedUser.userId != null && showForm == 1}">
                <div class="columns">
                    <div class="column">
                        <div class="box right-box">
                            <div class="box has-background-dark is-radiusless">
                                <div class="white-letter">Contact <c:out value="${user.userFirstName}" /></div>
                            </div>
                            <div class="description-box">
                                <div class="field is-horizontal">
                                    <div class="field-label is-normal">
                                        <label class="label">Name</label>
                                    </div>
                                    <div class="field-body">
                                        <div class="field">
                                            <p class="control is-expanded has-icons-left">
                                                <input class="input" type="text" placeholder="Name">
                                                <span class="icon is-small is-left">
                                                     <i class="fas fa-user"></i>
                                                </span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="field is-horizontal">
                                    <div class="field-label is-normal">
                                        <label class="label">Email</label>
                                    </div>
                                    <div class="field-body">
                                        <div class="field">
                                            <p class="control is-expanded has-icons-left has-icons-right">
                                                <input class="input" type="email" placeholder="Email" value="">
                                                <span class="icon is-small is-left">
                                                  <i class="fas fa-envelope"></i>
                                                </span>
                                                <span class="icon is-small is-right">
                                              <i class="fas fa-check"></i>
                                            </span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="field is-horizontal">
                                    <div class="field-label is-normal">
                                        <label class="label">Message</label>
                                    </div>
                                    <div class="field-body">
                                        <div class="field">
                                            <div class="control">
                                                <textarea class="textarea" placeholder=""></textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="field is-horizontal">
                                    <div class="field-label">
                                        <!-- Left empty for spacing -->
                                    </div>
                                    <div class="field-body">
                                        <div class="field">
                                            <div class="control">
                                                <button class="button is-primary">
                                                    Send message
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>
</body>
</html>
