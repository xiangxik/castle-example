package com.whenling.example;

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

			MenuEntity productManagement = menuEntityService.defaultCreate("产品管理", "product_management", "fa fa-cubes", null, null, sortNo++, null);
			menuEntityService.defaultCreate("产品列表", "product", "fa fa-cube", "app.view.product.ProductList", null, sortNo++, productManagement);
			menuEntityService.defaultCreate("产品分类", "product_category", "fa fa-filter", "app.view.productCategory.ProductCategoryList", null,
					sortNo++, productManagement);
			menuEntityService.defaultCreate("产品标签", "product_tag", "fa fa-bookmark", "app.view.productTag.ProductTagList", null, sortNo++,
					productManagement);
			menuEntityService.defaultCreate("品牌管理", "brand", "fa fa-th-large", "app.view.brand.BrandList", null, sortNo++, productManagement);

			MenuEntity cms = menuEntityService.defaultCreate("内容管理", "cms_management", "fa fa-newspaper-o", null, null, sortNo++, null);
			menuEntityService.defaultCreate("文章列表", "article", "fa fa-file", "app.view.article.ArticleList", null, sortNo++, cms);
			menuEntityService.defaultCreate("文章分类", "article_category", "fa fa-filter", "app.view.articleCategory.ArticleCategoryList", null,
					sortNo++, cms);
			menuEntityService.defaultCreate("文章标签", "article_tag", "fa fa-bookmark", "app.view.articleTag.ArticleTagList", null, sortNo++, cms);

			menuEntityService.defaultCreate("招聘管理", "job", "fa fa-archive", "app.view.job.JobList", null, sortNo++, cms);

			MenuEntity userManagement = menuEntityService.defaultCreate("用户管理", "user_management", "fa fa-users", null, null, sortNo++, null);
			menuEntityService.defaultCreate("用户列表", "member_list", "fa fa-user", "app.view.user.UserList", null, sortNo++, userManagement);
			menuEntityService.defaultCreate("角色权限", "role_list", "fa fa-gavel", "app.view.role.RoleList", null, sortNo++, userManagement);

			MenuEntity systemManagement = menuEntityService.defaultCreate("系统管理", "system_management", "fa fa-desktop", null, null, sortNo++, null);
			menuEntityService.defaultCreate("参数设置", "parameter_setting", "fa fa-cogs", "app.view.setting.SettingForm", null, sortNo++,
					systemManagement);
			menuEntityService.defaultCreate("菜单管理", "menu_management", "fa fa-navicon", "app.view.menu.MenuList", null, sortNo++, systemManagement);
			menuEntityService.defaultCreate("留言反馈", "feedback_management", "fa fa-crosshairs", "app.view.feedback.FeedbackList", null, sortNo++,
					systemManagement);
			menuEntityService.defaultCreate("邮件订阅", "subscribe_management", "fa fa-envelope-o", "app.view.subscribe.SubscribeList", null, sortNo++,
					systemManagement);
		}
	}

}
