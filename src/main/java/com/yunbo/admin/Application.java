package com.yunbo.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication //스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정, 항상 프로젝트 최상단에 위치
public class Application{
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
        //SpringApplication.run으로 인해 내장 WAS(Web Application Server)를 실행
        //내장WAS - 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것
        //-> 이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프링 부트로 만들어진 Jar 파일로 실행하면 됨
        //스프링 부트에서 내장 WAS 사용 권장(언제 어디서나 같은 환경에서 스프링부트를 배포할 수 있기 때문에)
    }
}
