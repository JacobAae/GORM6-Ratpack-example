package demo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import grails.gorm.annotation.Entity
import org.grails.datastore.gorm.GormEntity

@Entity
@JsonIgnoreProperties(['dirtyPropertyNames', 'errors', 'dirty', 'attached', 'version'])
class Person implements GormEntity<Person> {

    String firstName
    String lastName

}
