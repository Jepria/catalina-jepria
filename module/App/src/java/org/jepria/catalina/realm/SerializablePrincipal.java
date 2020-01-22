package org.jepria.catalina.realm;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Arrays;

/**
 * Упрощённый <b>сериализуемый</b> приницпал для передачи по сети.
 * @see {@link org.apache.catalina.tribes.tipis.ReplicatedMap}
 * @see {@link org.apache.catalina.tribes.Channel}
 */
public class SerializablePrincipal implements Serializable {

  private static final long serialVersionUID = -5147223156499802334L;

  private final String name, password;
  private final String[] roles;
  private final Principal userPrincipal;
  
  
  public SerializablePrincipal(String name, String password, String[] roles,
      Principal userPrincipal) {
    this.name = name;
    this.password = password;
    this.roles = roles;
    this.userPrincipal = userPrincipal;
  }

  /**
   * Used for serialization to deserialize a class whose parent is not serializable
   * @return
   * @throws ObjectStreamException
   * @see {@link JepGenericPrincipal#writeReplace()}
   */
  Object readResolve() throws ObjectStreamException {
    return new JepGenericPrincipal(name, password, Arrays.asList(roles), userPrincipal, null);
  }
}
