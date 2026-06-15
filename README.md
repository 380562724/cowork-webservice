# cowork-webservice

Spring Boot 4.1.0 (Java 21) 多模块 Maven 项目。Web 流量与后台作业分两个可执行 jar 部署，实现资源伸缩与故障域隔离。

## 模块结构

| 模块 | 类型 | 启动器 | 作用 |
| --- | --- | --- | --- |
| `cowork-webservice-component` | 普通 jar | 无 Spring Boot starter | api 与 runner 共享的领域代码库 |
| `cowork-webservice-api` | 可执行 jar | `spring-boot-starter-webflux` | 响应式 HTTP 入口，监听 8080 |
| `cowork-webservice-runner` | 可执行 jar | `spring-boot-starter`（无 web） | 后台作业入口（定时任务、消费者等） |

依赖方向：`api → component`、`runner → component`，两入口互不依赖。

## 构建与测试

需要 JDK 21。

```bash
# 全量构建（在仓库根）
./mvnw clean package

# 仅跑测试
./mvnw test

# 单独构建一个模块（-am 也会构建它的依赖）
./mvnw -pl cowork-webservice-api -am clean package
```

构建产物：

- `cowork-webservice-api/target/cowork-webservice-api-0.0.1-SNAPSHOT.jar`
- `cowork-webservice-runner/target/cowork-webservice-runner-0.0.1-SNAPSHOT.jar`

## 运行

```bash
# api
java -jar cowork-webservice-api/target/cowork-webservice-api-0.0.1-SNAPSHOT.jar

# runner
java -jar cowork-webservice-runner/target/cowork-webservice-runner-0.0.1-SNAPSHOT.jar
```

## 扩展点

### 在 `component` 里加共享代码

约定放在 `com.quchenyang.coworkwebservice.component` 及其子包下（`service/`、`entity/`、`util/` 等）。`component` 不依赖任何 Spring Boot starter，写出来的 `@Service` / `@Component` 由 api 或 runner 加载。

### 在 `api` 里加 controller

- 包路径：`com.quchenyang.coworkwebservice.api`
- 响应式：返回值用 `Mono<T>` / `Flux<T>`，Service / Repository 层应保持响应式链路
- 全局错误处理用 `@RestControllerAdvice`

### 在 `runner` 里加 `xxx_service`

- 包路径：`com.quchenyang.coworkwebservice.runner.service`
- 形式：定时任务（`@Scheduled`）、消费者（`@KafkaListener` 等，待引入中间件后启用）、手动作业（`ApplicationRunner` / `CommandLineRunner`）
- 在 `RunnerApplication.main` 里显式 `getBean` 或让 `@PostConstruct` 自启，遵循"显式列 service"风格

### 引入中间件

- 数据库、Redis、Kafka 等按需加 `spring-boot-starter-*` 到对应子模块
- 公共配置（如连接串）放在各模块自己的 `application.yaml`
