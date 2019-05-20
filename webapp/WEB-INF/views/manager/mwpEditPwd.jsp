<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		 <jsp:include page="commons/commons-header.jsp"></jsp:include>
	</head>

	<body class="no-skin">
	    <!-- 导航栏 start		-->
		 <jsp:include page="commons/commons-navbar.jsp"></jsp:include>
		<!-- 导航栏 end -->

		<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>
            <!--- 左边栏-菜单 start -->
			 <jsp:include page="commons/commons-sidebar.jsp"></jsp:include>
			<!--- 左边栏-菜单 end -->

			<!--- 内容主体 start -->
			<div class="main-content">
				<div class="main-content-inner">
				    <!-- 向导栏 start-->
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						<ul class="breadcrumb">
								<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">首页</a>
							</li>

							<li>
								<a href="#">我的工作平台</a>
							</li>
							<li class="active">修改个人密码</li>
						</ul><!-- /.breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="ace-icon fa fa-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- /.nav-search -->
					</div>
					<!-- 向导栏 end-->

					<!-- 内容页 start -->
					<div class="page-content">
				          			<div class="widget-box transparent" id="widget-box-13">
												<div class="widget-header">
													<h4 class="widget-title lighter">修改密码</h4>

													
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="editPwd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.mwp_edit_msg }</p>
																	<form action="${pageContext.request.contextPath }/mwp/editPwd.gkd" method="post" class="form-horizontal" role="form">
																	    <%--注意：更新必须要有管理员编号 --%>
																	    <input name="admin_id" value="${admin.admin_id }" type="hidden">
																		<div id="pwdOldDiv" class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 旧密码 :</label>
																			<div class="col-sm-9">
																				<input name="old_admin_pwd" type="password" id="form-field-1" placeholder="旧密码" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		
																		<div id="pwdNewDiv" class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 新密码 :</label>

																			<div class="col-sm-9">
																				<input name="new_admin_pwd" type="password" id="form-field-2" placeholder="新密码" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>

																		<div id="pwdNew2Div" class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-3"> 新密码确认 :</label>

																			<div class="col-sm-9">
																				<input name="confirm_admin_pwd" type="password" id="form-field-3" placeholder="新密码确认" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">确认修改</button>
																			</div>
																		</div>
																	</form>

																</div>
															</div>

												
														</div>
													</div>
												</div>
											</div>
					</div><!-- /.page-content -->
					<!-- 内容页 end -->
				</div>
			</div><!-- /.main-content -->
			<!--- 内容主体 end -->

			 <!--页尾 start -->
		<jsp:include page="commons/commons-footer.jsp"></jsp:include>
		
			 <!--页尾 end -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${pageContext.request.contextPath }/lib/assets/js/jquery-2.1.4.min.js"></script>
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath }/lib/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath }/lib/assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath }/lib/assets/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath }/lib/assets/js/ace.min.js"></script>

		<!-- inline scripts related to this page -->
		
	</body>
</html>
