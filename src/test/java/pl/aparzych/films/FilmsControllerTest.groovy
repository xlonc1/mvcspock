package pl.aparzych.films

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import pl.aparzych.films.domain.Film
import pl.aparzych.films.domain.FilmRepository
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static groovy.json.JsonOutput.toJson
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [FilmsController])
class FilmsControllerTest extends Specification {

    @Autowired
    protected MockMvc mvc

    @Autowired
    FilmRepository filmRepository

    @Autowired
    ObjectMapper objectMapper

    def "shouldAddFilm"() {
        given:
        Map request = [
                id         : 1,
                name       : 'film1',
                description: 'opis1'
        ]

        when:
        def results = mvc.perform(post('/films/add').contentType('application/json').content(toJson(request)))

        then:
        results.andExpect(status().isCreated())

    }

    def "shouldGetAllFilms"() {
        given:
        ArrayList<Film> response = [new Film(1, 'nameOne', 'descOne'), new Film(2, 'nameTwo', 'descTwo') ]
        filmRepository.findAll() >> response
        when:
        def results = mvc.perform(get('/films').contentType('application/json')).andReturn().response

        then:
        results.status == HttpStatus.OK.value()
        results.getContentType() == "application/json;charset=UTF-8"
        and:
        results.getContentAsString() ==
                "[{\"id\":1,\"name\":\"nameOne\",\"description\":\"descOne\"},{\"id\":2,\"name\":\"nameTwo\",\"description\":\"descTwo\"}]"
    }


    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        FilmRepository filmRepositoryImpl() {
            return detachedMockFactory.Stub(FilmRepository)
        }
    }
}
