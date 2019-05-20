<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.btn-primary{
		background-color: #6666ff!important;
		border-color:#6666ff;
	}
	.ace-nav .nav-user-photo {
		border: 0px solid;
	}
</style>
<div id="navbar" class="navbar navbar-default    navbar-collapse       h-navbar ace-save-state">
			<div class="navbar-container ace-save-state" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<img src="${pageContext.request.contextPath }/lib/img/18.jpg">
							人力资源管理系统
						</small>
					</a>

					<button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
						<span class="sr-only">Toggle user menu</span>

					</button>

					<button class="pull-right navbar-toggle collapsed" type="button" data-toggle="collapse" data-target="#sidebar">
						<span class="sr-only">Toggle sidebar</span>

						<span class="icon-bar"></span>

						<span class="icon-bar"></span>

						<span class="icon-bar"></span>
					</button>
				</div>

				<div class="navbar-buttons navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue dropdown-modal">
					<a data-toggle="dropdown" href="#" class="dropdown-toggle">
						<img class="nav-user-photo" src="${pageContext.request.contextPath }/lib/img/112.jpg" alt="Jason's Photo" />
						<span class="user-info">
						    <!--系统角色-->
							<small>${sessionScope.admin.role.role_name },</small>
							<!--管理员名称-->
							${sessionScope.admin.admin_account }
						</span>

						<i class="ace-icon fa fa-caret-down"></i>
					</a>

					<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						<li>
						<a href="${pageContext.request.contextPath }/mwp/toEditPwd.gkd">
								<i class="ace-icon fa fa-cog"></i>
								修改密码
							</a>
						</li>

					

						<li class="divider"></li>

						<li>
							<a href="${pageContext.request.contextPath }/mwp/undo.gkd">
								<i class="ace-icon fa fa-power-off"></i>
								注销
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>

				
			</div><!-- /.navbar-container -->
		</div>

		
	<!-- /.navbar-container -->
