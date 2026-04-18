<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sản phẩm</title>
</head>

<body>
<div class="page-wrapper">
    <%--<header>--%>
        <%--<!-- MENU  -->--%>
        <%--<div class="p-4">--%>
            <%--<div class="row navbar">--%>
                <%--<div class="col-12 col-md-3">--%>
                    <%--<div class="logo">--%>
                        <%--<a href="/trang-chu">--%>
                            <%--<img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo.png?1676257083798"--%>
                                 <%--alt="">--%>
                        <%--</a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-12 col-md-6">--%>
                    <%--<div class="item-menu">--%>
                        <%--<div class="nav nav1">--%>
                            <%--<div class="nav-item p-2"><a class="nav-item-link" href="/trang-chu"><span>Trang--%>
                                            <%--chủ</span></a></div>--%>
                            <%--<div class="nav-item p-2"><a class="nav-item-link" href="/gioi-thieu"><span>Giới--%>
                                            <%--thiệu</span></a></div>--%>
                            <%--<div class="nav-item p-2"><a class="nav-item-link" href=""><span--%>
                                    <%--style="color: var(--primary-color);">Sản phẩm</span></a></div>--%>
                            <%--<div class="nav-item p-2"><a class="nav-item-link" href="/tin-tuc"><span>Tin--%>
                                            <%--tức</span></a>--%>
                            <%--</div>--%>
                            <%--<div class="nav-item p-2"><a href='<c:url value='/lien-he'/>'><span>Liên hệ--%>
                                    <%--</span></a>--%>
                            <%--</div>--%>

                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-12 col-md-3">--%>
                    <%--<button class="btn btn-primary px-4">--%>
                        <%--Liên hệ tư vấn--%>
                    <%--</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</header>--%>
    <!-- INTRO  -->
    <div class="intro text-center">
        <div class="title-page">Tất cả dự án</div>
        <div class="row">
                    <div class="col-xs-12 a-left">
                        <ul class="desc-intro">
                            <li class="home">
                        <a href='<c:url value="/trang-chu"/>'><span style="color:#fff">Trang chủ</span></a>
                        <span class="mx-1" style="color:#fff"> / </span>
                    </li>
                    <li class="intro-item"><span>Tất cả sản phẩm</span></li>
                </ul>
            </div>
        </div>
    </div>
    <!-- SEARCH  -->
    <div class="search">
        <div class="container">
            <form action="<c:url value='/san-pham'/>" method="get" class="row">
                <div class="col-12 col-md-3 search-item">
                    <p class="search-text">Chọn tỉnh/thành phố</p>
                    <select class="search-option" name="province" id="search-option-city">
                        <option value="">- Tỉnh/thành phố</option>
                        <c:forEach var="item" items="${provinces}">
                            <option value="${item.key}" <c:if test="${selectedProvince == item.key}">selected</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-12 col-md-3 search-item">
                    <p class="search-text">Chọn quận/huyện</p>
                    <select class="search-option" name="district" id="search-option-district">
                        <option value="">- Quận/huyện</option>
                        <c:forEach var="item" items="${districts}">
                            <option value="${item.key}" <c:if test="${modelSearch.district == item.key}">selected</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-12 col-md-3 search-item">
                    <p class="search-text">Chọn loại bất động sản</p>
                    <select class="search-option" name="typeCode" id="search-option-type">
                        <option value="">- Loại bất động sản</option>
                        <c:forEach var="item" items="${typeCodes}">
                            <option value="${item.key}" <c:if test="${not empty modelSearch.typeCode and modelSearch.typeCode[0] == item.key}">selected</c:if>>${item.value}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-12 col-md-3 search-btn">
                    <button type="submit" class="search-btn-text pb-0">
                        <i class="fa-solid fa-magnifying-glass search-btn-icon"></i>
                        <span>Tìm kiếm nhanh</span>
                    </button>
                </div>
            </form>
        </div>
    </div>

    <!-- CONTENT  -->
    <div class="product mt-5">
        <div class="container">
            <div class="row">
                <c:choose>
                    <c:when test="${not empty buildings}">
                        <c:forEach var="building" items="${buildings}">
                            <div class="col-12 col-md-4 mb-3">
                                <div class="product1 vip">
                                    <div class="product1-image new"></div>
                                    <div class="product1-conntent">
                                        <div class="product1-conntent-header">
                                            <a href='<c:url value="/trang-chu"/>'>${building.name}</a>
                                        </div>
                                        <span class="product1-conntent-title">${building.address}</span>
                                        <ul class="product1-conntent-list">
                                            <li class="product1-conntent-item"><i class="fa-solid fa-location-dot"></i><span>${building.address}</span></li>
                                            <li class="product1-conntent-item"><i class="fa-solid fa-building"></i><span>Loại BĐS: Chung cư</span></li>
                                            <li class="product1-conntent-item"><i class="fa-solid fa-earth-asia"></i><span>Diện tích: ${building.rentArea}m2</span></li>
                                        </ul>
                                    </div>
                                    <div class="product1-footer">
                                        <span class="product1-footer-cost"><c:choose><c:when test="${not empty building.rentPrice}">${building.rentPrice} Tỷ</c:when><c:otherwise>Liên hệ</c:otherwise></c:choose></span>
                                        <button type="button" class="product1-footer-detail"
                                                data-name="<c:out value='${building.name}'/>"
                                                data-address="<c:out value='${building.address}'/>"
                                                data-rentarea="<c:out value='${building.rentArea}'/>"
                                                data-rentprice="<c:out value='${building.rentPrice}'/>"
                                                data-floorarea="<c:out value='${building.floorArea}'/>"
                                                onclick="openBuildingModal(this)">Xem chi tiết</button>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-12"><div class="alert alert-info text-center">Không có dữ liệu chung cư</div></div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- TIEN TRINH  -->
        <div class="container text-center">
            <div class="shop-pag text-xs-right mt-5">
                <nav class="text-center">
                    <ul class="pagination clearfix justify-content-center">
                        <li class="page-item disabled"><a class="page-link" href="#">«</a></li>
                        <li class="active page-item disabled"><a class="page-link" href="javascript:;">1</a></li>
                        <li class="page-item"><a class="page-link" onclick="doSearch(2)" href="javascript:;">2</a>
                        </li>
                        <li class="page-item"><a class="page-link" onclick="doSearch(2)" href="javascript:;">»</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>

        <!-- FOOTER  -->
        <footer class="footer">
            <div class="container">
                <div class="top-footer text-center mt-0">
                    <div class="logo logo-footer pt-5">
                        <a href="./ViewHome.html"><img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/logo_footer.png?1676257083798"
                                                       alt="logo-footer"></a>
                        <p class="desc-logo-footer mt-3">Với hơn 10 năm kinh nghiệm, SkyLand tự hào là sàn
                            mua
                            bán, giao dịch và quảng cáo
                            bất động sản hàng đầu tại Việt Nam</p>
                        <div class="item-footer mt-5">
                            <div class="row">
                                <div class="col-12 col-md-4 text-center">
                                    <div class="icon-footer">
                                        <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_maps.png?1676257083798" alt="">
                                    </div>
                                    <div class="content-center-footer">
                                        <p class="mb-1 mt-3">Trụ sở chính</p>
                                        <p class="desc-footer">Số 46 Man Thiện, TP Thủ Đức, TP HCM</p>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 text-center">
                                    <div class="icon-footer">
                                        <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_phone.png?1676257083798" alt="">
                                    </div>
                                    <div class="content-center-footer">
                                        <p class="mb-1 mt-3">Hotline</p>
                                        <p class="desc-footer"><a class="a-text" href="#">098828</a></p>
                                    </div>
                                </div>
                                <div class="col-12 col-md-4 text-center">
                                    <div class="icon-footer">
                                        <img src="https://bizweb.dktcdn.net/100/328/362/themes/894751/assets/place_email.png?1676257083798" alt="">
                                    </div>
                                    <div class="content-center-footer">
                                        <p class="mb-1 mt-3">Email</p>
                                        <p class="desc-footer"><a class="a-text" href="#">vsh@gmail.com</a>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="text-center">
                        <div class="border-bottom mb-5 mt-4"></div>
                    </div>
                </div>
                <div class="bottom-footer">
                    <div class="row">
                        <div class="col-12 col-md-3">
                            <h4 class="title-item-bottom-footer">Thông tin công ty</h4>
                            <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                                chủ</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                                sản</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                        </div>
                        <div class="col-12 col-md-3">
                            <h4 class="title-item-bottom-footer">Chính sách hoạt động</h4>
                            <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                                chủ</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                                sản</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                        </div>
                        <div class="col-12 col-md-3">
                            <h4 class="title-item-bottom-footer">Hỗ trợ khách hàng</h4>
                            <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                                chủ</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                                sản</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                        </div>
                        <div class="col-12 col-md-3">
                            <h4 class="title-item-bottom-footer">Kết nối với chúng tôi</h4>
                            <p class="desc-item-bottom-footer desc-1"><a class="a-text" href="">Trang
                                chủ</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Giới thiệu</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Dự án bất động
                                sản</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Tin tức</a></p>
                            <p class="desc-item-bottom-footer"><a class="a-text" href="">Liên hệ</a></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bottom-footer-2">
                <div class="text-center desc-bottom-footer-2">@ Bản quyền thuộc về Happy Team |
                    Cung cấp bởi <a class="a-text group-name" href="#">HappyTeam</a></div>
            </div>
        </footer>
    </div>
</div>
    <div class="modal fade" id="buildingDetailModal" tabindex="-1" aria-labelledby="buildingDetailModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="buildingDetailModalLabel">Chi tiết bất động sản</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <img src="https://bizweb.dktcdn.net/thumb/grande/100/328/362/products/97493029.jpg?v=1534497006637"
                             alt="Hình ảnh bất động sản" style="width:100%;max-height:260px;object-fit:cover;border-radius:8px;">
                    </div>
                    <div class="row">
                        <div class="col-12 col-md-6 mb-2"><strong>Tên dự án:</strong> <span id="modalBuildingName"></span></div>
                        <div class="col-12 col-md-6 mb-2"><strong>Loại BĐS:</strong> Chung cư</div>
                        <div class="col-12 mb-2"><strong>Địa chỉ:</strong> <span id="modalBuildingAddress"></span></div>
                        <div class="col-12 col-md-6 mb-2"><strong>Diện tích sàn:</strong> <span id="modalBuildingFloorArea"></span></div>
                        <div class="col-12 mb-2"><strong>Giá bán:</strong> <span id="modalBuildingPrice"></span></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a id="modalContactLink" href="<c:url value='/lien-he'/>" class="btn btn-success">Để lại thông tin</a>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    <script>
        function openBuildingModal(button) {
            var name = button.getAttribute('data-name') || 'Chưa cập nhật';
            var address = button.getAttribute('data-address') || 'Chưa cập nhật';
            var floorArea = button.getAttribute('data-floorarea') || 'Chưa cập nhật';
            var rentPrice = button.getAttribute('data-rentprice');

            document.getElementById('modalBuildingName').textContent = name;
            document.getElementById('modalBuildingAddress').textContent = address;
            document.getElementById('modalBuildingFloorArea').textContent = floorArea ? (floorArea + ' m2') : 'Chưa cập nhật';
            document.getElementById('modalBuildingPrice').textContent = rentPrice ? (rentPrice + ' Tỷ') : 'Liên hệ';

            var contactBase = '<c:url value="/lien-he"/>';
            document.getElementById('modalContactLink').setAttribute('href', contactBase + '?buildingName=' + encodeURIComponent(name));

            var modal = new bootstrap.Modal(document.getElementById('buildingDetailModal'));
            modal.show();
        }

        (function () {
            var provinceEl = document.getElementById('search-option-city');
            var districtEl = document.getElementById('search-option-district');
            if (!provinceEl || !districtEl) {
                return;
            }
            function toggleDistrict() {
                var isHcm = provinceEl.value === 'TP_HCM';
                districtEl.disabled = !isHcm;
                if (!isHcm) {
                    districtEl.value = '';
                }
            }
            provinceEl.addEventListener('change', toggleDistrict);
            toggleDistrict();
        })();
    </script>
</body>

</html>