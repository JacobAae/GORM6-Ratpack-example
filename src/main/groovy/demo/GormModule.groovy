package demo

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.grails.orm.hibernate.HibernateDatastore

import javax.sql.DataSource

class GormModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    HibernateDatastore initializeHibernateDatastore(DataSource ds) {

        println "Configuring Gorm HibernateDatastore"

        assert ds
        assert ds instanceof com.zaxxer.hikari.HikariDataSource

        // TODO How can the HibernateDatastore be configured to use the DataSource provided by Hikari
        Map configuration = [
                'hibernate.hbm2ddl.auto':'update',
                'dataSource.driverClassName':'org.postgresql.Driver',
                'dataSource.url':'jdbc:postgresql://localhost:5432/demodb',
                'dataSource.username':'demo',
                'dataSource.password':'demo-password',
                'dataSource.dialect':'org.hibernate.dialect.PostgreSQLDialect',
        ]

        HibernateDatastore hibernateDatastore = new HibernateDatastore( configuration ,Person)
        hibernateDatastore
    }
}
