<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>E-Banking Website</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- Custom Theme files -->
        <link href="../../resources/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
        <link href="../../resources/css/style.css" type="text/css" rel="stylesheet" media="all">
        <link href="../../resources/css/font-awesome.css" rel="stylesheet">   <!-- font-awesome icons --> 
        <!-- //Custom Theme files -->  
        <!-- js --> 
        <script src="../../resources/js/jquery-2.2.3.min.js"></script>
        <!-- web-fonts -->
        <link href="//fonts.googleapis.com/css?family=Secular+One" rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i" rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <!-- //web-fonts --> 
    </head>
    <body> 
        <!-- header -->
        <div class="headerw3-agile"> 
            <div class="header-w3mdl"><!-- header-two --> 
                <div class="container"> 
                    <div class="agileits-logo navbar-left">
                        <h1><a href="index.jsp"><img src="../../resources/images/e.png" alt="logo"/>Banking</a></h1> 
                    </div> 
                    <div class="agileits-hdright nav navbar-nav">
                        <div class="header-w3top"><!-- header-top --> 
                            <ul class="w3l-nav-top">
                                <li><i class="fa fa-phone"></i><span> +0123456789 </span></li> 
                                <li><a href="mailto:a@gmail.com"><i class="fa fa-envelope-o"></i><span>hoangduynhat@gmail.com</span></a></li>
                            </ul>
                            <div class="clearfix"> </div> 	 
                        </div>
                        <div class="agile_social_banner">
                            <ul class="agileits_social_list">
                                <li><h2><span class="label label-danger">${username}</span></h2></li>
                                <li><h2><a href="<c:url value='/logout' />"><span class="label label-info">LogOut</span></a></h2></li>
                                
                            </ul>
                        </div>  

                    </div>
                    <div class="clearfix"> </div> 
                </div>	
            </div>	
        </div>	
        <!-- //header -->  
        <!-- banner -->
        <div class="banner">
            <div class="header-nav"><!-- header-three --> 	
                <nav class="navbar navbar-default">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span class="sr-only">Toggle navigation</span>
                            Menu 
                        </button> 
                    </div>
                    <!-- top-nav -->
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li><a href="<c:url value = "/"/>" class="active">Home</a></li>
                            <li><a href="#" class="scroll">About</a></li>    
                            <li><a href="#" class="scroll" data-toggle="dropdown">Services</a>  
                                <ul class="dropdown-menu">
                                    <li><a href="<c:url value = "/interTranfer"/>">Internal Tranfer</a></li>
                                    <li><a href="#">External Tranfer</a></li>
                                    <li><a href="<c:url value = "/viewTransaction"/>">View Tranfer</a></li>
                                    <li><a href="<c:url value = "/account/viewUserInformation"/>">View User Information</a></li>
                                </ul>
                            </li>
                            <li><a href="<c:url value = "/newAccount"/>" class="scroll">New Account</a></li>
                            <li><a href="#" class="scroll">News & Events</a></li>    	
                            <li><a href="#" class="scroll">Contact Us</a></li>
                        </ul>  
                        <div class="clearfix"> </div>	
                    </div>
                </nav>    
            </div>
            <!-- banner-text -->
            <!-- banner -->
            <div class="container">
                <div class="banner-text agileits-w3layouts">
                    <div  id="top" class="callbacks_container">
                        <ul class="rslides" id="slider3">
                            <li>
                                <div class="banner-textagileinfo">
                                    <h6>Welcome To E-Banking</h6>	 
                                    <h3>Safe,secure and convenient banking</h3>	 
                                    <p>Introduce something about Safe,secure and convenient banking.</p>
                                    <div class="agileits-banner-bot">
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>01</h5>
                                            <h4>convenient</h4>	 
                                            <p>Introduce something about convenient</p>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>02</h5>
                                            <h4>secure</h4>	 

                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>03</h5>
                                            <h4>reliable</h4>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>	
                                </div>	
                            </li>
                            <li>
                                <div class="banner-textagileinfo"> 
                                    <h6>Welcome To E-Banking</h6>	
                                    <h3>Safe,secure and convenient banking</h3>	 
                                    <p>Introduce something about Safe,secure and convenient banking.</p>
                                    <div class="agileits-banner-bot">
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>01</h5>
                                            <h4>convenient</h4>	 									
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>02</h5>
                                            <h4>secure</h4>	 
                                            <p>Introduce something about secure </p>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>03</h5>
                                            <h4>reliable</h4>

                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                </div>	
                            </li>
                            <li>
                                <div class="banner-textagileinfo">
                                    <h6>Welcome To E-Banking</h6>	
                                    <h3>Safe,secure and convenient banking</h3>
                                    <p>Introduce something about Safe,secure and convenient banking.</p>
                                    <div class="agileits-banner-bot">
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>01</h5>
                                            <h4>convenient</h4>		
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>02</h5>
                                            <h4>secure</h4>	 

                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-4 w3l-bb-grid1">
                                            <h5>03</h5>
                                            <h4>reliable</h4>
                                            <p>Introduce something about reliable </p>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>	
                                </div>	
                            </li> 
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- about -->
        <div class="home-about w3ls-section">
            <div class="container">
                <!-- about top-->
                <div class="w3ls-about agile-section">
                    <div class="w3-agileits-about-grids">
                        <div class="col-md-6 col-sm-6 col-xs-6 agileits-title">
                            <h3>what makes <img src="../../resources/images/logo.png" alt="logo"/>Unique?</h3> 
                        </div>
                        <div class="col-md-6 col-sm-6 col-xs-6  agile-about-bottom-right">
                            <p>Introduction something about website</p>
                            <p class="w3ls-about-text">Introduction something about the E-Banking.</p>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>
            <!-- //about top-->
            <!--footer-->	 
            <div class="w3_agile-copyright text-center">
                <p>� 2020 E-Banking. | Design by Hoang Duy Nhat</p>
            </div>
            <!--//footer-->	
            <!-- modal-sign -->
            <div class="modal bnr-modal fade" id="myModal1" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <img src="../../resources/images/logo.png" alt="logo"/>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
                        </div> 
                        <div class="modal-body modal-spa">
                            <p>Please Sign In your E-Banking Account to use our services</p>
                            <p>If you haven't have account yet. Please <a href="/account/showForm">Sign Up</a></p>
                            <form class=" wthree-subsribe" modelAttribute="customer"
                                  action="/login" method="post" id="login-Form"> 
                                <tr>
                                    <td>Username</td><br/>
                                <td><input id="user-name" type="text" name="username" placeholder="User Name" required=""></td>
                                </tr>
                                <br/>
                                <tr>
                                    <td>Password</td><br/>
                                <td><input id="password" type="password" name="password" placeholder="your Password" required=""></td>
                                <span id="usPass" style="color:red">${usPass}</span>
                                </tr>
                                <br/>
                                <br/>
                                <br/>
                                <tr>
                                    <td>
                                        <img src=${"/captcha"}>
                                        <br/>
                                        <br/>
                                        <input id="captcha" autocomplete="off" type="text" name="captcha" required="required" style="margin-top: 5px;">
                                        <br/>
                                        <span id="error" style="color:red">${error}</span>
                                        <span id="error2" style="color:red">${error2}</span>
                                    </td>
                                </tr>
                                <br/>
                                <br/>
                                <input type="submit" value="Sign In">  
                                <div class="clearfix"></div>
                            </form>
                        </div> 
                    </div>
                </div>
            </div>
            <!-- //modal-sign -->    
            <!-- //banner -->
            <!-- //banner-text -->  
            <!-- //banner -->
            <!-- banner Slider starts Here -->
            <script src="../../resources/js/responsiveslides.min.js"></script>
            <script>
                // You can also use "$(window).load(function() {"
                $(function () {
                    // Slideshow 3
                    $("#slider3").responsiveSlides({
                        auto: true,
                        pager: false,
                        nav: true,
                        speed: 500,
                        namespace: "callbacks",
                        before: function () {
                            $('.events').append("<li>before event fired.</li>");
                        },
                        after: function () {
                            $('.events').append("<li>after event fired.</li>");
                        }
                    });

                });
            </script>
            <script src="../../resources/js/SmoothScroll.min.js"></script>
            <!-- smooth-scrolling-of-move-up -->
            <script type="text/javascript" src="../../resources/js/move-top.js"></script>
            <script type="text/javascript" src="../../resources/js/easing.js"></script>
            <script type="text/javascript">
                $(document).ready(function () {
                    /*
                     var defaults = {
                     containerID: 'toTop', // fading element id
                     containerHoverID: 'toTopHover', // fading element hover id
                     scrollSpeed: 1200,
                     easingType: 'linear' 
                     };
                     */

                    $().UItoTop({easingType: 'easeOutQuart'});

                });
            </script>
            <!-- //smooth-scrolling-of-move-up -->  

            <!-- Bootstrap core JavaScript
        ================================================== -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="../../resources/js/bootstrap.js"></script>

            <script type="text/javascript">
                function showModal() {
                    $('#myModal1').modal('show');
                }
                ;
            </script>
            <c:if test="${error2!=null||error!=null}">
                <script>
                    showModal();
                </script>
            </c:if>


    </body>
</html>