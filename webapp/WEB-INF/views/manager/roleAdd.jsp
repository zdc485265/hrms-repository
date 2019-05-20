<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
							<li class="active">角色管理</li>
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
													<h4 class="widget-title lighter">角色管理</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="#roleAdd">新增</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/role/toRoleList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="roleAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.role_add_msg }</p>
																	<form action="${pageContext.request.contextPath }/role/addRole.gkd" method="post" class="form-horizontal" role="form">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 角色名 :</label>

																			<div class="col-sm-9">
																				<input name="role_name"  type="text" id="form-field-1" placeholder="角色名    例:超级管理员" class="col-xs-10 col-sm-5" /><p style="color: red;">*</p>
																			</div>
																		</div>
																		<!-- 矩阵start -->
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 全部权限 <input id="chkAllPowers"  type="checkbox" /></label>

																		</div>
																		
																		<c:forEach var="modular" items="${requestScope.modulars }">
																		<div class="form-group">
																			<label class="col-sm-3 control-label no-padding-right" for="form-field-1">${modular.modular_name }<input onclick="chkModularPowers(this)"  type="checkbox" /></label>

																			<div class="col-sm-6">
																			    <%--
																			         需求：每四条数据换一行
																			       思路：   定义一个变量，每次循环累加，如果为取模4，==0，换行
																			     --%>
																			    <c:set var="count" value="0" ></c:set>
																			    <table class="table">
																			       <%--
																			           每次遇到4的倍数，就会产生一对  </tr><tr>
																			        --%>
																			      <c:forEach var="power" items="${ modular.powers}">
																			         <c:if test="${count%4==0 }">
																				       <tr>
																				     </c:if>
																				       <td>${power.power_name }<input name="powerIds" value="${power.power_id }"  type="checkbox" /></td>
																					   <c:set var="count" value="${count+1}"></c:set>
																					  <c:if test="${count%4==0 }">
																					   </tr>
																					  </c:if>
																				
																				   </c:forEach>
																				   
																				   
																				    <c:if test="${count%4!=0 }">
																				      </tr>
																				    </c:if>
																				 
																				</table>
																		
																				
																			</div>
																		</div>
																		</c:forEach>
																		<!-- 矩阵end -->
																		
																
																		
					
																		

																		
																		
																		<div class="form-group">
																			
																			<div class="col-sm-7 text-right">
																				<button type="submit" class="btn btn-primary">新增角色</button>
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
		<script type="text/javascript">
		   //全选与反选所有复选框
		   $("#chkAllPowers").click(function(){
			   //全选所有的复选框
			   if($(this).prop("checked")){
				   $("input[type='checkbox']").prop("checked",true);
			   }else{
				   $("input[type='checkbox']").prop("checked",false);
			   }
		   });
		   

		   //全选与反选对应模块的复选框
		   var chkModularPowers=function(o){
			   //alert("==================="+o);
			   //通过位置计算，模块与对应的复选框的关系
			  var arr= $(o).parent().parent().find("input[name='powerIds']");
			   if($(o).prop("checked")){
				   arr.prop("checked",true);
			   }else{
				   arr.prop("checked",false);
			   }
		   }
		   
		   
		
		</script>
		
	</body>
</html>
