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
							<li class="active">权限管理</li>
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
													<h4 class="widget-title lighter">编辑权限</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#powerAdd">编辑</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/power/toPowerList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="powerAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																     <p style="color: red;">${requestScope.power_edit_msg }</p>
																	<form action="${pageContext.request.contextPath }/power/editPower.gkd" method="post" class="form-horizontal" role="form">
																	
																	    <%--注意：更新必须要有管理员编号 --%>
																	    <input name="power_id" value="${requestScope.power.power_id }" type="hidden">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 权限名 </label>

																			<div class="col-sm-9">
																				<input name="power_name" value="${requestScope.power.power_name }"  type="text" id="form-field-1" placeholder="权限名   例:权限管理" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>

																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 请求路径 </label>

																			<div class="col-sm-9">
																				<input name="power_action" value="${requestScope.power.power_action }" type="text" id="form-field-1" placeholder="请求路径   例:addPower.gkd" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> URL </label>

																			<div class="col-sm-9">
																				<input name="power_url" value="${requestScope.power.power_url }" type="text" id="form-field-1" placeholder="URL  例:power" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属模块 </label>

																			<div class="col-sm-9">
																				<select name="modular_id" >
																				   <c:forEach  var="modular" items="${requestScope.modulars }">
																				     <c:choose>
																				        <c:when test="${power.modular_id== modular.modular_id}">
																				             <option selected="selected" value="${modular.modular_id }">${modular.modular_name }</option>
																				        </c:when>
																				        <c:otherwise>
																				             <option value="${modular.modular_id }">${modular.modular_name }</option>
																				        </c:otherwise>
																				     </c:choose>
																				   	
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否显示 </label>

																			<div class="col-sm-9">
																				<select name="power_is_show"  >
																					<c:if test="${requestScope.power.power_is_show==0 }">
																						<option selected="selected" value="0">显示</option>
																				   		<option value="1">隐藏</option>
																					</c:if>
																					<c:if test="${requestScope.power.power_is_show==1 }">
																						<option value="0">显示</option>
																				   		<option selected="selected" value="1">隐藏</option>
																					</c:if>
																				</select>
																			</div>
																		</div>
																			<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 父权限 </label>
																			<div class="col-sm-9">
																			   	<select name="power_parent" >
																				   <option value="0">顶级菜单</option>
																				   <c:forEach var="p" items="${requestScope.powers }">
																				     <c:choose>
																				       <c:when test="${requestScope.power.power_parent==p.power_id}">
																				          <option selected="selected" value="${p.power_id }">${p.power_name}</option>
																				       </c:when>
																				       <c:otherwise>
																				          <option value="${p.power_id }">${p.power_name}</option>
																				       </c:otherwise>
																				     </c:choose>
																				     
																				   </c:forEach>
																				</select>
																			</div>
																		</div>
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">编辑权限</button>
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
