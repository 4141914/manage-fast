package com.mf.common.utils;

import com.mf.common.domain.generator.GenColumn;
import com.mf.common.domain.generator.GenTable;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GenUtils {
	public static List<String> getTemplates() {
		List<String> templates = new ArrayList<String>();
		templates.add("gentemplate/Entity.java.vm");
		templates.add("gentemplate/Dao.java.vm");
		templates.add("gentemplate/Cond.java.vm");
		templates.add("gentemplate/Service.java.vm");
		templates.add("gentemplate/Controller.java.vm");
		templates.add("gentemplate/Action.java.vm");
		templates.add("gentemplate/Bus.java.vm");
		templates.add("gentemplate/Client.java.vm");
		templates.add("gentemplate/Mock.js.vm");
		templates.add("gentemplate/List.vue.vm");
		templates.add("gentemplate/Dialog.vue.vm");

		return templates;
	}

	/**
	 * 生成代码
	 */
	public static void generatorCode(Map<String, String> table,
									 List<Map<String, String>> columns, ZipOutputStream zip) {
		//配置信息
		Configuration config = getConfig();
		boolean hasBigDecimal = false;
		//表信息
		GenTable tableEntity = new GenTable();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		//表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		tableEntity.setClassName(className);
		tableEntity.setClassname(StringUtils.uncapitalize(className));

		//列信息
		List<GenColumn> columsList = new ArrayList<>();
		for (Map<String, String> column : columns) {
			GenColumn columnEntity = new GenColumn();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));

			//列名转换成Java属性名
			String attrName = columnToJava(columnEntity.getColumnName());
			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

			//列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			columnEntity.setAttrType(attrType);
			if (!hasBigDecimal && attrType.equals("BigDecimal")) {
				hasBigDecimal = true;
			}
			//是否主键
			if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null) {
				tableEntity.setPk(columnEntity);
			}

			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);

		//没主键，则第一个字段为主键
		if (tableEntity.getPk() == null) {
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}

		//设置velocity资源加载器
		Properties prop = new Properties();
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		Velocity.init(prop);
		String mainPath = config.getString("mainPath");
		mainPath = StringUtils.isBlank(mainPath) ? "io.renren" : mainPath;
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("hasBigDecimal", hasBigDecimal);
		map.put("mainPath", mainPath);
		map.put("package", config.getString("package"));
		map.put("moduleName", config.getString("moduleName"));
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN));
		VelocityContext context = new VelocityContext(map);

		//获取模板列表
		List<String> templates = getTemplates();
		for (String template : templates) {
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);

			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), tableEntity.getClassName().toLowerCase())));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (Exception e) {
				throw new RuntimeException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
			}
		}
	}


	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}

	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if (StringUtils.isNotBlank(tablePrefix)) {
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}

	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig() {
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("获取配置文件失败，", e);
		}
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName, String moduleName) {
		String packagePath = "main" + File.separator + "java" + File.separator;

		if (StringUtils.isNotBlank(packageName)) {
			packagePath += packageName.replace(".", File.separator) + File.separator + moduleName + File.separator;
		}

		if (template.contains("Entity.java.vm")) {
			return packagePath + className + ".java";
		}

		if (template.contains("Dao.java.vm")) {
			return packagePath + className + "Dao.java";
		}

		if (template.contains("Cond.java.vm")) {
			return packagePath + className + "Cond.java";
		}

		if (template.contains("Service.java.vm")) {
			return packagePath + className + "Service.java";
		}

		if (template.contains("Controller.java.vm")) {
			return packagePath + className + "Controller.java";
		}

		if (template.contains("Action.java.vm")) {
			return packagePath + className + "Action.java";
		}

		if (template.contains("Bus.java.vm")) {
			return packagePath + className + "Bus.java";
		}

		if (template.contains("Client.java.vm")) {
			return packagePath + "I" + className + "Client.java";
		}

		if (template.contains("Mock.js.vm")) {
			return packagePath + className + "Mock.js";
		}

		if (template.contains("List.vue.vm")) {
			return packagePath + className + "List.vue";
		}

		if (template.contains("Dialog.vue.vm")) {
			return packagePath + className + "Dialog.vue";
		}

		return null;
	}
}
