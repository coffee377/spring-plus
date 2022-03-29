plugins {
  id("com.voc.app.info")
}

dependencyManagement {
  val authorizationServerVersion = ext.get("spring.security.oauth2.authorization.server.version")
  dependencies {
    dependency("org.springframework.security:spring-security-oauth2-authorization-server:$authorizationServerVersion")
  }
}

dependencies {
  api(project(":${parent!!.name}:${parent!!.name}-core"))
  api("org.springframework.boot:spring-boot-starter-security")

  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  implementation("mysql:mysql-connector-java")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")

  /* 认证服务器 */
  implementation("org.springframework.security:spring-security-oauth2-authorization-server")
  implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

  testImplementation("org.springframework.security:spring-security-test")
  testImplementation("org.springframework.boot:spring-boot-starter-cache")

  compileOnly("javax.servlet:javax.servlet-api")
}

