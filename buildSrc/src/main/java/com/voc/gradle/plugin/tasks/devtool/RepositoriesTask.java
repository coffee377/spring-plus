package com.voc.gradle.plugin.tasks.devtool;

import com.voc.gradle.plugin.aliyun.AliYunMavenExtension;
import com.voc.gradle.plugin.tasks.BaseTask;
import com.voc.gradle.plugin.util.StringUtils;
import org.gradle.api.Project;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.artifacts.repositories.ArtifactRepository;
import org.gradle.api.artifacts.repositories.MavenArtifactRepository;
import org.gradle.api.tasks.TaskAction;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.Nullable;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/11 14:13
 */
public class RepositoriesTask extends BaseTask {

    private final List<ArtifactRepository> repositories;

    public RepositoriesTask() {
        this.repositories = new LinkedList<>();
    }

    @TaskAction
    public void repositoriesAction() {
        getProject().getLogger().warn(">>>>>>>>>>>>>>>>>>>>> RepositoriesTask start");
        List<ArtifactRepository> list = new ArrayList<>(this.repositories);
        list.forEach(this::add);
        AliYunMavenExtension byType = getProject().getExtensions().getByType(AliYunMavenExtension.class);
        getProject().getLogger().warn(">>>>>>>>>>>>>>>>>>>>> RepositoriesTask end");
    }

    /**
     * 添加 maven 仓库
     *
     * @param repositoryUrl 仓库地址
     * @param username      用户名
     * @param password      密码
     */
    public void addMavenRepository(@NonNull Object repositoryUrl, @Nullable String username, @Nullable String password) {
        Optional.ofNullable(repositoryUrl).ifPresent(url -> {
            if (StringUtils.isNotEmpty((String) url)) {
                RepositoryHandler repositoryHandler = getProject().getRepositories();
                MavenArtifactRepository maven = repositoryHandler.maven(repository -> {
                    repository.setUrl(url);
                    repository.credentials(credentials -> {
                        if (StringUtils.isNotEmpty(username)) {
                            credentials.setUsername(username);
                        }
                        if (StringUtils.isNotEmpty(password)) {
                            credentials.setPassword(password);
                        }
                    });
                });
                this.repositories.add(maven);
            }

        });
    }

    /**
     * 添加无需认证的 maven 仓库
     *
     * @param url 仓库地址
     */
    public void addMavenRepository(Object url) {
        addMavenRepository(url, null, null);
    }

    /**
     * 添加本地仓库地址
     */
    public void addMavenLocal(String localMavenRepository) {
        Project project = getProject();
        MavenArtifactRepository mavenLocal = project.getRepositories().mavenLocal();
        if (StringUtils.isNotEmpty(localMavenRepository)) {
            File file = new File(localMavenRepository);
            URI uri = file.toURI();
            if (file.exists() && !mavenLocal.getUrl().equals(uri)) {
                mavenLocal.setUrl(uri);
            }
        }
        this.repositories.add(mavenLocal);
    }

    /**
     * 添加中央仓库地址
     */
    public void addMavenCentral() {
        this.repositories.add(getProject().getRepositories().mavenCentral());
    }

    protected void add(ArtifactRepository repository) {
        Optional.ofNullable(repository).ifPresent(rep -> getProject().getRepositories().add(rep));
    }

}
