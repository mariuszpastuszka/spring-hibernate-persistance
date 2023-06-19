
package com.javapersistence.part05;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

/**
 * Prefixes all SQL table names with "CE_", for CaveatEmptor.
 */
public class CENamingStrategy extends PhysicalNamingStrategyStandardImpl {

    @Override
    public Identifier toPhysicalTableName(Identifier name,
                                          JdbcEnvironment context) {
        return new Identifier("CE_" + name.getText(), name.isQuoted());
    }

}
