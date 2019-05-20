<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>登录</title>

		<meta name="description" content="User login page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/css/fonts.googleapis.com.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath }/lib/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath }/lib/assets/js/html5shiv.min.js"></script>
		<script src="${pageContext.request.contextPath }/lib/assets/js/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
			.login-layout{
				background-image: url(${pageContext.request.contextPath }/lib/img/12.jpg);
				background-repeat:no-repeat;
				background-size:100% 100%;
				background-attachment: fixed;
			}
		</style>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1" style="top: 120px;">
						<div class="login-container">
							<div class="center">
								
								<h4 class="blue" id="id-company-text">${requestScope.admin_login_msg }</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible no-border">
									<div class="widget-body" style="border-radius:10px;">
										<div class="widget-main">
											<h1>
												<img src="${pageContext.request.contextPath }/lib/img/17.jpg">
												<span class="blue" id="id-text2">人力资源管理系统</span>
											</h1>

											<div class="space-6"></div>

											<form action="${pageContext.request.contextPath }/admin/login.gkd" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="admin_account" type="text" class="form-control" placeholder="账号名" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input name="admin_pwd" type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
												

														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-users"></i>
															<span class="bigger-110">登&nbsp;录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										
										</div><!-- /.widget-main -->

									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->


							</div><!-- /.position-relative -->

						
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath }/lib/assets/js/jquery-2.1.4.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="${pageContext.request.contextPath }/lib/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath }/lib/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->

	</body>
</html>
