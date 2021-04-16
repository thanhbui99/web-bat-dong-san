<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url var="LoadStaffAPI" value="/api/building"/>
<c:url var="searchFormURL" value="/admin/building-list"/>
<c:url var="search" value="/admin/handle-search"/>


<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 3/6/2021
  Time: 9:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">

    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {
                }
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
            <div class="row">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm kiếm</h4>

                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>

                            <a href="#" data-action="close">
                                <i class="ace-icon fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <form:form commandName="modelSearch" action="${search}" id="listForm" method="POST">
                        <div class="widget-body">
                            <div class="widget-main">
                                <form action="" class="" method="GET">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="col-sm-6">
                                                <div>
                                                    <label id="name-building">Tên tòa nhà</label>
                                                    <form:input path="name" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-6">
                                                <div>
                                                    <label>Diện tích sàn</label>
                                                    <input type="text" id="floorAre" class="form-control"
                                                           name="floorArea" value="${modelSearch.floorArea}"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <br/>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="col-sm-4">
                                                <br/>
                                                <form:select path="district">
                                                    <form:option value="" label="---Chọn Quận---"/>
                                                    <form:options items="${districtsEnumMap}"/>
                                                </form:select>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Phường</label>
                                                    <form:input path="ward" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Đường</label>
                                                    <form:input path="street" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <br/>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="col-sm-4">
                                                <div>
                                                    <label id="name">Số đường hầm</label>
                                                    <form:input path="numberOfBasement" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Hướng</label>
                                                    <form:input path="direction" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Hạng</label>
                                                    <form:input path="level" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <br/>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="col-sm-3">
                                                <div>
                                                    <label id="name">Diện tích từ</label>
                                                    <form:input path="rentAreaFrom" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label>Diện tích đến</label>
                                                    <form:input path="rentAreaTo" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label>Giá thuê từ</label>
                                                    <form:input path="rentPriceFrom" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-3">
                                                <div>
                                                    <label>Giá thuê đến</label>
                                                    <form:input path="rentPriceTo" cssClass="form-control"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <br/>
                                        </div>
                                        <div class="col-xs-12">
                                            <div class="col-sm-4">
                                                <div>
                                                    <label id="name">Tên quản lý</label>
                                                    <form:input path="managerName" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <div>
                                                    <label>Điện thoại quản lí</label>
                                                    <form:input path="managerPhone" cssClass="form-control"/>
                                                </div>
                                            </div>
                                            <div class="col-sm-4">
                                                <br/>
                                                <form:select path="staffId" cssStyle="margin-top: 5px">
                                                    <form:option value="" label="---Chọn nhân viên---"/>
                                                    <form:options items="${staffMaps}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                        <div class="col-xs-12">
                                            <br/>
                                        </div>
                                        <div class="col-xs-12 ">
                                            <br/>
                                            <form:checkboxes items="${typesEnumStringMap}" path="buildingTypes"
                                                             cssStyle="margin-left: 10px"/>
                                        </div>
                                        <div class="col-xs-12"><br/></div>
                                        <div class="col-xs-12">
                                            <button
                                                    id="btnSearch"
                                                    class="btn btn-success">Tìm kiếm
                                                <i class="fa fa-arrow-right" aria-hidden="true"></i></button>
                                        </div>
                                        <div class="col-xs-12">
                                            <br/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </form:form>
                </div>

                <div class="pull-right">
                    <a href='<c:url value='/admin/building-edit'/>'>
                        <button
                                class="btn btn-white btn-info btn-bold"
                                id=""
                                data-toggle="tooltip" title="Thêm mới">
                            <i class="fa fa-plus" aria-hidden="true"
                            ></i>
                        </button>
                    </a>
                    <button
                            class="btn btn-white btn-warning btn-bold"
                            id="btnDeleteBuilding"
                            data-toggle="tooltip" title="Xóa">
                        <i class="fa fa-trash" aria-hidden="true"
                        ></i>
                    </button>
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-xs-12"><br/></div>
                </div>
                <div class="row">

                    <div class="col-xs-12">

                        <table id="buildingList" class="table table-striped table-bordered table-hover ">
                            <thead>
                            <tr>
                                <th></th>
                                <th>Tên sản phẩm</th>
                                <th>Địa chỉ</th>
                                <th>Tên quản lí</th>
                                <th>Số điện thoại</th>
                                <th>Số đường hầm</th>
                                <th>Diện tích sàn</th>
                                <th>Giá thuê</th>
                                <th>Phí dịch vụ</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${list}">
                                <tr style="font-size: 14px;font-weight: bold">
                                    <td><input type="checkbox" value="${item.id}}" id="${item.id}"/></td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.managerName}</td>
                                    <td>${item.managerPhone}</td>
                                    <td>${item.numberOfBasement}</td>
                                    <td>${item.floorArea}m<sup>2</sup></td>
                                    <td>${item.rentPrice}tr/m<sup>2</sup></td>
                                    <td>${item.serviceFee}</td>
                                    <td>
                                        <button class="btn btn-xs btn-info" onclick="assignmentBuilding(${item.id})">
                                            <i class="fa fa-share"
                                               data-toggle="tooltip" data-placement="top" title="Giao tòa nhà"
                                            ></i>
                                        </button>
                                        <button class="btn btn-xs btn-info" onclick="edit(${item.id})">
                                            <i class="ace-icon fa fa-pencil-square-o"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.page-content -->
    </div>
</div>
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value=""/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="click">Đóng</button>
            </div>
        </div>

    </div>
</div>
<script>
    function edit(buildingId) {
        $.ajax({
            type: 'GET',
            url: "${LoadStaffAPI}/building-edit/" + buildingId,
            success: function (response) {
                console.log("thanh cong");
                console.log(response);
            },
            error: function (response) {
                console.log("that bai");
                console.log(response);
            }
        });
    }

    function assignmentBuilding(BuildingId) {
        debugger;
        openModalAssignmentBuilding();
        loadStaffOfBuilding(BuildingId);
        $('#buildingId').val(BuildingId);
        console.log($('#buildingId').val());
    }

    function loadStaffOfBuilding(BuildingId) {
        $.ajax({
            type: 'GET',
            url: "${LoadStaffAPI}/" + BuildingId + "/staffs",
            dataType: 'json',
            success: function (response) {
                console.log("thanh cong");
                console.log(response);
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="text-center"><input type="checkbox" value=' + item.id + ' id="checkbox_' + item.id + '" ' + item.checked + '/></td>';
                    row += '<td class="text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });

                $('#staffList tbody').html(row);
            },
            error: function (response) {
                console.log("that bai");
                console.log(response);
            }
        });
    }

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        let data = {};
        data['buildingId'] = $('#buildingId').val();
        let staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = staffs;
        assignStaff(data)
    });

    function assignStaff(data) {
        $.ajax({
            type: 'POST',
            url: "${LoadStaffAPI}/assignStaff",
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $('#click').click();
            },
            error: function (response) {

            }
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        let data = {};
        let buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['buildingIds'] = buildingIds;
        btnDeleteBuilding(data)
    });

    function btnDeleteBuilding(data) {
        $.ajax({
            url: "",
            type: "DELETE",
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                // calleed when successful
                let name = $('#name').val();
            },
            error: function (response) {

            }
        });
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#listForm').submit();
    });
</script>
</body>
</html>
