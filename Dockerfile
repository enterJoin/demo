#获取base image
FROM adoptopenjdk/openjdk8:ubi
#类似于执行 linux指令
RUN mkdir /opt/app  
#类似于linux copy指令
COPY ss-community-1.0.jar /opt/app/
#对外端口
EXPOSE 80
EXPOSE 3306
#执行命令 java -jar /opt/app/ss-community-1.0.jar
CMD ["java", "-jar", "/opt/app/ss-community-1.0.jar"]
