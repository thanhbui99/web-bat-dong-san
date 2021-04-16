<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 3/6/2021
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="buildingApi" value="/api/building"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) { }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <!-- /.page-header -->
            <div class="row">
                <div class="col-xs-12">
                    <form:form class="form-horizontal" commandName="model" id="formEdit"  role="form" action="" method="POST">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name"> Tên tòa nhà </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="name" class="form-control" name="name" value=""/>--%>
                                <form:input path="name" id="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="name"> Quận </label>
<%--                            <select name="district" style="margin-left: 12px;color: black;">--%>
<%--                                <option value="-1">---Chọn quận---</option>--%>
<%--                                <c:forEach var="item" items="${districtsEnumMap}">--%>
<%--                                    <option value="${item.key}" >${item.value}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
                                  <form:select path="district" cssStyle="margin-left: 12px;color: black">
                                      <form:option value="-1" label="---Chọn Quận---"/>
                                      <form:options items="${districtsEnumMap}"/>
                                  </form:select>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="ward"> phường </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="ward" class="form-control" name="ward"/>--%>
                                    <form:input path="ward" id="ward" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="street"> Đường </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="street" class="form-control" name="street"/>--%>
                                <form:input path="street" id="street" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="structure"> Kết cấu </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="structure" class="form-control" name="structure"/>--%>
                                <form:input path="structure" id="structure" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="numberOfBasement">  Số tầng hầm </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="numberOfBasement" class="form-control" name="numberOfBasement"/>--%>
                                <form:input path="numberOfBasement" id="numberOfBasement" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="floorArea">  diện tích sàn </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="floorArea" class="form-control" name="floorArea"/>--%>
                                <form:input path="floorArea" id="flooArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="direction"> Hướng  </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="direction" class="form-control" name="direction"/>--%>
                                    <form:input path="direction" id="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="level"> hạng </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="level" class="form-control" name="level"/>--%>
                                <form:input path="level" id="level" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="areaRent"> Diện tích thuê </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="buildingArea" class="form-control" name="buildingArea"/>--%>
                                    <form:input path="areaRent" id="areaRent" cssClass="form-control"/>
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label class="col-sm-3 control-label no-padding-right" for="rentPriceDescription"> Mô tả diện tích </label>--%>
<%--                            <div class="col-sm-9">--%>
<%--                                <input type="text" id="rentPriceDescription" class="form-control" name="rentPriceDescription"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentPrice"> Giá thuê </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="rentPrice" class="form-control" name="rentPrice" value=""/>--%>
                                <form:input path="rentPrice" id="rentPrice" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentPriceDescription"> Mô tả giá </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="rentPriceDescription" class="form-control" name="rentPriceDescription"/>--%>
                                    <form:input path="rentPriceDescription" id="" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="serviceFee"> Phí dịch vụ </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="serviceFee" class="form-control" name="serviceFee"/>--%>
                                <form:input path="serviceFee" id="serviceFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="carFee"> Phí ô tô </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="carFee" class="form-control" name="carFee"/>--%>
                                <form:input path="carFee" id="carFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="motorbikeFee"> Phí mô tô </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="motorbikeFee" class="form-control" name="motorbikeFee"/>--%>
                                    <form:input path="motorbikeFee" id="motorbikeFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="overTimeFee"> Phí ngoài giờ </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="overTimeFee" class="form-control" name="overTimeFee"/>--%>
                                <form:input path="overTimeFee" id="overTimeFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="electricityFee"> Tiền điện </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="electricityFee" class="form-control" name="electricityFee"/>--%>
                                    <form:input path="electricityFee" id="electricityFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="deposit"> Đặt cọc  </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="deposit" class="form-control" name="deposit"/>--%>
                                    <form:input path="deposit" id="deposit" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="payment"> Thanh toán </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="payment" class="form-control" name="payment"/>--%>
                                <form:input path="payment" id="payment" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="rentTime"> Thời hạn thuê </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="rentTime" class="form-control" name="rentTime"/>--%>
                                <form:input path="rentTime" id="rentTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="decorationTime"> Thời gian trang trí </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="decorationTime" class="form-control" name="decorationTime"/>--%>
                                <form:input path="decorationTime" id="decorationTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerName"> Tên quản lí </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="managerName" class="form-control" name="managerName"/>--%>
                                <form:input path="managerName" id="managerName" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right" for="managerPhone"> Số điện thoại quản lí </label>
                            <div class="col-sm-9">
<%--                                <input type="text" id="managerPhone" class="form-control" name="managerPhone"/>--%>
                                <form:input path="managerPhone" id="managerPhone" cssClass="form-control"/>
                            </div>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label class="col-sm-3 control-label no-padding-right" for="name"> Phí môi giới </label>--%>
<%--                            <div class="col-sm-9">--%>
<%--                                <input type="text" id="name" class="form-control" name="name"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"> Loại tòa nhà :</label>
<%--                            <form:checkboxes path="" items="${typesEnumStringMap}"/>--%>
<%--                            <div style="margin-top: 6px;">--%>
<%--                                <c:forEach var="item" items="${typesEnumStringMap}" >--%>
<%--                                        <label style="margin-left: 5px;font-weight: bold">${item.value}</label>--%>
<%--                                        <input type="checkbox" name="buildingTypes" value="${item.key}">--%>
<%--                                </c:forEach>--%>
<%--                            </div>--%>
                            <br/>
                            <form:checkboxes items="${typesEnumStringMap}" path="buildingTypes" cssStyle="margin-left: 10px"/>
                        </div>
<%--                        <div class="form-group">--%>
<%--                            <label class="col-sm-3 control-label no-padding-right" for="name"> Ghi chú </label>--%>
<%--                            <div class="col-sm-9">--%>
<%--                                <input type="text" id="name" class="form-control" name="name"/>--%>
<%--                            </div>--%>
<%--                        </div>--%>
                        <div class="">
                            <div class="col-md-offset-3 col-md-9">
                                <button class="btn btn-primary" type="submit" id="btnAddBuilding">
                                    <i class="ace-icon fa fa-check bigger-110"></i>
                                    Thêm tòa nhà
                                </button>

                                &nbsp; &nbsp; &nbsp;
                                <button class="btn btn-danger" type="reset">
                                    <i class="ace-icon fa fa-undo bigger-110"></i>
                                    Hủy
                                </button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>

        </div>
    </div>

    <!-- /.page-content -->
</div>
<script>
    $('#btnAddBuilding').click(function(e){
        e.preventDefault();
        // call API add building
        let data = {};
        let dataBuildingTypes = [];
        let formData = $('#formEdit').serializeArray();
        $.each(formData, function (index, val) {
            if(val.name == 'buildingTypes'){
                dataBuildingTypes.push(val.value);
            }
            else data[""+val.name+""] = (val.value);
        });

        data['buildingTypes'] = dataBuildingTypes;
        $.ajax({
            url:'${buildingApi}',
            type:'POST',
            data:JSON.stringify(data),
            dataType:'json',
            contentType:'application/json',
            success:function(response){
                // calleed when successful
                let name = $('#name').val();
                console.log("thanh cong")
            },
            error:function(response){

            }
        });
    });
</script>
</body>
</html>
