<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/7/15
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>疫情数据查询</title>
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page" />
    <!-- Bootstrap 的 CSS 文件 -->
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
    <!-- Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Nunito:400,600,700,800|Roboto:400,500,700" rel="stylesheet">
    <!-- Theme CSS -->
    <link type="text/css" href="${path}/resource/assets/css/theme.css" rel="stylesheet">
    <!-- Demo CSS - No need to use these in your project -->
    <link type="text/css" href="${path}/resource/assets/css/demo.css" rel="stylesheet">
</head>
<body>



<main class="main">
    <section class="spotlight parallax bg-cover bg-size--cover" data-spotlight="fullscreen" style="background-image: url('${path}/resource/assets/images/backgrounds/img-4.jpg')">
        <span class="mask bg-tertiary alpha-7"></span>
        <div class="spotlight-holder py-lg pt-lg-xl">
            <div class="container d-flex align-items-center no-padding">
                <div class="col">
                    <div class="row cols-xs-space align-items-center text-center text-md-left justify-content-center">
                        <div class="col-lg-7">
                            <div class="mb-5 text-center">
                                <h1 class="heading h1 text-white">全国疫情数据查询</h1>
                            </div>
                            <div class="text-center mt-5">
                                <form>
                                    <div class="row mb-4">
                                        <div class="col-md-12">
                                            <h1 class="heading h1 text-white">
                                                查询省份
                                            </h1>
                                            <select class="selectpicker" id="province"  data-live-search="true" data-live-search-placeholder="Search ...">
                                                <option selected>Choose...</option>
                                                <option value="天津市" >天津市</option>
                                                <option value="北京市">北京市</option>
                                                <option value="河北省">河北省</option>
                                                <option value="河南省">河南省</option>
                                                <option value="山西省">山西省</option>
                                                <option value="陕西省">陕西省</option>
                                                <option value="黑龙江省">黑龙江省</option>
                                                <option value="辽宁省">辽宁省</option>
                                                <option value="吉林省">吉林省</option>
                                                <option value="山东省">山东省</option>
                                                <option value="湖南省">湖南省</option>
                                                <option value="江西省">江西省</option>
                                                <option value="湖北省">湖北省</option>
                                                <option value="重庆市">重庆市</option>
                                                <option value="四川省">四川省</option>
                                                <option value="云南省">云南省</option>
                                                <option value="贵州省">贵州省</option>
                                                <option value="西藏自治区">西藏自治区</option>
                                                <option value="广东省">广东省</option>
                                                <option value="广西壮族自治区">广西壮族自治区</option>
                                                <option value="新疆维吾尔自治区">新疆维吾尔自治区</option>
                                                <option value="宁夏回族自治区">宁夏回族自治区</option>
                                                <option value="浙江省">浙江省</option>
                                                <option value="江苏省">江苏省</option>
                                                <option value="上海市">上海市</option>
                                                <option value="安徽省">安徽省</option>
                                                <option value="福建省">福建省</option>
                                                <option value="海南省">海南省</option>
                                                <option value="内蒙古自治区">内蒙古自治区</option>
                                                <option value="甘肃省">甘肃省</option>
                                                <option value="青海省">青海省</option>
                                                <option value="台湾省">台湾省</option>
                                                <option value="香港特别行政区">香港特别行政区</option>
                                                <option value="澳门特别行政区">澳门特别行政区</option>
                                            </select>
                                        </div>

                                    </div>
                                    <div class="row mb-4">
                                        <div class="col-md-12">
                                            <h1 class="heading h1 text-white">
                                                查询月份
                                            </h1>
                                            <select class="selectpicker" id="month" data-live-search="true" data-live-search-placeholder="Search ..." >
                                                    <option selected>Choose...</option>
                                                    <option value="201912">2019年12月</option>
                                                    <option value="202001">2020年1月</option>
                                                    <option value="202002">2020年2月</option>
                                                    <option value="202003">2020年3月</option>
                                                    <option value="202004">2020年4月</option>
                                                    <option value="202005">2020年5月</option>
                                                    <option value="202006">2020年6月</option>
                                                    <option value="202007">2020年7月</option>
                                                    <option value="202008">2020年8月</option>
                                                    <option value="202009">2020年9月</option>
                                                    <option value="202010">2020年10月</option>
                                                    <option value="202011">2020年11月</option>

                                            </select>
                                        </div>
                                    </div>

                                    <button type="button" class="btn btn-primary btn-animated btn-animated-x" onclick="queryData()">
                                        <span class="btn-inner--visible">查询</span>
                                        <span class="btn-inner--hidden"><i class="fas fa-arrow-right"></i></span>
                                    </button>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</main>


    <script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>
<!-- Core -->
<script src="${path}/resource/assets/vendor/jquery/jquery.min.js"></script>
<script src="${path}/resource/assets/vendor/popper/popper.min.js"></script>
<script src="${path}/resource/assets/js/bootstrap/bootstrap.min.js"></script>
<!-- FontAwesome 5 -->
<script src="${path}/resource/assets/vendor/fontawesome/js/fontawesome-all.min.js" defer></script>
<!-- Page plugins -->
<script src="${path}/resource/assets/vendor/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="${path}/resource/assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="${path}/resource/assets/vendor/input-mask/input-mask.min.js"></script>
<script src="${path}/resource/assets/vendor/nouislider/js/nouislider.min.js"></script>
<script src="${path}/resource/assets/vendor/textarea-autosize/textarea-autosize.min.js"></script>
<!-- Theme JS -->
<script src="${path}/resource/assets/js/theme.js"></script>
    <script type="text/javascript">
        function queryData() {
            location.href="${path}/yq/view.do?province="+$("#province").val()+"&&month="+$("#month").val();
        }
    </script>
</body>
</html>
