<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		 <jsp:include page="commons/commons-header.jsp"></jsp:include>
	<style>
	.form-horizontal .controls{
	height:30px;
	}
	</style>
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
								<a href="#">薪酬管理</a>
							</li>
							<li class="active">薪酬标准管理</li>
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
													<h4 class="widget-title lighter">薪酬标准登记</h4>

													<div class="widget-toolbar no-border">
														
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
															<div id="salaryStandardEdit" class="tab-pane in active">
															<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.salary_add_msg }</p>
																	<form id="salaryForm" action="${pageContext.request.contextPath }/salary/addSalary.gkd" method="post" class="form-horizontal" role="form">
																	    <%--注意：更新必须要有管理员编号 --%>
																	    <table>
																	    	<tr>
																	    		<td style="width:450px">
																					<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 薪酬标准编号 </label>
			
																						<div class="col-sm-9">
																							<input name="salary_code" type="text" id="form-field-1" placeholder="薪酬标准编号" class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:450px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1">  薪酬标准名称</label>
			
																						<div class="col-sm-9">
																							<input name="salary_name" type="text" placeholder="薪酬标准名称" class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:450px">
																		    		<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 薪酬总额 </label>
			
																						<div class="col-sm-9">
																							<input name="salary_total" id="salaryTotal" readonly="readonly" value="0" type="text" placeholder="薪酬总额" class="col-xs-10 col-sm-5" style="width: 210px" />
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<tr>
																	    		<td style="width:450px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 制定人 </label>
			
																						<div class="col-sm-9">
																							<input name="salary_marker" type="text" placeholder="制定人" class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:450px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登记人 </label>
																						<div class="col-sm-9">
																							<input name="admin_id" value="${admin.admin_id }" type="hidden"/>
																							<input name="admin_account" type="text" readonly="readonly" value="${admin.admin_account }" placeholder="登记人" class="col-xs-10 col-sm-5" style="width: 210px" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:450px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登记时间:</label>
			
																						<div class="col-sm-9">
																							<input name="salary_date" class="form-control layer-date" value="${requestScope.newDate }" placeholder="YYYY-MM-DD" style="width: 210px" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    </table>
																			
																		<table class="table table-striped table-bordered align-center " style="width: 1300px">
																			<tr class="bolder">
																				<td>序号</td>
																				<td>薪酬项目名称</td>
																				<td>金额</td>
																			</tr>
																			<c:set var="xh" value="0"></c:set>
																			<c:forEach var="item" varStatus="status" items="${requestScope.items }">
																	  			<c:set var="xh" value="${xh+1 }"></c:set>
																	  			<tr class="bolder">
																					<td><label class="col-sm-3 control-label no-padding-right" for="form-field-1">${xh }</label></td>
																					<td><label class="col-sm-3 control-label no-padding-right" for="form-field-1">${item.item_name }</label></td>
																					<td>
																						<input name="item_id" value="${item.item_id }" type="hidden"/>
																						<input name="salary_item_value" type="text" value="0" placeholder="金额" class="col-xs-10 col-sm-5 salaryItemValue" />
																					</td>
																				</tr>
																	  		</c:forEach>
																			<c:set var="xh" value="0"></c:set>
																		</table>
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" name="salary_status" value="0" class="btn btn-primary">保存</button>&nbsp;&nbsp;
																				<button type="submit" name="salary_status" value="1"  class="btn btn-primary">申请复核</button>
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

			</div>
			 <!--页尾 end -->
		<!-- /.main-container -->

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
		<script src="${pageContext.request.contextPath }/lib/js/plugins/jeditable/jquery.jeditable.js"></script>
		<script src="${pageContext.request.contextPath }/lib/js/plugins/layer/laydate/laydate.js"></script>
		<script src="${pageContext.request.contextPath }/lib/js/plugins/layer2.4/layer.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			$(function(){
				$(".salaryItemValue").on("blur",function(){
					var salaryItemValues= $(".salaryItemValue");
					var values=parseFloat(0);
					for(var i in salaryItemValues){
						var value= parseFloat(salaryItemValues[i].value);
						console.log(value);
						console.log(isNaN(value));
						if(!isNaN(value)){
							values=values+value;
						}
					}
					$("#salaryTotal").val(values);
				});
			})
		</script>
		
	</body>
</html>
