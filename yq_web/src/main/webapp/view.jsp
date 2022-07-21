<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2022/7/15
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>疫情数据可视化</title>
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page" />
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
    <!-- 必须的 meta 标签 -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap 的 CSS 文件 -->
    <link rel="stylesheet" href="${path}/resource/bootstrap/css/bootstrap.css">
    <link href="${path}/resource/css/tabler.min.css" rel="stylesheet"/>
    <link href="${path}/resource/css/tabler-flags.min.css" rel="stylesheet"/>
    <link href="${path}/resource/css/tabler-payments.min.css" rel="stylesheet"/>
    <link href="${path}/resource/css/tabler-vendors.min.css" rel="stylesheet"/>
    <link href="${path}/resource/css/demo.min.css" rel="stylesheet"/>
</head>
<body>

<div class="page">
    <header class="navbar navbar-expand-md navbar-light d-print-none">
        <div class="container-xl">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbar-menu">
                <span class="navbar-toggler-icon"></span>
            </button>
            <h1 class="navbar-brand navbar-brand-autodark d-none-navbar-horizontal pe-0 pe-md-3">
                <a href=".">
                    <img src="${path}/resource/static/logo.svg" width="110" height="32" alt="Tabler" class="navbar-brand-image">
                </a>
            </h1>
            <div class="navbar-nav flex-row order-md-last">
                <!--github链接-->
                <div class="nav-item d-none d-md-flex me-3">
                    <div class="btn-list">
                        <a href="https://github.com/Christine-0107" class="btn" target="_blank" rel="noreferrer">
                            <!-- Download SVG icon from http://tabler-icons.io/i/brand-github -->
                            <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M9 19c-4.3 1.4 -4.3 -2.5 -6 -3m12 5v-3.5c0 -1 .1 -1.4 -.5 -2c2.8 -.3 5.5 -1.4 5.5 -6a4.6 4.6 0 0 0 -1.3 -3.2a4.2 4.2 0 0 0 -.1 -3.2s-1.1 -.3 -3.5 1.3a12.3 12.3 0 0 0 -6.2 0c-2.4 -1.6 -3.5 -1.3 -3.5 -1.3a4.2 4.2 0 0 0 -.1 3.2a4.6 4.6 0 0 0 -1.3 3.2c0 4.6 2.7 5.7 5.5 6c-.6 .6 -.6 1.2 -.5 2v3.5" /></svg>
                            Source code
                        </a>
                    </div>
                </div>
                <!--亮度模式-->
                <div class="d-none d-md-flex">
                    <a href="?theme=dark" class="nav-link px-0 hide-theme-dark" title="Enable dark mode" data-bs-toggle="tooltip" data-bs-placement="bottom">
                        <!-- Download SVG icon from http://tabler-icons.io/i/moon -->
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><path d="M12 3c.132 0 .263 0 .393 0a7.5 7.5 0 0 0 7.92 12.446a9 9 0 1 1 -8.313 -12.454z" /></svg>
                    </a>
                    <a href="?theme=light" class="nav-link px-0 hide-theme-light" title="Enable light mode" data-bs-toggle="tooltip" data-bs-placement="bottom">
                        <!-- Download SVG icon from http://tabler-icons.io/i/sun -->
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><circle cx="12" cy="12" r="4" /><path d="M3 12h1m8 -9v1m8 8h1m-9 8v1m-6.4 -15.4l.7 .7m12.1 -.7l-.7 .7m0 11.4l.7 .7m-12.1 -.7l-.7 .7" /></svg>
                    </a>
                </div>
            </div>
        </div>
    </header>

    <div class="navbar-expand-md">
        <div class="collapse navbar-collapse" id="navbar-menu">
            <div class="navbar navbar-light">
                <div class="container-xl">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="query.do" >
                    <span class="nav-link-icon d-md-none d-lg-inline-block"><!-- Download SVG icon from http://tabler-icons.io/i/home -->
                      <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><polyline points="5 12 3 12 12 3 21 12 19 12" /><path d="M5 12v7a2 2 0 0 0 2 2h10a2 2 0 0 0 2 -2v-7" /><path d="M9 21v-6a2 2 0 0 1 2 -2h2a2 2 0 0 1 2 2v6" /></svg>
                    </span>
                                <span class="nav-link-title">
                      Home
                    </span>
                            </a>
                        </li>

                    </ul>
                    <div class="my-2 my-md-0 flex-grow-1 flex-md-grow-0 order-first order-md-last">
                        <form action="." method="get">
                            <div class="input-icon">
                    <span class="input-icon-addon">
                      <!-- Download SVG icon from http://tabler-icons.io/i/search -->
                      <svg xmlns="http://www.w3.org/2000/svg" class="icon" width="24" height="24" viewBox="0 0 24 24" stroke-width="2" stroke="currentColor" fill="none" stroke-linecap="round" stroke-linejoin="round"><path stroke="none" d="M0 0h24v24H0z" fill="none"/><circle cx="10" cy="10" r="7" /><line x1="21" y1="21" x2="15" y2="15" /></svg>
                    </span>
                                <input type="text" value="" class="form-control" placeholder="Search…" aria-label="Search in website">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="page-wrapper">
        <div class="container-xl">
            <!-- Page title -->
            <div class="page-header d-print-none">
                <div class="row g-2 align-items-center">
                    <div class="col">
                        <h2 class="page-title">
                            Epidemic data
                        </h2>
                    </div>
                </div>
            </div>
        </div>
        <div class="page-body">
            <div class="container-xl">
                <div class="row row-cards">

                    <div class="col-4">
                    </div>
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex">
                                    <h3 class="card-title">${queryInfo.date6}月${queryInfo.province}疫情信息</h3>
                                    <div class="ms-auto">
                                        <div class="dropdown">
                                            <a class="dropdown-toggle text-muted" href="#" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">This month</a>
                                            <div class="dropdown-menu dropdown-menu-end">
                                                <a class="dropdown-item active" href="#">This month</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="chart-social-referrals"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex">
                                    <h3 class="card-title">2019年12月至2020年12月${queryInfo.province}疫情信息</h3>
                                    <div class="ms-auto">
                                    </div>
                                </div>
                                <div id="chart-tasks-overview"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xl-4">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex">
                                    <h3 class="card-title">${queryInfo.date6}月${queryInfo.province}疫情信息比例</h3>
                                    <div class="ms-auto">
                                    </div>
                                </div>
                                <div id="chart-demo-pie"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6 col-xl-8">
                        <div class="card">
                            <div class="card-body">
                                <div class="d-flex">
                                    <h3 class="card-title">2019年12月至2020年12月全国疫情信息</h3>
                                    <div class="ms-auto">
                                    </div>
                                </div>
                            </div>
                                <div id="chart-completion-tasks"></div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <footer class="footer footer-transparent d-print-none">
            <div class="container-xl">
                <div class="row text-center align-items-center flex-row-reverse">
                    <div class="col-12 col-lg-auto mt-3 mt-lg-0">
                        <ul class="list-inline list-inline-dots mb-0">
                            <li class="list-inline-item">
                                Copyright &copy; 2022
                                <a href="." class="link-secondary">Tabler</a>.
                                All rights reserved.
                            </li>
                            <li class="list-inline-item">
                                <a href="./changelog.html" class="link-secondary" rel="noopener">
                                    v1.0.0-beta10
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</div>


<!-- Libs JS -->
<script src="${path}/resource/dist/libs/apexcharts/dist/apexcharts.min.js" defer></script>
<!-- Tabler Core -->
<script src="${path}/resource/dist/js/tabler.min.js" defer></script>
<script src="${path}/resource/dist/js/demo.min.js" defer></script>
<script type="text/javascript" src="${path}/resource/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${path}/resource/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${path}/resource/jquery/echarts.min.js"></script>
<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<!--图一-->
<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        window.ApexCharts && (new ApexCharts(document.getElementById('chart-social-referrals'), {
            chart: {
                type: "line",
                fontFamily: 'inherit',
                height: 288,
                parentHeightOffset: 0,
                toolbar: {
                    show: false,
                },
                animations: {
                    enabled: false
                },
            },
            fill: {
                opacity: 1,
            },
            stroke: {
                width: 2,
                lineCap: "round",
                curve: "smooth",
            },
            series: [{
                name: "现存确诊人数",
                data: [
                    <c:forEach items="${yqDataDay}" var="yqDay">
                    ${yqDay.tdConfirmed},
                    </c:forEach>
                ]
            },{
                name: "新增确诊人数",
                data: [
                    <c:forEach items="${yqDataDay}" var="yqDay">
                    ${yqDay.incConfirmed},
                    </c:forEach>
                ]
            },{
                name: "新增治愈人数",
                data: [
                    <c:forEach items="${yqDataDay}" var="yqDay">
                    ${yqDay.incCured},
                    </c:forEach>
                ]
            },{
                name: "新增死亡人数",
                data: [
                    <c:forEach items="${yqDataDay}" var="yqDay">
                    ${yqDay.incDead},
                    </c:forEach>
                ]
            }],
            grid: {
                padding: {
                    top: -20,
                    right: 0,
                    left: -4,
                    bottom: -4
                },
                strokeDashArray: 4,
                xaxis: {
                    lines: {
                        show: true
                    }
                },
            },
            xaxis: {
                labels: {
                    padding: 0,
                },
                tooltip: {
                    enabled: false
                },

                data: [
                    <c:forEach items="${yqDataDay}" var="yqDay">
                    ${yqDay.date},
                    </c:forEach>
                ]
            },
            yaxis: {
                labels: {
                    padding: 4
                },
            },

            colors: ["#206bc4", "#79a6dc", "#d2e1f3", "#e9ecf1"],
            legend: {
                show: true,
                position: 'bottom',
                offsetY: 12,
                markers: {
                    width: 10,
                    height: 10,
                    radius: 100,
                },
                itemMargin: {
                    horizontal: 8,
                    vertical: 8
                },
            },
        })).render();
    });
    // @formatter:on
</script>
<!--图二-->
<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        window.ApexCharts && (new ApexCharts(document.getElementById('chart-tasks-overview'), {
            chart: {
                type: "bar",
                fontFamily: 'inherit',
                height: 240,
                parentHeightOffset: 0,
                toolbar: {
                    show: false,
                },
                animations: {
                    enabled: false
                },
            },
            plotOptions: {
                bar: {
                    columnWidth: '50%',
                }
            },
            dataLabels: {
                enabled: false,
            },
            fill: {
                opacity: 1,
            },
            series: [{
                name: "总计确诊人数",
                data: [
                    <c:forEach items="${yqDataMonth}" var="yqMonth">
                    ${yqMonth.sumConfirmed},
                    </c:forEach>
                ]
            },{
                name: "总计疑似人数",
                data: [
                    <c:forEach items="${yqDataMonth}" var="yqMonth">
                    ${yqMonth.sumSuspected},
                    </c:forEach>
                ]
            },{
                name: "总计治愈人数",
                data: [
                    <c:forEach items="${yqDataMonth}" var="yqMonth">
                    ${yqMonth.sumCured},
                    </c:forEach>
                ]
            },{
                name: "总计死亡人数",
                data: [
                    <c:forEach items="${yqDataMonth}" var="yqMonth">
                    ${yqMonth.sumDead},
                    </c:forEach>
                ]
            }],
            grid: {
                padding: {
                    top: -20,
                    right: 0,
                    left: -4,
                    bottom: -4
                },
                strokeDashArray: 4,
            },
            xaxis: {
                labels: {
                    padding: 0,
                },
                tooltip: {
                    enabled: false
                },
                axisBorder: {
                    show: false,
                },
                categories: ['2019.12','2020.01', '2020.02', '2020.03', '2020.04', '2020.05', '2020.06', '2020.07', '2020.08', '2020.09', '2020.10', '2020.11','2020.12'],
            },
            yaxis: {
                labels: {
                    padding: 4
                },
            },
            colors: ["#206bc4", "#79a6dc", "#d2e1f3", "#e9ecf1"],
            legend: {
                show: true,
                position: 'bottom',
                offsetY: 12,
                markers: {
                    width: 10,
                    height: 10,
                    radius: 100,
                },
                itemMargin: {
                    horizontal: 8,
                    vertical: 8
                },
            },
        })).render();
    });
    // @formatter:on
</script>
<!--图三-->
<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        window.ApexCharts && (new ApexCharts(document.getElementById('chart-demo-pie'), {
            chart: {
                type: "donut",
                fontFamily: 'inherit',
                height: 240,
                sparkline: {
                    enabled: true
                },
                animations: {
                    enabled: false
                },
            },
            fill: {
                opacity: 1,
            },
            series: [
                ${yqDataMonthThis.sumConfirmed},
                ${yqDataMonthThis.sumSuspected},
                ${yqDataMonthThis.sumCured},
                ${yqDataMonthThis.sumDead}
            ],
            labels: ["总计确诊人数", "总计疑似人数", "总计治愈人数", "总计死亡人数"],
            grid: {
                strokeDashArray: 4,
            },
            colors: ["#206bc4", "#79a6dc", "#d2e1f3", "#e9ecf1"],
            legend: {
                show: true,
                position: 'bottom',
                offsetY: 12,
                markers: {
                    width: 10,
                    height: 10,
                    radius: 100,
                },
                itemMargin: {
                    horizontal: 8,
                    vertical: 8
                },
            },
            tooltip: {
                fillSeriesColor: false
            },
        })).render();
    });
    // @formatter:on
</script>
<!--图四-->
<script>
    // @formatter:off
    document.addEventListener("DOMContentLoaded", function () {
        window.ApexCharts && (new ApexCharts(document.getElementById('chart-completion-tasks'), {
            chart: {
                type: "bar",
                fontFamily: 'inherit',
                height: 240,
                parentHeightOffset: 0,
                toolbar: {
                    show: false,
                },
                animations: {
                    enabled: false
                },
                stacked: true,
            },
            plotOptions: {
                bar: {
                    columnWidth: '50%',
                }
            },
            dataLabels: {
                enabled: false,
            },
            fill: {
                opacity: 1,
            },
            series: [{
                name: "全国总计确诊人数",
                data: [
                    <c:forEach items="${yqDataMonthAll}" var="yqMonth">
                    ${yqMonth.sumConfirmed},
                    </c:forEach>
                ]
            },{
                name: "全国总计疑似人数",
                data: [
                    <c:forEach items="${yqDataMonthAll}" var="yqMonth">
                    ${yqMonth.sumSuspected},
                    </c:forEach>
                ]
            },{
                name: "全国总计治愈人数",
                data: [
                    <c:forEach items="${yqDataMonthAll}" var="yqMonth">
                    ${yqMonth.sumCured},
                    </c:forEach>
                ]
            },{
                name: "全国总计死亡人数",
                data: [
                    <c:forEach items="${yqDataMonthAll}" var="yqMonth">
                    ${yqMonth.sumDead},
                    </c:forEach>
                ]
            }],
            grid: {
                padding: {
                    top: -20,
                    right: 0,
                    left: -4,
                    bottom: -4
                },
                strokeDashArray: 4,
            },
            xaxis: {
                labels: {
                    padding: 0,
                },
                tooltip: {
                    enabled: false
                },
                axisBorder: {
                    show: false,
                },
                categories: ['2019.12','2020.01', '2020.02', '2020.03', '2020.04', '2020.05', '2020.06', '2020.07', '2020.08', '2020.09', '2020.10', '2020.11','2020.12']
            },
            yaxis: {
                labels: {
                    padding: 4
                },
            },
            colors: ["#206bc4", "#79a6dc", "#d2e1f3", "#e9ecf1"],
            legend: {
                show: true,
                position: 'bottom',
                offsetY: 12,
                markers: {
                    width: 10,
                    height: 10,
                    radius: 100,
                },
                itemMargin: {
                    horizontal: 8,
                    vertical: 8
                },
            },
        })).render();
    });
    // @formatter:on
</script>
</body>
</html>
