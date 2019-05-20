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
								<a href="#">系统设置</a>
							</li>
							<li class="active">用户管理</li>
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
													<h4 class="widget-title lighter">编辑用户</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#adminEdit">编辑</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/admin/toAdminList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="adminEdit" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.admin_edit_msg }</p>
																	<form  action="${pageContext.request.contextPath }/admin/editAdmin.gkd" method="post" class="form-horizontal" role="form">
																	    <%--注意：更新必须要有用户编号 --%>
																	    <input name="admin_id" value="${requestScope.admin.admin_id }" type="hidden">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户名 :</label>

																			<div class="col-sm-9">
																				<input name="admin_name" value="${requestScope.admin.admin_name }" type="text" id="form-field-1" placeholder="用户名   例：张三" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户账号 :</label>

																			<div class="col-sm-9">
																				<input name="admin_account" value="${requestScope.admin.admin_account }" type="text" id="form-field-1" placeholder="用户账号   例：zhangsan" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>

																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 密&nbsp;&nbsp;码  :</label>

																			<div class="col-sm-9">
																				<input name="admin_pwd" value="${requestScope.admin.admin_pwd }" type="password" id="form-field-1" placeholder="密码   例：123" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户状态 : </label>

																			<div class="col-sm-9">
																				<select name="admin_status" >
																				   <c:choose>
																				      <c:when test="${requestScope.admin.admin_status==0 }">
																				          <option value="0" selected="selected">可用</option>
																				 		  <option value="1">禁用</option>
																				      </c:when>
																				      <c:otherwise>
																				          <option value="0">可用</option>
																				 		  <option value="1" selected="selected">禁用</option>
																				      </c:otherwise>
																				   </c:choose>
																				  
																				</select>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 用户角色 : </label>

																			<div class="col-sm-9">
																				<select name="role_id" >
																				   <c:forEach var="role" items="${requestScope.roles }">
																				     <c:choose>
																				        <%-- 注意：判断选中当前用户的角色 --%>
																				        <c:when test="${role.role_id==admin.role_id }">
																				           <option selected="selected" value="${role.role_id }">${role.role_name }</option>
																				        </c:when>
																				        <c:otherwise>
																				            <option  value="${role.role_id }">${role.role_name }</option>
																				        </c:otherwise>
																				     </c:choose>
																				  	
																				    </c:forEach>
																				</select>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属部门 : </label>

																			<div class="col-sm-9">
																				<select name="dept_id" >
																				   <c:forEach var="dept" items="${requestScope.depts }">
																				     <c:choose>
																				        <%-- 注意：判断选中当前用户的部门 --%>
																				        <c:when test="${dept.dept_id==admin.dept_id }">
																				           <option selected="selected" value="${dept.dept_id }">${dept.dept_name }</option>
																				        </c:when>
																				        <c:otherwise>
																				            <option  value="${dept.dept_id }">${dept.dept_name }</option>
																				        </c:otherwise>
																				     </c:choose>
																				  	
																				    </c:forEach>
																				</select>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">编辑用户</button>
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
