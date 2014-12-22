* もっと賢いやり方があるかも．

## JSPで改行の抑制 (JSP)
    <%@ page trimDirectiveWhitespaces="true"%>

## web.xmlから初期値の取得
web.xml側

    <webapp>
    …
      <context-param>
        <param-name>mailAddress</param-name>
        <param-value>mail@address</param-value>
      </context-param>

#### JSPの場合 (JSP)

    <%= application.getInitParameter("mailAddress") %>

### Servletの場合 (Servlet)
    ServletContext ctx = ((HttpServlet)this).getServletContext();
    // ServletContext ctx = ((HttpServletRequest)request).getServletContext();
    String buildNumber = ctx.getInitParameter("buildNumber");

## ファイルパスの取得 (Servlet)
'WEB-INF/classes'からの相対パス

    URL url = ((HttpServlet)this).getClass().getClassLoader().getResource("../text.txt");
    Path path = Paths.get(url.toURI());

### ファイル内容を文字列として取得
    byte[] fileContentBytes = Files.readAllBytes(path);
    String fileContent = new String(fileContentBytes, StandardCharsets.UTF_8);

## buildの日時をpropertyとして取得 (pom)
    <project>
    …
      <properties>
        <maven.build.timestamp.format>yyyyMMdd_HHmm</maven.build.timestamp.format>
        <buildNumber>${maven.build.timestamp}</buildNumber>
      </properties>

## デフォルトのプロファイルの指定 (pom)
`<activeByDefault>true</activeByDefault>`

    <project>
    …
      <profiles>
        <profile>
          <id>main</id>
          <activation>
            <activeByDefault>true</activeByDefault>
          </activation>
    …
        </profile>
    …
      <profiles>

## propertiesファイルの指定 (pom)
`<filter>main.properties</filter>`

    <project>
    …
      <profiles>
        <profile>
          <id>main</id>
    …
          <build>
            <filters>
              <filter>main.properties</filter>
            </filters>
    …
          </build>
        </profile>
    …
      <profiles>

## buildディレクトリ名(default: target)の指定 (pom)
`<directory>target-dist</directory>`

    <project>
    …
      <profiles>
        <profile>
          <id>dist</id>
    …
          <build>
            <directory>target-dist</directory>
    …
          </build>
        </profile>
    …
      <profiles>

## buildディレクトリ内の出力名(default: ${artifactId}-${version})の指定 (pom)
    <project>
    …
	<build>
		<finalName>ServletBeanJSP</finalName>
    …
	</build>

## 任意ファイルのフィルタリングの指定 (pom)
通常通りweb.xmlが複写された後にfilteringされたもので上書きされる

    <project>
    …
	<build>
    …
		<plugins>
    …
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<configuration>
					<webResources>
						<resource>
							<directory>src/main/webapp/WEB-INF</directory>
							<filtering>true</filtering>
							<includes>
								<include>web.xml</include>
							</includes>
							<targetPath>WEB-INF</targetPath>
						</resource>
					</webResources>
				</configuration>
			</plugin>
    …
		</plugins>
    …
	</build>

## 出力に依存ライブラリを含めない(実行環境で提供されるため) (pom)
`<scope>provided</scope>`

    <project>
    …
      <dependencies>
    …
        <dependency>
    …
          <scope>provided</scope>
    …
       </dependency>
    …
	</dependencies>