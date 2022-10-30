dependencies {
  implementation(project(":common:common-api"))
  api(project(":spring-boot-starter:spring-boot-starter-result"))
  implementation("org.springframework.security:spring-security-crypto")
  implementation("org.springframework.security:spring-security-oauth2-core")

  compileOnly("org.springframework.data:spring-data-commons")
  compileOnly("org.springframework.boot:spring-boot-starter-security")
  compileOnly("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
  compileOnly(libs.spring.authorization.server)
//  compileOnly(files("${rootDir}/libs/spring-security-oauth2-authorization-server-0.3.2.jar"))
}

