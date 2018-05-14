package pl.aparzych.films.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

public class FilmRepositoryImpl implements FilmRepository {
  private ConcurrentHashMap<Long, Film> map = new ConcurrentHashMap<>();

  @Override
  public Film save(Film film) {
    requireNonNull(film);
    map.put(film.getId(), film);
    return film;
  }

  @Override
  public Film findOne(Long filmId) {
    return map.get(filmId);
  }

  @Override
  public void delete(Long filmId) {
    map.remove(filmId);
  }

  @Override
  public List<Film> findAll() {
    return new ArrayList<>(map.values());
  }
}
