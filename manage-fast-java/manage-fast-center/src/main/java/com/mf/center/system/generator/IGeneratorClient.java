package com.mf.center.system.generator;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("mf-data/gen")
public interface IGeneratorClient {

	@RequestMapping("/queryList")
	List<Map<String, Object>> queryList();

	@RequestMapping("/queryTotal")
	Integer queryTotal();

	@RequestMapping("/queryTable")
	Map<String, Object> queryTable(@RequestParam("tableName") String tableName);

	@RequestMapping("/queryColumns")
	List<Map<String, Object>> queryColumns(@RequestParam("tableName") String tableName);
}
