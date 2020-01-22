package org.jepria.catalina.realm;

import java.security.Principal;
import java.util.Arrays;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

import org.apache.catalina.realm.GenericPrincipal;
import org.apache.catalina.realm.JAASRealm;

/**
 * JAASRealm, возвращающий сериализуемый {@link JepGenericPrincipal} вместо дефолтного несериализуемого GenericPrincipal.
 * Для использования данного реалма необходимо указать его в {@code META-INF/context.xml} приложения 
 */
public class JepJAASRealm extends JAASRealm {
  @Override
  protected Principal createPrincipal(String username, Subject subject,
      LoginContext loginContext) {
    
    GenericPrincipal genericPrincipal = (GenericPrincipal)
        /*returns GenericPrincipal*/super.createPrincipal(username, subject, loginContext);
    
    return new JepGenericPrincipal(
        genericPrincipal.getName(),
        genericPrincipal.getPassword(),
        Arrays.asList(genericPrincipal.getRoles()),
        genericPrincipal.getUserPrincipal(),
        loginContext);
  }
}
