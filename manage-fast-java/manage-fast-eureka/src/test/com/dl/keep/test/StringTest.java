package com.dl.keep.test;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    /**
     * 吧所有的监控url 获取出来
     *
     * @param args
     */
    public static void main(String[] args) {
        String text = "o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/health || /health.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint.invoke(javax.servlet.http.HttpServletRequest,java.security.Principal)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/resume || /resume.json],methods=[POST]}\" onto public java.lang.Object org.springframework.cloud.endpoint.GenericPostableMvcEndpoint.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/shutdown || /shutdown.json],methods=[POST],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.ShutdownMvcEndpoint.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/heapdump || /heapdump.json],methods=[GET],produces=[application/octet-stream]}\" onto public void org.springframework.boot.actuate.endpoint.mvc.HeapdumpMvcEndpoint.invoke(boolean,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.io.IOException,javax.servlet.ServletException\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/auditevents || /auditevents.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public org.springframework.http.ResponseEntity<?> org.springframework.boot.actuate.endpoint.mvc.AuditEventsMvcEndpoint.findByPrincipalAndAfterAndType(java.lang.String,java.util.Date,java.lang.String)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/beans || /beans.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/refresh || /refresh.json],methods=[POST]}\" onto public java.lang.Object org.springframework.cloud.endpoint.GenericPostableMvcEndpoint.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/pause || /pause.json],methods=[POST]}\" onto public java.lang.Object org.springframework.cloud.endpoint.GenericPostableMvcEndpoint.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/restart || /restart.json],methods=[POST]}\" onto public java.lang.Object org.springframework.cloud.context.restart.RestartMvcEndpoint.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/autoconfig || /autoconfig.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/features || /features.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/service-registry/instance-status],methods=[POST]}\" onto public org.springframework.http.ResponseEntity<?> org.springframework.cloud.client.serviceregistry.endpoint.ServiceRegistryEndpoint.setStatus(java.lang.String)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/service-registry/instance-status],methods=[GET]}\" onto public org.springframework.http.ResponseEntity org.springframework.cloud.client.serviceregistry.endpoint.ServiceRegistryEndpoint.getStatus()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/archaius || /archaius.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/mappings || /mappings.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/trace || /trace.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/env],methods=[POST]}\" onto public java.lang.Object org.springframework.cloud.context.environment.EnvironmentManagerMvcEndpoint.value(java.util.Map<java.lang.String, java.lang.String>)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/env/reset],methods=[POST]}\" onto public java.util.Map<java.lang.String, java.lang.Object> org.springframework.cloud.context.environment.EnvironmentManagerMvcEndpoint.reset()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/info || /info.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/env/{name:.*}],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EnvironmentMvcEndpoint.value(java.lang.String)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/env || /env.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/configprops || /configprops.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/jolokia/**]}\" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.actuate.endpoint.mvc.JolokiaMvcEndpoint.handle(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws java.lang.Exception\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/dump || /dump.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/loggers/{name:.*}],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.LoggersMvcEndpoint.get(java.lang.String)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/loggers/{name:.*}],methods=[POST],consumes=[application/vnd.spring-boot.actuator.v1+json || application/json],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.LoggersMvcEndpoint.set(java.lang.String,java.util.Map<java.lang.String, java.lang.String>)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/loggers || /loggers.json],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter.invoke()\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/metrics/{name:.*}],methods=[GET],produces=[application/vnd.spring-boot.actuator.v1+json || application/json]}\" onto public java.lang.Object org.springframework.boot.actuate.endpoint.mvc.MetricsMvcEndpoint.value(java.lang.String)\n" +
                "2017-09-28 09:09:10 [main] INFO  o.s.b.a.e.mvc.EndpointHandlerMapping - Mapped \"{[/metrics || /metrics.json]";

        String regex = "\\{\\[([/][a-zA-Z]+)";

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(text);
        Set<String> urlSets = new HashSet<>();
        while (m.find()) {
            urlSets.add(m.group(1));
        }
        urlSets.forEach(item -> {
            //拼接成security的信息
            String url = " .antMatchers(\"" + item + "\").permitAll()";
//            System.out.println(url);
        });
    }
}
