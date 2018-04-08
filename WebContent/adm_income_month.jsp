
<%@page import="com.medicio.model.AppHistoryDetails"%>
<%@page import="com.medicio.model.PatientDetails"%>
<%@page import="java.util.List"%>
<%@page import="com.medicio.model.DoctorDetails"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Medicio landing page template for Health niche</title>

  <!-- css -->
  <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="plugins/cubeportfolio/css/cubeportfolio.min.css">
  <link href="css/nivo-lightbox.css" rel="stylesheet" />
  <link href="css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css" />
  <link href="css/owl.carousel.css" rel="stylesheet" media="screen" />
  <link href="css/owl.theme.css" rel="stylesheet" media="screen" />
  <link href="css/animate.css" rel="stylesheet" />
  <link href="css/style.css" rel="stylesheet">

  <!-- boxed bg -->
  <link id="bodybg" href="bodybg/bg1.css" rel="stylesheet" type="text/css" />
  <!-- template skin -->
  <link id="t-colors" href="color/default.css" rel="stylesheet">
  <!-- =======================================================
    Theme Name: Medicio
    Theme URL: https://bootstrapmade.com/medicio-free-bootstrap-theme/
    Author: BootstrapMade
    Author URL: https://bootstrapmade.com
  ======================================================= -->
<style>
body {
  background: #F1F3FA;
}

/* Profile container */
.profile {
  margin: 20px 0;
}

/* Profile sidebar */
.profile-sidebar {
  padding: 20px 0 10px 0;
  background: #fff;
}

.profile-userpic img {
  float: none;
  margin: 0 auto;
  width: 50%;
  height: 50%;
  -webkit-border-radius: 50% !important;
  -moz-border-radius: 50% !important;
  border-radius: 50% !important;
}

.profile-usertitle {
  text-align: center;
  margin-top: 20px;
}

.profile-usertitle-name {
  color: #5a7391;
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 7px;
}

.profile-usertitle-job {
  text-transform: uppercase;
  color: #5b9bd1;
  font-size: 12px;
  font-weight: 600;
  margin-bottom: 15px;
}

.profile-userbuttons {
  text-align: center;
  margin-top: 10px;
}

.profile-userbuttons .btn {
  text-transform: uppercase;
  font-size: 11px;
  font-weight: 600;
  padding: 6px 15px;
  margin-right: 5px;
}

.profile-userbuttons .btn:last-child {
  margin-right: 0px;
}
    
.profile-usermenu {
  margin-top: 30px;
}

.profile-usermenu ul li {
  border-bottom: 1px solid #f0f4f7;
}

.profile-usermenu ul li:last-child {
  border-bottom: none;
}

.profile-usermenu ul li a {
  color: #93a3b5;
  font-size: 14px;
  font-weight: 400;
}

.profile-usermenu ul li a i {
  margin-right: 8px;
  font-size: 14px;
}

.profile-usermenu ul li a:hover {
  background-color: #fafcfd;
  color: #5b9bd1;
}

.profile-usermenu ul li.active {
  border-bottom: none;
}

.profile-usermenu ul li.active a {
  color: #5b9bd1;
  background-color: #f6f9fb;
  border-left: 2px solid #5b9bd1;
  margin-left: -2px;
}

/* Profile Content */
.profile-content {
  padding: 20px;
  background: #fff;
  min-height: 300px;
}
  #upleft {
	width: 100%;
	height: 750px;
	background: #25c9da;
	float: left;
	border-right-style: ridge;
	border-top-color: #999;
	border-right-color: #999;
	border-bottom-color: #999;
	border-left-color: #999;
	z-index: -1;
	}
#upright {
	width: 500px;
	height: 800px;
	background: white;
}
#below {
	width: 1100px;
	position: absolute;
	float: right;
	grid-rows: inherit;
	left: 361px;
	top: 152px;
	border-style: ridge;
	background-size: cover;
	background-color: white;
	background-image: url(img/photo/2.jpg);
	background-repeat: no-repeat;
}

table, th, td {  
    border: 4px solid transparent;  
    border-bottom-style:dotted;
    border-collapse: collapse;  
color:white;
} 
 


th, td {  
    padding-top: 10px;
    padding-right: 30px;
    padding-left: 30px;  
}  

table {  
    background-color: rgba(0,0,0,0.5);  
}   

 

table#alter th {  
    color: white;  
    background-color: black;  
}  

</style>
</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-custom">


  <div id="wrapper">

    <nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
      <div class="top-area">
        <div class="container">
          <div class="row">
            <div class="col-sm-6 col-md-6">
              <p class="bold text-left">Monday - Saturday, 8am to 10pm </p>
            </div>
            <div class="col-sm-6 col-md-6">
              <p class="bold text-right">Call us now +62 008 65 001</p>
            </div>
          </div>
        </div>
      </div>
      <div class="container navigation">

        <div class="navbar-header page-scroll">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-main-collapse">
                    <i class="fa fa-bars"></i>
                </button>
          <a class="navbar-brand" href="index.html">
                    <img src="img/logo.png" alt="" width="150" height="40" />
                </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-right navbar-main-collapse">
          
        </div>
        <!-- /.navbar-collapse -->
      </div>
      <!-- /.container -->
    </nav>

<div id="upleft" style="display:table;margin-left:0px;margin-top:130px;">
           
<div class="container" style="float:left">
    <div class="row profile">
		<div class="col-md-3">
			<div class="profile-sidebar">
				<!-- SIDEBAR USERPIC-->
				
				<!-- END SIDEBAR USERPIC-->
				<!-- SIDEBAR USER TITLE-->
				<div class="profile-usertitle">
					<div class="profile-usertitle-name">
<%! String email=""; %>
  
  <%
      email=(String)session.getAttribute("email");
  %>
  
				</div>
					<div class="profile-usertitle-job">
						Patient
					</div>
				</div>
				<!-- END SIDEBAR USER TITLE-->
				<!-- SIDEBAR BUTTONS-->
				<div class="profile-userbuttons">
					<a href="adminlog.jsp"><button type="button" class="btn btn-danger btn-sm">Sign out</button></a>
				</div>
				<!-- END SIDEBAR BUTTONS -->
				<!-- SIDEBAR MENU-->
				<div class="profile-usermenu">
					<ul class="nav">
						<li>
							<a href="adm_today_app.jsp">
							<i class="glyphicon glyphicon-bell"></i>Today's Appointments </a>
						</li>
						<li>
							<a href="adm_list_doc.jsp">
							<i class="glyphicon glyphicon-search"></i>
							List of Doctors </a>
						</li>
						<li>
							<a href="adm_income.jsp">
							<i class="glyphicon glyphicon-th-large"></i>
							Doctor Booking Fund @ Day </a>
						</li>
						<li class="active">
							<a href="adm_income_month.jsp">
							<i class="glyphicon glyphicon-th"></i>
							Doc Booking Fund @ Month </a>
						</li>
						<li>
							<a href="adm_income_year.jsp">
							<i class="glyphicon glyphicon-th-list"></i>
							Doctor Booking Fund @ Year </a>
						</li>
						<li>
							<a href="adm_diag_month.jsp">
							<i class="glyphicon glyphicon-th-large"></i>
							Diagnostic Test Fund @ Month </a>
						</li>
						<li>
							<a href="adm_diag_year.jsp">
							<i class="glyphicon glyphicon-th"></i>
							Diagnostic Test Fund @ Year </a>
						</li>
						<li>
							<a href="adm_auth_doc.jsp">
							<i class="glyphicon glyphicon-lock"></i>
							Authenticate Doctor </a>
						</li>
                        <li>
							<a href="adm_del_doc.jsp">
							<i class="glyphicon glyphicon-remove-circle"></i>
							Delete Doctor </a>
						</li>
					</ul>
				</div>
				<!-- END MENU-->
			</div>
		</div>
        </div>
        </div>

</div>     
	
</div>
	<div id="below" style="overflow-y: scroll; height:550px">
	    <form class="well form-horizontal" action="adm_income_month" method="get" style="border:none;padding-left: 100px">
	
     <div class="container" style="margin-left:0px;margin-bottom:0px;padding:0px;">    
         
<legend><h3><b style="color:white;">Month wise income track</b></h3></legend>
<div class="form-group">
  <label class="col-md-4 control-label"></label>
 
    <div class="form-group">
  <label class="col-md-4 control-label"></label>  
  <div class="col-md-4 inputGroupContainer">
  Enter year:
  <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  <input id="id1" name="appointment_date" placeholder="Search Doctor By Department.." class="form-control"  type="month">
    </div>
  </div>
  
      
<%! String servermsg=""; %>  
  <%     
  servermsg=(String)request.getAttribute("servermsg");

  if(servermsg != null)
  {
  %>
 <div class="col-sm-4">
    
  <div class="col-md-10 inputGroupContainer">
Amount:
  <div class="input-group">
  <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
  
  <input id="id1" name="booking_ref" value =<%=servermsg%> class="form-control"  type="text" readonly>
    </div>
  </div>
</div>
   
    
   
   <%
   }
   %>
  
 
</div>

  <div class="col-md-4">
    <button type="submit" class="btn btn-warning">Get the Amount!<span class="glyphicon glyphicon-send"></span></button>
  </div>
</div>
</div>
</form>
             
<!-- Text input-->


</div>


<!-- Button -->


</fieldset>
</form>
</div>
    </div><!-- /.container -->
   
 </div>
    
    </div>
    </div>
   



    <footer>

      
      
    </footer>

  <a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>

  <!-- Core JavaScript Files -->
  <script src="js/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.min.js"></script>
  <script src="js/wow.min.js"></script>
  <script src="js/jquery.scrollTo.js"></script>
  <script src="js/jquery.appear.js"></script>
  <script src="js/stellar.js"></script>
  <script src="plugins/cubeportfolio/js/jquery.cubeportfolio.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/nivo-lightbox.min.js"></script>
  <script src="js/custom.js"></script>

</body>

</html>
