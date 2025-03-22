<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Video Manage</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"  crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="icon" href="/ASSJAVA4_PD10303/image/software-engineer.png">
</head>

<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg bg-warning border navbar-warning">
			<div class="container">
				<a class="navbar-brand text-danger fw-bolder" href="#">ADMINISTRATION TOOLS</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item">
						<a class="nav-link active text-primary" aria-current="page" href="/ASSJAVA4_PD10303/indexServlet">
						<i class="fa-solid fa-house"></i> HOME</a></li>
						<li class="nav-item"><a class="nav-link text-primary" href="/ASSJAVA4_PD10303/video">
						<i class="fa-solid fa-photo-film"></i> VIDEOS</a></li>
						<li class="nav-item"><a class="nav-link text-primary" href="/ASSJAVA4_PD10303/user">
						<i class="fa-regular fa-user"></i>USERS</a></li>
						<li class="nav-item"><a class="nav-link text-primary" href="/ASSJAVA4_PD10303/report">
						<i class="fa-solid fa-square-poll-horizontal"></i> REPORTS</a></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="container mt-3">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home" type="button" role="tab"
						aria-controls="home" aria-selected="true">VIDEO EDITION
					</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile" type="button" role="tab"
						aria-controls="profile" aria-selected="false">VIDEO LIST
					</button>
				</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="home" role="tabpanel"aria-labelledby="home-tab">
					<div class="container mt-3 border border-danger">
						<c:url var="url" value="/video" />
						<form method="post" enctype="multipart/form-data">
							<div class="row">
								<div class="col-12 col-md-7 p-2 border">
									<div class="container mt-5">
										<h2 class="text-center">Upload and Display Image</h2>
										<div class="form-group text-center">
											<label for="poster">Choose an image:</label> 
											<input type="file" class="form-control-file mt-2" id="poster"
												name="poster" accept="image/*" onchange="previewImage(event)">
										</div>
										<div class="text-center mt-4" id="imageContainer">
											<img id="currentImage" src="${video.poster != null ? video.poster : ''}"
												alt="Preview Image" class="img-fluid">
										</div>
									</div>
								</div>
								<div class="col-12 col-md-5">
									<div class="form-group p-2">
										<label for="youtubeId">Youtube ID:</label> 
											<input type="text" class="form-control border border-warning" id="youtubeId"
											name="id" value="${video.id}">
									</div>
									<div class="form-group p-2">
										<label for="videoTitle">Link Video:</label> 
											<input type="text"class="form-control border border-warning" id="videoLink"
											name="link" value="${video.link}">
									</div>
									<div class="form-group p-2">
										<label for="videoTitle">Video Title:</label> 
											<input type="text" class="form-control border border-warning"
											id="videoTitle" name="title" value="${video.title}">
									</div>
									<div class="form-group p-2">
										<label for="viewCount">View Count:</label> 
											<input type="number" class="form-control border border-warning"
											id="viewCount" name="views" value="${video.views}">
									</div>
									<div class="form-group d-flex align-items-center p-2">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="active"id="active" value="true"
											 	${video.active ? 'checked' : ''}>
												<label class="form-check-label" for="active">Active</label>
										</div>
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="radio" name="active" id="inactive" value="false"
												${!video.active ? 'checked' : ''}> 
												<label class="form-check-label" for="inactive">Inactive</label>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label for="description">Description</label>
									<textarea class="form-control border border-warning"id="description" name="description"
									 rows="3">${video.description}</textarea>
							</div>
							<hr>
							<div class="text-end mb-2">
								<button type="submit" class="btn btn-primary" formaction="${url}/create">Create</button>
								<button type="submit" class="btn btn-success" formaction="${url}/update">Update</button>
								<button type="submit" class="btn btn-danger" formaction="${url}/delete"
								onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này không?')" >Delete</button>
								<button type="submit" class="btn btn-warning" formaction="${url}/reset">Reset</button>
							</div>
						</form>
					</div>
				</div>
				<div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
					<table class="table table-bordered table-primary">
						<thead>
							<tr>
								<th scope="col">Video ID</th>
								<th scope="col">Link Video</th>
								<th scope="col">Video Title</th>
								<th scope="col">Description</th>
								<th scope="col">View Count</th>
								<th scope="col">Poster</th>
								<th scope="col">Status</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty videos}">
									<c:forEach var="v" items="${videos}">
										<tr>
											<td>${v.id}</td>
											<td>${v.link}</td>
											<td>${v.title}</td>
											<td>${v.description}</td>
											<td>${v.views}</td>
											<td>${v.poster}</td>
											<td>${v.active? 'Active' : 'InActive'}</td>
											<td>
											<a href="${pageContext.request.contextPath}/video/edit/${v.id}" class="btn btn-info">Edit</a>
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5" class="text-center">No videos available</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<script>
			function previewImage(event) {
				var reader = new FileReader();
				reader.onload = function() {
					var output = document.getElementById('currentImage');
					output.src = reader.result;
				}
				reader.readAsDataURL(event.target.files[0]);
			}
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