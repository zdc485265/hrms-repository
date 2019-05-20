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
													<h4 class="widget-title lighter">角色列表</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li >
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath}/role/toAddRole.gkd'">新增</a>
															</li>

													

															<li class="active">
																<a data-toggle="tab" href="#roleList">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
															

													        <p style="color: red;">${requestScope.role_list_msg }</p>
															<div id="roleList" class="tab-pane in active">
															<button onclick="deleteRoleById()" class="btn btn-primary">删除勾选角色</button>
															     
																 <table class="table table-striped table-bordered align-center "> 
																    <tr class="bolder" >
																	  <td><input id="chkAllRole" type="checkbox"></td>
																	  <td>编号</td>
																	  <td>角色名</td>
																	  <td>角色权限</td>
																	
																	  <td>操作</td>
																	</tr>
																	<c:forEach var="role" varStatus="status" items="${requestScope.page.data }">
																	   <tr>
																		  <td><input name="role_id"  type="checkbox" value="${role.role_id }"></td>
																		  <td>${role.role_id }</td>
																		  <td>${role.role_name }</td>
																		 <td>
																		 <textarea rows="3" cols="70" readonly="readonly">${role.role_powers}</textarea> 
	 
																		 </td>
																		   <td><button class="btn btn-sm btn-primary" onclick="window.location.href='${pageContext.request.contextPath}/role/toEditRole.gkd?role_id=${role.role_id }'">编辑</button>&nbsp;&nbsp;<button onclick="window.location.href='${pageContext.request.contextPath}/role/deleteRole.gkd?role_id=${role.role_id}'" class="btn btn-sm btn-primary">删除</button></td>
																		</tr>
																	</c:forEach>
																 </table>
																 <div class="text-right">
																   <nav>
																	  <ul class="pagination">
																	 	<li>
																		      			<a href="${pageContext.request.contextPath }/role/toRoleList.gkd?index=0">首页</a>
																		      			</li>
																	      <c:choose>
																	         <c:when test="${requestScope.page.previous }">
																	                 <li>
																	               <a href="${pageContext.request.contextPath }/role/toRoleList.gkd?index=${requestScope.page.index-1 }" aria-label="Previous">
																	        			<span aria-hidden="true">&laquo;</span>
																	      			</a>
																	      			</li>
																	         </c:when>
																	         <c:otherwise>
																	             <li class="disabled">
																	                <a href="#" aria-label="Previous">
																	        			<span aria-hidden="true">&laquo;</span>
																	      			</a>
																	      		 </li>
																	         </c:otherwise>
																	      </c:choose>
																	  
																	      
																	   
																	       <c:set var="s" value="${page.index-1 }"></c:set>
			 															     <c:set var="e" value="${page.index+3 }"></c:set>
																	 		 <c:if test="${page.index-2<1}">
			 															     	<c:set var="s" value="1"></c:set>
			 															     	<c:set var="e" value="5"></c:set>
			 															     </c:if>
																		     <c:if test="${page.index+3>requestScope.page.totalPage }">
																		     	<c:set var="s" value="${requestScope.page.totalPage-4 }"></c:set>
																		     	<c:set var="e" value="${requestScope.page.totalPage }"></c:set>
																		     </c:if>
																		<c:forEach begin="1" varStatus="status" end="${requestScope.page.totalPage }">
			 															     
																		     <c:if test="${status.count>=s&&status.count<=e }">
																		       <c:choose>
																		          <%--需求：选中当前页
																		            	思路：如果 status.count=index+1 选中
																		           --%>
																		          <c:when test="${status.count==requestScope.page.index+1}">
								                                                         <%-- 注意事项：数据库的索引是从0开始的，而页面索引是从1开始的。索引数据索引=页面索引-1 --%>
																		      			<li class="active">
																		      			<a href="${pageContext.request.contextPath }/role/toRoleList.gkd?index=${status.count-1}">${status.count }</a>
																		      			</li>										          
																		          </c:when>
																		          <c:otherwise>
																		                <li>
																		                <a href="${pageContext.request.contextPath }/role/toRoleList.gkd?index=${status.count-1}">${status.count }</a>
																		                </li>
																		          </c:otherwise>
																		       </c:choose>
																		      </c:if>
																	    </c:forEach>
																
																	   
																	    
																	     <c:choose>
																	        <c:when test="${requestScope.page.next }">
																	           <li>
																	             <a href="${pageContext.request.contextPath }/role/toRoleList.gkd?index=${requestScope.page.index+1}" aria-label="Next">
																	        	<span aria-hidden="true">&raquo;</span>
																	     		 </a>
																	     		    </li>
																	        </c:when>
																	        <c:otherwise>
																	            <li class="disabled">
																	              <a href="#" aria-label="Next">
																	       			 <span aria-hidden="true">&raquo;</span>
																	        		</a>
																	           </li>
																	        </c:otherwise>
																	     </c:choose>
																	   
																	      <li>
																		      			<a href="${pageContext.request.contextPath }/role/toRoleList.gkd?index=${requestScope.page.totalPage-1}">尾页</a>
																		      			</li>
																	 
																	  </ul>
																	</nav>
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
		   $("#chkAllRole").click(function(){
			   //绑定的复选框checked属性为true。其他复选框也为true
			   if($(this).prop("checked")){
				   //返回的是一组对象，所以设置一组对象的属性，必须使用prop
				   $("input[name='role_id']").prop("checked",true);
			   }else{
				   $("input[name='role_id']").prop("checked",false);
			   }
		   });
		   
		   //声明一个方法
		   var deleteRoleById=function(){
			   //alert("--deleteRoleById-");
			   //1.获得选中 的复选框,使用serialize()方法可以将参数以格式：chkRoleId=3&chkRoleId=4 发送
			   var arr=$("input[name='role_id']:checked").serialize();
			   console.log(arr);
			   //2.获得对象的值，发送到后台
			   window.location.href="${pageContext.request.contextPath}/role/deleteRole.gkd?"+arr;
		   }
			</script>
		   
	</body>
</html>
