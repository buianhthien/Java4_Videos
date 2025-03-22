<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Đổi mật khẩu</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="icon" href="/ASSJAVA4_PD10303/image/video.png">
</head>

<body>
	<main class="container d-flex justify-content-center mt-5">
		<form action="changePass" method="post">
			<div class="card" style="width: 500px">
				<div class="card-body border bg-success-subtle">
					<h5 class="card-title text-dark fw-bolder fs-4">Change
						Password</h5>
				</div>
				<div id="messageBox" class="mb-3">
					<c:if test="${not empty errorMessage}">
						<div class="alert alert-danger" role="alert">
							${errorMessage}</div>
					</c:if>
					<c:if test="${not empty successMessage}">
						<div class="alert alert-success" role="alert">
							${successMessage}</div>
					</c:if>
				</div>
				<div class="row">
					<div class="col-6">
						<div class="p-2">
							<label for="username" class="form-label">Username:</label> <input
								type="text" class="form-control" name="username" id="username"
								placeholder="Vui lòng nhập username" />
						</div>
					</div>
					<div class="col-6">
						<div class="p-2">
							<label for="currentPassword" class="form-label">Password
								Hiện Tại:</label> <input type="password" class="form-control"
								name="currentPassword" id="currentPassword"
								placeholder="Vui lòng nhập password hiện tại" />
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-6">
						<div class="p-2">
							<label for="newPassword" class="form-label">Password Mới:</label>
							<input type="password" class="form-control" name="newPassword"
								id="newPassword" placeholder="Vui lòng nhập password mới" />
						</div>
					</div>
					<div class="col-6">
						<div class="p-2">
							<label for="confirmPassword" class="form-label">Xác Nhận
								Lại Password:</label> <input type="password" class="form-control"
								name="confirmPassword" id="confirmPassword"
								placeholder="Vui lòng xác nhận lại password" />
						</div>
					</div>
					<div class="ms-3 mb-2">
						<button type="button" class="btn btn-danger"
							onclick="window.location.href='/ASSJAVA4_PD10303/indexServlet'">Hủy
							bỏ</button>
						<button type="submit" class="btn btn-primary">Đồng ý</button>
					</div>
				</div>
			</div>
		</form>

	</main>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>

</html>