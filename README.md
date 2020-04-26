# springcloud-oauth2-demo

Security-based architecture demo in Spring Cloud powered by OAuth2
Spring Cloud + 前后端分离架构下，基于Spring Security OAuth2及JWT的无状态认证授权与单点登录demo

 - 网关负责对客户端请求进行过滤，认证中心负责认证，资源服务器（微服务）负责在url及方法级别上授权
 
 - 注册中心采用Nacos，但网关暂时仍采用Spring Cloud Netflix Zuul，未来重构为Spring Cloud Gateway
