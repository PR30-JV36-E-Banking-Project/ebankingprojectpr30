<%-- 
    Document   : registerSuccess
    Created on : Sep 29, 2020, 6:31:04 PM
    Author     : Hoang Duy Nhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>E-Banking a Banking Category Bootstrap Bootstrap Responsive website Template | Contact :: w3layouts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="E-Banking Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                        <h1><a href="index.html"><img src="../../resources/images/e.png" alt="logo"/>Banking</a></h1> 
                    </div> 
                    <div class="agileits-hdright nav navbar-nav">
                        <div class="header-w3top"><!-- header-top --> 
                            <ul class="w3l-nav-top">
                                <li><i class="fa fa-phone"></i><span> +01 222 111 444 </span></li> 
                                <li><a href="mailto:example@mail.com"><i class="fa fa-envelope-o"></i> <span> mail@example.com</span></a></li>
                            </ul>
                            <div class="clearfix"> </div> 	 
                        </div>
                        <div class="agile_social_banner">
                            <ul class="agileits_social_list">
                                <li><a href="#" class="w3_agile_facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                <li><a href="#" class="agile_twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                <li><a href="#" class="w3_agile_dribble"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
                                <li><a href="#" class="w3_agile_vimeo"><i class="fa fa-vimeo" aria-hidden="true"></i></a></li>
                            </ul>
                        </div>  

                    </div>
                    <div class="clearfix"> </div> 
                </div>	
            </div>	
        </div>	
        <!-- //header -->  
        <!-- banner -->
        <div class="banner inner-banner">
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
                            <li><a href="index.html">Home</a></li>
                            <li><a href="about.html">About</a></li>    
                            <li><a href="services.html">services</a></li>    
                            <li><a href="gallery.html">Gallery</a></li>    
                            <li><a href="icons.html" data-toggle="dropdown">Short Codes<span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="icons.html">Icons</a></li>
                                    <li><a href="typography.html">Typograpghy</a></li>
                                </ul>
                            </li>	
                            <li><a href="contact.html" >Contact Us</a></li>
                        </ul>  
                        <div class="clearfix"> </div>	
                    </div>
                </nav>    
            </div>
            <!-- banner-text -->
            <!-- banner -->
        </div>	
        <!-- contact -->
        <div class="w3-bar-block w3-collapse w3-card w3-animate-left" style="width:30%; float: left; margin: 70px; height: 100%" id="mySidebar">
            <ul style="margin: 20px; list-style-type: none">Accounts
                <li><a href="#" class="w3-bar-item w3-button" style="margin: 10px;">Customer Information</a></li>
                <li><a href="/viewTransaction" class="w3-bar-item w3-button" style="margin: 10px">View Transactions</a></li>
            </ul>
            <ul style="margin: 20px; list-style-type: none">Tranfers
                <li><a href="/interTranfer" class="w3-bar-item w3-button" style="margin: 10px">Internal Tranfer</a></li>

                <li><a href="#" class="w3-bar-item w3-button" style="margin: 10px">External Tranfer</a></li>
            </ul>
            <ul style="margin: 20px; list-style-type: none">Suports
                <li><a href="#" class="w3-bar-item w3-button" style="margin: 10px">Help</a></li>
                <li><a href="#" class="w3-bar-item w3-button" style="margin: 10px">Change password</a></li>
                <li><a href="#" class="w3-bar-item w3-button" style="margin: 10px">Logout</a></li>
            </ul>
        </div>

        <div style="margin-left:30%">
            <div class="w3ls-section contact">
                <div class="container"> 
                    <div>
                        <div class="w3ls-section contact">
                            <div class="container"> 
                                <div class="contact_wthreerow agileits-w3layouts">
                                    <div class="col-md-7 w3l_contact_form" style="border: 1px solid black; padding: 30px;">
                                        <h1 style="margin: 20px">Change Your Password</h1>
                                        <form action="/user/updatePassword" method="post" modelAttribute="user">
                                            <input style="margin: 10px;" type="password"  required="" placeholder="Enter Your Old Password" name="oldPassword" value="oldPassword"/>
                                            <errors class="error"/>
                                            <input style="margin: 10px;" type="password"  required="" placeholder="Enter Your New Password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" id="password"  onkeyup='check();' />
                                            <errors class="error1"/>
                                            <input style="margin: 10px;" type="password" placeholder="Comfirm Your New Password" name="confirm_password" id="confirm_password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" id="password"  onkeyup='check();'/>
                                            <span id='message'></span>
                                            <span id="error" style="color:red">${error}</span>
                                            <span id="success" style="color:blue">${success}</span>
                                            <input style="margin: 10px; float: right;" type="submit" value="Change Your Password">
                                        </form>
                                    </div>
                                </div>

                                <div class="clearfix"> </div>
                            </div>
                        </div>
                    </div>  

                    <div class="clearfix"> </div>
                </div>                                                         </div>
        </div>
        <!-- //contact --> 

        <!--footer-->
        <div class="agile-footer w3ls-section">
            <div class="container">
                <div class="col-md-7 list-footer">
                    <ul class="footer-nav">
                        <li><a  href="index.html">Home</a></li>
                        <li><a  href="about.html">About</a></li>
                        <li><a  href="services.html">Services</a></li>
                        <li><a href="gallery.html">Gallery</a></li>
                        <li><a href="contact.html">Contact Us</a></li>
                    </ul>
                    <p>Vivamus sed porttitor felis. Pellentesque habitant morbi tristique senectus et netus et ctetur adipiscing elit. Cras rutrum iaculis</p>
                </div>
                <div class="col-md-5 agileinfo-sub">
                    <h6>Click the link below to start the subscription service</h6>
                    <a href="#" data-toggle="modal" data-target="#myModal1">subscribe</a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>	 
        <div class="w3_agile-copyright text-center">
            <p>© 2017 E-Banking. All rights reserved | Design by <a href="//w3layouts.com/">W3layouts</a></p>
        </div>
        <!--//footer-->	
        <!-- subscribe -->
        <div class="modal bnr-modal fade" id="myModal1" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <img src="../../resources/images/logo.png" alt="logo"/>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
                    </div> 
                    <div class="modal-body modal-spa">
                        <p>E-Banking's email newsletter provides subscribers with helpful articles on important issues in the banking industry, as well as news about events and more! To sign up for the newsletter, fill the below form.</p>
                        <form class=" wthree-subsribe" action="#" method="post"> 
                            <input type="text" name="name" placeholder="Your Name" required="">
                            <input type="email" name="email" placeholder="your Email" required="">
                            <input type="submit" value="SignUp"> 
                            <div class="clearfix"></div>
                        </form>
                    </div> 
                </div>
            </div>
        </div>
        <!-- //subscribe --> 
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
                                        var check = function () {
                                            if (document.getElementById('password').value ==
                                                    document.getElementById('confirm_password').value) {
                                                document.getElementById('message').style.color = 'green';
                                                document.getElementById('message').innerHTML = 'Password is matched';
                                            } else {
                                                document.getElementById('message').style.color = 'red';
                                                document.getElementById('message').innerHTML = 'Password is not matched';
                                            }
                                        }
        </script>
</html>
