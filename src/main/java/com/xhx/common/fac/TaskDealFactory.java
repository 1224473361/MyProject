package com.xhx.common.fac;

/**
 * 工厂模式，可动态获取指定文件夹中的所有实现类
 * 
 * @date 2019年6月3日
 * @author lihui
 */
//@Component
public class TaskDealFactory {

	/*private Logger log = LoggerFactory.getLogger(getClass());

	private String implClassPath = "com/emotte/card/emerp/service/taskdeal/impl";

	private TaskDealService[] inters = null;*/

	/**
	 * 从容器获取所有实现类
	 */
	/*public void inti() {
		if (null != this.inters) {
			return;
		}
		try {
			List<String> rlist = scanClasses(implClassPath);
			if (rlist.isEmpty()) {
				log.info("{}路径无被@Component注解的类", implClassPath);
				return;
			}
			this.inters = new TaskDealService[rlist.size()];
			for (int i = 0; i < rlist.size(); i++) {
				String str = rlist.get(i);
				this.inters[i] = (TaskDealService) Context.getBean(str);
				log.info("加载实现类：{}", str);
			}
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage(), e);
		}
	}*/

	/**
	 * 动态获取执行实例
	 * 
	 * @param type
	 * @return
	 */
	/*public TaskDealService getDealImpl(Long type) {
		inti();
		if (null == this.inters) {
			return null;
		}
		for (TaskDealService inter : this.inters) {
			if (inter.getType().equals(type)) {
				return inter;
			}
		}
		return null;
	}*/

	/**
	 * 扫描指定路径下面的class
	 * 
	 * @param dir
	 * @return
	 * @throws ClassNotFoundException
	 */
	/*private List<String> scanClasses(String dir) throws ClassNotFoundException {
		String rootPath = SystemHelper.getCurrentPath(0) + com.emotte.boot.Bootstrap.CLASSPATH + File.separator + dir;
		String scanRegex = dir.replaceAll("/", ".");
		List<String> rlist = new ArrayList<>(16);
		File libFile = new File(rootPath + File.separator);
		File[] flist = libFile.listFiles();
		for (File str : flist) {
			String toName = str.getName();
			String cla = scanRegex + "." + toName.substring(0, toName.indexOf('.'));
			Class<?> clazz = Class.forName(cla, true, ClassLoader.getSystemClassLoader());
			Component componentAnno = clazz.getAnnotation(Component.class);
			if (null == componentAnno) {
				continue;
			}
			String name = componentAnno.value();
			String version = componentAnno.version();
			if (StringUtils.isBlank(name)) {
				name = clazz.getSimpleName();
				name = name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
			}
			rlist.add(clazz.getPackage().getName() + "." + name + "-" + version);
		}
		return rlist;
	}*/
	
}
