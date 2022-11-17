plugins {
  `java-platform`
  `maven-publish`
}

javaPlatform {
  allowDependencies()
}

val common = "common"
val starter = "spring-plus-starter"

dependencies {
  /* Spring Boot 依赖 */
  api(platform(libs.spring.boot.dependencies))
  api(platform(libs.spring.security.bom))

  constraints {
    /* 授权服务器依赖 */
    api(libs.spring.authorization.server)
    api(libs.bundles.dingtalk)
    api("com.baomidou:mybatis-plus-boot-starter:3.5.2")

    api("org.mapstruct:mapstruct:1.5.0.RC1")
    api("org.mapstruct:mapstruct-processor:1.5.0.RC1")
    api("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    api("org.gitlab4j:gitlab4j-api:5.0.1")

//    api(toolsLibs.mapstruct)
//    api(toolsLibs.mapstruct.processor)
//    api(toolsLibs.lombok.mapstruct)

    api("org.springdoc:springdoc-openapi-ui:1.6.11")

    /* projects */
    api(project(":$starter:$starter-result"))
  }
}

publishing {
  repositories {
    maven {
      name = "local"
      url = uri("${project.rootProject.buildDir}/publications/repos")
    }
    maven {
      name = "nexus3"
      val versionType = if (project.version.toString().endsWith("", true)) "snapshots" else "releases"
      url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-${versionType}/")
      isAllowInsecureProtocol = true
      credentials {
        username = System.getenv("DEV_OPTS_JQ_USERNAME")
        password = System.getenv("DEV_OPTS_JQ_PASSWORD")
      }
    }
  }
  publications {
    create<MavenPublication>("Bom") {
      from(components["javaPlatform"])
    }
  }
}
