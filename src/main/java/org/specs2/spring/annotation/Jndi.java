package org.specs2.spring.annotation;

import org.specs2.spring.BlankJndiBuilder;
import org.specs2.spring.JndiBuilder;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Use this annotation on your tests to inject values into the JNDI environment for the
 * tests.
 * <p>You can specify any number of {@link #dataSources()}, {@link #mailSessions()},
 * {@link #beans()}</p>
 * <p>In addition to these fairly standard items, you can specify {@link #builder()},
 * which needs to be an implementation of the {@link org.specs2.spring.JndiBuilder} interface with
 * nullary constructor. The runtime will instantiate the given class and call its
 * {@link org.specs2.spring.JndiBuilder#build(java.util.Map)} method.</p>
 *
 *
 * @author janmmachacek
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Jndi {

	/**
	 * Specify any number of {@link javax.sql.DataSource} JNDI entries.
	 * <p>Typically, you'd write something like
	 * <code>@DataSource(name = "java:comp/env/jdbc/x", url = "jdbc:hsqldb:mem:x", username = "sa", password = "")</code>:
	 * this would register a {@link javax.sql.DataSource} entry in the JNDI environment under name <code>java:comp/env/jdbc/x</code>,
	 * with the given {@code url}, {@code username} and {@code password}.
	 * </p>
	 *
	 * @return the data sources
	 */
	DataSource[] dataSources() default {};

	/**
	 * Specify any number of {@link javax.mail.Session} JNDI entries.
	 *
	 * @return the mail sessions
	 */
	MailSession[] mailSessions() default {};

	/**
	 * Specify any number of any objects as JNDI entries. The objects must have nullary constructors.
	 *
	 * @return the beans
	 */
	Bean[] beans() default {};

	WorkManager[] workManagers() default {};

	/**
	 * Specify any number of {@link javax.transaction.TransactionManager} as JNDI entries.
	 *
	 * @return the transaction managers.
	 */
	TransactionManager[] transactionManager() default {};

	/**
	 * Configure the JMS environment; configure the queues, topics and connection factory.
	 *
	 * @return the JMS environment
	 */
	Jms[] jms() default {};

	/**
	 * If you require some complex environment setup, you can set this value. The type you specify here
	 * must be an implementation of the {@link org.specs2.spring.JndiBuilder} with nullary constructor.
	 *
	 * @return the custom JndiBuilder
	 */
	Class<? extends JndiBuilder> builder() default BlankJndiBuilder.class;

}
