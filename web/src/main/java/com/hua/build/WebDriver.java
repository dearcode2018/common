/**
 * 描述: 
 * WebDriver.java
 * @author	qye.zheng
 *  version 1.0
 */
package com.hua.build;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.hua.base.ProjectModule;
import com.hua.base.Property;
import com.hua.base.WebModule;
import com.hua.base.WebParam;
import com.hua.base.WebProjectFilter;
import com.hua.base.WebResource;
import com.hua.constant.Constant;
import com.hua.util.ClassPathUtil;
import com.hua.util.FileUtil;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;
import com.hua.util.StringUtil;

/**
 * 描述: - 驱动器
 * 
 * @author qye.zheng WebDriver
 */
public class WebDriver {

	/**
	 * 构造方法 描述:
	 * 
	 * @author qye.zheng
	 */
	private WebDriver() {
	}

	/**
	 * 
	 * 描述:
	 * 
	 * @author qye.zheng
	 * @return
	 */
	public static final boolean buildWeb() {
		boolean flag = false;
		final WebParam webParam = WebParam.getInstance();
		try {
			final String projectName = webParam.getProjectName();
			final String workspacePath = ProjectUtil.getWorkspacePath();
			File[] projectFiles = null;
			if (StringUtil.isEmpty(projectName)) { // 为空，处理工作空间中所有的web项目
				final File workspace = new File(workspacePath);
				projectFiles = workspace.listFiles(new WebProjectFilter());
			} else {
				projectFiles = new File[] { new File(workspacePath + "/"
						+ projectName) };
			}
			assemble(webParam, projectFiles);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 
	 * @description 
	 * @param webParam
	 * @param projectFiles
	 * @author qye.zheng
	 */
	private static final void assemble(final WebParam webParam,
			final File[] projectFiles) {
		String projectName = null;
		ProjectModule projectModule = null;
		WebModule webModule = null;
		WebResource webResource = null;
		Property property = null;
		int count = 0;
		String message = null;
		for (File file : projectFiles) {
			// 建立构造器
			System.out.println("path = " + file.getPath());
			projectName = file.getName();
			
			projectModule = new ProjectModule();
			// projectModule.setId("moduleCoreId");
			webModule = new WebModule();
			webModule.setDeployName(projectName);
			
			for (String v : WebParam.SOURCE_PATH) {
				webResource = new WebResource();
				webResource.setDeployPath(WebParam.DEPLOY_PATH);
				webResource.setSourcePath(v);
				webModule.getWebResources().add(webResource);
			}
			// 测试部分
			if (webParam.isIncludeTest()) {
				for (String v : WebParam.TEST_SOURCE_PATH) {
					webResource = new WebResource();
					webResource.setDeployPath(WebParam.DEPLOY_PATH);
					webResource.setSourcePath(v);
					webModule.getWebResources().add(webResource);
				}
			}
			webResource = new WebResource();
			webResource.setDeployPath(WebParam.WEB_DEPLOY_PATH);
			webResource.setSourcePath(WebParam.WEB_SOURCE_PATH);
			webModule.getWebResources().add(webResource);

			property = new Property();
			property.setName(WebParam.PROPERTY_CONTEXT_ROOT);
			property.setValue("/" + projectName);
			webModule.getProperties().add(property);

			property = new Property();
			property.setName(WebParam.PROPERTY_OUTPUT_PATH);
			property.setValue("/" +projectName + "/target/classes");
			webModule.getProperties().add(property);
			
			projectModule.setWebModule(webModule);
			
			createXMLFile(file, projectModule);
			count++;
		}
		System.out.println("操作信息: ");
		message = "总共操作 " + count + " 个web项目";
		System.out.println(message);
	}

	/**
	 * 
	 * @description 
	 * @param file
	 * @param projectModule
	 * @author qye.zheng
	 */
	private static final void createXMLFile(final File file, final ProjectModule projectModule) {
		final String componentPath = "/.settings/org.eclipse.wst.common.component";
		final Format format = Format.getPrettyFormat();
		// 设置编码 - 中文
		format.setEncoding(Constant.CHART_SET_UTF_8);
		Document doc = null;
		Element root = null;
		doc = new Document();
		root = new Element(projectModule.getTagName());
		root.setAttribute(ProjectModule.PROPERTY_KEY, projectModule.getProjectVersion());
		doc.setRootElement(root);
		OutputStream output = null;
		XMLOutputter out = null;
		final WebModule webModule = projectModule.getWebModule();
		Element eWebModule = new Element(webModule.getTagName());
		eWebModule.setAttribute(WebModule.PROPERTY_KEY, webModule.getDeployName());
		root.addContent(eWebModule);
		
		Element element = null;
		for (WebResource r : webModule.getWebResources())
		{
			element = new Element(r.getTagName());
			element.setAttribute(WebResource.PROPERTY_DEPLOY_KEY, r.getDeployPath());
			element.setAttribute(WebResource.PROPERTY_SOURCE_KEY, r.getSourcePath());
			eWebModule.addContent(element);
		}
		for (Property p : webModule.getProperties())
		{
			element = new Element(p.getTagName());
			element.setAttribute(Property.PROPERTY_NAME_KEY, p.getName());
			element.setAttribute(Property.PROPERTY_VALUE_KEY, p.getValue());
			eWebModule.addContent(element);
		}
		
		out = new XMLOutputter();
		try {
			output = new FileOutputStream(file.getPath() + componentPath);
			out.setFormat(format);
			// 输出 xml 文件
			out.output(doc, output);
			// 拷贝 .classpath 文件
			copyWebClassPathFile(file);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @description 拷贝 .classpath 文件
	 * 所有web项目的.classpath文件都是相同的，
	 * 直接拷贝即可，可以统一修改 conf/xml/web-classpath.xml 文件，
	 * 拷贝之后，将重命名 为 .classpath 文件
	 * @param projectFile
	 * @author qye.zheng
	 */
	private static final void copyWebClassPathFile(final File projectFile)
	{
		final String filePath = projectFile.getPath() + "/" + WebParam.CLASSPATH_NAME;
		final File classPathFile = new File(ClassPathUtil.getClassPath(WebParam.WEB_PROJECT_CLASSPATH_PATH));
		FileUtil.copyFile(classPathFile, filePath);
	}
	
}
