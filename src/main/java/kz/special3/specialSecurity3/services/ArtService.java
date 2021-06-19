package kz.special3.specialSecurity3.services;

import kz.special3.specialSecurity3.entities.Art;
import kz.special3.specialSecurity3.entities.SchoolSuplies;

import java.util.ArrayList;

public interface ArtService {
    ArrayList<Art> getAllArt();
    void saveCourse (Art art);
    Art getArtById(Long id);
    Art saveArt(Art art);
}
