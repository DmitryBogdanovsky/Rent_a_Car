
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<!doctype html>
<html lang="en" data-bs-theme="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>

<header>
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


          <div class="dropdown open">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Show cars
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/list_cars/1.view">Choose a car</a>
              <c:forEach items="${brandss}" var="brands">
                <a class="dropdown-item" href="${pageContext.request.contextPath}/list_cars.view?model=${brands}">Show car ${brands}</a>
              </c:forEach>
            </div>
          </div>

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
              <a class="nav-link" href="${pageContext.request.contextPath}/personal_page.view">User account</a>
            </security:authorize>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</header>

  </nav>
</html>
