# Servlet API

1. `servlet` 的初始化， `HttpServlet` 自带两个实现
    1. 带参数的
        
        ```java
        public void init(ServletConfig config) throws ServletException {
        	this.config = config;
        	this.init();
        }
        ```
        
    2. 不带参数
        
        ```java
        public void init() throws ServletException {}
        ```
        
    3. 重写 `init()` 方法可以方便我们初始化
        - `web.xml` 方式
            
            ```xml
            		<servlet>
                    <servlet-name>TestServlet</servlet-name>
                    <servlet-class>com.servletdemo.myspringmvc.TestServlet</servlet-class>
                    <init-param>
            						<!--初始化参数，可以配置多个-->
                        <param-name>key</param-name>
                        <param-value>word</param-value>
                    </init-param>
                </servlet>
                <servlet-mapping>
                    <servlet-name>TestServlet</servlet-name>
                    <url-pattern>/test</url-pattern>
                </servlet-mapping>
            ```
            
            ```java
            public void init() throws ServletException {
                    ServletConfig servletConfig = getServletConfig();
                    String initVal = servletConfig.getInitParameter("key");
                }
            ```
            
        - 注解方式
            
            ```java
            @WebServlet(urlPatterns = {"/test"},initParams = {
                    @WebInitParam(name="key",value = "value"),
                    @WebInitParam(name="user",value = "Suxton")
            })
            public class TestServlet extends HttpServlet {
            }
            ```
            
2. `servletContext`
    1. 任何人都可以访问 `servletContext` 包括 `request` `session`
    2. 配置方法：
        - `XML`
            
            ```xml
            <context-param>
                    <param-name>loc</param-name>
                    <param-value>xiaan</param-value>
            </context-param>
            ```
            
    3. 调用方法：
        - `Java`
            
            ```java
            @WebServlet(urlPatterns = {"/test"}, initParams = {
                    @WebInitParam(name = "key", value = "value"),
                    @WebInitParam(name = "user", value = "Suxton")
            })
            public class TestServlet extends HttpServlet {
                @Override
            
                public void init() throws ServletException {
                    ServletConfig servletConfig = getServletConfig();
                    String initVal = servletConfig.getInitParameter("key");
                    System.out.println("initParameter = " + initVal);
            
                    ServletContext servletContext = getServletContext();
                    String servletContextInitParameter = servletContext.getInitParameter("loc");
                    System.out.println("ContexParameter = " + servletContextInitParameter);
                }
            
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                    ServletContext servletContext = req.getServletContext();
                    String initParameter = servletContext.getInitParameter("loc");
                    System.out.println("requset : " + initParameter);
                    HttpSession session = req.getSession();
                    initParameter = session.getServletContext().getInitParameter("loc");
                    System.out.println("session : " + initParameter);
                }
            }
            ```
            
        - 结果，由于第一次访问才会实例化 `Servlet` 所以一起出来
            
            ```
            initParameter = value
            ContexParameter = xiaan
            requset : xiaan
            session : xiaan
            ```
            
3. d