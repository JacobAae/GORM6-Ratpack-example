package demo

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import org.grails.orm.hibernate.HibernateDatastore
import org.postgresql.ds.PGPoolingDataSource
import org.postgresql.ds.PGSimpleDataSource

import javax.sql.DataSource

class PostgresModule extends AbstractModule {

    @Override
    protected void configure() {
    }


    @Provides
    DataSource dataSource() {
        new PGPoolingDataSource(
                user: "demo",
                password: "demo-password",
                serverName: "localhost",
                databaseName: "demodb",
                portNumber: 5432)
    }


}
