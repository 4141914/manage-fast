package com.mf.data.system.generator;

import com.mf.data.common.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GeneratorDao extends BaseDao {

	public List<Map<String, Object>> queryList() {
		String sql = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) ";
		List<Map<String, Object>> lists = jdbcTemplate.queryForList(sql);
		return lists;
	}

	public Integer queryTotal() {
		String sql = "select count(*) from information_schema.tables where table_schema = (select database())";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public Map<String, Object> queryTable(String tableName) {
		String sql = "select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) and table_name = ?";
		Object[] params = {tableName};
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, params);
		return map;
	}

	public List<Map<String, Object>> queryColumns(String tableName) {
		String sql = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = ? and table_schema = (select database()) order by ordinal_position";
		Object[] params = {tableName};
		List<Map<String, Object>> lists = jdbcTemplate.queryForList(sql, params);
		return lists;
	}

}
