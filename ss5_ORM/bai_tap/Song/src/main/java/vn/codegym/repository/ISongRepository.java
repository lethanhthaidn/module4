package vn.codegym.repository;

import vn.codegym.model.Song;

import java.util.List;

public interface ISongRepository {
    List<Song> findAll();
    void save(Song song);
    void update(Song song);
    Song findById(int id);
    void delete(int id);
}
