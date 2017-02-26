import demo.GormModule
import demo.Person
import ratpack.exec.Blocking

import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json


import ratpack.service.Service
import ratpack.service.StartEvent
import org.grails.orm.hibernate.HibernateDatastore

ratpack {
  bindings {
    module GormModule

      bindInstance new Service() {
          void onStart(StartEvent e) throws Exception {
              e.getRegistry().get(HibernateDatastore)
              Blocking.exec {
                  Person.withNewSession {
                      if( !Person.count() ) {
                          new Person(firstName: "Bart", lastName: "Simpson").save(flush: true)
                          new Person(firstName: "Homer", lastName: "Simpson").save(flush:true)
                          new Person(firstName: "Liza", lastName: "Simpson").save(flush:true)
                      }
                  }
              }
          }
      }
  }

  handlers {
    get {
        Blocking.get {
            Person.withNewSession {
                Person.list()
            }
        } then { names ->
            render(json(names))
        }
    }
  }
}
