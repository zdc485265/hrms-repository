<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
								<a href="#">人事管理</a>
							</li>
							<li class="active">人事档案管理</li>
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
													<h4 class="widget-title lighter">人事档案明细</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="javascpript:void(0)">明细</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/personnel/toPersonnelList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="recruitPersonnelAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.personnel_detailed_msg }</p>
																	<form action="${pageContext.request.contextPath }/personnel/toPersonnelList.gkd" method="post" class="form-horizontal" role="form">
																	   <table style="margin-left: 200px">
																	    	<%-- 第一行 --%>
																	    	<tr>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属部门:</label>
			
																						<div class="col-sm-9">
																							<select id="deptSelect" name="dept_id">
																								<option value="0">--请选择--</option>
																								<c:forEach var="dept" items="${depts }">
																									<c:choose>
																										<c:when test="${dept.dept_id==personnel.job.dept_id }">
																											<option value="${dept.dept_id }" selected="selected">${dept.dept_name }</option>
																										</c:when>
																										<c:otherwise>
																											<option value="${dept.dept_id }">${dept.dept_name }</option>
																										</c:otherwise>
																									</c:choose>
																								</c:forEach>
																							</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 档案状态:</label>
			
																						<div class="col-sm-9">
																							<select name="personnel_status">
																								<c:choose>
																									<c:when test="${personnel.personnel_status==0 }">
																										<option value="0" selected="selected">实习</option>
																										<option value="1">试用期</option>
																										<option value="2">正式</option>
																										<option value="3">离职</option>
																										<option value="4">删除</option>
																									</c:when>
																									<c:when test="${personnel.personnel_status==1 }">
																										<option value="0">实习</option>
																										<option value="1" selected="selected">试用期</option>
																										<option value="2">正式</option>
																										<option value="3">离职</option>
																										<option value="4">删除</option>
																									</c:when>
																									<c:when test="${personnel.personnel_status==2 }">
																										<option value="0">实习</option>
																										<option value="1">试用期</option>
																										<option value="2" selected="selected">正式</option>
																										<option value="3">离职</option>
																										<option value="4">删除</option>
																									</c:when>
																									<c:when test="${personnel.personnel_status==3 }">
																										<option value="0">实习</option>
																										<option value="1">试用期</option>
																										<option value="2">正式</option>
																										<option value="3" selected="selected">离职</option>
																										<option value="4">删除</option>
																									</c:when>
																									<c:when test="${personnel.personnel_status==4 }">
																										<option value="0">实习</option>
																										<option value="1">试用期</option>
																										<option value="2">正式</option>
																										<option value="3">离职</option>
																										<option value="4" selected="selected">删除</option>
																									</c:when>
																								</c:choose>
																							</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;" rowspan="4">
																	    			<div class="form-group">
																	    				<span id="spanPhoto" class="profile-picture" style="width: 155px;height: 165px;">
																							<img id="personnelPhoto" style="width: 150px;height: 160px;" class="editable img-responsive editable-click editable-empty" alt="Alex's Avatar" src="/file/${requestScope.personnel.personnel_photo }">
																						</span>
																						
																					</div>
																	    		</td>
																	    		</tr>
																	    		<%--第二行 --%>
																	    		<tr>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位名称:</label>
																						<div class="col-sm-9">
																							<select id="jobSelect" name="job_id">
																								<c:forEach var="job" items="${requestScope.jobs }">
																									<c:choose>
																										<c:when test="${job.job_id==personnel.job_id }">
																											<option value="${job.job_id }" selected="selected">${job.job_name }</option>
																										</c:when>
																										<c:otherwise>
																											<option value="${job.job_id }">${job.job_name }</option>
																										</c:otherwise>
																									</c:choose>
																								</c:forEach>
																							</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位编码:</label>
																						<div class="col-sm-9">
																							<input readonly="readonly" style="width: 150px;" value="${personnel.job.job_code }" name="job_code" type="text" id="jobCode" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			
																	    		</td>
																	    	</tr>
																	    	<%--第三行 --%>
																	    		<tr>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 姓名:</label>
			
																						<div class="col-sm-9">
																							<input style="width:100px;" readonly="readonly" name="personnel_name" value="${personnel.personnel_name }" type="text" placeholder=" 姓名 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别:</label>
																						<div class="col-sm-9">
																							<select name="personnel_sex">
																								<c:choose>
																									<c:when test="${personnel.personnel_sex==0 }">
																										<option value="0" selected="selected">男</option>
																										<option value="1">女</option>
																									</c:when>
																										<c:otherwise>
																										<option value="0">男</option>
																										<option value="1" selected="selected">女</option>
																									</c:otherwise>
																								</c:choose>
																							</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			
																	    		</td>
																	    	</tr>
																	    	<%-- 第四行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> EMAIL:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" readonly="readonly" value="${personnel.personnel_email }" name="personnel_email" type="text" id="form-field-1" placeholder=" EMAIL " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 联系电话:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" readonly="readonly" name="personnel_phone" type="text" value="${personnel.personnel_phone }" placeholder=" 联系电话 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			
																	    		</td>
																	    	</tr>
																	    	<%-- 第五行 --%>
																	    	
																	    	<tr>
																	    		<td style="width:400px" colspan="2">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" style="width:117px;" for="form-field-1"> 住址:</label>
			
																						<div class="col-sm-9">
																							<input style="width:300px;"  readonly="readonly" name="personnel_address" type="text" value="${personnel.personnel_address }"  placeholder=" 住址 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						
																					</div>
																	    		</td>
																	    	</tr>
																	    	
																	    	<%-- 第六行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 身份证号:</label>
			
																						<div class="col-sm-9">
																							<input style="width:160px;"  readonly="readonly" name="personnel_identify" value="${personnel.personnel_identify }" type="text" placeholder=" 身份证号 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 户口所在地:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_hokou_address" type="text" value="${personnel.personnel_hokou_address }"  placeholder=" 户口所在地 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 政治面貌:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_zzmm" type="text" value="${personnel.personnel_zzmm }" placeholder=" 政治面貌 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		
																	    	</tr>
																	    	
																	    	<%--第七行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 毕业院校:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_school" type="text" value="${personnel.personnel_school }" placeholder=" 毕业院校 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 专业:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_major" type="text" value="${personnel.personnel_major }" placeholder=" 专业 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 学历:</label>
																						<div class="col-sm-9">
																						<select name="personnel_xl">
																							<c:choose>
																								<c:when test="${personnel.personnel_xl==0 }">
																									<option value="0" selected="selected">无</option>
																									<option value="1">高中</option>
																									<option value="2">大专</option>
																									<option value="3">本科</option>
																									<option value="4">硕士</option>
																									<option value="5">博士</option>
																								</c:when>
																								<c:when test="${personnel.personnel_xl==1 }">
																									<option value="0">无</option>
																									<option value="1" selected="selected">高中</option>
																									<option value="2">大专</option>
																									<option value="3">本科</option>
																									<option value="4">硕士</option>
																									<option value="5">博士</option>
																								</c:when>
																								<c:when test="${personnel.personnel_xl==2 }">
																									<option value="0">无</option>
																									<option value="1">高中</option>
																									<option value="2" selected="selected">大专</option>
																									<option value="3">本科</option>
																									<option value="4">硕士</option>
																									<option value="5">博士</option>
																								</c:when>
																								<c:when test="${personnel.personnel_xl==3 }">
																									<option value="0">无</option>
																									<option value="1">高中</option>
																									<option value="2">大专</option>
																									<option value="3" selected="selected">本科</option>
																									<option value="4">硕士</option>
																									<option value="5">博士</option>
																								</c:when>
																								<c:when test="${personnel.personnel_xl==4 }">
																									<option value="0">无</option>
																									<option value="1">高中</option>
																									<option value="2">大专</option>
																									<option value="3">本科</option>
																									<option value="4" selected="selected">硕士</option>
																									<option value="5">博士</option>
																								</c:when>
																								<c:when test="${personnel.personnel_xl==5 }">
																									<option value="0">无</option>
																									<option value="1">高中</option>
																									<option value="2">大专</option>
																									<option value="3">本科</option>
																									<option value="4">硕士</option>
																									<option value="5" selected="selected">博士</option>
																								</c:when>
																							</c:choose>
																						</select>
																						</div>
																					</div>
																	    		</td>
																	    		
																	    	</tr>
																	    	<%--第八行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 薪酬标准:</label>
			
																						<div class="col-sm-9">
																						<select name="salary_id">
																							<option value="0">--请选择--</option>
																							<c:forEach var="salary" items="${requestScope.salarys }">
																								<c:choose>
																									<c:when test="${salary.salary_id==personnel.salary_id }">
																										<option value="${salary.salary_id }" selected="selected">${salary.salary_name }</option>
																									</c:when>
																									<c:otherwise>
																										<option value="${salary.salary_id }">${salary.salary_name }</option>
																									</c:otherwise>
																								</c:choose>
																								
																							</c:forEach>
																						</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px"><div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 开户行:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_bank" value="${personnel.personnel_bank }" type="text" placeholder=" 开户行 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    			
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 银行卡号:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_bank_card" value="${personnel.personnel_bank_card }" type="text" placeholder=" 银行卡号" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第九行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 社保卡号:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_social_security_card" value="${personnel.personnel_social_security_card }" type="text" placeholder=" 社保卡号 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px"><div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登记人:</label>
			
																						<div class="col-sm-9">
																							<input name="admin_id" value="${personnel.admin.admin_id }" type="hidden">
																							<input style="width:150px;"  readonly="readonly" name="admin_account" value="${personnel.admin.admin_account }" type="text" placeholder=" 登记人 " class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    			
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 建档时间:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;"  readonly="readonly" name="personnel_register_date" value="${requestScope.personnel.personnel_register_date }" placeholder=" 建档时间" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="layd ate({istime: true, format: 'YYYY-MM-DD'})"/>
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第十行 --%>
																	    	
																	    	<tr>
																	    		<td style="width:500px" colspan="2">
																	    			<div class="form-group">
																						<label style="width:120px;" class="col-sm-3 control-label no-padding-right" for="form-field-1"> 个人履历:</label>
			
																						<div class="col-sm-9">
																							<textarea style="width:600px;height: 100px;left: 10px"  readonly="readonly" name="personnel_identify_info">${personnel.personnel_identify_info }</textarea>
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第十一行 --%>
																	    	
																	    	<tr>
																	    		<td style="width:500px" colspan="2">
																	    			<div class="form-group">
																						<label style="width:120px;" class="col-sm-3 control-label no-padding-right" for="form-field-1"> 家庭关系信息:</label>
			
																						<div class="col-sm-9">
																							<textarea style="width:600px;height: 100px;left: 10px"  readonly="readonly" name="personnel_family_info">${personnel.personnel_family_info }</textarea>
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第十二行 --%>
																	    	
																	    	<tr>
																	    		<td style="width:500px" colspan="2">
																	    			<div class="form-group">
																						<label style="width:120px;" class="col-sm-3 control-label no-padding-right" for="form-field-1"> 备注:</label>
			
																						<div class="col-sm-9">
																							<textarea style="width:600px;height: 100px;left: 10px"  readonly="readonly" name="personnel_remarks">${personnel.personnel_remarks }</textarea>
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%-- 第十三行 --%>
																	    	<tr>
																	    		<td style="width:500px" colspan="1">
																	    			<div class="form-group">
																						<label  class="col-sm-3 control-label no-padding-right" for="form-field-1">简历附件:</label>
																						
																						<div class="col-sm-9">
																							<div style="position: relative;float: left;size:30px;">
																								<a  href="javascript:download('${personnel.personnel_resume_info}')" >${personnel.personnel_resume_info }</a>
																							</div>
																							
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			
																	    		</td>
																	    		<td style="width:400px">
																	    			
																	    		</td>
																	    	</tr>
																	    </table>
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
		<script src="${pageContext.request.contextPath }/lib/js/plugins/jeditable/jquery.jeditable.js"></script>
		<script src="${pageContext.request.contextPath }/lib/js/plugins/layer/laydate/laydate.js"></script>
		<script src="${pageContext.request.contextPath }/lib/js/plugins/layer2.4/layer.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			function download(personnelResumeInfo){
				var filename=encodeURI(personnelResumeInfo);
				var path="${pageContext.request.contextPath }/download.gkd?resumeInfo="+escape(filename);
				window.location.href=path;
			}
		</script>
	</body>
</html>
