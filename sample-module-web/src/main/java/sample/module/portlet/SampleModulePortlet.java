package sample.module.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import sample.module.constants.SampleModulePortletKeys;


@Component(immediate = true,
        property = { "com.liferay.portlet.display-category=category.hidden",
                "com.liferay.portlet.preferences-owned-by-group=true",
                "com.liferay.portlet.preferences-unique-per-layout=false", "com.liferay.portlet.scopeable=true",
                "javax.portlet.init-param.mvc-command-names-default-views=/",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.name=" + SampleModulePortletKeys.SAMPLE_MODULE_PORTLET_FULL_LIFERAY_NAME,
                "javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user",
                "javax.portlet.version=3.0" },
        service = Portlet.class)
public class SampleModulePortlet extends MVCPortlet {

}
