# 进入MVC

M(model)  V(view) C(Controller)

架构图：

![2.png](2.png)

1. 只保留一个 `Servlet` 用它拦截所有请求，然后讲根据请求的路径调用对应的 `controller` 
    1. 拦截所有请求 `@WebServler("*.do")` 
    2. 在中央调度器 `dispatcherServlet` 中得到访问的地址
        
        ```java
        req.setCharacterEncoding("utf-8");
        String servletPath = req.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        //得到的这个 servletPath 后就可以根据其名称得到对应的 controller 类
        //   /user.do -> user
        servletPath = servletPath.substring(0, lastDotIndex);
        ```
        
    3. 建立 `applicationContext.xml` 文件，将访问地址和 `controller` 类的引用联系起来。
        - `applicationContext.xml`
            
            ```xml
            <?xml version="1.0" encoding="utf-8" ?>
            <beans>
                <bean id="user" class="com.servletdemo.controllers.UserController"/>
            </beans>
            ```
            
        - 在初始化的时候，读入 `xml` 文件，使用 `DOM` 技术建立路径和 `controller` 之间的映射
            
            ```java
            public void init() {
                    super.init();
                    InputStream inputStream = DispatcherServlet.class.getResourceAsStream("/applicationContext.xml");
                    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder documentBuilder;
                    try {
                        documentBuilder = documentBuilderFactory.newDocumentBuilder();
                        Document document = documentBuilder.parse(inputStream);
                        NodeList beanNodeList = document.getElementsByTagName("bean");
            
                        for (int i = 0; i < beanNodeList.getLength(); i++) {
                            Node beanNode = beanNodeList.item(i);
                            if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element beanElement = (Element) beanNode;
                                String beanId = beanElement.getAttribute("id");
                                String className = beanElement.getAttribute("class");
                                Constructor<?> constructor = Class.forName(className).getConstructor();
                                Object beanObj = constructor.newInstance();
            //                    Object beanObj = Class.forName(className).newInstance();
                                beanMap.put(beanId, beanObj);
            
            //                    System.out.println(beanId);
                            }
                        }
            
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            ```
            
    4. 使用反射找到类中对应的方法然后执行
2. 对每个对象操作的方法都存放在 `contoller` 中，将所有接受参数（为了不再传入 `HttpRequest` & `HttpResponse` ）和跳转的任务都交给 `Dispatcher*Servlet*`
    1. 接收参数的时候使用反射，如果需要获取目标方法的参数名，需要在 `Javac` 的参数中添加 `-parameters` 
        - 如果项目使用 `maven` 构建，必须在 `pom.xml` 中加入编译插件并配置编译选项
            
            ```xml
            <plugin>
            		<groupId>org.apache.maven.plugins</groupId>
            		<artifactId>maven-compiler-plugin</artifactId>
            		<version>3.8.1</version>
            		<configuration>
            				<parameters>true</parameters>
            		</configuration>
            </plugin>
            ```
            
    2. 返回的时候均返回字符串，由 `DispatcherServlet` 进行统一响应
3. 增加服务层来承载复杂的业务，将底层的代码进行封装后由 `controller` 调用 `service` 实例中的服务方法。
4. 通过反射进一步降低代码的耦合度，通过配置文件将依赖注入
    - 通过配置文件指定两个组件之间的耦合关系
        
        ```xml
        <bean id="userService" class="com.servletdemo.user.service.impl.UserServiceImpl"/>
            <bean id="user" class="com.servletdemo.user.controllers.UserController">
                <!--    name是一会需要实例类的属性名    ref中的值是某一个 bean的 id-->
                <property name="userService" ref="userService"/>
        </bean>
        ```
        
    - 通过一个类读取 `XML` 中的配置
        
        ```java
        public class ClassPathXmlApplicationContext implements BeanFactory {
            private final Map<String, Object> beanMap = new HashMap<>();
        
            public ClassPathXmlApplicationContext() {
                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/applicationContext.xml");
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder;
                try {
                    documentBuilder = documentBuilderFactory.newDocumentBuilder();
                    Document document = documentBuilder.parse(inputStream);
                    NodeList beanNodeList = document.getElementsByTagName("bean");
        
                    for (int i = 0; i < beanNodeList.getLength(); i++) {
                        Node beanNode = beanNodeList.item(i);
                        if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element beanElement = (Element) beanNode;
                            String beanId = beanElement.getAttribute("id");
                            String className = beanElement.getAttribute("class");
        
                            Constructor<?> constructor = Class.forName(className).getConstructor();
                            Object beanObj = constructor.newInstance();
        //                    JDK-8
        //                    Class beanClass = Class.forName(className);
        //                    beanObj = beanClass.newInstance();
                            beanMap.put(beanId, beanObj);
                        }
                    }
                    //确定bean之间的引用关系
                    for (int i = 0; i < beanNodeList.getLength(); i++) {
                        Node beanNode = beanNodeList.item(i);
                        if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element beanElement = (Element) beanNode;
                            String beanId = beanElement.getAttribute("id");
                            NodeList childNodes = beanElement.getChildNodes();
                            for (int j = 0; j < childNodes.getLength(); j++) {
                                Node childNode = childNodes.item(j);
                                if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                                    String childNodeName = childNode.getNodeName();
                                    if ("property".equals(childNodeName)) {
                                        Element propertyElement = (Element) childNode;
                                        String propertyName = propertyElement.getAttribute("name");
                                        String propertyRef = propertyElement.getAttribute("ref");
        //                              找 ref对应的实例
                                        Object refObj = beanMap.get(propertyRef);
                                        Object beanObj = beanMap.get(beanId);
                                        Field propertyField = beanObj.getClass().getDeclaredField(propertyName);
                                        propertyField.setAccessible(true);
                                        propertyField.set(beanObj, refObj);
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
            @Override
            public Object getBean(String id) {
                return beanMap.get(id);
            }
        }
        ```
        
5. 加入 `filter` 
    
    使用注解 `@WebFilter` 可以指定过滤器拦截的访问地址，请求经过处理后才会传送到 `servlet` 中。注意一定要使用 `doFilter()` 放行。
    
    - 简单的字符集设定 `filter`
        
        ```java
        package com.servletdemo.myspringmvc.filters;
        
        import javax.servlet.*;
        import javax.servlet.annotation.WebFilter;
        import javax.servlet.http.HttpServletRequest;
        import java.io.IOException;
        
        import static com.servletdemo.utils.Tools.isEmpty;
        
        @WebFilter("*.do")
        public class CharacterEncodingFilter implements Filter {
            private String encodingCharacterSet = "UTF-8";
        
            @Override
            public void init(FilterConfig filterConfig) {
                String encoding = filterConfig.getInitParameter("encoding");
                if (isEmpty(encoding)) {
                    encoding = "UTF-8";
                }
                encodingCharacterSet = encoding;
            }
        
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                request.setCharacterEncoding(encodingCharacterSet);
                //放行
                chain.doFilter(request, response);
            }
        }
        ```
        
6. 由于没学 JDBC 和 MySQL 中的事务，先停在51集