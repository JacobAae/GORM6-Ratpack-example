package demo

import groovy.json.JsonSlurper
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Specification
import spock.lang.AutoCleanup
import spock.lang.Shared

class RatpackGormSpec extends Specification {

    @AutoCleanup
    @Shared
    GroovyRatpackMainApplicationUnderTest aut = new GroovyRatpackMainApplicationUnderTest()

    @Delegate
    TestHttpClient client = aut.httpClient


    void "Test we get data from database"() {
        when:
        get()

        then:
        response.statusCode == 200

        when:
        def json = new JsonSlurper().parseText(response.body.text)

        then:
        json.size() == 3
        json*.firstName == ['Bart', 'Homer', 'Liza']
    }
}
