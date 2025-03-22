<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!doctype html>
<html lang="en">

<head>
    <title>Send Email</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="icon" href="/ASSJAVA4_PD10303/image/video.png">       
</head>

<body>
    <main class="container d-flex justify-content-center mt-5">
        <form action="" method="post">
            <div class="card" style="width: 500px">
                <div class="card-body border bg-success-subtle">
                    <h5 class="card-title text-dark fw-bolder fs-4">SEND VIDEO TO YOUR FRIEND</h5>
                </div>
                    <div class="col">
                        <div class="p-2">
                            <label for="shareemail" class="form-label">Your friends's email?</label>
                            <input type="text" class="form-control border border-warning" name="shareemail" id="shareemail"
                                placeholder="Vui lòng nhập email" />
                        </div>
                    </div>
                <div class="mb-2 me-3 text-end">
                    <button type="button" class="btn btn-danger" onclick="window.location.href='/ASSJAVA4_PD10303/indexServlet'">Hủy bỏ</button>
                    <button type="submit" class="badge btn btn-warning"><i class="fa-regular fa-paper-plane"></i> Send</button>
                </div>
        </form>
    </main>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>

</html>