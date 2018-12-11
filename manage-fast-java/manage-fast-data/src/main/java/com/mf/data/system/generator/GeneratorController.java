package com.mf.data.system.generator;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gen")
public class GeneratorController {

	@Autowired
	private GeneratorService generatorService;

	@RequestMapping("/queryList")
	public List<Map<String, Object>> queryList() {
		return this.generatorService.queryList();
	}

	@RequestMapping("/queryTotal")
	public Integer queryTotal() {
		return this.generatorService.queryTotal();
	}

	@RequestMapping("/queryTable")
	public Map<String, Object> queryTable(@RequestParam("tableName") String tableName) {
		return this.generatorService.queryTable(tableName);
	}

	@RequestMapping("/queryColumns")
	public List<Map<String, Object>> queryColumns(@RequestParam("tableName") String tableName) {
		return this.generatorService.queryColumns(tableName);
	}
}
