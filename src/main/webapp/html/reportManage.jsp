<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Report Manage</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="/ASSJAVA4_PD10303/image/software-engineer.png">
</head>

<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg bg-warning border navbar-warning">
			<div class="container">
				<a class="navbar-brand text-danger fw-bolder" href="#">ADMINISTRATION
					TOOLS</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link active text-primary"
							aria-current="page" href="/ASSJAVA4_PD10303/indexServlet"><i
								class="fa-solid fa-house"></i> HOME</a></li>
						<li class="nav-item"><a class="nav-link text-primary"
							href="/ASSJAVA4_PD10303/video"><i
								class="fa-solid fa-photo-film"></i> VIDEOS</a></li>
						<li class="nav-item"><a class="nav-link text-primary"
							href="/ASSJAVA4_PD10303/user"><i class="fa-regular fa-user"></i>
								USERS</a></li>
						<li class="nav-item"><a class="nav-link text-primary"
							href="/ASSJAVA4_PD10303/report"><i
								class="fa-solid fa-square-poll-horizontal"></i> REPORTS</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container mt-3">
			<nav>
				<div class="nav nav-tabs" id="nav-tab" role="tablist">
					<button class="nav-link active" id="nav-home-tab"
						data-bs-toggle="tab" data-bs-target="#nav-home" type="button"
						role="tab" aria-controls="nav-home" aria-selected="true">FAVORITES</button>
					<button class="nav-link" id="nav-profile-tab" data-bs-toggle="tab"
						data-bs-target="#nav-profile" type="button" role="tab"
						aria-controls="nav-profile" aria-selected="false">FAVORITE
						USERS</button>
					<button class="nav-link" id="nav-contact-tab" data-bs-toggle="tab"
						data-bs-target="#nav-contact" type="button" role="tab"
						aria-controls="nav-contact" aria-selected="false">SHARE
						FRIENDS</button>
				</div>

			</nav>
			<div class="tab-content" id="nav-tabContent">
				<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
					aria-labelledby="nav-home-tab" tabindex="0">
					<table class="table table-bordered table-primary mt-3">
						<thead>
							<tr>
								<th scope="col">Video Title</th>
								<th scope="col">Favorite Count</th>
								<th scope="col">Latest Date</th>
								<th scope="col">Oldest Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="stat" items="${videoLikeStatistics}">
								<tr>
									<td>${stat[0]}</td>
									<td>${stat[1]}</td>
									<td>${stat[2]}</td>
									<td>${stat[3]}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty videoLikeStatistics}">
								<tr>
									<td colspan="4">No data available</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<div class="tab-pane fade" id="nav-profile" role="tabpanel"
					aria-labelledby="nav-profile-tab" tabindex="0">
					<div>
						<div class="mb-3">
							<label for="videoTitle" class="form-label">VIDEO TITLE?</label>
							<form
								action="${pageContext.request.contextPath}/report/favoriteuser"
								method="post">
								<input type="hidden" name="activeTab" value="favoriteuser">
								<select class="form-select form-select-sm" name="videoTitle"
									id="videoTitle" onchange="this.form.submit()">
									<option selected>Select one</option>
									<c:forEach var="video" items="${videoTitles}">
										<option value="${video}"
											${selectedVideoTitle == video ? 'selected' : ''}>${video}</option>
									</c:forEach>
								</select>
							</form>
						</div>
					</div>
					<table class="table table-bordered table-primary">
						<thead>
							<tr>
								<th scope="col">Username</th>
								<th scope="col">Fullname</th>
								<th scope="col">Email</th>
								<th scope="col">Favorite Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="user" items="${favoriteUsers}">
								<tr>
									<td>${user[0]}</td>
									<td>${user[1]}</td>
									<td>${user[2]}</td>
									<td>${user[3]}</td>
								</tr>
							</c:forEach>
							<c:if test="${empty favoriteUsers}">
								<tr>
									<td colspan="4">No data available</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				<div
					class="tab-pane fade ${activeTab == 'shareUser' ? 'show active' : ''}"
					id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab"
					tabindex="0">
					<div>
						<form method="post"
							action="${pageContext.request.contextPath}/report/shareUser">
							<input type="hidden" name="activeTab" value="shareUser">
							<div class="mb-3">
								<label for="videoTitle" class="form-label">VIDEO TITLE?</label>
								<select class="form-select form-select-sm" name="videoTitle"
									id="videoTitle" onchange="this.form.submit()">
									<option selected>Select one</option>
									<c:forEach var="title" items="${videoTitles}">
										<option value="${title}"
											${title == selectedVideoTitle ? 'selected' : ''}>${title}</option>
									</c:forEach>
								</select>
							</div>
						</form>
					</div>
					<table class="table table-bordered table-primary mt-3">
						<thead>
							<tr>
								<th scope="col">Sender Name</th>
								<th scope="col">Sender Email</th>
								<th scope="col">Receiver Email</th>
								<th scope="col">Sent Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="share" items="${shareUsers}">
								<tr>
									<td>${share[0]}</td>
									<td>${share[1]}</td>
									<td>${share[2]}</td>
									<td>${share[3]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

			</div>
		</div>
	</div>
	<script>
	document.querySelectorAll('.nav-link').forEach(tab => {
	    tab.addEventListener('click', function () {
	        localStorage.setItem('activeTab', this.id);
	    });
	});

	document.addEventListener('DOMContentLoaded', function () {
	    const activeTabId = localStorage.getItem('activeTab');
	    if (activeTabId) {
	        const activeTab = document.getElementById(activeTabId);
	        if (activeTab) activeTab.click();
	    }
	});

</script>

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