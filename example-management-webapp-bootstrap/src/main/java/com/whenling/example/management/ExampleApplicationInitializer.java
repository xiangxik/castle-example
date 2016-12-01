package com.whenling.example.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.whenling.castle.integration.ApplicationInitializer;
import com.whenling.castle.main.entity.MenuEntity;
import com.whenling.castle.main.service.AdminEntityService;
import com.whenling.castle.main.service.MenuEntityService;
import com.whenling.castle.main.service.OrganizationEntityService;

@Component
public class ExampleApplicationInitializer extends ApplicationInitializer {

	@Autowired
	private AdminEntityService adminEntityService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private OrganizationEntityService organizationEntityService;

	@Autowired
	private MenuEntityService menuEntityService;

	@Value("${info.company_name?:广州当凌信息科技有限公司}")
	private String companyName;

	@Override
	public void onRootContextRefreshed() {
		if (adminEntityService.count() == 0) {
			adminEntityService.defaultCreate("孔祥溪", "admin", passwordEncoder.encode("asd1234"), "13265323553", "ken@whenling.com");
		}
		if (organizationEntityService.count() == 0) {
			organizationEntityService.defaultCreate(companyName, "whenling");
		}
		if (menuEntityService.count() == 0) {
			int sortNo = 0;
			menuEntityService.defaultCreate("用户管理", "user_managerment", "fa fa-users", "/user", null, sortNo++, null);
			MenuEntity systemMenu = menuEntityService.defaultCreate("系统管理", "system_management", "fa fa-desktop", null, null, sortNo++, null);
			menuEntityService.defaultCreate("系统设置", "system_setting", "fa fa-cogs", "/setting", null, sortNo++, systemMenu);
			menuEntityService.defaultCreate("管理员", "system_admin", "fa fa-briefcase", "/admin", null, sortNo++, systemMenu);
			menuEntityService.defaultCreate("管理组", "system_admin_group", "fa fa-gavel", "/group", null, sortNo++, systemMenu);
		}
	}

}
