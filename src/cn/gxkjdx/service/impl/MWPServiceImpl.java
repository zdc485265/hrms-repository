package cn.gxkjdx.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gxkjdx.mapper.AdminMapper;
import cn.gxkjdx.service.MWPService;
import cn.gxkjdx.util.Md5Utils;

@Service
public class MWPServiceImpl implements MWPService{
	
	@Autowired
	AdminMapper adminMapper;

	@Override
	public Map<String, Object> editPwd(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<>();
		StringBuffer sb=new StringBuffer("修改密码失败,");
		Object adminId = map.get("admin_id");
		Object oldAdminPwd = map.get("old_admin_pwd");
		Object newAdminPwd = map.get("new_admin_pwd");
		Object confirmAdminPwd = map.get("confirm_admin_pwd");
		int num=0;
		if(null==adminId||"".equals(adminId)){
			sb.append("找不到当前用户,");
			num++;
		}
		if(null==oldAdminPwd||"".equals(oldAdminPwd)){
			sb.append("旧密码不能为空,");
			num++;
		}
		if(null==newAdminPwd||"".equals(newAdminPwd)){
			sb.append("新密码不能为空,");
			num++;
		}else if(!newAdminPwd.equals(confirmAdminPwd)){
			sb.append("两次密码不一致,");
			num++;
		}
		sb.setLength(sb.length()-1);
		if(num==0){
			Map<String, Object> admin = adminMapper.findById(adminId);
			if(null!=admin){
				Object adminPwd = admin.get("admin_pwd");
				if(null!=adminPwd&&adminPwd.equals(Md5Utils.md5(oldAdminPwd.toString()))){
					admin.put("admin_pwd", Md5Utils.md5(newAdminPwd.toString()));
					int update = adminMapper.update(admin);
					if(update>0){
						sb=new StringBuffer("修改密码成功");
					}else{
						sb.append(",未知错误!");
					}
				}else{
					sb.append(",旧密码不正确!");
				}
			}
		}
		info.put("mwp_edit_msg", sb.toString());
		return info;
	}

}
