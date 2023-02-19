<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rent a Car</title>
    <!-- Bootstrap CSS -->
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">

</head>
<body>
<!--Main Navigation-->
<header>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: rgba(0, 0, 0,100.0);">
        <!-- Container wrapper -->
        <div class="container-fluid">
            <!-- Collapsible wrapper -->
            <div class="collapse navbar-collapse"  id="navbarSupportedContent">
                 <!-- Left links -->
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/index.html">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/list_cars/1.view">Choose a car</a>
                    </li>

                    <security:authorize access="hasRole('ROLE_ADMIN')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">Edit
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/add-brand.view">Add brand</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/add-model.view">Add model</a>
                                </li>
                                <li>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/add-car.view">Add car</a>
                                </li>
                            </ul>
                        </li>
                    </security:authorize>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                           aria-expanded="false">List
                        </a>
                        <ul class="dropdown-menu">
                            <security:authorize access="isAuthenticated()">
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/brand-list.view">List brands</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/model-list.view">List models</a>
                                </li>
                            </security:authorize>
                            <li>
                                <a class="dropdown-item"
                                   href="${pageContext.request.contextPath}/car-list.view">List cars</a>
                            </li>
                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                <div class="dropdown-divider"></div>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/user-list.view">List user</a>
                                </li>
                                <div class="dropdown-divider"></div>
                                <li>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/order-list.view">List order</a>
                                </li>
                            </security:authorize>
                        </ul>
                    </li>
                </ul>
                <!-- Left links -->
            </div>
            <!-- Collapsible wrapper -->

            <!-- Right elements -->
              <ul class="navbar-nav mb-2 mb-lg-0">
<%--                <div class="d-flex align-items-center">--%>
                    <li class="d-flex nav-item dropdown">
                        <security:authorize access="isAnonymous()">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">Anonymous</a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/login.view">LogIn/SignUp</a>
                                </li>
                            </ul>
                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false"><security:authentication property="name"/></a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-profile.view">
                                    Personal profile</a>
                                </li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/user-orders.view">
                                    My orders</a>
                                </li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a>
                                </li>
                            </ul>
                        </security:authorize>
                    </li>
<%--                </div>--%>
              </ul>
            <!-- Right elements -->
        </div>
        <!-- Container wrapper -->
    </nav>
    <!-- Navbar -->

</header>
<!--Main Navigation-->

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>

</html>
