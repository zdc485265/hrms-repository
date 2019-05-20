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
													<h4 class="widget-title lighter">职位明细</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#powerAdd">明细</a>
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
																    <p style="color: red;">${requestScope.job_detailed_msg }</p>
																	<form action="${pageContext.request.contextPath }/job/toJobList.gkd" method="post" class="form-horizontal" role="form">
																	    <%--注意：更新必须要有管理员编号 --%>
																	    <input name="job_id" value="${requestScope.job.job_id }" type="hidden">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位编码 </label>

																			<div class="col-sm-9">
																				<input name="job_code" value="${requestScope.job.job_code }" type="text" id="form-field-1" readonly="readonly"  class="col-xs-10 col-sm-5" />
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位名 </label>

																			<div class="col-sm-9">
																				<input name="job_name" value="${requestScope.job.job_name }"  type="text" id="form-field-1" readonly="readonly" class="col-xs-10 col-sm-5" />
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">职位分类：</label>
								
																			<div class="col-sm-9">
																				<select name="classify_id" >
																				   	 <option value="${requestScope.job.classify.classify_id }">${requestScope.job.classify.classify_name }</option>
																				</select>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">所属部门 ：</label>
																			<div class="col-sm-9">
																				<select name="dept_id" >
																				   	 <option value="${requestScope.job.dept.dept_id }">${requestScope.job.dept.dept_name }</option>
																				</select>
																			</div>
																		</div>
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位描述 </label>

																			<div class="col-sm-9">
																				<textarea rows="4" cols="80" name="job_description" 
																				 id="form-field-1" readonly="readonly"   class="col-xs-10 col-sm-5" >${requestScope.job.job_description }</textarea>
																			</div>
																		</div>
																	<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位备注 </label>

																			<div class="col-sm-9">
																				<textarea rows="4" cols="80" name="job_remark" 
																				 id="form-field-1" readonly="readonly"   class="col-xs-10 col-sm-5" >${requestScope.job.job_remark }</textarea>
																			</div>
																		</div>
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">返回</button>
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
