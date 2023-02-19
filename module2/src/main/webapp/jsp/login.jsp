<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://unicons.iconscout.com/release/v2.1.9/css/unicons.css'>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_login.css">

<div class="section">
    <div class="container">
        <div class="row full-height justify-content-center">
            <div class="col-12 text-center align-self-center py-5">
                <div class="section pb-5 pt-5 pt-sm-2 text-center">
                    <h6 class="mb-0 pb-3"><span>Log In </span><span>Sign Up</span></h6>
                    <input class="checkbox" type="checkbox" id="reg-log" name="reg-log"/>
                    <label for="reg-log"></label>
                    <div class="card-3d-wrap mx-auto">
                        <div class="card-3d-wrapper">
                            <div class="card-front">
                                <div class="center-wrap">
                                    <div class="section text-center">
                                        <h4 class="mb-4 pb-3">Log In</h4>
                                        <form method="POST" action="${pageContext.request.contextPath}/login">
                                            <div class="form-group" >
                                                <input type="email" name="username" class="form-style"
                                                       placeholder="Your Email" id="username" autocomplete="on">
                                                <i class="input-icon uil uil-at"></i>
                                            </div>
                                            <div class="form-group mt-2">
                                                <input type="password" name="password" class="form-style"
                                                       placeholder="Your Password" id="password" autocomplete="off">
                                                <i class="input-icon uil uil-lock-alt"></i>
                                            </div>
                                                <button class="btn mt-4-primary btn-lg">submit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>


                            <div class="card-back">
                                <div class="center-wrap">
                                    <div class="section text-center">
                                        <h4 class="mb-4 pb-3">Sign Up</h4>
                                      <form method="post" action="${pageContext.request.contextPath}/registration.action"  modelAttribute="user">
                                        <div class="form-group">
                                            <input type="text" name="userDetails.firstName" class="form-style"
                                                   placeholder="First Name" id="firstName" autocomplete="on">
                                            <i class="input-icon uil uil-user"></i>
                                        </div>
                                        <div class="form-group mt-2">
                                            <input type="text" name="userDetails.lastName" class="form-style"
                                                   placeholder="Last Name" id="lastName" autocomplete="on">
                                            <i class="input-icon uil uil-user"></i>
                                        </div>

                                        <div class="form-group mt-3">
                                            <input type="tel" name="userDetails.phoneNumber" class="form-style"
                                                   placeholder="Phone number" id="phoneNumber" autocomplete="on">
                                            <i class="input-icon uil uil-user"></i>
                                        </div>
                                        <div class="form-group mt-3">
                                            <input type="date" name="userDetails.birthDate" class="form-style"
                                                   placeholder="Your Birth day" id="birthDate" autocomplete="on">
                                            <i class="input-icon uil uil-user"></i>
                                        </div>

                                        <div class="form-group mt-3">
                                            <input type="email" name="email" class="form-style" placeholder="Your Email"
                                                   id="email" autocomplete="on">
                                            <i class="input-icon uil uil-at"></i>
                                        </div>
                                        <div class="form-group mt-3">
                                            <input type="password" name="password" class="form-style"
                                                   placeholder="Your Password" id="regPassword" autocomplete="on">
                                            <i class="input-icon uil uil-lock-alt"></i>
                                        </div>
                                        <button class="btn mt-4-primary btn-lg">submit</button>
                                          <p class="mb-0 mt-4 text-center"><a href="${pageContext.request.contextPath}/login.view" class="link">>Have already an account?</a></p>
                                      </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>

