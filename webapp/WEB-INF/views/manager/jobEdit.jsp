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
							<li class="active">职位设置</li>
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
													<h4 class="widget-title lighter">编辑职位</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#powerAdd">编辑</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/job/toJobList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="powerAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																     <p style="color: red;">${requestScope.job_edit_msg }</p>
																	<form action="${pageContext.request.contextPath }/job/editJob.gkd" method="post" class="form-horizontal" role="form">
																	    <%--注意：更新必须要有管理员编号 --%>
																	    <input name="job_id" value="${requestScope.job.job_id }" type="hidden">
																	    
																	    <div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">职位编码：</label>

																			<div class="col-sm-9">
																				<input name="job_code" value="${requestScope.job.job_code }" type="text" id="form-field-1" placeholder="职位编码    例：BDQN-BM03" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																	    
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">职位名：</label>

																			<div class="col-sm-9">
																				<input name="job_name" value="${requestScope.job.job_name }"  type="text" id="form-field-1" placeholder="职位名    例：技术培训师" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>

																			<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">职位分类：</label>
								
																			<div class="col-sm-9">
																				<select name="classify_id" >
																				   <c:forEach  var="classify" items="${requestScope.classifys }">
																				   	 <c:choose>
																				   	 	<c:when test="${requestScope.job.classify_id == classify.classify_id }">
																				   			 <option selected="selected" value="${classify.classify_id }">${classify.classify_name }</option>
																				   	 	</c:when>
																				   	 	<c:otherwise>
																				   	 		<option value="${classify.classify_id }">${classify.classify_name }</option>
																				   	 	</c:otherwise>
																				   	 </c:choose>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																			
																			<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">所属部门：</label>
								
																			<div class="col-sm-9">
																				<select name="dept_id" >
																				   <c:forEach  var="dept" items="${requestScope.depts }">
																				     <c:choose>
																				        <c:when test="${job.dept_id == dept.dept_id}">
																				             <option selected="selected" value="${dept.dept_id }">${dept.dept_name }</option>
																				        </c:when>
																				        <c:otherwise>
																				             <option value="${dept.dept_id }">${dept.dept_name }</option>
																				        </c:otherwise>
																				     </c:choose>
																				   	
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">职位描述：</label>

																			<div class="col-sm-9">
																				<textarea name="job_description"  placeholder="职位描述    例：培训员工技术的人员" id="form-field-1" rows="3" cols="80" class="col-xs-10 col-sm-5">${requestScope.job.job_description }</textarea><p style="color: red;">*</p>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">职位备注：</label>

																			<div class="col-sm-9">
																				<textarea name="job_remark" placeholder="职位备注   例：有好的培训人员，才有高技术的员工" id="form-field-1" rows="3" cols="80" class="col-xs-10 col-sm-5">${requestScope.job.job_remark }</textarea><p style="color: red;">*</p>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">是否启用：</label>

																			<div class="col-sm-9">
																				<select name="job_status"  >
																					<c:if test="${requestScope.job.job_status==0 }">
																						<option selected="selected" value="0">启用</option>
																				   		<option value="1">禁用</option>
																					</c:if>
																					<c:if test="${requestScope.job.job_status==1 }">
																						<option value="0">启用</option>
																				   		<option selected="selected" value="1">禁用</option>
																					</c:if>
																				</select>
																			</div>
																		</div>
																		
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">编辑职位</button>
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
