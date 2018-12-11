package com.mf.center.system.generator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mf.common.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorBus {

	@Autowired
	private IGeneratorClient generatorClient;

	public List<Map<String, Object>> queryList() {
		return generatorClient.queryList();
	}

	public Integer queryTotal() {
		return generatorClient.queryTotal();
	}

	public Map<String, String> queryTable(String tableName) {
		Map<String, Object> map = generatorClient.queryTable(tableName);

		Map<String, String> resultMap = Maps.newHashMap();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			resultMap.put(entry.getKey(), entry.getValue().toString());
		}

		return resultMap;
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		List<Map<String, Object>> mapList = generatorClient.queryColumns(tableName);

		List<Map<String, String>> resultList = Lists.newArrayList();

		for (Map<String, Object> tempMap : mapList) {

			Map<String, String> resultMap = Maps.newHashMap();
			for (Map.Entry<String, Object> entry : tempMap.entrySet()) {
				resultMap.put(entry.getKey(), entry.getValue().toString());
			}
			resultList.add(resultMap);
		}

		return resultList;
	}

	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for (String tableName : tableNames) {
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}
