package org.jepria.catalina.realm;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.security.auth.login.LoginContext;

import org.apache.catalina.realm.GenericPrincipal;

/**
 * GenericPrincipal с поддержкой сериализации.
 */
public class JepGenericPrincipal extends GenericPrincipal implements Serializable {

  public JepGenericPrincipal(String name, String password, List<String> roles,
      Principal userPrincipal, LoginContext loginContext) {
    super(name, password, roles, userPrincipal, loginContext);
  }

  /**
   * Used for serialization to serialize a class whose parent is not serializable
   * @return
   * @throws ObjectStreamException
   * @see {@link SerializablePrincipal#readResolve()}
   */
  Object writeReplace() throws ObjectStreamException {
    return new SerializablePrincipal(name, password, roles, userPrincipal);
  }
}
