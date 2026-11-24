package kr.gsm.minibank.config;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component                                                              //ApplicationContext
public class BeenPrinter implements CommandLineRunner {
        // DI(의존성 주입 = 생성자 주입)
        private final ApplicationContext applicationContext;
        public BeenPrinter(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }
    @Override
                                                            //                          String
        public void run(String... args) throws Exception { //ApplicationContext(Object)[0,0,0,0,0,0,0---]
        System.out.println("-- 등록된 Account 관련 Been 목록");
        Arrays.stream(applicationContext.getBeanDefinitionNames())
                .filter(name->name.toLowerCase().contains("account"))
                .forEach(System.out::println);
    }//BeenPrinter beenprinter = new BeenPrinter(); Spring Container(객체가 생성되는 메모리 관리)
}
