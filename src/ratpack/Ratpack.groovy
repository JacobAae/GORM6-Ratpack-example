import com.zaxxer.hikari.HikariConfig
import demo.GormModule
import demo.Person
import demo.PostgresModule
import ratpack.hikari.HikariModule
import ratpack.exec.Blocking
import ratpack.groovy.template.MarkupTemplateModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json


import ratpack.service.Service
import ratpack.service.StartEvent
import org.grails.orm.hibernate.HibernateDatastore

ratpack {
  bindings {
    module MarkupTemplateModule
    module GormModule

    module(HikariModule) { HikariConfig c ->
        c.setDataSource( new PostgresModule().dataSource() )
      }

      bindInstance new Service() {
          void onStart(StartEvent e) throws Exception {
              e.getRegistry().get(HibernateDatastore)
              Blocking.exec {
                  Person.withNewSession {
                      new Person(firstName: "Bart", lastName: "Simpson").save(flush: true)
                      new Person(firstName: "Homer", lastName: "Simpson").save(flush:true)
                  }
              }
          }
      }

  }

  handlers {
    get {
        Blocking.get {
            Person.withNewSession {
                Person.list().collect { "${it.firstName} ${it.lastName}" }
            }
        } then { names ->
            println names
            render(json(names))
        }
    }
  }
}
