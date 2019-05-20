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
								<a href="#">招聘管理</a>
							</li>
							<li class="active">简历管理</li>
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
													<h4 class="widget-title lighter">简历登记明细</h4>

													<div class="widget-toolbar no-border">
														<ul class="nav nav-tabs" id="myTab2">
															<li  class="active">
																<a data-toggle="tab" href="javascpript:void(0)">明细</a>
															</li>

													

															<li>
																<a data-toggle="tab" href="javascript:void(0)" onclick="window.location.href='${pageContext.request.contextPath }/resume/toResumeList.gkd'">列表</a>
															</li>
														</ul>
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="recruitResumeAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.resume_detailed_msg }</p>
																	<form action="${pageContext.request.contextPath }/resume/toResumeList.gkd" method="post" class="form-horizontal" role="form">
																	    <table>
																	    	<%-- 第一行 --%>
																	    	<tr>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 应聘职位名称:</label>
			
																						<div class="col-sm-9">
																							<select>
																								<c:forEach var="recruit" items="${recruits }">
																									<c:choose>
																										<c:when test="${recruit.job.job_id==resume.job.job_id }">
																											<option value="${recruit.job.job_id }" selected="selected">${recruit.job.job_name }</option>
																										</c:when>
																										<c:otherwise>
																											<option value="${recruit.job.job_id }">${recruit.job.job_name }</option>
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
																							<input readonly="readonly" style="width:140px;" readonly="readonly" id="jobCode" name="jobCode" type="text" id="form-field-1" value="${requestScope.resume.job.job_code }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 职位分类:</label>
			
																						<div class="col-sm-9">
																							<input readonly="readonly" style="width:100px;" readonly="readonly" id="classifyName" name="classify_name" type="text" id="form-field-1" value="${requestScope.resume.job.classify.classify_name}" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		</tr>
																	    		<%--第二行 --%>
																	    		<tr>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 姓名:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" readonly="readonly" name="resume_name" type="text" id="form-field-1" value="${requestScope.resume.resume_name }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px;">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 性别:</label>
			
																						<div class="col-sm-9">
																							<select name="resume_sex" >
																								<c:choose>
																									<c:when test="${resume.resume_sex==0 }">
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
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 招聘类型:</label>
			
																						<div class="col-sm-9">
																							<select name="recruitSort" >
																							<c:choose>
																									<c:when test="${requestScope.resume.recruit.recruit_sort==0 }">
																							   			<option value="0" selected="selected">校园招聘</option>
																							   			<option value="1">社会招聘</option>
																									</c:when>
																									<c:otherwise>
																										 <option value="0">校园招聘</option>
																							  			 <option value="1" selected="selected">社会招聘</option>
																									</c:otherwise>
																								</c:choose>
																							</select>
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%-- 第三行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> EMAIL:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" name="resume_email" readonly="readonly" type="text" id="form-field-1" value="${requestScope.resume.resume_email }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 联系电话:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" name="resume_phone" readonly="readonly" type="text" id="form-field-1" value="${requestScope.resume.resume_phone }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 户口所在地:</label>
			
																						<div class="col-sm-9">
																							<input style="width:100px;" name="resume_hokou_address" readonly="readonly" type="text" id="form-field-1" value="${requestScope.resume.resume_hokou_address }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%-- 第四行 --%>
																	    	
																	    	<tr>
																	    		<td style="width:400px" colspan="1">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 住址:</label>
			
																						<div class="col-sm-9">
																							<input style="width:300px;" name="resume_address" readonly="readonly" type="text" id="form-field-1" value="${requestScope.resume.resume_address }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 政治面貌:</label>
			
																						<div class="col-sm-9">
																							<input style="width:100px;" name="resume_zzmm" readonly="readonly" type="text" id="form-field-1" value="${requestScope.resume.resume_zzmm }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	
																	    	<%-- 第五行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 身份证号:</label>
			
																						<div class="col-sm-9">
																							<input style="width:160px;" name="resume_identity" readonly="readonly" type="text" id="form-field-1"  value="${requestScope.resume.resume_identity }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 毕业院校:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" name="resume_school" readonly="readonly" type="text" id="form-field-1"  value="${requestScope.resume.resume_school }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 学历:</label>
			
																						<div class="col-sm-9">
																						<select name="resume_xl">
																						<c:choose>
																							<c:when test="${resume.resume_xl==0 }">
																								<option value="0" selected="selected">无</option>
																								<option value="1">高中</option>
																								<option value="2">大专</option>
																								<option value="3">本科</option>
																								<option value="4">硕士</option>
																								<option value="5">博士</option>
																							</c:when>
																							<c:when test="${resume.resume_xl==1 }">
																								<option value="0">无</option>
																								<option value="1" selected="selected">高中</option>
																								<option value="2">大专</option>
																								<option value="3">本科</option>
																								<option value="4">硕士</option>
																								<option value="5">博士</option>
																							</c:when>
																							<c:when test="${resume.resume_xl==2 }">
																								<option value="0">无</option>
																								<option value="1">高中</option>
																								<option value="2" selected="selected">大专</option>
																								<option value="3">本科</option>
																								<option value="4">硕士</option>
																								<option value="5">博士</option>
																							</c:when>
																							<c:when test="${resume.resume_xl==3 }">
																								<option value="0">无</option>
																								<option value="1">高中</option>
																								<option value="2">大专</option>
																								<option value="3" selected="selected">本科</option>
																								<option value="4">硕士</option>
																								<option value="5">博士</option>
																							</c:when>
																							<c:when test="${resume.resume_xl==4 }">
																								<option value="0">无</option>
																								<option value="1">高中</option>
																								<option value="2">大专</option>
																								<option value="3">本科</option>
																								<option value="4" selected="selected">硕士</option>
																								<option value="5">博士</option>
																							</c:when>
																							<c:when test="${resume.resume_xl==5 }">
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
																	    	
																	    	<%--第六行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 专业:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" name="resume_major" readonly="readonly" type="text" id="form-field-1"  value="${requestScope.resume.resume_major }" class="col-xs-10 col-sm-5" />
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 工作经验:</label>
			
																						<div class="col-sm-9">
																							<input style="width:150px;" name="resume_experience" readonly="readonly" type="text" id="form-field-1"  value="${requestScope.resume.resume_experience }" class="col-xs-10 col-sm-5" />(例如：5  表示5年)
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 薪资要求:</label>
			
																						<div class="col-sm-9">
																							<input style="width:100px;" name="resume_sal_req" readonly="readonly" type="text" id="form-field-1"  value="${requestScope.resume.resume_sal_req }" class="col-xs-10 col-sm-5" />(月薪)
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第七行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否在职:</label>
			
																						<div class="col-sm-9">
																						<select name="resume_is_inword">
																							<c:choose>
																								<c:when test="${requestScope.resume.resume_is_inword==0 }">
																									<option value="0" selected="selected">在职</option>
																									<option value="1">离职</option>
																								</c:when>
																								<c:otherwise>
																									<option value="0">在职</option>
																									<option value="1" selected="selected">离职</option>
																								</c:otherwise>
																							</c:choose>
																						</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px"><div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否应届生:</label>
			
																						<div class="col-sm-9">
																						<select name="resume_is_current">
																							<c:choose>
																								<c:when test="${requestScope.resume.resume_is_current==0 }">
																									<option value="0" selected="selected">是</option>
																									<option value="1">否</option>
																								</c:when>
																								<c:otherwise>
																									<option value="0">是</option>
																									<option value="1" selected="selected">否</option>
																								</c:otherwise>
																							</c:choose>
																						</select>
																						</div>
																					</div>
																	    			
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 登记时间:</label>
			
																						<div class="col-sm-9">
																							<input name="resume_login_date" class="form-control layer-date" placeholder="YYYY-MM-DD" readonly="readonly" value="${requestScope.resume.resume_login_date }" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第八行 --%>
																	    	
																	    	<tr>
																	    		<td style="width:500px" colspan="2">
																	    			<div class="form-group">
																						<label style="width:120px;" class="col-sm-3 control-label no-padding-right" for="form-field-1"> 个人履历:</label>
			
																						<div class="col-sm-9">
																							<textarea style="width:600px;height: 100px;left: 10px" readonly="readonly" name="resume_identity_info" rows="" cols="">${requestScope.resume.resume_identity_info }</textarea>
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%-- 第九行 --%>
																	    	<tr>
																	    		<td style="width:500px" colspan="1">
																	    			<div class="form-group">
																						<label  class="col-sm-3 control-label no-padding-right" for="form-field-1">简历附件:</label>
																						
																						<div class="col-sm-9">
																								<a  href="javascript:download('${resume.resume_info}')" >${resume.resume_info }</a>
																						
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px">
																	    			
																	    		</td>
																	    		<td style="width:400px">
																	    			
																	    		</td>
																	    	</tr>
																	    	
																	    	<%--第十行 --%>
																	    	<tr>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否推荐面试:</label>
			
																						<div class="col-sm-9">
																						<select name="resume_status">
																							<c:choose>
																								<c:when test="${requestScope.resume.resume_status==0 }">
																									<option value="1">是</option>
																									<option value="0" selected="selected">否</option>
																								</c:when>
																								<c:otherwise>
																									<option value="1" selected="selected">是</option>
																									<option value="0">否</option>
																								</c:otherwise>
																							</c:choose>
																						</select>
																						</div>
																					</div>
																	    		</td>
																	    		<td style="width:400px"><div class="form-group">
																						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 推荐人:</label>
			
																						<div class="col-sm-9">
																							<input name="admin_account" value="${requestScope.resume.admin.admin_account }" readonly="readonly" type="text"/>
																						</div>
																					</div>
																	    			
																	    		</td>
																	    		<td style="width:400px">
																	    			<div class="form-group">
																						<label  class="col-sm-3 control-label no-padding-right" for="form-field-1"> 推荐时间:</label>
			
																						<div class="col-sm-9">
																							<input name="resume_recommend_date" value="${requestScope.resume.resume_recommend_date }" readonly="readonly" class="form-control layer-date" placeholder="YYYY-MM-DD" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
																						</div>
																					</div>
																	    		</td>
																	    	</tr>
																	    	<%--第十一行 --%>
																	    	<tr>
																	    		<td style="width:500px" colspan="2">
																	    			<div class="form-group">
																						<label style="width:120px;" class="col-sm-3 control-label no-padding-right" for="form-field-1"> 推荐面试意见:</label>
			
																						<div class="col-sm-9">
																							<textarea style="width:600px;height: 100px;left: 10px" readonly="readonly" name="resume_suggest">${requestScope.resume.resume_suggest }</textarea>
																						</div>
																					</div>
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
			function download(resumeInfo){
				var filename=encodeURI(resumeInfo);
				var path="${pageContext.request.contextPath }/download.gkd?resumeInfo="+escape(filename);
				window.location.href=path;
			}
		</script>
	</body>
</html>
