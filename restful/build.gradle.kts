import com.voc.gradle.plugin.core.DevType

group = "com.voc.restful"
description = "Spring Boot Plus Restful Dependencies"

plugins {
  id("com.voc.devtools")
}

dependencyManagement {
  dependencies {
    dependencySet("$group:$version") {
      entry("${name}-core")
      entry("${name}-security")
    }
  }
}

devtools {
  type(DevType.BOM)
}

subprojects {
  group = "com.voc.boot.restful"

  apply(plugin = "com.voc.boot")

  dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
      exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
  }

}


