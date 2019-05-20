<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="sidebar" class="sidebar      h-sidebar                navbar-collapse collapse          ace-save-state" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">
<script type="text/javascript">
	try{ace.settings.loadState('sidebar')}catch(e){}
</script>

	<ul class="nav nav-list">
					<li class="hover">
						<a href="${pageContext.request.contextPath }/index.gkd">
							<i class="menu-icon fa fa-desktop"></i>
							<span class="menu-text">
								首页
							</span>

							<b class="arrow fa fa-angle-down"></b>
						</a>
						
						<b class="arrow"></b>
						</li>
						<c:forEach var="modular" items="${sessionScope.admin.modulars }">
							<li class="hover">
								<a href="#">
								<i class="menu-icon glyphicon glyphicon-cog"></i>
									<span class="menu-text">
										${modular.modular_name}
									</span>
		
									<b class="arrow fa fa-angle-down"></b>
								</a>
						
							<b class="arrow"></b>
							<ul class="submenu">
								 <c:forEach var="power" items="${sessionScope.admin.role.powers }">
							        <c:if test="${modular.modular_id==power.modular_id and power.power_parent==0 and power.power_is_show ==0 }">
									   	<li class="hover">
											<a href="${pageContext.request.contextPath }/${power.power_url}/${power.power_action}">
												<i class="menu-icon fa fa-caret-right"></i>
												${power.power_name }
											</a>
							
											<b class="arrow"></b>
										</li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
						</c:forEach>
				</ul>
	<!-- /.nav-list -->

	<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
		<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
	</div>
</div>