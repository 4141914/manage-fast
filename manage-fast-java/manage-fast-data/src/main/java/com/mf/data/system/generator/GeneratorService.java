package com.mf.data.system.generator;

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
public class GeneratorService {

	@Autowired
	private GeneratorDao generatorDao;

	public List<Map<String, Object>> queryList() {
		return this.generatorDao.queryList();
	}

	public Integer queryTotal() {
		return this.generatorDao.queryTotal();
	}

	public Map<String, Object> queryTable(String tableName) {
		return this.generatorDao.queryTable(tableName);
	}

	public List<Map<String, Object>> queryColumns(String tableName) {
		return this.generatorDao.queryColumns(tableName);
	}
}
