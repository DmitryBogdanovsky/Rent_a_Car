<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-white">
    <div class="container-fluid">
        <button
                class="navbar-toggler"
                type="button"
                data-mdb-toggle="collapse"
                data-mdb-target="#navbarExample01"
                aria-controls="navbarExample01"
                aria-expanded="false"
                aria-label="Toggle navigation"
        >
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarExample01">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" aria-current="page" href="${pageContext.request.contextPath}/index.html">Home</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/list_cars/1.view">Show cars</a>
                </li>

                <li class="nav-item">

                    <security:authorize access="isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                    </security:authorize>
                    <security:authorize access="!isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/login.view">Login</a>
                    </security:authorize>

                </li>
                <li class="nav-item">
                    <security:authorize access="!isAuthenticated()">
                    <a class="nav-link" href="${pageContext.request.contextPath}/registration.view">Registration</a>
                    </security:authorize>
                </li>

                <li class="nav-item">
                    <security:authorize access="isAuthenticated()">
                        <a class="nav-link" href="${pageContext.request.contextPath}/personal_page.view">Personal account</a>
                    </security:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>
