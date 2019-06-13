package com.xhx.mybatisplustest.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

/**
 * 
 * @date 2019年6月12日
 * @author lihui
 */
@Configuration
//@MapperScan("com.mybatistest.mapper*")
public class MybatisPlusConfig {

	/**
	 * mybatis-plus 分页插件
	 * 
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor p = new PaginationInterceptor();

		/**
		 * [测试多租户]SQL 解析处理器<br>
		 * 这里固定写成住户 1 实际情况可以从cookie读取，因此数据看不到【*】这条记录（注意观察SQL）
		 */
		/*
		 * List<ISqlParser> sqlParseList = new ArrayList<>(); TenantSqlParser
		 * tenantSqlParser = new TenantSqlParser(); tenantSqlParser.setTenantHandler(new
		 * TenantHandler() {
		 * 
		 * @Override public String getTenantIdColumn() { return "tenant_id"; }
		 * 
		 * @Override public Expression getTenantId() { // TODO Auto-generated method
		 * stub return new LongValue(1L); }
		 * 
		 * @Override public boolean doTableFilter(String tableName) { // 这里可以判断是否过滤 /*
		 * if("user".equals(tableName)){ return true; } / return false; } });
		 * sqlParseList.add(tenantSqlParser); p.setSqlParserList(sqlParseList);
		 */

		// 自定义过滤
		/*
		 * p.setSqlParserFilter(new ISqlParserFilter() {
		 * 
		 * @Override public boolean doFilter(MetaObject metaObject) { // PluginUtils.
		 * 
		 * return false; } });
		 */
		return p;
	}

	/**
	 * 与上面@mapperscan注解作用一样
	 * 
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.mybatistest.mapper*");
		return mapperScannerConfigurer;
	}

	/**
	 * key生成器
	 * 
	 * @return
	 */
	@Bean
	public OracleKeyGenerator oracleKeyGenerator() {
		return new OracleKeyGenerator();
	}

	/**
	 * 性能分析拦截器，不建议生产使用
	 * 
	 * @return
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		return new PerformanceInterceptor();
	}

	/**
	 * 执行分析插件 (SQL 执行分析拦截器【 目前只支持 MYSQL-5.6.3 以上版本 】，作用是分析 处理 DELETE UPDATE 语句，
	 * 防止小白或者恶意 delete update 全表操作)<br>
	 * 注意！该插件只用于开发环境，不建议生产环境使用。。。<br>
	 * 会导致重复获取Sequence
	 * 
	 * @return
	 */
	/*
	 * @Bean public SqlExplainInterceptor explainInterceptor() {
	 * SqlExplainInterceptor inter = new SqlExplainInterceptor(); Properties prop =
	 * new Properties(); prop.put("stopProceed", false); inter.setProperties(prop);
	 * return inter; }
	 */

	/**
	 * 公共补全
	 * 
	 * @return
	 */
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new MetaObjectHandlerConfig();
	}
}