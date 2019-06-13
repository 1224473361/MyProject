package com.xhx.mybatisgenerate;

import org.junit.Test;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 
 * mybatis-plus代码生成器
 * 
 * @date 2019年6月12日
 * @author lihui
 */
public class MpGenerate {

	private static final String PACKAGE_NAME = "com.mybatistest";

	private static final String DB_URL = "jdbc:oracle:thin:@10.10.100.230:1521:orcl";
	private static final String USER_NAME = "oa";
	private static final String USER_PASS = "oa";
	private static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";

	private static final String OUT_DIR = "D:\\\\codeGen";

	@Test
	public void generateCode() {
		String packageName = PACKAGE_NAME;
		// user->UserService,设置为true:user->IUserService
		boolean s = false;
		generateByTables(s, packageName, "MUSER");
	}

	private void generateByTables(boolean serviceNameStartWithI, String packageName, String... tableNames) {
		GlobalConfig config = new GlobalConfig();
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.ORACLE).setUrl(DB_URL).setUsername(USER_NAME).setPassword(USER_PASS)
				.setDriverName(DRIVER_NAME);
		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setCapitalMode(true).setEntityLombokModel(true).setNaming(NamingStrategy.underline_to_camel)
				.setInclude(tableNames);

		config.setActiveRecord(true).setAuthor("XXX").setOutputDir(OUT_DIR).setFileOverride(true);
		if (!serviceNameStartWithI) {
			config.setServiceName("%sService");
		}
		AutoGenerator auto = new AutoGenerator();
		auto.setGlobalConfig(config).setDataSource(dataSourceConfig).setStrategy(strategyConfig)
				.setPackageInfo(
						new PackageConfig().setParent(packageName).setController("controller").setEntity("entity"))
				.execute();
	}

	public void generateByTables(String packageName, String... tableNames) {
		generateByTables(true, packageName, tableNames);
	}
}
