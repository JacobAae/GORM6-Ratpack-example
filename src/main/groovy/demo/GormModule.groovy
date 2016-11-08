package demo

import com.google.inject.AbstractModule
import com.google.inject.Provides
import org.grails.orm.hibernate.HibernateDatastore

class GormModule extends AbstractModule {

    @Override
    protected void configure() {
    }

    @Provides
    HibernateDatastore hibernateDatastore() {
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
