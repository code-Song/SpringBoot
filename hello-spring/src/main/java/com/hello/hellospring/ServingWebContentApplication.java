package com.hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*@SpringBootApplication은 아래의 annotation을 모두 포함하는 편리한 annotation이다
@Configuration : 클래스를 애플리케이션 컨텍스트에 대한 Bean 정의 소스로 태그 지정합니다.
@EnableAutoConfiguration : 클래스 경로 설정 등 다양한 속성 설정을 기반으로 Bean 추가를 지시, DispatcherServlet 설정과 같은 주요 동작 활성화
@ComponetScan : com/hello 패키지에 다른 component, configurations, services를 찾도록 지시
*/
//spring-boot-devtools 라이브러리를 추가하면, html 파일을 컴파일만 해주면 서버 재시작없이 
//View 파일 변경가능.

@SpringBootApplication
public class ServingWebContentApplication {
	public static void main(String[] args) {
		//xml파일 없이 자바로 구성가능!
		SpringApplication.run(ServingWebContentApplication.class, args);
	}
}
