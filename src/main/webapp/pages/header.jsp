<nav class="navbar is-dark" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
        <a class="navbar-item" href="${pageContext.request.contextPath}/index">
            <img src="${pageContext.request.contextPath}/src/img/logo.png">
        </a>

        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
            <span aria-hidden="true">Home</span>
            <span aria-hidden="true">About</span>
            <span aria-hidden="true">Contact</span>
        </a>
    </div>

    <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
            <a href="${pageContext.request.contextPath}/index" class="navbar-item">
                Home
            </a>

            <a class="navbar-item">
                About
            </a>

            <a class="navbar-item">
                Contact
            </a>
        </div>

        <div class="navbar-end">
            <div class="navbar-item">
                <div class="buttons">
                    <c:if test="${loggedUser.userId == null}">
                        <a href="${pageContext.request.contextPath}/signUp/" class="button is-primary is-outlined modal-button">Sign up</a>
                        <button class="button is-primary is-outlined modal-button" data-target="modal-logIn">Log in</button>
                    </c:if>
                    <c:if test="${loggedUser.userId != null}">
                        <a href="${pageContext.request.contextPath}/signUp/" class="button is-primary is-outlined modal-button">User edit</a>
                        <a href="${pageContext.request.contextPath}/logOut/" class="button is-primary is-outlined modal-button" data-target="">Log out</a>
                    </c:if>
                 </div>
            </div>
        </div>
    </div>
</nav>



<div id="modal-signUp" class="modal">
    <div class="modal-background"></div>
    <div class="modal-card">
        <header class="modal-card-head">
            <p class="modal-card-title">Sign In</p>
            <button class="delete" aria-label="close"></button>
        </header>
        <section class="modal-card-body">

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
                <label class="label">Postal code</label>
                <div class="control has-icons-left has-icons-right">
                    <input class="input" type="text" placeholder="Postal code" value="">
                    <span class="icon is-small is-left">
                          <i class="fas fa-envelope"></i>
                    </span>
                    <span class="icon is-small is-right">
                        <i class="fas fa-exclamation-triangle"></i>
                    </span>
                </div>
            </div>

            <div class="field">
                <label class="label">Password</label>
                <div class="control has-icons-left has-icons-right">
                    <input class="input" type="password" placeholder="Password" value="">
                    <span class="icon is-small is-left">
                          <i class="fas fa-envelope"></i>
                    </span>
                    <span class="icon is-small is-right">
                        <i class="fas fa-exclamation-triangle"></i>
                    </span>
                </div>
            </div>

            <div class="file is-boxed">
                <label class="file-label">
                    <input class="file-input" type="file" name="picture">
                    <span class="file-cta">
                        <span class="file-label">
                            Choose a picture
                        </span>
                    </span>
                </label>
            </div>

            <div class="field">
                <div class="control">
                    <label class="checkbox">
                        <input type="checkbox">
                        I agree to the <a href="">terms and conditions</a>
                    </label>
                </div>
            </div>
        </section>
        <footer class="modal-card-foot">
            <button class="button is-primary">Save</button>
        </footer>
    </div>
</div>


<div id="modal-logIn" class="modal">
    <div class="modal-background"></div>
    <div class="modal-card">

        <header class="modal-card-head">
            <p class="modal-card-title">Log in</p>
            <button class="delete" aria-label="close"></button>
        </header>
        <form method="post" id="formLogIn" action="${pageContext.request.contextPath}/logIn">
            <section class="modal-card-body">
                <div class="field">
                    <label class="label">Email</label>
                    <div class="control">
                        <input class="input" name="email" type="email" placeholder="">
                    </div>
                </div>

                <div class="field">
                    <label class="label">Password</label>
                    <div class="control">
                        <input class="input" name="password" type="password" placeholder="">
                    </div>
                </div>
            </section>
            <footer class="modal-card-foot">
                <button class="button is-primary">Log in</button>
            </footer>
        </form>
    </div>
</div>


<script>
    'use strict';
    document.addEventListener('DOMContentLoaded', function () {

        var rootEl = document.documentElement;
        var $modals = getAll('.modal');
        var $modalButtons = getAll('.modal-button');
        var $modalCloses = getAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button');

        if ($modalButtons.length > 0) {
            $modalButtons.forEach(function ($el) {
                $el.addEventListener('click', function () {
                    var target = $el.dataset.target;
                    var $target = document.getElementById(target);
                    rootEl.classList.add('is-clipped');
                    $target.classList.add('is-active');
                });
            });
        }

        if ($modalCloses.length > 0) {
            $modalCloses.forEach(function ($el) {
                $el.addEventListener('click', function () {
                    closeModals();
                });
            });
        }

        document.addEventListener('keydown', function (event) {
            var e = event || window.event;
            if (e.keyCode === 27) {
                closeModals();
            }
        });

        function closeModals() {
            rootEl.classList.remove('is-clipped');
            $modals.forEach(function ($el) {
                $el.classList.remove('is-active');
            });
        }

        function getAll(selector) {
            return Array.prototype.slice.call(document.querySelectorAll(selector), 0);
        }
    });
</script>