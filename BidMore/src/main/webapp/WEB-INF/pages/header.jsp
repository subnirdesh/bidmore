<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header">
	<header class="header">
		<div class="header-container">
			<h1 class="logo">
				<a href="${pageContext.request.contextPath}/home"> <img
					src="${pageContext.request.contextPath}/resources/images/system/logo.png"
					alt="bidmore"
					onmouseover="this.src='${pageContext.request.contextPath}/resources/images/system/altlogo.png'"
					onmouseout="this.src='${pageContext.request.contextPath}/resources/images/system/logo.png'" />
				</a>
			</h1>

			<ul class="main-nav">
				<li><a href="${pageContext.request.contextPath}/home">HOME</a></li>
				<li><a href="${pageContext.request.contextPath}/buy">BUY</a></li>
				<li><a href="${pageContext.request.contextPath}/sell">SELL</a></li>
				<li><a href="${pageContext.request.contextPath}/portfolio">PORTFOLIO</a></li>
				<li><a href="${pageContext.request.contextPath}/about">ABOUT</a></li>
			</ul>

			<div class="right-nav">
				<c:choose>
					<c:when test="${not empty sessionScope.username}">
						<!-- User is logged in, show logout button -->
						<form action="${pageContext.request.contextPath}/logout"
							method="post" id="logoutForm">
							<button type="submit" class="btn-logout">
								 Logout
							</button>
						</form>
					</c:when>
					<c:otherwise>
						<!-- User is not logged in, show login and signup buttons -->
						<button class="login-button"
							onclick="window.location.href='${pageContext.request.contextPath}/login'">Log
							In</button>
						<button class="signup-button"
							onclick="window.location.href='${pageContext.request.contextPath}/registration'">Sign
							Up</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>
	<div class="header-border"></div>
</div>