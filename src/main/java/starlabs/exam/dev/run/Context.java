package starlabs.exam.dev.run;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import starlabs.exam.dev.config.Config;
import starlabs.exam.dev.repository.Repositorio;

public class Context {
	private static Logger LOG = Logger.getLogger(Context.class.getName());
	private static Repositorio repositorio;
	
	public static void main(String[] args) {
		try {
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
