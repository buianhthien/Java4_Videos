<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html lang="en">

<head>
<title>Home</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="/ASSJAVA4_PD10303/image/video.png">
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function () {
    $('.favorite-btn').click(function () {
        var videoId = $(this).data('video-id');
        var $button = $(this);

        $.ajax({
            url: '${pageContext.request.contextPath}/myFavoriteServlet',
            method: 'POST', 
            data: { videoId: videoId },
            success: function (response) {
                console.log(response); 
                if (response.status === "success") {
                    alert(response.message);  
                    $button.text('Đã thích'); 
                    $button.find('i').removeClass('fa-thumbs-up').addClass('fa-heart'); 
                    $button.attr('disabled', true);
                } else {
                    alert(response.message);
                }
            },
            error: function () {
                alert("Đã có lỗi xảy ra. Vui lòng thử lại.");
            }
        });
    });
});

</script>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg bg-warning border navbar-warning">
			<div class="container">
				<a class="navbar-brand text-danger fw-bolder"
					href="/ASSJAVA4_PD10303/indexServlet">ONLINE ENTERTAINMENT</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav ms-auto me-5">
						<li class="nav-item"><a class="nav-link active text-primary" aria-current="page"
							href="${pageContext.request.contextPath}/myFavoriteServlet">
								<i class="fa-solid fa-heart"></i> MY FAVORITES</a>
						</li>
						<li class="nav-item dropdown">
						<a class="nav-link dropdown-toggle text-primary" href="#"
							role="button" data-bs-toggle="dropdown" aria-expanded="false"> <i class="fa-solid fa-user"></i> 
								<c:choose>
									<c:when test="${not empty sessionScope.user}">
				                        ${sessionScope.user.fullname}
				                    </c:when>
									<c:otherwise>
				                        MY ACCOUNT
				                    </c:otherwise>
								</c:choose>
						</a>
							<ul class="dropdown-menu">
								<c:choose>
									<c:when test="${empty sessionScope.user}">
										<li>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/login">
											<i class="fa-solid fa-right-to-bracket"></i> Login</a>
										</li>
										<li>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/forgotPass">
											<i class="fa-solid fa-lock"></i> Forgot Password</a>
										</li>
										<li>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/register">
											<i class="fa-solid fa-user-plus"></i> Registration</a>
										</li>
									</c:when>
									<c:otherwise>
										<li>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/Logout">
											<i class="fa-solid fa-right-from-bracket"></i> Logoff</a>
										</li>
										<li>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/changePass">
											<i class="fa-solid fa-rotate"></i> Change Password</a>
										</li>
										<li>
											<a class="dropdown-item" href="${pageContext.request.contextPath}/editProfile">
											<i class="fa-solid fa-pen-to-square"></i> Edit Profile</a>
										</li>
										<c:if
											test="${not empty sessionScope.user and sessionScope.user.admin}">
											<li>
												<a class="dropdown-item" href="${pageContext.request.contextPath}/video">
												<i class="fa-solid fa-cogs"></i> Admin Dashboard</a>
											</li>
										</c:if>
									</c:otherwise>
								</c:choose>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger text-center m-3" role="alert">
						${errorMessage}</div>
				</c:if>
		<div class="container mt-4 border">
			<div class="row">
				<c:forEach var="video" items="${videolist}">
					<div class="col-12 col-sm-6 col-lg-4 mb-4">
						<div class="card h-100">
							<a
								href="${pageContext.request.contextPath}/videoDetailsServlet?id=${video.id}">
								<img src="${pageContext.request.contextPath}/${video.poster}"
								class="card-img-top" alt="${video.title}">
							</a>
							<div class="card-body">
								<a class="card-text small text-decoration-none"
									href="${pageContext.request.contextPath}/videoDetailsServlet?id=${video.id}">
									<p>${video.title}</p>
								</a>
								<div class="d-flex justify-content-end">
									<button class="badge btn btn-primary me-2 favorite-btn" data-video-id="${video.id}">
                                        <i class="fa-solid fa-thumbs-up"></i> LIKE
                                    </button>
									<button class="badge btn btn-warning me-2">
										<i class="fa-solid fa-share"></i> SHARE
									</button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

	</div>
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