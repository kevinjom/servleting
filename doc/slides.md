title: Introduction to java Servlet
author:
  name: kevinjom
  twitter: _kevinjom
  url: http://kevinjom.github.io
output: servleting.html
theme: jdan/cleaver-retro
controls: false

--

# Introduction to Java Servlet

--

### What we are doing
# WEB

--

### Web server & container

![server](server.jpg)

--

### What is a Servlet

#### A servlet is a Java technology-based Web component, managed by a container, that generates dynamic content.

#### Servlets interact with Web clients via a request/response paradigm implemented by the servlet container.


--

### Servlet Container

#### Servlet containers execute and manage servlets.

> The servlet container is a part of a Web server or application server that
 provides the network services over which requests and responses are sent,
 decodes MIME-based requests, and formats MIME-based responses. A servlet
 container also contains and manages servlets through their lifecycle.

> A servlet container can be built into a host Web server, or installed as an
 add-on component to a Web Server via that server’s native extension API. Servlet
 containers can also be built into or possibly installed into Web-enabled
 application server

--

### Servlet Interface

```java

package javax.servlet;

public interface Servlet {
    public void init(ServletConfig config) throws ServletException;

    public ServletConfig getServletConfig();

    public void **service**(ServletRequest req, ServletResponse res)
	throws ServletException, IOException;

    public String getServletInfo();

    public void destroy();
}

```

--

### HttpServlet

```java
protected void service(HttpServletRequest req, HttpServletResponse resp)

protected void doGet(HttpServletRequest req, HttpServletResponse resp)
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
...
```


--

### Simple Servlet Demo

```java
public class InboxServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("hello sevleting");
    }
}
```

--

### How a java web app looks like
![directory-structure](dir.jpg)

--

### ServletRequest

The request object encapsulates all information from the client request.

You can get these information from request object
- Request URL
- Parameters
- Headers
- Cookies
- Attributes
- Reqeust Body via InputStream
- ...

--

### ServletResponse

The response object encapsulates all information to be returned from the server
to the client.

--

### Servlet Lifecycle

- **Loading and Instantiation** \- locate the class and construct a instance of that
  class
- **Initialization** \- `init` with `ServletConfig` configured in DD, before it can handle any request
- **Request Handling** \- handle each request (in a seperate Thread) and modify response
- **End of Service** \- waiting for all the request handled and cleanup resources
  (`destory`)

--

## Filter

--

## Filter (demo)
(demo)

--

### ServletContext

> The ServletContext interface defines a servlet’s view of the Web application
within which the servlet is running.

each web applicaton only has **ONE** `ServletContext`

--

### Event Listeners
- **ServletContextListener**
- ServletContextAttributeListener
- ServletRequestListener
- ServletRequestListener

--

### ServletContext and Listener Demo
(demo)

--

# Servlet 3.0

--

### Whats new

- **Ease of Development with annotations**
- **Dynamic Registration of Servlets and Filters**
- Asynchronous support
- Pluggability
- Resources in bundled jar files

--

### Drop web.xml, use Annotations

- `@WebServlet` – Define a Servlet
- `@WebFilter` – Define a Filter
- `@WebListener` – Define a Listener
- `@WebInitParam` – Define init params
- ...

--

### Dynamic Servlet/Filter Registration

#### new methods had been added to `ServletContext` to make it possible to register servlet/filter/listener with java code.

```java
public ServletRegistration.Dynamic addServlet(...)

public FilterRegistration.Dynamic addFilter(...)

public void addListener()
```

--

### Demo codebase

check it out on github https://github.com/kevinjom/servleting
