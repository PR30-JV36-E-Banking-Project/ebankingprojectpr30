<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">

    <head>
        <title>Manage Teller For Admin</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <!-- VENDOR CSS -->
        <link rel="stylesheet" href="../../resources/assets/vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="../../resources/assets/vendor/font-awesome/css/font-awesome.min.css">
        <link rel="stylesheet" href="../../resources/assets/vendor/linearicons/style.css">
        <link rel="stylesheet" href="../../resources/assets/vendor/chartist/css/chartist-custom.css">
        <!-- MAIN CSS -->
        <link rel="stylesheet" href="../../resources/assets/css/main.css">
        <!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
        <link rel="stylesheet" href="../../resources/assets/css/demo.css">
        <!-- GOOGLE FONTS -->
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
        <!-- ICONS -->
        <link rel="apple-touch-icon" sizes="76x76" href="../../resources/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="../../resources/assets/img/favicon.png">
    </head>

    <body>
        <!-- WRAPPER -->
        <div id="wrapper">
            <!-- NAVBAR -->
            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="brand">
                    <a href="index.html"><img src="../../resources/assets/img/logo.png" alt="Klorofil Logo" class="img-responsive logo"
                                              style="height: 60%; width: 60%;"></a>
                </div>
                <div class="container-fluid">
                    <div class="navbar-btn">
                        <button type="button" class="btn-toggle-fullwidth"><i
                                class="lnr lnr-arrow-left-circle"></i></button>
                    </div>
                    <form class="navbar-form navbar-left">
                    </form>
                    <div id="navbar-menu">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                                    <i class="lnr lnr-alarm"></i>
                                    <span class="badge bg-danger"></span>
                                </a>
                                <ul class="dropdown-menu notifications">
                                    <li><a href="#" class="notification-item"><span class="dot bg-success"></span>You don't
                                            have any notification right now</a></li>
                                    <li><a href="#" class="more">See all notifications</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i
                                        class="lnr lnr-question-circle"></i> <span>Help</span> <i
                                        class="icon-submenu lnr lnr-chevron-down"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Basic Use</a></li>
                                    <li><a href="#">Working With Data</a></li>
                                    <li><a href="#">Security</a></li>
                                    <li><a href="#">Troubleshooting</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img
                                        src="../../resources/assets/img/apple-icon.png" class="img-circle" alt="Avatar">
                                    <span>${userName}</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                                <ul class="dropdown-menu">
                                    <li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
                                    <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
                                    <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                                    <li><a href="#"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- END NAVBAR -->
            <!-- LEFT SIDEBAR -->
            <div id="sidebar-nav" class="sidebar" style="margin-top: 30px;">
                <div class="sidebar-scroll">
                    <nav>
                        <ul class="nav">
                            <li><a href="index.html"><i class="lnr lnr-home"></i> <span>Dashboard</span></a></li>
                            <li><a href="#" class=""><i class="lnr lnr-alarm"></i> <span>Notifications</span></a></li>
                            <li>
                                <a href="#subPages" data-toggle="collapse" class="active"><i class="lnr lnr-cog"></i>
                                    <span>Management</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                                <div id="subPages" class="collapse ">
                                    <ul class="nav">
                                        <li><a href="list-teller" class="lnr lnr-user"> Tellers</a></li>
                                        <li><a href="/account/list-customer" class="lnr lnr-user"> Customers</a></li>
                                        <li><a href="/list-account" class="lnr lnr-user"> Account</a></li>
                                        <li><a href="/list-transaction" class="fa fa-random active"> Transactions</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li><a href="#" class=""><i class="fa fa-credit-card"></i> <span>Withdraw Money</span></a></li>
                            <li><a href="#" class=""><i class="fa fa-credit-card"></i> <span>Deposit Money</span></a></li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- END LEFT SIDEBAR -->
            <!-- MAIN -->
            <div class="main">
                <!-- MAIN CONTENT -->
                <div class="main-content">
                    <div class="container-fluid">
                        <!-- OVERVIEW -->
                        <div class="content">
                            <div class="col-md-7 info">
                                <h4 id="tittle" class="tittle">${tittle}</h4>
                                <h1 style="text-align: center">External Tranfer</h1>
                        <div class="panel-body">
                            <form:form action="addExternalTranfer" cssClass="form-horizontal"
                                       method="post" modelAttribute="transaction">
                                <form:hidden path="transactionID"/>
                                <!-- need to associate this data with customer id -->
                                <div class="form-group">
                                    <label for="senderAccount" class="col-md-3 control-label">Input Sender Account</label>
                                    <div class="col-md-9">
                                        <form:input path="senderAccount" Class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="receiverAccount" class="col-md-3 control-label">Input Receive Account</label>
                                    <div class="col-md-9">
                                        <form:input type="text" path="receiverAccount" Class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="content" class="col-md-3 control-label">Input Content</label>
                                    <div class="col-md-9">
                                        <form:input type="text" path="content" Class="form-control" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="amount" class="col-md-3 control-label">Input Amount</label>
                                    <div class="col-md-9">
                                        <form:input type="text" path="amount" cssClass="form-control" />
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <p style="color: blue">*Note: The tranfer fee is 5000</p>
                                </div>
                                        <span id="success" style="color: green">${success}</span>
                                        <span id="error" style="color: red">${error}</span>
                                    <!-- Button -->
                                    <div class="col-md-offset-3 col-md-9">
                                        <form:button Class="btn btn-primary" style="float: right"> Tranfer Money</form:button>
                                        </div>
                                    </div>

                            </form:form>
                        </div>
                            </div>
                            <div class="clearfix"> </div>
                        </div>
                        <!-- END OVERVIEW -->
                    </div>
                </div>
                <!-- END MAIN CONTENT -->
            </div>
            <!-- END MAIN -->
            <div class="clearfix"></div>
            <footer>
                <div class="container-fluid">
                    <p class="copyright">&copy; 2017 <a href="https://www.themeineed.com" target="_blank">Theme I Need</a>.
                        All Rights Reserved.</p>
                </div>
            </footer>
        </div>
        <!-- END WRAPPER -->
        <!-- Javascript -->
        <script src="../../resources/assets/vendor/jquery/jquery.min.js"></script>
        <script src="../../resources/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
        <script src="../../resources/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
        <script src="../../resources/assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
        <script src="../../resources/assets/vendor/chartist/js/chartist.min.js"></script>
        <script src="../../resources/assets/scripts/klorofil-common.js"></script>
        <script>
                                        $(function () {
                                            var data, options;

                                            // headline charts
                                            data = {
                                                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                                                series: [
                                                    [23, 29, 24, 40, 25, 24, 35],
                                                    [14, 25, 18, 34, 29, 38, 44],
                                                ]
                                            };

                                            options = {
                                                height: 300,
                                                showArea: true,
                                                showLine: false,
                                                showPoint: false,
                                                fullWidth: true,
                                                axisX: {
                                                    showGrid: false
                                                },
                                                lineSmooth: false,
                                            };

                                            new Chartist.Line('#headline-chart', data, options);


                                            // visits trend charts
                                            data = {
                                                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                                                series: [{
                                                        name: 'series-real',
                                                        data: [200, 380, 350, 320, 410, 450, 570, 400, 555, 620, 750, 900],
                                                    }, {
                                                        name: 'series-projection',
                                                        data: [240, 350, 360, 380, 400, 450, 480, 523, 555, 600, 700, 800],
                                                    }]
                                            };

                                            options = {
                                                fullWidth: true,
                                                lineSmooth: false,
                                                height: "270px",
                                                low: 0,
                                                high: 'auto',
                                                series: {
                                                    'series-projection': {
                                                        showArea: true,
                                                        showPoint: false,
                                                        showLine: false
                                                    },
                                                },
                                                axisX: {
                                                    showGrid: false,

                                                },
                                                axisY: {
                                                    showGrid: false,
                                                    onlyInteger: true,
                                                    offset: 0,
                                                },
                                                chartPadding: {
                                                    left: 20,
                                                    right: 20
                                                }
                                            };

                                            new Chartist.Line('#visits-trends-chart', data, options);


                                            // visits chart
                                            data = {
                                                labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                                                series: [
                                                    [6384, 6342, 5437, 2764, 3958, 5068, 7654]
                                                ]
                                            };

                                            options = {
                                                height: 300,
                                                axisX: {
                                                    showGrid: false
                                                },
                                            };

                                            new Chartist.Bar('#visits-chart', data, options);


                                            // real-time pie chart
                                            var sysLoad = $('#system-load').easyPieChart({
                                                size: 130,
                                                barColor: function (percent) {
                                                    return "rgb(" + Math.round(200 * percent / 100) + ", " + Math.round(200 * (1.1 - percent / 100)) + ", 0)";
                                                },
                                                trackColor: 'rgba(245, 245, 245, 0.8)',
                                                scaleColor: false,
                                                lineWidth: 5,
                                                lineCap: "square",
                                                animate: 800
                                            });

                                            var updateInterval = 3000; // in milliseconds

                                            setInterval(function () {
                                                var randomVal;
                                                randomVal = getRandomInt(0, 100);

                                                sysLoad.data('easyPieChart').update(randomVal);
                                                sysLoad.find('.percent').text(randomVal);
                                            }, updateInterval);

                                            function getRandomInt(min, max) {
                                                return Math.floor(Math.random() * (max - min + 1)) + min;
                                            }

                                        });
        </script>
    </body>

</html>