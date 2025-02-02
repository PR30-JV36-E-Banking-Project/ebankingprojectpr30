<%-- 
    Document   : registerSuccess
    Created on : Sep 29, 2020, 6:31:04 PM
    Author     : Hoang Duy Nhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>E-Banking a Banking Category Bootstrap Bootstrap Responsive website Template | Contact :: w3layouts</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="E-Banking Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, Sony Ericsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- Custom Theme files -->
        <link href="../../resources/css/bootstrap.css" type="text/css" rel="stylesheet" media="all">
        <link href="../../resources/css/style.css" type="text/css" rel="stylesheet" media="all">
        <link href="../../resources/css/font-awesome.css" rel="stylesheet">   <!-- font-awesome icons --> 
        <link href="../../resources/css/custom.css" rel="stylesheet"> 

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
                        <h1><a href="<c:url value = "/"/>"><img src="../../resources/images/e.png" alt="logo"/>Banking</a></h1> 
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
        <div><!-- header-three --> 	
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
                                <li><a href="<c:url value = "/newTranfer"/>">Tranfer Services</a></li>
                                <li><a href="<c:url value = "/viewTransaction"/>">View Tranfer</a></li>
                                <li><a href="<c:url value = "/account/viewCustomerInfor"/>">View account Information</a></li>
                            </ul>
                        </li>
                        <li><a href="<c:url value = "/newAccount"/>" class="scroll">New Account</a></li>
                        <li><a href="#" class="scroll">News & Events</a></li>    	
                        <li><a href="list-teller" class="scroll">Contact Us</a></li>
                    </ul>  
                    <div class="clearfix"> </div>	
                </div>
            </nav>    
        </div>
        <!-- banner-text -->

        <!--slide carousel-->
        <div class="container slidecarousel">
            <div  id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">

                    <div class="item active">
                        <img  style="border-radius:10px;" src="../../resources/images/Banner1.jpg" alt="Chania" width="100%" height="300">
                        <div class="carousel-caption">
                            <h3>Deposit!</h3>
                            <h4>Only one registration for all transactions!</h4>
                        </div>
                    </div>

                    <div class="item">
                        <img style="border-radius:10px;" src="../../resources/images/Banner2.png" alt="Chania" width="100%" height="250">
                        <div class="carousel-caption">
                            <h3>Quick transaction!</h3>
                            <h4>Only one registration for all transactions!</h4>
                        </div>
                    </div>

                    <div class="item">
                        <img style="border-radius:10px;" src="../../resources/images/Banner3.png" alt="Chania" width="100%" height="250">
                        <div class="carousel-caption">
                            <h3>Basic account package.</h3>
                            <h4>Only one registration for all transactions!</h4>
                        </div>
                    </div>

                    <div class="item">
                        <img style="border-radius:10px;" src="../../resources/images/Banner4.png" alt="Chania" width="100%" height="250">
                        <div class="carousel-caption">
                            <h3>Withdraw.</h3>
                            <h4>Only one registration for all transactions!</h4>
                        </div>
                    </div>

                    <div class="item">
                        <img style="border-radius:10px;" src="../../resources/images/Banner5.jpg" alt="Chania" width="100%" height="250">
                        <div class="carousel-caption">
                            <h3>Personal loan.</h3>
                            <h4>Only one registration for all transactions!</h4>
                        </div>
                    </div>
                </div>
                <!-- Left and right controls -->
                <a style="border-radius:10px;"  class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a style="border-radius:10px;"  class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <!--slide carousel-->

        <!-- Vertical navbar -->
        <div class="verMenu">
            <div class="bormenu">
                <p class="textmenu">Information Query</p>
                <ul class="listmenu">
                    <li><a href="<c:url value = "/account/viewCustomerInfor"/>">Account Profile</a></li>
                    <li><a href="<c:url value = "/viewTransaction"/>">Transaction Details</a></li>
                    <li><a href="<c:url value = "/viewBallance"/>">View Ballance</a></li>
                    <li><a href="<c:url value = "/account/changePass"/>">Change Password</a></li>
                </ul>
            </div>
            <br>
            <div class="bormenu">
                <p class="textmenu">Transaction</p>
                <ul class="listmenu">
                    <li><a href="<c:url value = "/selectTF?typeTF=1"/>">Internal Transaction</a></li>
                    <li><a href="<c:url value = "/selectTF?typeTF=2"/>">External Transaction</a></li>
                </ul>
            </div>
            <br>
            <div class="bormenu">
                <p class="textmenu">Card</p>
                <ul class="listmenu">
                    <li><a href="#">Open Card</a></li>
                    <li><a href="#">Lock Card</a></li>
                    <li><a href="#">Cancel Internet Banking</a></li>
                </ul>
            </div>
        </div>
        <!--<-----content------->

        <div class="content">
            <div class="col-md-7 info">
                <h4 id="tittle" class="tittle">Registration</h4>
                <div class="wrapper">
                    <form:form action="/account/sign-up" method="post" modelAttribute="user"> 
                        <table class="tablec" style="line-height: 16px !important;">
                            <tbody>
                                <tr>
                                    <td colspan="2"><p>Please user the email which you registered customer account in our bank</p></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">FullName:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your Full Name" path="customerEntity.fullName"/>
                                        <form:errors path="customerEntity.fullName" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Your Date of birth:</label></th>
                                    <td><form:input  type="date"  class="form-control myinput" required="required" path="customerEntity.birthDay"/>
                                        <form:errors path="customerEntity.birthDay" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Gender:</label></th>
                                    <td><form:input type="text"  class="form-control myinput" required="required" placeholder="Enter Your Gender" path="customerEntity.sex"/>
                                        <form:errors path="customerEntity.sex" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Address:</label></th>
                                    <td><form:input type="text"  class="form-control myinput" required="required" placeholder="Enter Your Address" path="customerEntity.address"/>
                                        <form:errors path="customerEntity.address" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Country:</label></th>
                                    <td><form:input type="text"  class="form-control myinput" required="required" placeholder="Enter Your country" path="customerEntity.country"/>
                                        <form:errors path="customerEntity.country" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Nationality:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your Nationality" path="customerEntity.nationlaty"/>
                                        <form:errors path="customerEntity.nationlaty" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">District:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your District" path="customerEntity.district"/>
                                        <form:errors path="customerEntity.district" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">City:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your City" path="customerEntity.city"/>
                                        <form:errors path="customerEntity.city" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Phone:</label></th>
                                    <td><form:input  type="text" class="form-control myinput" required="required" placeholder="Enter Your Phone" path="customerEntity.phone"/>
                                        <form:errors path="customerEntity.phone" class="error"/></td>
                                </tr>
                                
                                <tr>
                                    <th scope="row"><label class="control-label">ID card:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your ID card" path="customerEntity.IDcard"/>
                                        <form:errors path="customerEntity.IDcard" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Date Issue ID Card:</label></th>
                                    <td><form:input type="date" class="form-control myinput" required="required" placeholder="Enter Your date Issue ID Card" path="customerEntity.dateIssueIDCard"/>
                                        <form:errors path="customerEntity.dateIssueIDCard" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">ID Card Office:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your issue ID Card Office" path="customerEntity.issueIDCardOffice"/>
                                        <form:errors path="customerEntity.issueIDCardOffice" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Email (User):</label></th>
                                    <td><form:input type="email"  class="form-control myinput" required="required" placeholder="Enter Your Email (User)" path="email"/>
                                        <form:errors path="email" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">User Name:</label></th>
                                    <td><form:input type="text" class="form-control myinput" required="required" placeholder="Enter Your User Name" path="userName"/>
                                        <form:errors path="userName" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Password:</label></th>
                                    <td><form:input type="password" class="form-control myinput" required="required" placeholder="Enter Your Password" path="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" id="password"  onkeyup='check();' />
                                        <form:errors path="password" class="error"/></td>
                                </tr>
                                <tr>
                                    <th scope="row"><label class="control-label">Comfirm Your Password:</label></th>
                                    <td><input type="password" class="form-control myinput" required="required" placeholder="Comfirm Your Password" name="confirm_password" id="confirm_password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" id="password"  onkeyup='check();'/>
                                        <span id='message'></span></td>
                                </tr>
                                
                                <tr>
                                    <td><span id="error1" style="color:red">${error1}</span>
                                        <span id="error2" style="color:red">${error2}</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <input type="submit"  class="btn btn-primary buttonback" value="Register New Customer">
                    <button onclick="goBack()" type="button" class="btn btn-primary buttonback">Go Back</button>
                </form:form>
            </div>
            <div class="clearfix"> </div>
        </div>
        <!-- //contact --> 

        <!--footer-->
        <div class="agile-footer w3ls-section footer">
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
        <div class="modal bnr-modal fade " id="myModal1" tabindex="-1" role="dialog">
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
        <script src="js/SmoothScroll.min.js"></script>
        <!-- smooth-scrolling-of-move-up -->
        <script type="text/javascript" src="../../resources/js/move-top.js"></script>
        <script type="text/javascript" src="../../resources/js/easing.js"></script>
        <script type="text/javascript" src="../../resources/js/myJavascript.js"></script>
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
    </body>
</html>