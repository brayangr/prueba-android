package cl.prueba.prureba.models;

/**
 * The Genre model implements the attributes that comes from themoviedb.org/3/genre/movie/list
 * end point.
 *
 * @author  Brayan Gonzalez
 * @version 1.0
 * @since   2018-11-10
 */
public class Genre {
    private int id;
    private String name;

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
