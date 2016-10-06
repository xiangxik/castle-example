package com.whenling.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.whenling.castle.integration.ApplicationInitializer;
import com.whenling.castle.main.entity.MenuEntity;
import com.whenling.castle.main.entity.OrganizationEntity;
import com.whenling.castle.main.entity.RoleEntity;
import com.whenling.castle.main.entity.UserEntity;
import com.whenling.castle.main.entity.UserRoleEntity;
import com.whenling.castle.main.entity.OrganizationEntity.OrgType;
import com.whenling.castle.main.init.InitDataTools;
import com.whenling.castle.main.security.PasswordService;
import com.whenling.castle.main.service.OrganizationEntityService;
import com.whenling.castle.main.service.RoleEntityService;
import com.whenling.castle.main.service.UserEntityService;
import com.whenling.castle.main.service.UserRoleEntityService;

@Component
public class ExampleApplicationInitializer extends ApplicationInitializer {

	@Autowired
	private UserEntityService userEntityService;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private OrganizationEntityService organizationEntityService;

	@Autowired
	private RoleEntityService roleEntityService;

	@Autowired
	private UserRoleEntityService userRoleEntityService;

	@Autowired
	private InitDataTools tools;

	@Value("${info.company_name?:广州当凌信息科技有限公司}")
	private String companyName;

	@Override
	public void onRootContextRefreshed() {
		if (roleEntityService.count() == 0) {
			RoleEntity role = roleEntityService.newEntity();
			role.setName("管理员");
			role.setCode("admin");
			role.setLocked(true);
			roleEntityService.save(role);

			OrganizationEntity org = organizationEntityService.newEntity();
			org.setName(companyName);
			org.setCode("main_company");
			org.setType(OrgType.company);
			organizationEntityService.save(org);

			UserEntity admin = userEntityService.newEntity();
			passwordService.changeUserPassword(admin, null, "asd123");
			admin.setUsername("admin");
			admin.setMobile("13265323553");
			admin.setEmail("ken@whenling.com");
			admin.setName("孔祥溪");
			admin.setOrg(org);
			userEntityService.save(admin);

			org.setPrimaryLeader(admin);
			organizationEntityService.save(org);

			UserRoleEntity userRole = userRoleEntityService.newEntity();
			userRole.setRole(role);
			userRole.setUser(admin);
			userRoleEntityService.save(userRole);

		}

		if (!tools.existMenu()) {

			tools.setting("example", "/assets/images/logo_header.png", "/extjs/app/images/login_header.jpg", "020-85655521", "18023458612", "ken@whenling.com",
					"广东省，广州市，天河区 天河路351号广东外经贸大厦22层2223");

			int sortNo = 0;

			MenuEntity productManagement = tools.createMenuByParent("产品管理", "product_management", "fa fa-cubes", null, null, sortNo++, null);
			tools.createMenuByParent("产品列表", "product", "fa fa-cube", "app.view.product.ProductList", null, sortNo++, productManagement);
			tools.createMenuByParent("产品分类", "product_category", "fa fa-filter", "app.view.productCategory.ProductCategoryList", null, sortNo++, productManagement);
			tools.createMenuByParent("产品标签", "product_tag", "fa fa-bookmark", "app.view.productTag.ProductTagList", null, sortNo++, productManagement);
			tools.createMenuByParent("品牌管理", "brand", "fa fa-th-large", "app.view.brand.BrandList", null, sortNo++, productManagement);

			MenuEntity cms = tools.createMenuByParent("内容管理", "cms_management", "fa fa-newspaper-o", null, null, sortNo++, null);
			tools.createMenuByParent("文章列表", "article", "fa fa-file", "app.view.article.ArticleList", null, sortNo++, cms);
			tools.createMenuByParent("文章分类", "article_category", "fa fa-filter", "app.view.articleCategory.ArticleCategoryList", null, sortNo++, cms);
			tools.createMenuByParent("文章标签", "article_tag", "fa fa-bookmark", "app.view.articleTag.ArticleTagList", null, sortNo++, cms);

			tools.createMenuByParent("招聘管理", "job", "fa fa-archive", "app.view.job.JobList", null, sortNo++, cms);

			MenuEntity userManagement = tools.createMenuByParent("用户管理", "user_management", "fa fa-users", null, null, sortNo++, null);
			tools.createMenuByParent("用户列表", "member_list", "fa fa-user", "app.view.user.UserList", null, sortNo++, userManagement);
			tools.createMenuByParent("角色权限", "role_list", "fa fa-gavel", "app.view.role.RoleList", null, sortNo++, userManagement);

			MenuEntity systemManagement = tools.createMenuByParent("系统管理", "system_management", "fa fa-desktop", null, null, sortNo++, null);
			tools.createMenuByParent("参数设置", "parameter_setting", "fa fa-cogs", "app.view.setting.SettingForm", null, sortNo++, systemManagement);
			tools.createMenuByParent("菜单管理", "menu_management", "fa fa-navicon", "app.view.menu.MenuList", null, sortNo++, systemManagement);
			tools.createMenuByParent("留言反馈", "feedback_management", "fa fa-crosshairs", "app.view.feedback.FeedbackList", null, sortNo++, systemManagement);
			tools.createMenuByParent("邮件订阅", "subscribe_management", "fa fa-envelope-o", "app.view.subscribe.SubscribeList", null, sortNo++, systemManagement);
		}
	}

}
