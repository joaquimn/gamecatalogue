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
                        <div class="white-letter">Sign up</div>
                    </div>
                    <div class="description-box">



                        <div class="field">
                            <label class="label">Name</label>
                            <div class="control">
                                <input class="input" type="text" placeholder="Name">
                            </div>
                        </div>

                        <div class="field">
                            <label class="label">Email</label>
                            <div class="control has-icons-left has-icons-right">
                                <input class="input" type="email" placeholder="Email" value="">
                                <span class="icon is-small is-left">
                                  <i class="fas fa-envelope"></i>
                                </span>
                                <span class="icon is-small is-right">
                                  <i class="fas fa-exclamation-triangle"></i>
                                </span>
                            </div>
                        </div>



                        <div class="field">

                            <label class="label">Province</label>

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
                            <div class="field">
                                <label class="label">City</label>

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
                        </div>





                        <div class="field">
                            <label class="label">Message</label>
                            <div class="control">
                                <textarea class="textarea" placeholder="Textarea"></textarea>
                            </div>
                        </div>

                        <div class="field">
                            <div class="control">
                                <label class="checkbox">
                                    <input type="checkbox">
                                    I agree to the <a href="#">terms and conditions</a>
                                </label>
                            </div>
                        </div>

                        <div class="field">
                            <div class="control">
                                <label class="radio">
                                    <input type="radio" name="question">
                                    Yes
                                </label>
                                <label class="radio">
                                    <input type="radio" name="question">
                                    No
                                </label>
                            </div>
                        </div>

                        <div class="field is-grouped">
                            <div class="control">
                                <button class="button is-link">Submit</button>
                            </div>
                            <div class="control">
                                <button class="button is-text">Cancel</button>
                            </div>
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
