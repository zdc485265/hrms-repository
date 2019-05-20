<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		 <jsp:include page="commons/commons-header.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/lib/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/lib/js/jquery-easyui-1.4.1/themes/icon.css" />
		<style type="text/css">
			#d1,#d2{
				position: static;
				float: left;
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
								<a href="#">系统设置</a>
							</li>
							<li class="active">组织结构设置</li>
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
													<h4 class="widget-title lighter">组织设置</h4>

													<div class="widget-toolbar no-border">
														
													</div>
												</div>

												<div class="widget-body">
													<div class="widget-main padding-12 no-padding-left no-padding-right">
														<div class="tab-content padding-4">
														
															<div id="adminAdd" class="tab-pane in active ">
																<div class="scrollable-horizontal" data-size="800">
																    <p style="color: red;">${requestScope.dept_set_up_msg }</p>
																	<form action="${pageContext.request.contextPath }/dept/deptSetUp.gkd" method="post" class="form-horizontal" role="form">
																		<input id="deptId" name="dept_id" type="hidden">
																		<input id="pdeptId" name="pdeptId" value="0"  type="hidden">
																		<input id="setUp" name="set_up" type="hidden">
																		<div id="d1" style="width: 20%">
																			<ul id="myTree" class="easyui-tree">
																				
																			</ul>
																			<div id="myMenu" class="easyui-menu" style="width:120px;">
																				<div onclick="append()" data-options="iconCls:'icon-add'">新增</div>
																				<div onclick="edit()" data-options="iconCls:'icon-edit'">编辑</div>
																				<div onclick="remove()" data-options="iconCls:'icon-remove'">删除</div>
																			</div>
																		</div>	
																		<div id="d2" style="width: 60%">
																			<table>
																				<!-- 第一行 -->
																				<tr>
																		    		<td style="width:400px">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 组织编码:</label>
				
																							<div class="col-sm-9">
																								<input name="dept_code" type="text" id="deptCode" placeholder=" 组织编码 例：DBU34683 " class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																							</div>
																						</div>
																		    		</td>
																		    		<td style="width:400px">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 组织简称:</label>
																							
																							<div class="col-sm-9">
																								<input name="dept_abbreviation" type="text" id="deptAbbreviation" placeholder=" 组织简称   例：总办 " class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																							</div>
																						</div>
																		    		</td>
																		    	</tr>
																		    	<!-- 第二行 -->
																		    	<tr>
																		    		<td style="width:400px">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 组织全称:</label>
				
																							<div class="col-sm-9">
																								<input name="dept_name" type="text" id="deptName" placeholder=" 组织全称   例：总裁办 " class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																							</div>
																						</div>
																		    		</td>
																		    		<td style="width:400px">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 所属组织:</label>
																							
																							<div class="col-sm-9">
																								<input id="deptParent" name="dept_parent" type="hidden">
																								<input id="deptParentName" name="dept_parent_name" readonly="readonly" type="text" id="deptParent" placeholder=" 所属组织  例：顶级组织" class="col-xs-10 col-sm-5" style="width: 210px" /><p style="color: red;">*</p>
																							</div>
																						</div>
																		    		</td>
																		    	</tr>
																		    	<!-- 第三行 -->
																		    	<tr>
																		    		<td style="width:200px" colspan="2">
																		    			<div class="form-group">
																							<label style="width: 107px" class="col-sm-3 control-label no-padding-right" for="form-field-1"> 地址:</label>
																							
																							<div class="col-sm-9">
																								<input name="dept_address" style="width: 610px" type="text" id="deptAddress" placeholder=" 地址  例：广西柳州*******号" class="col-xs-10 col-sm-5" /><p style="color: red;width: 650px;">*</p>
																							</div>
																						</div>
																		    		</td>
																	    		</tr>
																	    		<!-- 第四行 -->
																	    		<tr>
																		    		<td style="width:200px" colspan="2">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1" style="width:105px"> 简介:</label>
				
																							<div class="col-sm-9">
																								<textarea style="width:610px;height: 123px;left: 10px" id="deptIntroduction" name="dept_introduction"></textarea>
																							</div>
																						</div>
																		    		</td>
																	    		</tr>
																	    		<!-- 第五行 -->
																	    		<tr>
																		    		<td style="width:200px" colspan="2">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1" style="width:105px"> 备注:</label>
				
																							<div class="col-sm-9">
																								<textarea style="width:610px;height: 123px;left: 10px" id="deptRemarks" name="dept_remarks"></textarea>
																							</div>
																						</div>
																		    		</td>
																	    		</tr>
																	    		<tr>
																	    			<td style="width:400px">
																		    			<div class="form-group">
																							<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> 是否启用:</label>
																							
																							<div class="col-sm-9">
																								<select id="deptStatus" name="dept_status" >
																								   <option value="0" selected="selected">是</option>
																								   <option value="1">否</option>
																								</select>
																							</div>
																						</div>
																		    		</td>
																	    		</tr>
																			</table>
																			<div class="form-group">
																				
																				<div class="col-sm-7 text-right">
																					<button type="submit" class="btn btn-primary">保存</button>
																				</div>
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
		
		<script src="${pageContext.request.contextPath }/lib/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
		$(function(){
			$('#myTree').tree({  
		       checkbox: false,  
		       url:'${pageContext.request.contextPath }/dept/getDepts.gkd?deptId=0',  
		       onBeforeExpand:function(node,param){ 
		         $('#myTree').tree('options').url = "${pageContext.request.contextPath }/dept/getDepts.gkd?deptId=" + node.id;
				},        
		      onClick:function(node){
		        var state=node.state;
		         if(!state){                  //判断当前选中的节点是否为根节点
		           currentId=node.id;
		          $("#chooseOk").attr( "disabled" , false );  //如果为根节点 则OK按钮可用
		          }else{
		          $("#chooseOk").attr( "disabled" , true );  //如果不为根节点 则OK按钮不可用
		          }
		        },
				onContextMenu: function(e, node){
					e.preventDefault();
					// select the node
					$('#myTree').tree('select', node.target);
					// display context menu
					$('#myMenu').menu('show', {
						left: e.pageX,
						top: e.pageY
					});
					var id=node.id;
					$("#pdeptId").val(id);
				}
			});
		});
		</script>
		<script type="text/javascript">
			function append(){
				var deptId=$("#pdeptId").val();
				$.ajax({
					url:"${pageContext.request.contextPath }/dept/toAddDept.gkd?deptId="+deptId,
					data:{},
					type:"post",
					dataType:"json",
					success:function(info){
						if(info.msgStatus==0){
							$("#deptId").val("");
							$("#deptName").val("");
							$("#deptCode").val("");
							$("#deptAbbreviation").val("");
							$("#deptAddress").val("");
							$("#deptIntroduction").val("");
							$("#deptRemarks").val("");
							$("#setUp").val(1);
							$("#deptParent").val(info.dept.dept_id);
							$("#deptParentName").val(info.dept.dept_name);
						}else{
							alert(info.msgInfo);
						}
					}
				
				});
			}
			function edit(){
				var deptId=$("#pdeptId").val();
				$.ajax({
					url:"${pageContext.request.contextPath }/dept/toEditDept.gkd?deptId="+deptId,
					data:{},
					type:"post",
					dataType:"json",
					success:function(info){
						if(info.msgStatus==0){
							$("#setUp").val(2);
							$("#deptId").val(info.dept.dept_id);
							$("#deptName").val(info.dept.dept_name);
							$("#deptCode").val(info.dept.dept_code);
							$("#deptAbbreviation").val(info.dept.dept_abbreviation);
							$("#deptParent").val(info.dept.deptParent.dept_id);
							$("#deptAddress").val(info.dept.dept_address);
							$("#deptIntroduction").val(info.dept.dept_introduction);
							$("#deptRemarks").val(info.dept.dept_remarks);
							$("#deptParentName").val(info.dept.deptParent.dept_name);
							var status=info.dept.dept_status;
							if(status==0){
								$("#deptStatus").html("<option value='0' selected='selected'>是</option><option value='1'>否</option>");
							}
							if(status==1){
								$("#deptStatus").html("<option value='0'>是</option><option value='1' selected='selected'>否</option>");
							}
							
						}else{
							alert(info.msgInfo);
						}
					}
				
				});
			}
			function remove(){
				var deptId=$("#pdeptId").val();
				$.ajax({
					url:"${pageContext.request.contextPath }/dept/deleteDept.gkd?deptId="+deptId,
					data:{},
					type:"post",
					dataType:"json",
					success:function(info){
						if(info.msgStatus==0){
							//$('#myMenu').tree('remove', $("#"+deptId));
							window.location.href="${pageContext.request.contextPath }/dept/toDeptSetUp.gkd";
						}else{
							alert(info.msgInfo);
						}
					}
				
				});
			}
		</script>
	</body>
</html>
