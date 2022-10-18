# Attribute 保存作用域

1. `request` 作用域
    1. 存入
        
        `request.setAttribute("name",new Object());`
        
    2. 取出
        
        `object obj = request.getAttribute("name");`
        
    3. 作用范围
        - 如果是同一个 `request` 对象，那么属性值不变。也就是说每访问一次，不论是否之前访问过，都会生成一个新的 `request` 对象。
        - 如果我们使用 `request.getRequestDispatcher("demo2").forward(request,response);` 对请求进行服务器内转发，因为传递了 `request` 的引用，所以在另一个 `servlet` 中也能访问到 `request` 中的属性。
        - 使用 `response.sendRedirect("demo2");` 重定向的话，就无法保存 `request` 中的数据
2. `session` 作用域
    1. 存入
        
        ```java
        HttpSession session = request.getSession();
        session.setAttribute("name",new Object());
        ```
        
    2. 取出
        
        ```java
        HttpSession session = request.getSession();
        session.getAttribute("name");
        ```
        
    3. 作用范围
        
        只要 `session` 没有过期，或者用户没换浏览器，就能一直保持。
        
3. `application` 作用域
    1. 存入
        
        ```java
        //ServletContext ：servlet上下文
        ServletContext application = request.getServletContext();
        application.setAttribute("name",new Object());
        ```
        
    2. 取出
        
        ```java
        //ServletContext ：servlet上下文
        ServletContext application = request.getServletContext();
        application.getAttribute("name");
        ```
        
    3. 作用范围
        
        只要 `tomcat` 没被关掉，就能访问到