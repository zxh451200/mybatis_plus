# 使用官方的Java基础镜像
FROM openjdk:21-jdk

ENV TIMEZONE=Asia/Shanghai

# 设置工作目录
WORKDIR /app

# 复制项目中的jar文件到容器的工作目录
COPY target/mybatis_plus.jar /app/mybatis_plus.jar

# 暴露应用程序使用的端口
EXPOSE 8080

# 运行Java应用程序
#CMD ["java", "-jar", "/app/your-java-project.jar"]

# 入口，java项目的启动命令
ENTRYPOINT java -jar /tmp/app.jar
