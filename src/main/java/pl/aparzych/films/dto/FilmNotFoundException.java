package pl.aparzych.films.dto;

public class FilmNotFoundException extends RuntimeException {
    public FilmNotFoundException(Long filmId) {
        super("No film of id " + filmId + " found", null, false, false);
    }
}
