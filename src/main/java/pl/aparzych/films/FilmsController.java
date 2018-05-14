package pl.aparzych.films;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.aparzych.films.domain.Film;
import pl.aparzych.films.domain.FilmRepository;

import java.util.List;


@RestController
@RequestMapping(path = "/films")
public class FilmsController {
  @Autowired
  private FilmRepository filmRepository ;

  @GetMapping(produces = "application/json")
  public List<Film> getAllFilms() {
    return filmRepository.findAll();
  }

  @GetMapping("/{id}")
  public Film getFilm(@PathVariable Long id) {
    return filmRepository.findOne(id);
  }

  @PostMapping(path = "/add", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void addFilm(@RequestBody Film film) {
    filmRepository.save(film);
  }
}
