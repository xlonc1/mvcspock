package pl.aparzych.films.domain;
import org.springframework.data.repository.Repository;
import pl.aparzych.films.dto.FilmNotFoundException;

import java.util.List;

public interface FilmRepository extends Repository<Film, String>{
  Film save(Film film);
  Film findOne(Long filmId);
  void delete(Long filmId);
  List<Film> findAll();

  default Film findOneOrThrow(Long filmId) {
    Film film = findOne(filmId);
    if(film == null) {
      throw new FilmNotFoundException(filmId);
    }
    return film;
  }
}
