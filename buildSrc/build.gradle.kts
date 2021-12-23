import java.io.FileInputStream
import java.util.*
import java.util.function.Consumer

val props = Props();
val springBootVerion = props.get("org.springframework.boot.version", "2.3.5.RELEASE")
val dependencyManagementVersion = props.get("io.spring.dependency-management.version", "1.0.10.RELEASE")
val pluginVersion = props.get("version", "0.0.1")

plugins {
  `java-library`
  `java-gradle-plugin`
  `kotlin-dsl`
}

group = "com.voc"
version = pluginVersion

configurations {

  val annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
  val testAnnotationProcessor = configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
  val compileOnly = configurations.getByName(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME)
  val runtimeOnly = configurations.getByName(JavaPlugin.RUNTIME_ONLY_CONFIGURATION_NAME)
  val runtimeClasspath = configurations.getByName(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME)
  val testImplementation = configurations.getByName(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME)

  testAnnotationProcessor {
    extendsFrom(annotationProcessor)
  }

  compileOnly {
    extendsFrom(annotationProcessor)
  }

  testCompileOnly {
    extendsFrom(compileOnly, testAnnotationProcessor)
  }

  runtimeClasspath {
    extendsFrom(annotationProcessor)
  }

  runtimeOnly {
//    extendsFrom(annotationProcessor)
  }

  testRuntimeOnly {
    extendsFrom(runtimeOnly)
  }

}

repositories {
  /* Local */
  mavenLocal()
  /* Ali Yun central仓和jcenter仓的聚合仓 */
  maven {
    url = uri("https://maven.aliyun.com/repository/public/")
  }
}

dependencies {

  /* Spring Boot 版本 */
  if (!springBootVerion.isNullOrEmpty()) {
    implementation("org.springframework.boot:spring-boot-gradle-plugin:$springBootVerion")
  }
  if (!dependencyManagementVersion.isNullOrEmpty()) {
    implementation("io.spring.gradle:dependency-management-plugin:$dependencyManagementVersion")
  }

  annotationProcessor("org.projectlombok:lombok:1.18.20")

  /*  Use JUnit Jupiter for testing. */
  testImplementation("org.junit.jupiter:junit-jupiter-engine:5.5.2")
}

tasks {
  test {
    useJUnitPlatform()
  }

  withType<Jar> {
    archiveBaseName.set("gradle-plugin")
//    dependsOn("lib")
    /* 重复文件策略，排除 */
    setDuplicatesStrategy(DuplicatesStrategy.EXCLUDE)
  }

  withType<JavaCompile> {
  }

  register("lib", Copy::class.java) {
    group = "build"
    from(configurations.runtimeClasspath)
    into("${buildDir.path}/lib")
  }
}

gradlePlugin {
  plugins {
    create("devtools") {
      id = "com.voc.devtools"
      implementationClass = "com.voc.gradle.plugin.DevToolsPlugin"
    }
    create("app-info") {
      id = "com.voc.app.info"
      implementationClass = "com.voc.gradle.plugin.AppInfoPlugin"
    }
  }

}


class Props {
  private val properties: Properties

  init {
    this.properties = Properties()
    val root = rootProject.projectDir.parentFile.absolutePath
    val file = file("${root}/gradle.properties")
    this.properties.load(FileInputStream(file))
  }

  operator fun get(name: String, defaultValue: String): String {
    val value = properties.getProperty(name)
    if (!value.isNullOrEmpty()) {
      return value
    }
    return defaultValue
  }
}
