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
								<a href="#">系统模块管理</a>
							</li>
							<li class="active">模块管理</li>
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
													<h4 class="widget-title lighter">编辑模块</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#modularEdit">编辑</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/modular/toModularList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

																   <p style="color: red;">${requestScope.modular_edit_msg }</p>
												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4" style="top: 50px;">
														
															<div id="modularEdit" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																	<form action="${pageContext.request.contextPath }/modular/editModular.gkd" method="post" class="form-horizontal" style="width: 700px;margin-left: 400px;" role="form">
																	    <%--注意：更新必须要有管理员编号 --%>
																	    <input name="modular_id" value="${requestScope.modular.modular_id }" type="hidden">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 模块名 :</label>

																			<div class="col-sm-9">
																				<input name="modular_name" value="${requestScope.modular.modular_name }" type="text" id="form-field-1" placeholder="模块名   例：系统管理模块" class="col-xs-10 col-sm-5" style="width: 210px;" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 排序序号 :</label>

																			<div class="col-sm-9">
																				<input name="modular_sort" value="${requestScope.modular.modular_sort }" type="text" id="form-field-1" placeholder="排序序号   例：1 " class="col-xs-10 col-sm-5" style="width: 210px;" /><p style="color: red;">*</p>
																			</div>
																		</div>

																		
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right" style="width: 344px;">
																				<button type="submit"  class="btn btn-primary">编辑模块</button>
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

		<!-- <![endif]-->

		<!--[if IE]>
<script src="${pageContext.request.contextPath }/lib/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
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
