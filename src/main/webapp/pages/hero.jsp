<section class="hero is-fixed-bottom is-dark is-fullheight-with-navbar">
    <div class="hero-body">
        <div class="container">
            <h1 class="title border">
                <strong>Your Game Catalogue</strong>
            </h1>
            <h2 class="subtitle border">
                is the easiest way to swich your games, make friends and save money.
            </h2>
            <div>
                <form method="POST" action="${pageContext.request.contextPath}/game/gameSearch.jsp">
                    <div class="field has-addons">
                        <div class="field is-grouped">
                            <p class="control is-expanded">
                                <input class="input" type="text" name="gameName" placeholder="Find your new game" value="">
                            </p>
                            <p class="control">
                                <input class="button is-primary" type="submit" value="Search" />
                            </p>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<nav class="navbar is-dark" role="navigation" aria-label="main navigation"></nav>