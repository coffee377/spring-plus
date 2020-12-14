package com.voc.gradle.plugin;

import com.voc.gradle.plugin.extension.DevToolExtension;
import com.voc.gradle.plugin.tasks.devtool.RepositoriesTask;
import com.voc.gradle.plugin.util.ExtraPropsUtils;
import lombok.Getter;
import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.file.DuplicatesStrategy;
import org.gradle.api.plugins.JavaLibraryPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;
import org.gradle.api.tasks.Delete;
import org.gradle.ide.visualstudio.plugins.VisualStudioPlugin;
import org.gradle.ide.xcode.plugins.XcodePlugin;
import org.gradle.jvm.tasks.Jar;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.idea.IdeaPlugin;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/23 14:27
 */
@Getter
public class DevToolsPlugin extends AbstractPlugin {

    public static final String DEV_TOOL_EXTENSION_NAME = "devtools";
    public final static String REPOSITORIES_TASK_NAME = "buildRepositories";
    public final static String DEPENDENCIES_TASK_NAME = "buildDependencies";

    @Override
    public void onApply(Project project) {
        /* 1. 应用开发工具插件，默认使用 idea */
        String ide = ExtraPropsUtils.getStringValue(project, "ide", "idea");
        PluginContainer plugins = project.getPlugins();
        switch (ide) {
            case "ECLIPSE":
                plugins.apply(EclipsePlugin.class);
                break;
            case "VISUAL_STUDIO":
                plugins.apply(VisualStudioPlugin.class);
                break;
            case "XCODE":
                plugins.apply(XcodePlugin.class);
                break;
            case "IDEA":
            default:
                plugins.apply(IdeaPlugin.class);
        }

        /* 2.应用 JavaLibrary 插件 */
        plugins.apply(JavaLibraryPlugin.class);

        /* 3.应用 MavenPublish 插件 */
        plugins.apply(MavenPublishPlugin.class);

//        boolean booleanValue = ExtraPropsUtils.getBooleanValue(project, ExtraProps.ENABLE_KOTLIN);
//
//        if (booleanValue) {
////            project.getPlugins().apply("org.jetbrains.kotlin.jvm");
//        }
//
////        project.afterEvaluate(p -> {
////            /* 3.应用 kotlin 插件 */
////            DevToolExtension ext = p.getExtensions().getByType(DevToolExtension.class);
//////            if (ext.isKotlin()) {
//////                p.getPlugins().apply("org.jetbrains.kotlin.jvm");
//////            }
////            /* 4.应用 groovy 插件 */
////            if (ext.isGroovy()) {
////                p.getPlugins().apply(GroovyPlugin.class);
////            }
////        });
//    }

//    public void registerTask(Project project) {
//        /* 注册任务 */
//        project.getTasks().register(REPOSITORIES_TASK_NAME, RepositoriesTask.class, task -> {
//            task.setDescription("Automatically add repositories.");
//            task.setGroup(DEVTOOLS_GROUP_NAME);
//            task.dependsOn(JavaPlugin.CLASSES_TASK_NAME);
//        });
//
//        project.getTasks().register(DEPENDENCIES_TASK_NAME, DependenciesTask.class, task -> {
//            task.setDescription("Automatically add dependencies.");
//            task.setGroup(DEVTOOLS_GROUP_NAME);
//            task.dependsOn(REPOSITORIES_TASK_NAME);
//        });
//    }
//
////    @Override
////    public String getExtensionName() {
////        return DEV_TOOL_EXTENSION_NAME;
////    }
//
////
////    @Override
////    public void configureBuildscript(Action<ScriptHandler> buildScript) {
//////        super.configureBuildscript(buildScript);
//////        RepositoryHandler repositories = buildScript.getRepositories();
//////        /* 本地仓库 */
//////        repositories.add(repositories.mavenLocal());
//////        /* 中央仓库 */
//////        repositories.add(repositories.mavenCentral());
//////
//////        DependencyHandler dependencies = buildScript.getDependencies();
////
//////        DevToolExtension ext = project.getExtensions().getByType(DevToolExtension.class);
////        /* classpath "org.jetbrains.kotlin:kotlin-gradle-javaPluginExtension:$kotlin_version" */
//////        boolean booleanValue = ExtraPropsUtils.getBooleanValue(project, ExtraProps.ENABLE_KOTLIN);
//////        if (booleanValue) {
//////            String version = ExtraPropsUtils.getStringValue(project, ExtraProps.KOTLIN_VERSION);
//////            dependencies.add(ScriptHandler.CLASSPATH_CONFIGURATION, DependencyUtils.of(Dependency.KOTLIN_GRADLE_PLUGIN, version));
//////        }
////    }
//
//    public void buildscript(Project project) {
//        ScriptHandler buildScript = project.getBuildscript();
//
//    }
//
//

//
//    public void configureDependencies(Project project) {
//        TaskContainer tasks = project.getTasks();
//        tasks.withType(DependenciesTask.class, task -> {
//            String version = ExtraPropsUtils.getStringValue(project, ExtraProps.LOMBOK_VERSION);
//            task.add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, DependencyUtils.of(Dependency.LOMBOK, version));
//            task.add(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME, "org.junit.jupiter:junit-jupiter-engine:5.5.2");
//        });
//    }
//
//    public void configureSourceSets(Project project) {
//
//    }
//
//    public void configureJavaCompile(Project project) {
//        /* Java 编译 */
//        project.getTasks().withType(JavaCompile.class, javaCompile -> {
////                    CompileExtension compile = extension.getCompile();
////                    String javaVersion = Optional.ofNullable(JavaVersion.toVersion(compile.getJavaVersion())).orElse(JavaVersion.current()).toString();
////                    javaCompile.setSourceCompatibility(javaVersion);
////                    javaCompile.setTargetCompatibility(javaVersion);
//
//                    CompileOptions options = javaCompile.getOptions();
//                    /*在单独的守护程序进程中启用编译，默认false*/
////                    options.setFork(compile.isFork());
////                    options.setVerbose(compile.isVerbose());
//                    /*编译JAVA文件时采用UTF-8*/
////                    String encoding = StringUtils.isEmpty(compile.getEncoding()) ? "UTF-8" : compile.getEncoding();
////                    options.setEncoding(encoding);
//                    /*增量编译，默认true*/
////                    options.setIncremental(compile.isIncremental());
//                }
//        );
//    }
//
//    public void configureKotlinCompile(Project project) {
//        /* Kotlin 编译 */
////        project.getTasks().withType(KotlinCompile.class, kotlinCompile ->
////                Optional.ofNullable(extension).ifPresent(conf -> {
////                    CompileExtension compile = extension.getCompile();
////                    /* 是否增量编译 */
////                    kotlinCompile.setIncremental(compile.isIncremental());
////                    KotlinJvmOptions kotlinOptions = kotlinCompile.getKotlinOptions();
////                    String jvmVersion = Optional.ofNullable(conf.getKotlinVersion()).orElse(JavaVersion.current().toString());
////                    /* 生成的 JVM 字节码的目标版本（1.6、 1.8、 9、 10、 11 或 12），默认为 1.6 */
////                    kotlinOptions.setJvmTarget(jvmVersion);
////                    /* 启用详细日志输出 */
////                    kotlinOptions.setVerbose(compile.isVerbose());
////                }));
//    }
//

//
//    public void configureWar(Project project) {
//
//    }
//
//    public void configureZip(Project project) {
//    }
//
    }

    private void applyIDEPlugin(String ide) {

    }

    /**
     * 配置打包策略
     *
     * @param project Project
     */
    private void configureJar(Project project) {
        project.getTasks().withType(Jar.class, jar -> {
            /* 重复文件策略，排除 */
            jar.setDuplicatesStrategy(DuplicatesStrategy.EXCLUDE);
            jar.setIncludeEmptyDirs(false);
            /* 排除 JRebel 配置文件 */
            jar.exclude("rebel.xml");
        });
    }

    /**
     * 注解处理器配置添加到编译环境
     *
     * @param project Project
     */
    public void configureAnnotationProcessor(Project project) {
        ConfigurationContainer configurations = project.getConfigurations();
        Configuration compileOnly = configurations.getByName(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME);
        Configuration testCompileOnly = configurations.getByName(JavaPlugin.TEST_COMPILE_ONLY_CONFIGURATION_NAME);
        Configuration annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME);
        compileOnly.extendsFrom(annotationProcessor);
        testCompileOnly.extendsFrom(annotationProcessor);
    }

    /**
     * 清理任务增加删除目录
     *
     * @param project Project
     */
    public void configureClean(Project project) {
        /* 清理任务增加删除目录 */
        project.getTasks().withType(Delete.class, delete -> delete.delete("out"));
    }


    /**
     * 配置默认仓库
     *
     * @param project Project
     */
    public void configureRepositories(Project project) {
        DevToolExtension extension = project.getExtensions().getByType(DevToolExtension.class);
        project.afterEvaluate(p -> {
            this.configureRepositories(new Action<RepositoryHandler>() {
                @Override
                public void execute(RepositoryHandler repositoryHandler) {
//                    repositoryHandler.
                }
            });
        });
//        this.configureRepositories();


        project.getTasks().withType(RepositoriesTask.class, task -> {
//            task.addMavenLocal();
            task.addMavenCentral();
        });
    }
}
