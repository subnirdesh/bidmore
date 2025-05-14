<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Change Password | BidMore</title>

    <!-- Use pageContext to include the correct path -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/password.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
</head>
<body>

<div class="main-container">
    <div class="form-card">
        <h1>Change Password</h1>

        <div class="message">
            <c:if test="${not empty error}">
                <div class="alert error">
                    <i class="fas fa-exclamation-circle"></i> ${error}
                </div>
            </c:if>
            <c:if test="${not empty success}">
                <div class="alert success">
                    <i class="fas fa-check-circle"></i> ${success}
                </div>
            </c:if>
        </div>

        <form method="post" action="${pageContext.request.contextPath}/changePassword">
            <div class="form-group">
                <label for="currentPassword">Current Password</label>
                <input type="password" id="currentPassword" name="currentPassword" required />
            </div>

            <div class="form-group">
                <label for="newPassword">New Password</label>
                <input type="password" id="newPassword" name="newPassword" required />
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm New Password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required />
            </div>

            <div class="actions">
                <button type="button" id="cancel-btn" class="btn cancel">Cancel</button>
                <button type="submit" class="btn submit">Change Password</button>
            </div>
        </form>
    </div>
</div>

<script>
    document.getElementById('cancel-btn').addEventListener('click', () => {
        window.location.href = '${pageContext.request.contextPath}/portfolio';
    });
</script>

</body>
</html>
