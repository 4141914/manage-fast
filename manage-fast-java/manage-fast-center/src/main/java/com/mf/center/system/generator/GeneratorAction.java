package com.mf.center.system.generator;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("api/gen")
public class GeneratorAction {

	@Autowired
	private GeneratorBus generatorService;

	/**
	 * @方法说明:新增记录
	 **/
	@RequestMapping("/list")
	public Object test() {
		return generatorService.queryList();
	}

	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tables = request.getParameter("tables");

		byte[] data = generatorService.generatorCode(new String[]{tables});

		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=" + tables + ".zip");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream; charset=UTF-8");

		IOUtils.write(data, response.getOutputStream());

//以下一段代码可以返回中文文件名,需要前端解析hander中的文件名称
//		String tables = request.getParameter("tables");
//
//		byte[] data = generatorService.generatorCode(new String[]{tables});
//
//		response.reset();
//		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("" + tables + "你好吗") + ".zip");
//		response.addHeader("Content-Length", "" + data.length);
//		response.setContentType("application/octet-stream; charset=UTF-8");
//
//		IOUtils.write(data, response.getOutputStream());
	}
}
