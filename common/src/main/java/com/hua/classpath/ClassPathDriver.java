/**
 * ClassPathDriver.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.classpath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.hua.constant.Constant;
import com.hua.util.ClassPathUtil;
import com.hua.util.EmptyUtil;
import com.hua.util.FileNameUtil;
import com.hua.util.IOUtil;
import com.hua.util.ProjectUtil;

/**
 * ClassPathDriver
 * 描述: 项目 类路径工具
 * 可以生成 .classpath 文件
 * 快速将lib目录下所有Jar文件加入到.classpath文件中
 * 实现快速类环境部署
 * @author qye.zheng
 * 
 */
public final class ClassPathDriver
{
	/* 项目根路径 */
	private static final String PROJECT_ROOT = System.getProperty("user.dir");
	
	private static final String classPathFile;
	
	private static String libAbsolutePath;
	
	// 类库目录的相对路径
	private static final String libRelativePath = "/lib";
	
	private static String sourceAbsolutePath;
	
	// 源码目录的相对路径
	private static final String sourceRelativePath= "/doc/source";
	
	private static final Set<String> sourceFilenames = new HashSet<String>();
	
	//@SuppressWarnings(value = {"unused"})
	// 是否是web项目
	private static boolean isWeb = false;
	
	static {
		// .classpath 文件的路径 (不同类型项目 通用)
		classPathFile = PROJECT_ROOT + File.separator + ".classpath";
		System.out.println(".classpath --> " + classPathFile);
		final String path = ClassPathUtil.getClassRootPath();
		// lib 类库的路径，web 项目和java项目不同
		if (path.contains("bin/")) {
			// java 项目
			isWeb = false;
			libAbsolutePath = PROJECT_ROOT + libRelativePath;
			sourceAbsolutePath = PROJECT_ROOT + sourceRelativePath;
		} else {
			// web 项目
			isWeb = true;
			libAbsolutePath = path.substring(0, path.lastIndexOf("/classes")) + libRelativePath;
			sourceAbsolutePath = PROJECT_ROOT + sourceRelativePath;
		}
		// 加载源码文件
		loadSource();
		System.out.println("lib file path --> " + libAbsolutePath);
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private ClassPathDriver()
	{
	}
	
	/**
	 * 描述: 项目的类路径处理器 
	 * @author qye.zheng
	 */
	public static void classPathHandler() {
		OutputStream output = null;
		try
		{
			/**
			 * 1. 构造ClassPath总对象，然后通过2种途径逐个生成ClassPathEntry对象，
			 * 2. 从旧的文档中构造kind != lib的ClassPathEntry对象，并加入ClassPath中
			 * 3. 从lib目录下读取jar文件来构造ClassPathEntry对象，并加入ClassPath中
			 * 4. 操作ClassPath总对象，统一生成完整的.classpath文件
			 */
			// 构造总对象
			final ClassPath classPath = new ClassPath();
			// ClassPathEntry 子对象
			ClassPathEntry entry = null;
			// 建立构造器  
			final SAXBuilder saxBuilder = new SAXBuilder();
			// 读入指定文件  
			final Document oldDoc = saxBuilder.build(new FileInputStream(classPathFile));
			
			// 获得根节点  
			final Element oldRoot = oldDoc.getRootElement();
			// 将根节点下的所有子节点放入List中  
			final List<Element> oldElements = oldRoot.getChildren();
			Element classPathEntryElement = null;
			
			/** ===== begin 从旧文档构造kind != lib的对象 ===== */
			String kindValue = null;
			String pathValue = null;
			Element attributes = null;
			List<Element> attributeElements = null;
			Attribute attribute = null;
			for (int i = 0; i < oldElements.size(); i++) {
				classPathEntryElement = oldElements.get(i);
				kindValue = classPathEntryElement.getAttributeValue(ClassPathConstant.ATTRIBUTE_KIND);
				pathValue = classPathEntryElement.getAttributeValue(ClassPathConstant.ATTRIBUTE_PATH);
				// 忽略掉kind = lib
				if (Kind.LIB.equals(kindValue)) {
					continue;
				}
				/*
				  web项目 忽略掉   
				<classpathentry kind="con" path="org.eclipse.jst.j2ee.internal.web.container" />
				 */
				if (isWeb && Kind.CON.equals(kindValue))
				{
					if (ClassPathConstant.CON_PATH_INTERNAL_WEB.equals(pathValue))
					{
						continue;
					}
				}
				// 构造ClassPathEntry对象
				entry = new ClassPathEntry();
				entry.setKind(kindValue);
				entry.setPath(pathValue);
				attributes = classPathEntryElement.getChild("attributes");
				if (null != attributes)
				{
					attributeElements = attributes.getChildren();
					for (Element attributeE : attributeElements)
					{
						attribute = new Attribute();
						attribute.setName(attributeE.getAttributeValue(ClassPathConstant.ATTRIBUTE_NAME));
						attribute.setValue(attributeE.getAttributeValue(ClassPathConstant.ATTRIBUTE_VALUE));
						entry.addAttribute(attribute);
					}
				}
				// 加入总对象
				classPath.addClassPathEntry(entry);
			}
			/** ===== end of 从旧文档构造kind != lib的对象 ===== */
			
			/** ===== begin 从lib目录下读取jar文件构造对象 ===== */
			// 读取 lib 目录下的jar文件
			final File libDir = new File(libAbsolutePath);
			final File[] files = libDir.listFiles();
			File file = null;
			String filename = null;
			// jar文件名前缀(即不含后缀名)
			String jarFilePrefix = null;
			String sourceFilename = null;
			String sourcePath = null;
			int totalItem = -1;
			for (totalItem = 0; totalItem < files.length; totalItem++) {
				file = files[totalItem];
				filename = file.getName();
				entry = new ClassPathEntry();
				// web项目和java项目
				if (isWeb)
				{
					// web 项目
					entry.setPath(ClassPathConstant.WEB_RELATIVE_PATH_LIB + filename);
				} else
				{
					// java 项目
					entry.setPath(ClassPathConstant.RELATIVE_PATH_LIB + filename);
				}
				
				// 获取文件前缀名
				jarFilePrefix = FileNameUtil.getPrefix(filename);
				// 组装成源码文件名 (zip或jar)
				sourceFilename = jarFilePrefix + ClassPathConstant.SOURCE_SUFFIX_ZIP;
				// 判断/doc/source下是否有zip文件，否则使用jar文件
				if (!sourceFilenames.contains(sourceFilename))
				{
					// 不包含zip文件，默认使用jar文件
					sourceFilename = jarFilePrefix + ClassPathConstant.SOURCE_SUFFIX_JAR;
				}
				sourcePath = ClassPathConstant.RELATIVE_PATH_SOURCE + sourceFilename;
				// 源码文件路径 - 设置
				entry.setSourcePath(sourcePath);
				// 加入总对象
				classPath.addClassPathEntry(entry);
			}
			/** ===== end of 从lib目录下读取jar文件构造对象 ===== */
			
			/** ===== begin 生成新的.classpath文件 ===== */
			final Document newDoc = new Document(); 
			final Element newRoot = new Element(ClassPath.TAG_Name);
			newDoc.setRootElement(newRoot);
			
			final List<Element> newElements = new ArrayList<Element>();
			
			final List<ClassPathEntry> classPathEntries = classPath.getClassPathEntries();
			org.jdom2.Attribute kindAttr = null;
			org.jdom2.Attribute pathAttr = null;
			org.jdom2.Attribute sourcePathAttr = null;
			Element attributesElement = null;
			Element attributeElement = null;
			org.jdom2.Attribute nameAttr = null;
			org.jdom2.Attribute valueAttr = null;
			
			for (ClassPathEntry temp : classPathEntries)
			{
				entry = temp;
				classPathEntryElement = new Element(ClassPathEntry.TAG_NAME);
				kindAttr = new org.jdom2.Attribute(ClassPathConstant.ATTRIBUTE_KIND, entry.getKind());
				classPathEntryElement.setAttribute(kindAttr);
				
				pathAttr = new org.jdom2.Attribute(ClassPathConstant.ATTRIBUTE_PATH, entry.getPath());
				classPathEntryElement.setAttribute(pathAttr);
				// attributes 标签
				if (!EmptyUtil.isEmpty(entry.getAttributes()))
				{
					attributesElement = new Element(ClassPathEntry.TAG_NAME_ATTRIBUTES);
					for (Attribute attr : entry.getAttributes())
					{
						attributeElement = new Element(Attribute.TAG_NAME);
						nameAttr = new org.jdom2.Attribute(ClassPathConstant.ATTRIBUTE_NAME, attr.getName());
						valueAttr = new org.jdom2.Attribute(ClassPathConstant.ATTRIBUTE_VALUE, attr.getValue());
						attributeElement.setAttribute(nameAttr);
						attributeElement.setAttribute(valueAttr);
						attributesElement.addContent(attributeElement);
					}
					// 加入
					classPathEntryElement.addContent(attributesElement);
				}
				// kind = lib 才进行 sourcepath设置
				if (Kind.LIB.equals(entry.getKind()))
				{
					sourcePathAttr = new org.jdom2.Attribute(ClassPathConstant.ATTRIBUTE_SOURCE_PATH, 
							entry.getSourcePath());
					classPathEntryElement.setAttribute(sourcePathAttr);
				}
				
				// 加入新元素集合
				newElements.add(classPathEntryElement);
			}
			newRoot.addContent(newElements);
			
			System.out.println("it is " + totalItem + " jar item to add to classpath");
			
			// 将 doc 输出到 .classpath 文件
			// XMLOutput
			final XMLOutputter out = new XMLOutputter();
			// 获取格式对象
			//final Format format = out.getFormat();
			final Format format = Format.getPrettyFormat();
			// 设置编码 - 中文
			format.setEncoding(Constant.CHART_SET_UTF_8);
			// format 为 PreetyFormat 对象时，设置该属性，Pretty输出将失效
			//format.setTextMode(TextMode.PRESERVE);
			// 设置输出格式
			out.setFormat(format);
			// 文件输出流 (可以在后面加上若干字符，作为测试使用)
			output = new FileOutputStream(classPathFile);
			//output = new FileOutputStream(classPathFile + "-test");
			// 输出 xml 文件
			out.output(newDoc, output);
			/** ===== end of 生成新的.classpath文件 ===== */
			
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			try
			{
				if (null != output) {
					output.close();
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * 描述: 加载获取源码文件名称 
	 * @author qye.zheng
	 */
	private static void loadSource()
	{
		System.out.println(sourceAbsolutePath);
		// 读取 doc/source 目录下的(jar/zip)文件
		final File sourceDir = new File(sourceAbsolutePath);
		// 不存在这创建一个source目录
		if (!sourceDir.exists())
		{
			System.out.println("创建一个 " + sourceDir.getName() + " 目录");
			// 创建一个
			sourceDir.mkdir();
		}
		final File[] files = sourceDir.listFiles();
		File file = null;
		if (!EmptyUtil.isEmpty(files))
		{
			for (int i = 0; i < files.length; i++)
			{
				file = files[i];
				sourceFilenames.add(file.getName());
			}
		} else 
		{
			System.out.println(sourceDir.getPath() + " 目录为空");
		}
	}
	
	/**
	 * 
	 * 描述: 项目的类路径处理器 
	 * 说明: 不包含源码路径属性，不推荐使用这种方式
	 * @author qye.zheng
	 * 
	 */
	@Deprecated
	public static void classPathHandlerWithoutSourcePath() {
		OutputStream output = null;
		try
		{
			/**
			 * 1. 读取项目的 .classpath 文件，将kind != lib的原样拷贝到新文档
			 * kind = lib classpathentry 将根据lib目录下的jar文件来逐个生成
			 * 2. 读取lib 下的文件，构造 classpathentry 对象
			 * 3. 关闭读取的.classpath 相关对象，输出doc覆盖到.classpath
			 */
			// 建立构造器  
			final SAXBuilder saxBuilder = new SAXBuilder();
			// 读入指定文件  
			final Document oldDoc = saxBuilder.build(new FileInputStream(classPathFile));
			
			// 获得根节点  
			final Element oldRoot = oldDoc.getRootElement();
			// 将根节点下的所有子节点放入List中  
			final List<Element> oldElements = oldRoot.getChildren();
			
			final Document newDoc = new Document(); 
			final Element newRoot = new Element(ClassPath.TAG_Name);
			newDoc.setRootElement(newRoot);
			
			final List<Element> newElements = new ArrayList<Element>();
			Element e = null;
			String name = null;
			for (int i = 0; i < oldElements.size(); i++) {
				e = oldElements.get(i);
				name = e.getAttributeValue("kind");
				if (Kind.LIB.equals(name)) {
					//System.out.println("remove ...");
					// 移除掉指定的元素
					//elements.remove(e);
					//oldRoot.removeContent(e);
					
					continue;
				}
				// 加入新标签集合中 (将 kind != lib的直接拷贝到新文档)
				newElements.add(e.clone());
			}
			
			// 读取 lib 目录下的jar文件
			final File file = new File(libAbsolutePath);
			final File[] files = file.listFiles();
			File f = null;
			org.jdom2.Attribute kind = null;
			org.jdom2.Attribute path = null;
			org.jdom2.Attribute sourcePath = null;
			ClassPathEntry entry = null;
			int i;
			for (i = 0; i < files.length; i++) {
				e = new Element(ClassPathEntry.TAG_NAME);
				f = files[i];
				entry = new ClassPathEntry();
				entry.setPath("lib/" + f.getName());
				kind = new org.jdom2.Attribute("kind", entry.getKind());
				e.setAttribute(kind);
				
				path = new org.jdom2.Attribute("path", entry.getPath());
				e.setAttribute(path);
				
				sourcePath = new org.jdom2.Attribute("path", entry.getSourcePath());
				
				// 加入新元素集合
				newElements.add(e);
			}
			newRoot.addContent(newElements);
			
			System.out.println("it is " + i + " jar item to add to classpath");
			
			// 将 doc 输出到 .classpath 文件
			// XMLOutput
			final XMLOutputter out = new XMLOutputter();
			// 获取格式对象
			//final Format format = out.getFormat();
			final Format format = Format.getPrettyFormat();
			// 设置编码 - 中文
			format.setEncoding(Constant.CHART_SET_UTF_8);
			// format 为 PreetyFormat 对象时，设置该属性，Pretty输出将失效
			//format.setTextMode(TextMode.PRESERVE);
			// 设置输出格式
			out.setFormat(format);
			// 文件输出流
			output = new FileOutputStream(classPathFile);
			// 输出 xml 文件
			out.output(newDoc, output);
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally {
			try
			{
				if (null != output) {
					output.close();
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * 描述: 将类路径转换为 Web App Libraries 方式
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean transformToWebApp()
	{
		/**
		 将/conf/classpath/WebApp.classpath 输出到项目根路径下，并重名为 .classpath
		 */
		
		boolean flag = false;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		final boolean close = true;
		try
		{
			inputStream = ClassPathUtil.getInputStream(ClassPathConstant.WEB_APP_CLASSPATH_FILE);
			outputStream = new FileOutputStream(ProjectUtil.getAbsolutePath(File.separator + ClassPathConstant.PROJECT_CLASSPATH, true));
			IOUtil.transport(inputStream, outputStream, close);
			
			flag = true;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return flag;
	}

}
