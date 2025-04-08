<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    Thông tin toà nhà
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        overview &amp; stats
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12" style="font-family: 'Times New Roman', Times, serif;">
                    <form:form class="form-horizontal" role="form" id="form-edit" modelAttribute="building">
                        <div class="form-group">
                            <label class="col-xs-3 control-label">Tên tòa nhà(*)</label>
                            <div class="col-xs-9">
                                  <form:input path="name" class="form-control" id="name"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Quận(*)</label>
                            <div class="col-xs-2">
                                <form:select path="district" class="form-control">
                                    <form:option value="">--Chọn quận--</form:option>--%>
                                    <form:options items="${district}"></form:options>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Phường(*)</label>
                            <div class="col-xs-9">
                                <form:input path="ward" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Đường(*)</label>
                            <div class="col-xs-9">
                                <form:input path="street" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input path="structure" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <form:input path="numberOfBasement" class="form-control"/>

                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Diện tích sàn(*)</label>
                            <div class="col-xs-9">
                                <form:input path="floorArea" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Hướng</label>
                            <div class="col-xs-9">
                                <form:input path="direction" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Hạng</label>
                            <div class="col-xs-9">
                                <form:input path="level" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Diện tích thuê(*)</label>
                            <div class="col-xs-9">
                                <form:input path="rentArea" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Giá thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentPrice" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Mô tả giá(*)</label>
                            <div class="col-xs-9">
                                <form:input path="rentPriceDescription" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input path="serviceFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input path="carFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Phí mô tô</label>
                            <div class="col-xs-9">
                                <form:input path="motoFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input path="overtimeFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input path="electricityFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Tiền nước</label>
                            <div class="col-xs-9">
                                <form:input path="waterFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input path="deposit" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input path="payment" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Thời hạn thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentTime" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input path="decorationTime" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Tên quản lý(*)</label>
                            <div class="col-xs-9">
                                <form:input path="managerName" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">SDT quản lý(*)</label>
                            <div class="col-xs-9">
                                <form:input path="managerPhone" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Loại tòa nhà(*)</label>
                            <div class="col-xs-9">
                                <form:checkboxes path="typeCode" items="${typeCode}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Phí môi giới</label>
                            <div class="col-xs-9">
                                <form:input path="brokerageFee" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 control-label">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:input path="note" class="form-control"/>
                            </div>
                        </div>
                         <div class="form-group">
                            <label class="col-sm-5 control-label">Hình ảnh của tòa nhà</label>
                            <input class="col-sm-6 control-label" type="file" id="uploadImage"/>
                            <div class="col-sm-9 ">
                                <c:if test="${not empty building.image}">
                                    <c:set var="imagePath" value="/repository${building.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty building.image}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-xs-3 control-label"></label>
                            <div class="col-xs-9">
                               <c:if test="${empty building.id}">
                                   <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                    Thêm tòa nhà
                                    </button>
                               </c:if>
                               <c:if test="${not empty building.id}">
                                   <button type="button" class="btn btn-purple" id="btnAddOrUpdateBuilding">
                                    Sửa tòa nhà
                                    </button>
                               </c:if>
                                <button type="button" class="btn btn-warning" id="cancel">
                                    Hủy thao tác
                                </button>
                            </div>
                        </div>
                        <form:hidden path="id"/>

                    </form:form>
                </div>
            </div>

        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>
    var imageBase64 = '';
    var imageName = '';

    $("#submitBtn").click(function () {
        var data = {};
        var formData = $("#formEdit").serializeArray();
        $.each(formData, function (i, e) {
            if ('' !== e.value && null != e.value) {
                data['' + e.name + ''] = e.value;
            }

            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        });
        var buildingId = data['id'];

        $('#loading_image').show();

        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (res) {
                $('#loading_image').hide();
                showMessageConfirmation("Thành công", "Thao tác thành công!", "success", "/admin/building-edit-" + res.id);
            },
            error: function () {
                $('#loading_image').hide();
                var redirectUrl = (null === buildingId) ? "" : "/admin/building-edit-" + {buildingId};
                showMessageConfirmation("Thất bại", "Đã có lỗi xảy ra! Vui lòng kiểm tra lại.", "warning", redirectUrl);
            }
        });
    });

    $("#cancelBtn").click(function () {
        showAlertBeforeCancelForm(function() {
            window.location.href = '/admin/building-list';
        })
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>
<script>
    $('#btnAddOrUpdateBuilding').click(function(e){
        e.preventDefault();
        var formData = $('#form-edit').serializeArray(); //mang cac doi tuong
        var json = {};
        var typeCode = [];
        $.each(formData, function(i,it){
            if(it.name != 'typeCode') json["" + it.name + ""]= it.value;
            else typeCode.push(it.value);

            if('' !== imageBase64){
                json['imageBase64']= imageBase64;
                json['imageName'] = imageName;
            }
        });
        json['typeCode'] = typeCode;
        console.log('AAA');
        if(json['name'] != '' && typeCode.length !=0){
            AddBuilding(json);
        }
        else{
            alert('Tên hoặc loại tòa nhà không được trống!');
        }
    })
    $('#cancel').click(function(e){
        e.preventDefault();
        window.location.href="<c:url value='/admin/building-list'/>";

    })

    function AddBuilding(data){
        var jwtToken = localStorage.getItem("jwtToken");
        $.ajax({
            url: "/api/buildings",
            type : "POST",
            headers: {
                "Authorization": "Bearer " + jwtToken
            },
            contentType: 'application/json',
            data : JSON.stringify(data), //convert tu object json(JS) qua JSON
            // dataType:"JSON", //Kieu du lieu server tra ra
            success : function(response){
                console.log('SUCCESS');
                alert(response.message);
                window.location.href="<c:url value='/admin/building-list'/>";
            },
            error : function(response){
                console.log('FAILED');
                alert('FAILED: '+response.responseJSON);
            }
        });
    }

</script>
</body>
</html>
